package commands;

import java.util.Date;

import doug.workdaysapp.R;
import models.Workday;
import android.app.Activity;
import android.widget.DatePicker;
import android.widget.EditText;

public class GetFormValuesCommand {
	
	private Activity activity;
	
	public GetFormValuesCommand(Activity activity)
	{
		this.activity = activity;
	}
	
	public void setActivityToNull()
	{
		this.activity = null;
	}
	
	public Workday execute()
	{
		Date date = getDate((DatePicker) activity.findViewById(R.id.date_picker_value));
    	String shift = ((EditText) activity.findViewById(R.id.shift_value)).getText().toString();
    	String job = ((EditText) activity.findViewById(R.id.job_value)).getText().toString();
    	String foremanName = ((EditText) activity.findViewById(R.id.foreman_name_value)).getText().toString();
    	Double hours = parseDouble(((EditText) activity.findViewById(R.id.hours_value)).getText().toString());
    	Double overtimeHours = parseDouble(((EditText) activity.findViewById(R.id.overtime_hours_value)).getText().toString());
    	Double payscale = parseDouble(((EditText) activity.findViewById(R.id.payscale_value)).getText().toString());
    	Double overtimePayscale = parseDouble(((EditText) activity.findViewById(R.id.overtime_payscale_value)).getText().toString());
    	String comment = ((EditText) activity.findViewById(R.id.comment_value)).getText().toString();
    	
    	Workday workday = Workday.generateWorkday(date, shift, job, foremanName, hours, overtimeHours, payscale, overtimePayscale, comment);
    	
    	return workday;
	}
	
	private double parseDouble(String inputText)
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

	@SuppressWarnings("deprecation")
	private Date getDate(DatePicker picker)
	{
		return new Date(picker.getYear(), picker.getMonth(), picker.getDayOfMonth());
	}
	
}
