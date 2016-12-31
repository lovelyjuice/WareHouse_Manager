package 库存管理系统;

import java.util.Scanner;
import java.io.*;
public class Interactive 
{

	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
		System.out.println("欢迎来到库存管理系统！");
		Form form;
		Warehouse house;
		try{
			ObjectInputStream oishouse=new ObjectInputStream(new FileInputStream("warehouse.dat"));
			ObjectInputStream oisform=new ObjectInputStream(new FileInputStream("form.dat"));
			form=(Form)oisform.readObject();
			house=(Warehouse)oishouse.readObject();
		}catch(FileNotFoundException e)
		{
			form=new Form();
			house=new Warehouse();
		}
		String name;
		Scanner in = new Scanner(System.in);
		int select;
		while(true)
		{
			System.out.println("*****************************************\n请选择操作：\n1.取货  2.下订单  3.刷新库存  4.销售统计  5.历史订单 6.查看仓库 7.保存更改");
			select=in.nextInt();
			switch(select)
			{
			case 1:	
				System.out.println("请输入货物名称，数量");
				int a=house.fetch(name=in.next(),in.nextInt());
				if(a==2)
					System.out.println("取货成功!");
				else if(a==0)
					System.out.println("您要取的货物不存在");
				else
					System.out.println("缺货！您所取的货物只剩下"+house.goods.get(name).mount+"件了");
				break;
			case 2:
				System.out.println("请输入货物名称，数量，供应商和运输时间：");
				form.order(in.next(), in.nextInt(),in.next(),in.nextInt());
				break;
			case 3:
				form.refresh(house);
				break;
			case 4:
				System.out.println("请选择操作： 1.按年销售额排序  2.按月销售额排序");
					switch(in.nextInt())
					{
					case 1:
						System.out.println("请输入商品名称：");
						house.sortByYear(in.next());
						break;
					case 2:
						System.out.println("请输入商品名称和年份：");
						house.sortByMonth(in.next(), in.next());
						break;
					}
				break;
			case 5:
				form.history();
				break;
			case 6:
				house.printHouse();
				break;
			case 7:
				in.close();
				ObjectOutputStream ooshouse=new ObjectOutputStream(new FileOutputStream("warehouse.dat"));
				ObjectOutputStream oosform=new ObjectOutputStream(new FileOutputStream("form.dat"));
				ooshouse.writeObject(house);
				oosform.writeObject(form);
				break;
			}
		}
		
	}
}

