package doug.workdaysapp;

import java.util.Date;
import java.util.List;

import validators.ValidationResult;
import validators.WorkdayValidator;

import models.Workday;

import commands.SetFormValuesCommand;
import commands.UpdateFormErrorMessagesCommand;
import database.connections.WorkdayDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class EditWorkdayActivity extends Activity {

	private WorkdayDataSource dataSource;
	private WorkdayValidator validator;
	
	private UpdateFormErrorMessagesCommand updateErrorMessagesCommand;
	private SetFormValuesCommand setFormValuesCommand;
	private long id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_workday_activity);
		
		initializeCommands();
		
		validator = new WorkdayValidator();
		
		dataSource = new WorkdayDataSource(this);
		dataSource.open();
		
		Intent intent = getIntent();
		Workday workday = (Workday) intent.getSerializableExtra("databaseEntryToUpdate");
		id = workday.getId();
		
		setFormValuesCommand.Execute(workday);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_workday, menu);
		return true;
	}
	
	@Override
	protected void onResume()
	{
	    super.onResume();
	    dataSource.open();
	    initializeCommands();
	}
	
	@Override
	protected void onPause()
	{
		dataSource.close();
		clearReferences();
	    super.onPause();
	}
	
	private void initializeCommands()
	{
		updateErrorMessagesCommand = new UpdateFormErrorMessagesCommand(this);
	    setFormValuesCommand = new SetFormValuesCommand(this);
	}
	
	private void clearReferences()
	{
		updateErrorMessagesCommand.setActivityToNull();
		updateErrorMessagesCommand = null;
		
		setFormValuesCommand.setActivityToNull();
		setFormValuesCommand = null;
	}
	
	public void onClick(View view)
	{
		if(view.getId() == R.id.save_button)
		{
			Date date = getDate((DatePicker) findViewById(R.id.date_picker_value));
	    	String shift = ((EditText) findViewById(R.id.shift_value)).getText().toString();
	    	String job = ((EditText) findViewById(R.id.job_value)).getText().toString();
	    	String foremanName = ((EditText) findViewById(R.id.foreman_name_value)).getText().toString();
	    	Double hours = parseDouble(((EditText) findViewById(R.id.hours_value)).getText().toString());
	    	Double overtimeHours = parseDouble(((EditText) findViewById(R.id.overtime_hours_value)).getText().toString());
	    	Double payscale = parseDouble(((EditText) findViewById(R.id.payscale_value)).getText().toString());
	    	Double overtimePayscale = parseDouble(((EditText) findViewById(R.id.overtime_payscale_value)).getText().toString());
	    	String comment = ((EditText) findViewById(R.id.comment_value)).getText().toString();
	    	
	    	Workday workday = Workday.generateWorkday(id, date, shift, job, foremanName, hours, overtimeHours, payscale, overtimePayscale, comment);
	    	
	    	List<ValidationResult> validationErrors = validator.validate(workday);
	    	
	    	if(validationErrors.isEmpty())
	    	{
		    	dataSource.editWorkday(id, date, shift, job, foremanName, hours, overtimeHours, payscale, overtimePayscale, comment);
		    	dataSource.close();
		    	
		    	clearReferences();
		    	finish();
	    	}
	    	else
	    	{
	    		updateErrorMessagesCommand.execute(validationErrors);
	    	}
		}
	}
	
	@SuppressWarnings("deprecation")
	private Date getDate(DatePicker picker)
	{
		return new Date(picker.getYear(), picker.getMonth(), picker.getDayOfMonth());
	}
	
	private Double parseDouble(String inputText)
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
}
