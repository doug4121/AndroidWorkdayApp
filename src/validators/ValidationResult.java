package validators;

public class ValidationResult {
	private String context;
	private String errorMessage;
	
	public String getContext()
	{
		return context;
	}
	
	public void setContext(String inputContext)
	{
		context = inputContext;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
	
	public void setErrorMessage(String inputErrorMessage)
	{
		errorMessage = inputErrorMessage;
	}
	
	public ValidationResult()
	{
		context = "";
		errorMessage ="";
	}
	
	public ValidationResult(String inputContext, String inputErrorMessage)
	{
		context = inputContext;
		errorMessage = inputErrorMessage;
	}
}
