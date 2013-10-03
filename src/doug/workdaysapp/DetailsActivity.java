package doug.workdaysapp;

import commands.DeleteWorkdayCommand;
import commands.GetWorkdayCommand;
import commands.SetDetailsActivityValuesCommand;

import models.Workday;
import database.connections.WorkdayDataSource;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class DetailsActivity extends Activity {
	
	private Workday workday;
	private WorkdayDataSource dataSource;
	
	private SetDetailsActivityValuesCommand setValuesCommand;
	private GetWorkdayCommand getWorkdayCommand;
	private DeleteWorkdayCommand deleteWorkdayCommand;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_activity);
		
		dataSource = new WorkdayDataSource(this);
		
		initializeCommands();
		
		Intent intent = getIntent();
		workday = (Workday) intent.getSerializableExtra("databaseEntry");
		
		setValuesCommand.execute(workday);
	}
	
	@Override
	protected void onResume()
	{
	    super.onResume();
	    
	    initializeCommands();
	    
	    workday = getWorkdayCommand.execute(workday.getId());
	    
	    setValuesCommand.execute(workday);
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
		setValuesCommand = new SetDetailsActivityValuesCommand(this);
		getWorkdayCommand = new GetWorkdayCommand(dataSource);
		deleteWorkdayCommand = new DeleteWorkdayCommand(dataSource);
	}
	
	private void clearReferences()
	{
		if(setValuesCommand != null)
		{
			setValuesCommand.setActivityToNull();
			setValuesCommand = null;
		}
		
		if(getWorkdayCommand != null)
		{
			getWorkdayCommand.setDataSourceToNull();
			getWorkdayCommand = null;
		}
		
		if(deleteWorkdayCommand != null)
		{
			deleteWorkdayCommand.setDataSourceToNull();
			deleteWorkdayCommand = null;
		}
	}
	
	public void onClick(View view)
	{
		if(view.getId() == R.id.edit_button)
		{
			clearReferences();
			
			Intent intent = new Intent(this, EditWorkdayActivity.class);
		    intent.putExtra("databaseEntryToUpdate", workday);
		    startActivity(intent);
		}
		else if(view.getId() == R.id.delete_button)
		{
			deleteWorkdayCommand.execute(workday);
			
			finish();
		}
	}
}
