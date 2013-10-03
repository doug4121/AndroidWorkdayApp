package doug.workdaysapp;

import java.util.List;

import validators.ValidationResult;
import validators.WorkdayValidator;

import models.Workday;

import commands.EditWorkdayCommand;
import commands.GetFormValuesCommand;
import commands.SetFormValuesCommand;
import commands.UpdateFormErrorMessagesCommand;
import database.connections.WorkdayDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class EditWorkdayActivity extends Activity {

	private WorkdayDataSource dataSource;
	private WorkdayValidator validator;
	
	private UpdateFormErrorMessagesCommand updateErrorMessagesCommand;
	private SetFormValuesCommand setFormValuesCommand;
	private GetFormValuesCommand getFormValuesCommand;
	private EditWorkdayCommand editWorkdayCommand;
	
	private long id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_workday_activity);
		
		initializeCommands();
		
		validator = new WorkdayValidator();
		
		dataSource = new WorkdayDataSource(this);
		
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
	    initializeCommands();
	}
	
	@Override
	protected void onPause()
	{
		clearReferences();
	    super.onPause();
	}
	
	@Override
	protected void onDestroy()
	{
		clearReferences();
		super.onDestroy();
	}
	
	private void initializeCommands()
	{
		updateErrorMessagesCommand = new UpdateFormErrorMessagesCommand(this);
	    setFormValuesCommand = new SetFormValuesCommand(this);
	    getFormValuesCommand = new GetFormValuesCommand(this);
	    editWorkdayCommand = new EditWorkdayCommand(dataSource);
	}
	
	private void clearReferences()
	{
		if(updateErrorMessagesCommand != null)
		{
			updateErrorMessagesCommand.setActivityToNull();
			updateErrorMessagesCommand = null;
		}
		
		if(setFormValuesCommand != null)
		{
			setFormValuesCommand.setActivityToNull();
			setFormValuesCommand = null;
		}
		
		if(getFormValuesCommand != null)
		{
			getFormValuesCommand.setActivityToNull();
			getFormValuesCommand = null;
		}
		
		if(editWorkdayCommand != null)
		{
			editWorkdayCommand.setDataSourceToNull();
			editWorkdayCommand = null;
		}
	}
	
	public void onClick(View view)
	{
		if(view.getId() == R.id.save_button)
		{
	    	Workday workday = getFormValuesCommand.execute();
	    	workday.setId(id);
	    	
	    	List<ValidationResult> validationErrors = validator.validate(workday);
	    	
	    	if(validationErrors.isEmpty())
	    	{
		    	editWorkdayCommand.execute(workday);
		    	
		    	finish();
	    	}
	    	else
	    	{
	    		updateErrorMessagesCommand.execute(validationErrors);
	    	}
		}
	}
}
