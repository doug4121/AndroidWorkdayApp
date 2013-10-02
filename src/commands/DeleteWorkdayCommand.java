package commands;

import models.Workday;
import database.connections.WorkdayDataSource;

public class DeleteWorkdayCommand {

private WorkdayDataSource dataSource;
	
	public DeleteWorkdayCommand(WorkdayDataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	public void execute(Workday workday)
	{
		dataSource.open();
		
		dataSource.deleteWorkday(workday);
		
		dataSource.close();	
	}
	
	public void setDataSourceToNull()
	{
		dataSource = null;
	}
}
