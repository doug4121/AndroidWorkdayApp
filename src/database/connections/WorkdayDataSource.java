package database.connections;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Workday;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Doug
 *
 */
public class WorkdayDataSource {
	
	// the database
	private SQLiteDatabase database;
	// the helper
	private MySQLiteHelper dbHelper;
	
	// constructor initializes the helper
	public WorkdayDataSource(Context context)
	{
		dbHelper = new MySQLiteHelper(context);
	}
	
	// initializes the database connection and opens it for writing
	public void open() throws SQLException
	{
		database = dbHelper.getWritableDatabase();
	}
	
	// closes the database connection
	public void close()
	{
		dbHelper.close();
	}
	
	// creates a workday and inserts it into database
	public Workday createWorkday(
			Workday workday
			)
	{
		ContentValues values = generateContentValues(
				workday.getDate(), 
				workday.getShift(), 
				workday.getJob(), 
				workday.getForemanName(), 
				workday.getHours(), 
				workday.getOvertimeHours(), 
				workday.getPayscale(), 
				workday.getOvertimePayscale(), 
				workday.getComment());
		
		// inserts data into database
		long insertId = database.insert(MySQLiteHelper.TABLE_NAME, null, values);
		
		// queries the newly inserted
		Cursor cursor = database.query(
				MySQLiteHelper.TABLE_NAME, 
				MySQLiteHelper.TABLE_COLUMNS, 
				MySQLiteHelper.TABLE_COLUMNS[0] + " = " + insertId, 
				null, 
				null, 
				null, 
				null);
		
		cursor.moveToFirst();
	    Workday newWorkday = cursorToWorkday(cursor);
	    cursor.close();
	    
	    // returns the query
	    return newWorkday;
	}
	
	// creates the contentvalues object for insertion and return
	private ContentValues generateContentValues(
			Date date, 
			String shift, 
			String job, 
			String foremanName, 
			double hours, 
			double overtimeHours, 
			double payscale,
			double overtimePayscale,
			String comment)
	{
		ContentValues values = new ContentValues();
		
		for (int i = 1; i < MySQLiteHelper.TABLE_COLUMNS.length; i++)
		{
			String columnName = MySQLiteHelper.TABLE_COLUMNS[i];
			
			switch(i)
			{
			case 1:
				values.put(columnName, dateToLong(date));
				break;
			case 2:
				values.put(columnName, shift);
				break;
			case 3:
				values.put(columnName, job);
				break;
			case 4:
				values.put(columnName, foremanName);
				break;
			case 5:
				values.put(columnName, hours);
				break;
			case 6:
				values.put(columnName, overtimeHours);
				break;
			case 7:
				values.put(columnName, payscale);
				break;
			case 8:
				values.put(columnName, overtimePayscale);
				break;
			case 9:
				values.put(columnName, comment);
				break;
			}
		}
		
		return values;
	}
	
	// converts the current cursor location to a workday
	private Workday cursorToWorkday(Cursor cursor)
	{
		Workday workday = new Workday();
		
		workday.setId(cursor.getLong(0));
		workday.setDate(longToDate(cursor.getLong(1)));
		workday.setShift(cursor.getString(2));
		workday.setJob(cursor.getString(3));
		workday.setForemanName(cursor.getString(4));
		workday.setHours(cursor.getDouble(5));
		workday.setOvertimeHours(cursor.getDouble(6));
		workday.setPayscale(cursor.getDouble(7));
		workday.setOvertimePayscale(cursor.getDouble(8));
		workday.setComment(cursor.getString(9));

		return workday;
	}
	
	// converts a date into a long
	private long dateToLong(Date date)
	{
		return date.getTime() + 3600 * 1000;
	}
	
	// converts a long into a date
	private Date longToDate(long dateAsLong)
	{
		return new Date(dateAsLong);
	}
	
	// deletes a workday entry
	public void deleteWorkday(Workday workday)
	{
		long id = workday.getId();
		System.out.println("Workday deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.TABLE_COLUMNS[0] + " = " + id, null);
	}
	
	// TODO need a read command
	public Workday getWorkday(long readId)
	{
		Cursor cursor = database.query(
				MySQLiteHelper.TABLE_NAME, 
				MySQLiteHelper.TABLE_COLUMNS, 
				MySQLiteHelper.TABLE_COLUMNS[0] + " = " + readId, 
				null, 
				null, 
				null, 
				null);
		
		cursor.moveToFirst();
	    Workday readWorkday = cursorToWorkday(cursor);
	    cursor.close();
	    
	    return readWorkday;
	}
	
	// TODO need an edit command
	public Workday editWorkday(
			long id,
			Date date, 
			String shift, 
			String job, 
			String foremanName, 
			double hours, 
			double overtimeHours, 
			double payscale,
			double overtimePayscale,
			String comment)
	{
		ContentValues values = generateContentValues(
				date, 
				shift, 
				job, 
				foremanName, 
				hours, 
				overtimeHours, 
				payscale, 
				overtimePayscale, 
				comment);
		
		String sqlId = "_id=" + id;
		
		database.update(MySQLiteHelper.TABLE_NAME, values, sqlId, null);
		
		// queries the newly inserted
		Cursor cursor = database.query(
				MySQLiteHelper.TABLE_NAME, 
				MySQLiteHelper.TABLE_COLUMNS, 
				MySQLiteHelper.TABLE_COLUMNS[0] + " = " + id, 
				null, 
				null, 
				null, 
				null);
		
		cursor.moveToFirst();
	    Workday updatedWorkday = cursorToWorkday(cursor);
	    cursor.close();
	    
	    // returns the query
	    return updatedWorkday;
	}
	
	// returns all workdays
	public List<Workday> getAllWorkdays()
	{
		List<Workday> workdays = new ArrayList<Workday>();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.TABLE_COLUMNS, null, null, null, null, null);
		
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast())
		{
			workdays.add(cursorToWorkday(cursor));
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return workdays;
	}
}
