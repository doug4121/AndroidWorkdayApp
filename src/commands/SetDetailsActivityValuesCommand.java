package commands;

import doug.workdaysapp.R;
import models.Workday;
import android.app.Activity;
import android.widget.TextView;

public class SetDetailsActivityValuesCommand {
	
	private Activity activity;
	
	public SetDetailsActivityValuesCommand(Activity activity)
	{
		this.activity = activity;
	}
	
	public void setActivityToNull()
	{
		this.activity = null;
	}
	
	public void execute(Workday workday)
	{
		setActivityValues(workday);
	}
	
	private void setActivityValues(Workday workday)
	{
		TextView view;
		view = (TextView) activity.findViewById(R.id.date_value);
		view.setText(workday.dateToString());
		
		view = (TextView) activity.findViewById(R.id.shift_value);
		view.setText(workday.getShift());
		
		view = (TextView) activity.findViewById(R.id.job_value);
		view.setText(workday.getJob());
		
		view = (TextView) activity.findViewById(R.id.foreman_name_value);
		view.setText(workday.getForemanName());
		
		view = (TextView) activity.findViewById(R.id.hours_value);
		view.setText(Double.toString(workday.getHours()));
		
		view = (TextView) activity.findViewById(R.id.overtime_hours_value);
		view.setText(Double.toString(workday.getOvertimeHours()));
		
		view = (TextView) activity.findViewById(R.id.payscale_value);
		view.setText(Double.toString(workday.getPayscale()));
		
		view = (TextView) activity.findViewById(R.id.overtime_payscale_value);
		view.setText(Double.toString(workday.getOvertimePayscale()));

		view = (TextView) activity.findViewById(R.id.comment_value);
		view.setText(workday.getComment());
	}
}
