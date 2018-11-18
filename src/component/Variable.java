package component;

import datatype.DataType;

public class Variable {

	private String identifier;
	private DataType type;
	private boolean isStatic, isConstant;
	private Value value;
	private AccessModifier accmod;
	private Parameter objectParam;
	
	// initialize variable (for primitive type only)
	public Variable(String identifier, DataType type, Value value, boolean isStatic, boolean isConstant, AccessModifier accmod)
	{
		this.identifier = identifier;
		this.type = type;
		this.value = value;
		this.isStatic = isStatic;
		this.isConstant = isConstant;
		this.accmod = accmod;
	}
	
	// declarating variable (for primitive type only)
	public Variable(String identifier, DataType type, boolean isStatic, boolean isConstant, AccessModifier accmod)
	{
		this.identifier = identifier;
		this.type = type;
		this.isStatic = isStatic;
		this.isConstant = isConstant;
		this.accmod = accmod;
	}
	
	// instantiate variable (for reference type) (with assignment)
	public Variable(String identifier, Parameter objectParam, boolean isStatic, boolean isConstant, AccessModifier accmod)
	{
		this.identifier = identifier;
		this.objectParam = objectParam;
		this.isStatic = isStatic;
		this.isConstant = isConstant;
		this.accmod = accmod;
	}
	
	// declarating variable (for reference type)
	
	
	
}
