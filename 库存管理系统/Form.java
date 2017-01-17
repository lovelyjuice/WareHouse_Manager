package 库存管理系统;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Form implements Serializable
{
	ArrayList<Record> newRecord= new ArrayList<>();
	ArrayList<Record> oldRecord= new ArrayList<>();
	
	public void refresh(Warehouse house)
	{
		int i;Record temp;
		Scanner in=new Scanner(System.in);
		for(i=0;i<newRecord.size();i++)
		{
			if((temp=newRecord.get(i)).reachDate.before(Calendar.getInstance()))  //获取一条订单记录并检查是否到达
			{
				System.out.print("有货物"+temp.name+"到达仓库了！请设置出售价格：");
				double price=in.nextDouble();
				house.store(temp.name,temp.mount,temp.desc,price);
				oldRecord.add(newRecord.remove(i));
				i--;
			}
		}
	}
	
	public void history()
	{
		for(int i=0;i<oldRecord.size();i++)
		{
			Record aRecord=oldRecord.get(i);
			System.out.println("商品名称："+aRecord.name+" 供应商："+aRecord.supplier+" 数量："+aRecord.mount+" 创建时间："+aRecord.createDate.get(Calendar.YEAR)+"年"+(aRecord.createDate.get(Calendar.MONTH)+1)+"月"+aRecord.createDate.get(Calendar.DATE)+"日"+aRecord.createDate.get(Calendar.HOUR_OF_DAY)+"时"+" 运输时间："+aRecord.costHour);
		}
	}
	
	public void checkRecord()
	{
		for(int i=0;i<newRecord.size();i++)
		{
			Record aRecord=oldRecord.get(i);
			System.out.println("商品名称："+aRecord.name+" 供应商："+aRecord.supplier+" 数量："+aRecord.mount+" 到达时间："+aRecord.createDate.get(Calendar.YEAR)+"年"+(aRecord.reachDate.get(Calendar.MONTH)+1)+"月"+aRecord.reachDate.get(Calendar.DATE)+"日"+aRecord.reachDate.get(Calendar.HOUR_OF_DAY)+"时");
		}
	}
	
	public void order(String name, int mount, String supplier,String desc, int costHour)
	{
		Scanner in=new Scanner(System.in);
		this.newRecord.add(new Record(name,mount,supplier,desc,costHour));
	}
}
