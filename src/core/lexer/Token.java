package core.lexer;

public class Token {

	private String val;
	private TokenID type;
	
	public Token(String val, TokenID type)
	{
		this.val = val;
		this.type = type;
	}
	
	public String getVal()
	{
		return val;
	}
	
	public TokenID getType()
	{
		return type;
	}
	
}
