package 库存管理系统;

import java.io.Serializable;
import java.util.HashMap;

public class Item implements Serializable
{
	String name;
	String description;
	int mount;
	double price;
	HashMap<String,double[]> turnover= new HashMap<>();
	
	public Item(String name, String description, int mount,double price) 
	{
		this.name = name;
		this.description = description;
		this.mount = mount;
		this.price = price;
	}
	
	public String toString()
	{
		return "商品名称："+this.name+" 数量："+this.mount+" 单价："+this.price+" 描述："+this.description;
	}
}
