package core.lexer;

public class Token {

	private String val;
	private TokenType type;
	
	public Token(String val, TokenType type)
	{
		this.val = val;
		this.type = type;
	}
	
	public String getVal()
	{
		return val;
	}
	
	public TokenType getType()
	{
		return type;
	}
	
}
