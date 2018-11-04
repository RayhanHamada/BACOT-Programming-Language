package core.lexer;

import java.util.ArrayList;

public class LexerDataHandler {

	private static ArrayList<String> stringRepToken = new ArrayList<String>();
	private static ArrayList<Token> tokens = new ArrayList<>();
	
	public static ArrayList<Token> getTokens() 
	{
		return tokens;
	}
	
	public static void addToken(Token t)
	{
		tokens.add(t);
	}
	
	public static void addStringRepToken(Token t)
	{
		stringRepToken.add("(" + t.getType().toString() + ", " + t.getVal() + ")");
	}
	
	public static ArrayList<String> getStringRepToken()
	{
		return stringRepToken;
	}
}
