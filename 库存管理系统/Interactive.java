package 库存管理系统;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
import java.text.NumberFormat;
public class Interactive 
{

	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
		System.out.println("欢迎来到库存管理系统！");
		Form form;
		Warehouse house;
		try{
			ObjectInputStream oishouse=new ObjectInputStream(new FileInputStream("D:\\warehouse.dat"));
			ObjectInputStream oisform=new ObjectInputStream(new FileInputStream("D:\\form.dat"));
			form=(Form)oisform.readObject();
			house=(Warehouse)oishouse.readObject();
			oishouse.close();
			oisform.close();
		}catch(FileNotFoundException e)
		{
			form=new Form();
			house=new Warehouse();
		}
		String name;
		int select;
		while(true)
		{	try{
			System.out.println("*****************************************\n请选择操作：\n1.取货  2.下订单  3.刷新库存  4.销售统计\n5.待收货订单  6.历史订单  7.查看仓库  8.保存更改");
			Scanner in = new Scanner(System.in);
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
				System.out.println("请输入货物名称，数量，供应商，描述和运输时间：");
				form.order(in.next(), in.nextInt(),in.next(),in.next(),in.nextInt());
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
				form.checkRecord();
				break;
			case 6:
				form.history();
				break;
			case 7:
				house.printHouse();
				break;
			case 8:
				ObjectOutputStream ooshouse=new ObjectOutputStream(new FileOutputStream("D:\\warehouse.dat"));
				ObjectOutputStream oosform=new ObjectOutputStream(new FileOutputStream("D:\\form.dat"));
				ooshouse.writeObject(house);
				oosform.writeObject(form);
				break;
			}
			}catch(InputMismatchException e)				//预防错误的输入
				{
				System.out.println("输入有误，请重新输入");
				continue;
				}
		}
	}
}

