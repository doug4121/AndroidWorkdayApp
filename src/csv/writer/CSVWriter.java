package csv.writer;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import models.Workday;

import commands.GetAllWorkdaysCommand;

import database.connections.MySQLiteHelper;
import database.connections.WorkdayDataSource;

import android.annotation.SuppressLint;
import android.app.Activity;

public class CSVWriter {

	private String fileName;
	private Activity activity;
	
	private GetAllWorkdaysCommand getAllWorkdaysCommand;
	
	public CSVWriter(String fileName, Activity activity, WorkdayDataSource dataSource)
	{
		this.fileName = fileName;
		this.activity = activity;
		
		getAllWorkdaysCommand = new GetAllWorkdaysCommand(dataSource);
	}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("WorldReadableFiles")
	public void write()
	{
		try{
			FileOutputStream outStream = activity.openFileOutput(fileName + ".csv", Activity.MODE_WORLD_READABLE);
			
			OutputStreamWriter osw = new OutputStreamWriter(outStream);
			
			// column headers
			String[] tableColumns = MySQLiteHelper.TABLE_COLUMNS;
			osw.append(workdayColumnHeadersToString(tableColumns));
			
			// data
			List<Workday> allWorkdays = getAllWorkdaysCommand.execute();
			for(int i = 0; i < allWorkdays.size(); i++)
			{
				osw.append(workdayToString(allWorkdays.get(i)));
			}
			
			osw.flush();
			osw.close();
		}
		catch(Exception e)
		{
			// need to do when creation error
		}
	}
	
	private String workdayToString(Workday workday)
	{
		String output = "";
		output += workday.getId() + ",";
		output += workday.getDate() + ",";
		output += workday.getShift() + ",";
		output += workday.getJob() + ",";
		output += workday.getForemanName() + ",";
		output += workday.getHours() + ",";
		output += workday.getOvertimeHours() + ",";
		output += workday.getPayscale() + ",";
		output += workday.getOvertimePayscale() + ",";
		output += workday.getComment() + "\n";
				
		return output;
	}
	
	private String workdayColumnHeadersToString(String[] tableColumns)
	{
		String output = "";
		
		for(int i = 0; i < tableColumns.length; i++)
		{
			if (i < tableColumns.length - 1)
			{
				output += tableColumns[i] + ",";
			}
			else
			{
				output += tableColumns[i] + "\n";
			}
		}
		
		return output;
	}
}
