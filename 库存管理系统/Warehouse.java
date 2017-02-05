package 库存管理系统;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Warehouse implements Serializable
{
	HashMap<String,Item> goods=new HashMap<String,Item>();

	public void store(String name,int mount,String description,double price)
	{
		if(goods.containsKey(name))
			goods.get(name).mount+=mount;
		else
			goods.put(name, new Item(name,description,mount,price));
	}
	
	public void printHouse()
	{
		for(Item temp:this.goods.values())System.out.println(temp);
	}

	public int fetch(String name,int fetchMount)
	{
		if(!goods.containsKey(name))return 0;							//状态码0表示货物不存在
		Item item=goods.get(name);
		if(item.mount-fetchMount<0)return 1;					//取货量超过库存
		item.mount-=fetchMount;
		String year=Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		int month=Calendar.getInstance().get(Calendar.MONTH);		//这个方法返回的month=1代表二月，2代表3月
		if(!item.turnover.containsKey(year))
		{
			item.turnover.put(year, new double[13]);
		}		
		item.turnover.get(year)[month]+=fetchMount*item.price;
		item.turnover.get(year)[12]+=fetchMount*item.price;
		return 2;
	}
	
	public void lackedgoods()
	{
		for(Item temp:this.goods.values())
		{
			if(temp.mount==0)System.out.println(temp);
		}
	}
	
	public void sortByMonth(String name,String year)
	{
		double[][] a=new double[2][12];
		int i,j;
		double yeartemp,datatemp;
		if(!goods.containsKey(name))
		{
			System.out.println("货物不存在");
			return;
		}
		if(!goods.get(name).turnover.containsKey(year))
		{
			System.out.println("该年没有销售数据");
			return;
		}
		for(j=0;j<12;j++)
		{
			a[0][j]=j;			
			a[1][j]=goods.get(name).turnover.get(year)[j];
		}
		for(i=0;i<12;i++)
		{
			for(j=i+1;j<12;j++)
			{
				if(a[1][i]<a[1][j])
				{
					yeartemp=a[0][i];datatemp=a[1][i];
					a[0][i]=a[0][j];a[1][i]=a[1][j];
					a[0][j]=yeartemp;a[1][j]=datatemp;
				}
			}
		}
		System.out.print(name+"\t");
		for(i=0;i<12;i++)System.out.printf("%.2f元(%d月) ",a[1][i],(int)a[0][i]+1);
	}
	
	public void sortByYear(String name)
	{
		Comparator<Sortclass> comparator=new Comparator<Sortclass>()		//http://blog.csdn.net/andywangcn/article/details/8285504
		{																	//定义比较器
			public int compare(Sortclass a,Sortclass b)						//定义比较方法
			{
				return (int)(a.yearTuruover-b.yearTuruover);
			}
		};
		
		ArrayList<Sortclass> temp=new ArrayList<Sortclass>();
		for(String key:goods.get(name).turnover.keySet())			//将不可排序的HashMap转化为可排序的ArrayList
		{
			Sortclass t=new Sortclass();
			t.year=key;								//年份赋值给t.year
			t.yearTuruover=goods.get(name).turnover.get(key)[12];	//年营业额赋值给t.yearTuruover
			temp.add(t);
		}
		Collections.sort(temp,comparator);
		for(int i=0;i<temp.size();i++)
		{
			System.out.printf("%.2f元(%s年) \n",temp.get(i).yearTuruover,temp.get(i).year);
		}
	}
}

class Sortclass
{
	public String year;
	public double yearTuruover;
}
