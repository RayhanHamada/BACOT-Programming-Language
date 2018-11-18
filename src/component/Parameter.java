package component;

import java.util.ArrayList;

public class Parameter {

	private ArrayList<Value> values;
	
	public Parameter(ArrayList<Value> values)
	{
		this.values = values;
	}
	
	public ArrayList<Value> getValues()
	{
		return values;
	}
}
