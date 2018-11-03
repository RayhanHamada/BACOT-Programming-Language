package core.lexer;

public class Variable {

	private String identifier;
	private Value value; // optional, in case of declared variable
	private boolean status; // false for declared, true for initialized
	private boolean isStatic; // false for non-static, true for static
	private boolean isConstant; // false for non-constant, true for constant
	private AccessModifier am; 
	
	public Variable(String identifier, Value value, boolean isStatic, boolean isConstant, AccessModifier am)
	{
		this.identifier = identifier;
		this.value = value;
		this.status = true;
		this.am = am;
		this.isStatic = isStatic;
		this.isConstant = isConstant;
	}
	
	public Variable(String identifier, boolean isStatic, boolean isConstant, AccessModifier am)
	{
		this.identifier = identifier;
		this.status = false;
		this.isStatic = isStatic;
		this.isConstant = isConstant;
	}
	
	public void declToInit()
	{
		this.status = true;
	}
	
	public void setValue(Value value)
	{
		this.value = value;
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
	
	public Value getValue()
	{
		return value;
	}
	
	public boolean getStatus()
	{
		return status;
	}
}
