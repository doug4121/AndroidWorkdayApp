package validators;
import java.util.*;

import models.Workday;



public class WorkdayValidator {
	
	public List<ValidationResult> validate(Workday workday)
	{
		List<ValidationResult> validationResults = new ArrayList<ValidationResult>();
		
		ValidationResult validationResult;
		
		// id
		validationResult = validateId(workday.getId());
		if(validationResult != null)
		{
			validationResults.add(validationResult);
		}
		
		// date
		validationResult = validateDate(workday.getDate());
		if(validationResult != null)
		{
			validationResults.add(validationResult);
		}
		
		// shift
		validationResult = validateShift(workday.getShift());
		if(validationResult != null)
		{
			validationResults.add(validationResult);
		}
		
		// job
		validationResult = validateJob(workday.getJob());
		if(validationResult != null)
		{
			validationResults.add(validationResult);
		}
		
		// foreman name
		validationResult = validateForemanName(workday.getForemanName());
		if(validationResult != null)
		{
			validationResults.add(validationResult);
		}
		
		// hours
		validationResult = validateHours(workday.getHours());
		if(validationResult != null)
		{
			validationResults.add(validationResult);
		}
		
		// overtime hours
		validationResult = validateOvertimeHours(workday.getOvertimeHours());
		if(validationResult != null)
		{
			validationResults.add(validationResult);
		}
		
		// payscale
		validationResult = validatePayscale(workday.getPayscale());
		if(validationResult != null)
		{
			validationResults.add(validationResult);
		}
		
		// overtime payscale
		validationResult = validateOvertimePayscale(workday.getOvertimePayscale());
		if(validationResult != null)
		{
			validationResults.add(validationResult);
		}
		
		// comment
		validationResult = validateComment(workday.getComment());
		if(validationResult != null)
		{
			validationResults.add(validationResult);
		}
		
		return validationResults;
	}
	
	private ValidationResult validateId(long id)
	{
		ValidationResult validationResult = null;
		
		if (id < 0)
		{
			String context = "id";
			String message = "Id is zero or negative.";
			validationResult = new ValidationResult(context, message);
		}
		
		return validationResult;
	}
	
	private ValidationResult validateDate(Date date)
	{
		ValidationResult validationResult = null;
		
		if (date == null)
		{
			String context = "Date";
			String message = "Date does not exit";
			validationResult = new ValidationResult(context, message);
		}
		
		return validationResult;
	}
	
	private ValidationResult validateShift(String shift)
	{
		ValidationResult validationResult = null;
		String context = "Shift";
		String message;
		
		if(shift == null)
		{
			message = "Shift is null.";
			validationResult = new ValidationResult(context, message);
		}
		else if (shift.length() == 0)
		{
			message = "Shift is an empty string.";
			validationResult = new ValidationResult(context, message);
		}
		else if(shift.length() > 25)
		{
			message = "Shift is greater than 25 characters.";
			validationResult = new ValidationResult(context, message);
		}
		
		return validationResult;
	}
	
	private ValidationResult validateJob(String job)
	{
		ValidationResult validationResult = null;
		String context = "Job";
		String message;
		
		if(job == null)
		{
			message = "Job is null.";
			validationResult = new ValidationResult(context, message);
		}
		else if (job.length() == 0)
		{
			message = "Job is an empty string.";
			validationResult = new ValidationResult(context, message);
		}
		else if(job.length() > 25)
		{
			message = "Job is greater than 25 characters.";
			validationResult = new ValidationResult(context, message);
		}
		
		return validationResult;
	}
	
	private ValidationResult validateForemanName(String foremanName)
	{
		ValidationResult validationResult = null;
		String context = "ForemanName";
		String message;
		
		if(foremanName == null)
		{
			message = "ForemanName is null.";
			validationResult = new ValidationResult(context, message);
		}
		else if (foremanName.length() == 0)
		{
			message = "ForemanName is an empty string.";
			validationResult = new ValidationResult(context, message);
		}
		else if(foremanName.length() > 25)
		{
			message = "ForemanName is greater than 25 characters.";
			validationResult = new ValidationResult(context, message);
		}
		
		return validationResult;
	}
	
	private ValidationResult validateHours(Double hours)
	{
		ValidationResult validationResult = null;
		String context = "Hours";
		String message;
		
		if (hours == null)
		{
			message = "Hours is null";
			validationResult = new ValidationResult(context, message);
		}
		else if(hours < 0)
		{
			message = "Hours is negative";
			validationResult = new ValidationResult(context, message);
		}
		else if(hours > 10)
		{
			message = "Hours is greater than 10";
			validationResult = new ValidationResult(context, message);
		}
		else if (hours == 0)
		{
			message ="Hours is empty";
			validationResult = new ValidationResult(context, message);
		}
		
		return validationResult;
	}
	
	private ValidationResult validateOvertimeHours(Double overtimeHours)
	{
		ValidationResult validationResult = null;
		String context = "OvertimeHours";
		String message;
		
		if (overtimeHours == null)
		{
			message = "OvertimeHours is null";
			validationResult = new ValidationResult(context, message);
		}
		else if(overtimeHours < 0)
		{
			message = "OvertimeHours is negative";
			validationResult = new ValidationResult(context, message);
		}
		else if(overtimeHours > 4)
		{
			message = "OvertimeHours is greater than 4";
			validationResult = new ValidationResult(context, message);
		}
		
		return validationResult;
	}
	
	private ValidationResult validatePayscale(Double payscale)
	{
		ValidationResult validationResult = null;
		String context = "Payscale";
		String message;
		
		if(payscale == null)
		{
			message = "Payscale is null";
			validationResult = new ValidationResult(context, message);
		}
		else if(payscale < 0)
		{
			message = "Payscale is negative";
			validationResult = new ValidationResult(context, message);
		}
		else if(payscale == 0)
		{
			message = "Payscale is zero";
			validationResult = new ValidationResult(context, message);
		}
		
		return validationResult;
	}
	
	private ValidationResult validateOvertimePayscale(Double overtimePayscale)
	{
		ValidationResult validationResult = null;
		String context = "OvertimePayscale";
		String message;
		
		if(overtimePayscale == null)
		{
			message = "OvertimePayscale is null";
			validationResult = new ValidationResult(context, message);
		}
		else if(overtimePayscale < 0)
		{
			message = "OvertimePayscale is negative";
			validationResult = new ValidationResult(context, message);
		}
		else if(overtimePayscale == 0)
		{
			message = "OvertimePayscale is zero";
			validationResult = new ValidationResult(context, message);
		}
		
		return validationResult;
	}
	
	private ValidationResult validateComment(String comment)
	{
		ValidationResult validationResult = null;
		String context = "Comment";
		String message;
		
		if(comment == null)
		{
			message = "Comment is null.";
			validationResult = new ValidationResult(context, message);
		}
		else if (comment.length() == 0)
		{
			message = "Comment is an empty string.";
			validationResult = new ValidationResult(context, message);
		}
		else if(comment.length() > 100)
		{
			message = "Comment is greater than 25 characters.";
			validationResult = new ValidationResult(context, message);
		}
		
		return validationResult;
	}
}
