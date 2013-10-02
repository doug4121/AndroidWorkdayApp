package commands;

import java.util.List;

import validators.ValidationResult;

import doug.workdaysapp.R;

import android.app.Activity;
import android.widget.TextView;

public class UpdateFormErrorMessagesCommand {

	private Activity activity;
	
	public UpdateFormErrorMessagesCommand(Activity activity)
	{
		this.activity = activity;
	}
	
	public void setActivityToNull()
	{
		this.activity = null;
	}
	
	public void execute(List<ValidationResult> errors)
	{
		updateValidationMessages(errors);
	}
	
	private void updateValidationMessages(List<ValidationResult> validationErrors)
	{
		resetValidationMessages();
		
		for (int i = 0; i < validationErrors.size(); i++)
		{
			ValidationResult error = validationErrors.get(i);
			String context = error.getContext();
			TextView view = null;
			
			if(context == "Shift")
			{
				view = (TextView) activity.findViewById(R.id.shift_error_message);
			}
			else if(context == "Job")
			{
				view = (TextView) activity.findViewById(R.id.job_error_message);
			}
			else if(context == "ForemanName")
			{
				view = (TextView) activity.findViewById(R.id.foreman_name_error_message);
			}
			else if(context == "Hours")
			{
				view = (TextView) activity.findViewById(R.id.hours_error_message);
			}
			else if(context == "OvertimeHours")
			{
				view = (TextView) activity.findViewById(R.id.overtime_hours_error_message);
			}
			else if(context == "Payscale")
			{
				view = (TextView) activity.findViewById(R.id.payscale_error_message);
			}
			else if(context == "OvertimePayscale")
			{
				view = (TextView) activity.findViewById(R.id.overtime_payscale_error_message);
			}
			else if(context == "Comment")
			{
				view = (TextView) activity.findViewById(R.id.comment_error_message);
			}
			
			if(view != null)
			{
				view.setText(error.getErrorMessage());
				view.setTextColor(activity.getResources().getColor(R.color.error_color));
			}
		}
	}
	
	private void resetValidationMessages()
	{
		TextView view;
		
		view = (TextView) activity.findViewById(R.id.date_error_message);
		view.setText("");
		
		view = (TextView) activity.findViewById(R.id.shift_error_message);
		view.setText("");
		
		view = (TextView) activity.findViewById(R.id.job_error_message);
		view.setText("");
		
		view = (TextView) activity.findViewById(R.id.foreman_name_error_message);
		view.setText("");
		
		view = (TextView) activity.findViewById(R.id.hours_error_message);
		view.setText("");
		
		view = (TextView) activity.findViewById(R.id.overtime_hours_error_message);
		view.setText("");
		
		view = (TextView) activity.findViewById(R.id.payscale_error_message);
		view.setText("");
		
		view = (TextView) activity.findViewById(R.id.overtime_payscale_error_message);
		view.setText("");
		
		view = (TextView) activity.findViewById(R.id.comment_error_message);
		view.setText("");
	}
}
