package commands;

import java.util.List;

import models.Workday;
import database.connections.WorkdayDataSource;

public class GetAllWorkdaysCommand {
	
	private WorkdayDataSource dataSource;
	
	public GetAllWorkdaysCommand(WorkdayDataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	public List<Workday> execute()
	{
		dataSource.open();
		
		List<Workday> listOfWorkdays = dataSource.getAllWorkdays();
		
		dataSource.close();	
		
		return listOfWorkdays;
	}
	
	public void setDataSourceToNull()
	{
		dataSource = null;
	}
}
