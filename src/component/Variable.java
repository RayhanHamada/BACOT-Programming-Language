package component;

import datatype.DataType;

public abstract class Variable {

	private String identifier;
	private DataType type;
	private boolean _static, _constant;
	private AccessModifier accmod;
	
	public Variable(String identifier, DataType type, boolean _static, boolean _constant, AccessModifier accmod)
	{
		this.identifier = identifier;
		this.type = type;
		this._static = _static;
		this._constant = _constant;
		this.accmod = accmod;
		
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public DataType getDataType()
	{
		return type;
	}
	
	public boolean isStatic()
	{
		return _static;
	}
	
	public boolean isConstant()
	{
		return _constant;
	}
	
	public AccessModifier getAccMod()
	{
		return accmod;
	}
	
}
