package models;

import java.io.Serializable;
import java.util.Date;

public class Workday implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Date date;
	private String shift;
	private String job;
	private String foremanName;
	private double hours;
	private double overtimeHours;
	private double payscale;
	private double overtimePayscale;
	private String comment;
	
	// getter/setter id
	public long getId(){
		return this.id;
	}
	public void setId(long id){
		this.id = id;
	}
	
	// getter/setter date
	public Date getDate()
	{
		return this.date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	// getter/setter shift
	public String getShift()
	{
		return this.shift;
	}
	public void setShift(String shift)
	{
		this.shift = shift;
	}
	
	// getter/setter job
	public String getJob()
	{
		return this.job;
	}
	public void setJob(String job)
	{
		this.job = job;
	}
	
	// getter/setter foreman
	public String getForemanName()
	{
		return this.foremanName;
	}
	public void setForemanName(String foremanName)
	{
		this.foremanName = foremanName;
	}
	
	// getter/setter hours
	public double getHours()
	{
		return this.hours;
	}
	public void setHours(double hours)
	{
		this.hours = hours;
	}
	
	// getter/setter overtimeHours
	public double getOvertimeHours()
	{
		return this.overtimeHours;
	}
	public void setOvertimeHours(double overtimeHours)
	{
		this.overtimeHours = overtimeHours;
	}
	
	// getter/setter payScale
	public double getPayscale()
	{
		return this.payscale;
	}
	public void setPayscale(double payscale)
	{
		this.payscale = payscale;
	}
	
	// getter/setter overtimePayScale
	public double getOvertimePayscale()
	{
		return this.overtimePayscale;
	}
	public void setOvertimePayscale(double overtimePayscale)
	{
		this.overtimePayscale = overtimePayscale;
	}
	
	// getter/setter comments
	public String getComment()
	{
		return this.comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	
	public String toString()
	{
		return dateToString() + ": " + shift;
	}
	
	@SuppressWarnings("deprecation")
	public String dateToString()
	{
		return (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getYear();
	}
	
	public static Workday generateWorkday(long id, Date date, String shift, String job, String foremanName, double hours, double overtimeHours, double payscale, double overtimePayscale, String comment)
	{
		Workday outputWorkday = new Workday();
		
		outputWorkday.setId(id);
		outputWorkday.setDate(date);
		outputWorkday.setShift(shift);
		outputWorkday.setJob(job);
		outputWorkday.setForemanName(foremanName);
		outputWorkday.setHours(hours);
		outputWorkday.setOvertimeHours(overtimeHours);
		outputWorkday.setPayscale(payscale);
		outputWorkday.setOvertimePayscale(overtimePayscale);
		outputWorkday.setComment(comment);
		
		return outputWorkday;
	}
	
	public static Workday generateWorkday(Date date, String shift, String job, String foremanName, double hours, double overtimeHours, double payscale, double overtimePayscale, String comment)
	{
		return generateWorkday(0, date, shift, job, foremanName, hours, overtimeHours, payscale, overtimePayscale, comment);
	}
}
