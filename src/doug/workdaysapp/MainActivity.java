package doug.workdaysapp;

import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity{
		
	private WorkdayDataSource dataSource;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		dataSource = new WorkdayDataSource(this);
		dataSource.open();
		
		List<Workday> workdays = dataSource.getAllWorkdays();
		
		ArrayAdapter<Workday> adapter = new ArrayAdapter<Workday>(this, android.R.layout.simple_list_item_1, workdays);
		
		setListAdapter(adapter);
	}
	
	@Override
	protected void onResume()
	{
		dataSource.open();
		
		List<Workday> workdays = dataSource.getAllWorkdays();
		
		ArrayAdapter<Workday> adapter = new ArrayAdapter<Workday>(this, android.R.layout.simple_list_item_1, workdays);
		
		setListAdapter(adapter);
		
	    super.onResume();
	}
	
	@Override
	protected void onPause()
	{
		dataSource.close();
	    super.onPause();
	}
	
	@Override  
	protected void onListItemClick(ListView l, View v, int pos, long id) {  
	    super.onListItemClick(l, v, pos, id);

	    Workday selectedWorkday = (Workday) l.getItemAtPosition(pos);
	    
	    dataSource.close();
	    Intent intent = new Intent(this, DetailsActivity.class);
	    intent.putExtra("databaseEntry", selectedWorkday);
	    startActivity(intent);
	}  
	
	public void onClick(View view)
	{		
		if (view.getId() == R.id.add) {
			Context context = this;
			
			Intent intent = new Intent(context, AddWorkdayActivity.class);
			startActivity(intent);
	    }
	}

}
