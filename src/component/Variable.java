package component;

public class Variable {

	private String identifier;
	private Value value;
	private boolean isConstant, isStatic;
	private AccessModifier accmod;
	
	//for initializing
	public Variable(String identifier, Value value, AccessModifier accmod, boolean isConstant, boolean isStatic)
	{
		this.identifier = identifier;
		this.value = value;
		this.accmod = accmod;
	}
	
	//for declaring
	public Variable(String identifier, AccessModifier accmod, boolean isConstant, boolean isStatic)
	{
		this.identifier = identifier;
		this.accmod = accmod;
		this.isConstant = isConstant;
		this.isStatic = isStatic;
		this.value = null;
	}
	
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	
	
}
