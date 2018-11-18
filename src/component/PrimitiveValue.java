package component;

public class PrimitiveValue {

	private String stringRepr;
	private ValueForm form;
	
	public PrimitiveValue(String representation, ValueForm form)
	{
		this.stringRepr = representation;
		this.form = form;
	}
	
	public String getRepresentation()
	{
		return stringRepr;
	}
	
	public ValueForm getForm()
	{
		return form;
	}
	
	
	
}
