package component;

public class Value<T> {

	private String val;
	private T valueType;
	private Value<T> evaluatedValue;
	
	public Value(String val, T valueType)
	{
		this.val = val;
		this.valueType = valueType;
	}
	
	public void evaluate()
	{
		
	}
	
}
