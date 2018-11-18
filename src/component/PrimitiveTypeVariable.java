package component;

import datatype.DataType;

public class PrimitiveTypeVariable extends Variable{

	private PrimitiveValue value;
	
	public PrimitiveTypeVariable(String identifier, PrimitiveValue value, DataType type, boolean _static, boolean _constant, AccessModifier accmod)
	{
		super(identifier, type, _static, _constant, accmod);
		this.value = value;
	}
	
	public PrimitiveValue getValue()
	{
		return value;
	}
	
}
