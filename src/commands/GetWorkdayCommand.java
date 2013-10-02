package commands;

import models.Workday;
import database.connections.WorkdayDataSource;

public class GetWorkdayCommand {

	private WorkdayDataSource dataSource;
	
	public GetWorkdayCommand(WorkdayDataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	public Workday execute(long id)
	{
		dataSource.open();
		
		Workday workday = dataSource.getWorkday(id);
		
		dataSource.close();
		
		return workday;
		
	}
	
	public void setDataSourceToNull()
	{
		dataSource = null;
	}
}
