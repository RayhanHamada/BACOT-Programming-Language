package core.lexer;

import java.util.ArrayList;

public class Parser {

	
	
	public void variableDeclaration()
	{
		
	}
	
	public void evaluate()
	{
		
		String currentTokenTypeSequence = "";
		ArrayList<Token> currentTokenSequence = new ArrayList<>();
		String polaVarDecl = "^KEYWORD IDENTIFIER(SEPARATOR IDENTIFIER)?(\\sKEYWORD){2,5} SEPARATOR";
		String polaVarInst = "^(KEYWORD\\s){2,4}IDENTIFIER SEPARATOR";
		String PolaForLoop = "^KEYWORD SEPARATOR";
		for (Token t : DataHandler.getTokens())
		{
			currentTokenSequence.add(t);
			
			
			
			
		}
	}
}
