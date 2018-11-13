package lexer;

public class Token {

	private String val;
	private TokenID type;
	private int linePos, rowPos, valueLength;
	
	public Token(String val, TokenID type, int  linePos, int rowPos)
	{
		this.val = val;
		this.type = type;
		this.linePos = linePos;
		this.rowPos = rowPos;
		this.valueLength = val.length();
	}
	
	public String getVal()
	{
		return val;
	}
	
	public TokenID getType()
	{
		return type;
	}
	
	public int getLinePos()
	{
		return linePos;
	}
	
	public int getRowPos()
	{
		return rowPos;
	}
	
	public int getValueLength()
	{
		return valueLength;
	}
	
}
