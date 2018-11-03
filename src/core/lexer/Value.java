package core.lexer;

public class Value {

	private String stringValue;
	private ValueForm form;
	
	public Value(String stringValue, ValueForm form)
	{
		this.stringValue = stringValue;
		this.form = form;
	}
	
	public String getValue()
	{
		return stringValue;
	}
	
	public ValueForm getValueForm()
	{
		return form;
	}
	
	public void setStringValue(String s)
	{
		this.stringValue = s;
	}
	
	public void setValueForm(ValueForm f)
	{
		this.form = f;
	}
}
