package commands;

import java.util.Date;

import models.Workday;

import doug.workdaysapp.R;

import android.app.Activity;
import android.widget.DatePicker;
import android.widget.EditText;

public class SetFormValuesCommand {

	private Activity activity;
	
	public SetFormValuesCommand(Activity activity)
	{
		this.activity = activity;
	}
	
	public void setActivityToNull()
	{
		this.activity = null;
	}
	
	public void Execute(Workday workday)
	{
		setActivityValues(workday);
	}
	
	@SuppressWarnings("deprecation")
	private void setActivityValues(Workday workday)
	{
		EditText view;
		
		DatePicker picker = (DatePicker) activity.findViewById(R.id.date_picker_value);
		Date theDate = workday.getDate();
		picker.updateDate(theDate.getYear(), theDate.getMonth(), theDate.getDate());
		
		view = (EditText) activity.findViewById(R.id.shift_value);
		view.setText(workday.getShift());
		
		view = (EditText) activity.findViewById(R.id.job_value);
		view.setText(workday.getJob());
		
		view = (EditText) activity.findViewById(R.id.foreman_name_value);
		view.setText(workday.getForemanName());
		
		view = (EditText) activity.findViewById(R.id.hours_value);
		view.setText(Double.toString(workday.getHours()));
		
		view = (EditText) activity.findViewById(R.id.overtime_hours_value);
		view.setText(Double.toString(workday.getOvertimeHours()));
		
		view = (EditText) activity.findViewById(R.id.payscale_value);
		view.setText(Double.toString(workday.getPayscale()));
		
		view = (EditText) activity.findViewById(R.id.overtime_payscale_value);
		view.setText(Double.toString(workday.getOvertimePayscale()));

		view = (EditText) activity.findViewById(R.id.comment_value);
		view.setText(workday.getComment());
	}
}
