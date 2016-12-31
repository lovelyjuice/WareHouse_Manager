package 库存管理系统;
import java.io.Serializable;

import java.util.Calendar;
public class Record implements Serializable
{
	String name;
	int mount;
	String supplier;
	Calendar createDate;				//http://www.w3cschool.cn/java/java-date-time.html
	int costHour;
	Calendar reachDate;
	
	public Record(String name, int mount, String supplier, int costHour) 
	{
		this.name = name;
		this.mount = mount;
		this.supplier = supplier;
		this.costHour=costHour;
		this.createDate = Calendar.getInstance();
		this.reachDate =(Calendar)this.createDate.clone();
		this.reachDate.add(Calendar.HOUR_OF_DAY, costHour);
		System.out.println("下单成功！货物到达时间为："+this.reachDate.get(Calendar.YEAR)+"年"+(this.reachDate.get(Calendar.MONTH)+1)+"月"+this.reachDate.get(Calendar.DATE)+"日"+this.reachDate.get(Calendar.HOUR_OF_DAY)+"时");
	}	
}
