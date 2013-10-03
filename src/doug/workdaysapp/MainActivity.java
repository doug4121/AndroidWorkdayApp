package doug.workdaysapp;

import java.util.List;

import commands.GetAllWorkdaysCommand;
//import csv.writer.CSVWriter;

import models.Workday;

import database.connections.WorkdayDataSource;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity{
		
	private WorkdayDataSource dataSource;
	
	private GetAllWorkdaysCommand getAllWorkdaysCommand;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		dataSource = new WorkdayDataSource(this);
		initializeCommands();
		
		List<Workday> workdays = getAllWorkdaysCommand.execute();
		
		ArrayAdapter<Workday> adapter = new ArrayAdapter<Workday>(this, android.R.layout.simple_list_item_1, workdays);
		
		setListAdapter(adapter);
		
		// test file creation
		/*CSVWriter fileWriter = new CSVWriter("test", this, dataSource);
		fileWriter.write();*/
	}
	
	@Override
	protected void onResume()
	{
	    super.onResume();
	    initializeCommands();
		
		List<Workday> workdays = getAllWorkdaysCommand.execute();
		
		ArrayAdapter<Workday> adapter = new ArrayAdapter<Workday>(this, android.R.layout.simple_list_item_1, workdays);
		setListAdapter(adapter);
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
		getAllWorkdaysCommand = new GetAllWorkdaysCommand(dataSource);
	}
	
	private void clearReferences()
	{
		if(getAllWorkdaysCommand != null)
		{
			getAllWorkdaysCommand.setDataSourceToNull();
			getAllWorkdaysCommand = null;
		}
	}
	
	@Override  
	protected void onListItemClick(ListView l, View v, int pos, long id) {  
	    super.onListItemClick(l, v, pos, id);

	    Workday selectedWorkday = (Workday) l.getItemAtPosition(pos);
	    
	    Intent intent = new Intent(this, DetailsActivity.class);
	    intent.putExtra("databaseEntry", selectedWorkday);
	    startActivity(intent);
	}  
	
	public void onClick(View view)
	{		
		if (view.getId() == R.id.add)
		{
			Intent intent = new Intent(this, AddWorkdayActivity.class);
			startActivity(intent);
	    }
		else if(view.getId() == R.id.settings)
		{
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		}
	}

}
