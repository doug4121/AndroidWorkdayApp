package commands;

import models.Workday;
import database.connections.WorkdayDataSource;

public class CreateWorkdayCommand {

	private WorkdayDataSource dataSource;
	
	public CreateWorkdayCommand(WorkdayDataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	public void execute(Workday workday)
	{
		dataSource.open();
		
		dataSource.createWorkday(workday);
		
		dataSource.close();	
	}
	
	public void setDataSourceToNull()
	{
		dataSource = null;
	}
}
