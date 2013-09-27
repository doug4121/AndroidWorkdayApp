package doug.workdaysapp;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddWorkdayActivity extends Activity {

	private WorkdayDataSource dataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_workday_activity);
		
		dataSource = new WorkdayDataSource(this);
		dataSource.open();
	}

	@Override
	protected void onResume()
	{
		dataSource.open();
	    super.onResume();
	}
	
	@Override
	protected void onPause()
	{
		dataSource.close();
	    super.onPause();
	}
	
	public void onClick(View view)
	{  
	    // what happens on save
	    if (view.getId() == R.id.save_button) {
	    	
	    	Date date = getDate((DatePicker) findViewById(R.id.date_picker_value));
	    	String shift = ((EditText) findViewById(R.id.shift_value)).getText().toString();
	    	String job = ((EditText) findViewById(R.id.job_value)).getText().toString();
	    	String foremanName = ((EditText) findViewById(R.id.foreman_name_value)).getText().toString();
	    	Double hours = parseDouble(((EditText) findViewById(R.id.hours_value)).getText().toString());
	    	Double overtimeHours = parseDouble(((EditText) findViewById(R.id.overtime_hours_value)).getText().toString());
	    	Double payscale = parseDouble(((EditText) findViewById(R.id.payscale_value)).getText().toString());
	    	Double overtimePayscale = parseDouble(((EditText) findViewById(R.id.overtime_payscale_value)).getText().toString());
	    	String comment = ((EditText) findViewById(R.id.comment_value)).getText().toString();
	    	
	    	dataSource.createWorkday(date, shift, job, foremanName, hours, overtimeHours, payscale, overtimePayscale, comment);
	    	dataSource.close();
	    	
	    	finish();
	    }
	}
	
	private double parseDouble(String inputText)
	{
		Double outputNumber;
		try
		{
			outputNumber = Double.parseDouble(inputText);
		}
		catch(NumberFormatException e)
		{
			outputNumber = 0.0;
		}
		
		return outputNumber;
	}

	@SuppressWarnings("deprecation")
	private Date getDate(DatePicker picker)
	{
		return new Date(picker.getYear(), picker.getMonth(), picker.getDayOfMonth());
	}
}
