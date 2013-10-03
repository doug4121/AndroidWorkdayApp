package doug.workdaysapp;

import csv.writer.CSVWriter;
import database.connections.WorkdayDataSource;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class SettingsActivity extends Activity {

	private WorkdayDataSource dataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_activity);
		
		dataSource = new WorkdayDataSource(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
	}
	
	public void onClick(View view)
	{
		if(view.getId() == R.id.backup_button)
		{
			// generate backup
			CSVWriter writer = new CSVWriter("OutputDatabase", this, dataSource);
			writer.write();
		}
	}

}
