package doug.workdaysapp;

import java.util.List;

import validators.ValidationResult;
import validators.WorkdayValidator;

import models.Workday;

import commands.CreateWorkdayCommand;
import commands.GetFormValuesCommand;
import commands.UpdateFormErrorMessagesCommand;
import database.connections.WorkdayDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class AddWorkdayActivity extends Activity {

	private WorkdayDataSource dataSource;
	private WorkdayValidator validator;
	
	private UpdateFormErrorMessagesCommand updateErrorMessagesCommand; 
	private GetFormValuesCommand getFormValuesCommand;
	private CreateWorkdayCommand createWorkdayCommand;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_workday_activity);
		
		initializeCommands();
		
		validator = new WorkdayValidator();
		
		dataSource = new WorkdayDataSource(this);
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
		getFormValuesCommand = new GetFormValuesCommand(this);
		createWorkdayCommand = new CreateWorkdayCommand(dataSource);
	}
	
	private void clearReferences()
	{
		if(updateErrorMessagesCommand != null)
		{
			updateErrorMessagesCommand.setActivityToNull();
			updateErrorMessagesCommand = null;
		}
		
		if( getFormValuesCommand != null)
		{
			getFormValuesCommand.setActivityToNull();
			getFormValuesCommand = null;
		}
		
		if(createWorkdayCommand != null)
		{
			createWorkdayCommand.setDataSourceToNull();
			createWorkdayCommand = null;
		}
	}
	
	public void onClick(View view)
	{  
	    // what happens on save
	    if (view.getId() == R.id.save_button) {
	    	
	    	Workday workday = getFormValuesCommand.execute();
	    	
	    	List<ValidationResult> validationErrors = validator.validate(workday);
	    	
	    	if(validationErrors.isEmpty())
	    	{
	    		createWorkdayCommand.execute(workday);
	    	
	    		finish();
	    	}
	    	else
	    	{
	    		updateErrorMessagesCommand.execute(validationErrors);
	    	}
	    }
	}
}
