package doug.workdaysapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	
	private Workday workday;
	private WorkdayDataSource dataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_activity);
		
		dataSource = new WorkdayDataSource(this);
		dataSource.open();
		
		Intent intent = getIntent();
		workday = (Workday) intent.getSerializableExtra("databaseEntry");
		setActivityValues(workday);
	}
	
	@Override
	protected void onResume()
	{
		dataSource.open();
	    super.onResume();
	    
	    workday = dataSource.getWorkday(workday.getId());
	    setActivityValues(workday);
	}
	
	@Override
	protected void onPause()
	{
		dataSource.close();
	    super.onPause();
	}
	
	private void setActivityValues(Workday workday)
	{
		TextView view;
		view = (TextView) findViewById(R.id.date_value);
		view.setText(workday.dateToString());
		
		view = (TextView) findViewById(R.id.shift_value);
		view.setText(workday.getShift());
		
		view = (TextView) findViewById(R.id.job_value);
		view.setText(workday.getJob());
		
		view = (TextView) findViewById(R.id.foreman_name_value);
		view.setText(workday.getForemanName());
		
		view = (TextView) findViewById(R.id.payscale_value);
		view.setText(Double.toString(workday.getPayScale()));
		
		view = (TextView) findViewById(R.id.overtime_payscale_value);
		view.setText(Double.toString(workday.getOvertimePayScale()));
		
		view = (TextView) findViewById(R.id.hours_value);
		view.setText(Double.toString(workday.getHours()));
		
		view = (TextView) findViewById(R.id.overtime_hours_value);
		view.setText(Double.toString(workday.getOvertimeHours()));

		view = (TextView) findViewById(R.id.comment_value);
		view.setText(workday.getComment());
	}
	
	public void onClick(View view)
	{
		if(view.getId() == R.id.edit_button)
		{
			dataSource.close();
			
			Intent intent = new Intent(this, EditWorkdayActivity.class);
		    intent.putExtra("databaseEntryToUpdate", workday);
		    startActivity(intent);
		}
		else if(view.getId() == R.id.delete_button)
		{
			dataSource.deleteWorkday(workday);
			dataSource.close();
			finish();
		}
	}
}
