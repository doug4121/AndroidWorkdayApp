package csv.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import models.Workday;

import commands.GetAllWorkdaysCommand;

import database.connections.MySQLiteHelper;
import database.connections.WorkdayDataSource;

import android.annotation.SuppressLint;
import android.os.Environment;

public class CSVWriter {

	private String fileName;
	
	private GetAllWorkdaysCommand getAllWorkdaysCommand;
	
	public CSVWriter(String fileName, WorkdayDataSource dataSource)
	{
		this.fileName = fileName;
		
		getAllWorkdaysCommand = new GetAllWorkdaysCommand(dataSource);
	}
	
	@SuppressLint("WorldReadableFiles")
	public void write()
	{
		try{
			File file = new File(Environment.getExternalStorageDirectory(), fileName + ".csv");
			FileOutputStream outStream = new FileOutputStream(file);
			
			// column headers
			String data = "";
			String[] tableColumns = MySQLiteHelper.TABLE_COLUMNS;
			data += workdayColumnHeadersToString(tableColumns);
			
			// data
			List<Workday> allWorkdays = getAllWorkdaysCommand.execute();
			for(int i = 0; i < allWorkdays.size(); i++)
			{
				data += workdayToString(allWorkdays.get(i));
			}
			
			outStream.write(data.getBytes());
			outStream.flush();
			outStream.close();
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
