package commands;

import models.Workday;
import database.connections.WorkdayDataSource;

public class EditWorkdayCommand {
	private WorkdayDataSource dataSource;
	
	public EditWorkdayCommand(WorkdayDataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	public void execute(Workday workday)
	{
		dataSource.open();
		
		dataSource.editWorkday(workday.getId(), workday.getDate(), workday.getShift(), workday.getJob(), workday.getForemanName(), workday.getHours(), workday.getOvertimeHours(), workday.getPayscale(), workday.getOvertimePayscale(), workday.getComment());
		
		dataSource.close();	
	}
	
	public void setDataSourceToNull()
	{
		dataSource = null;
	}
}
