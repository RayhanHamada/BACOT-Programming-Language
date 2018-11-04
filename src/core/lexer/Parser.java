package core.lexer;

import java.util.ArrayList;

public class Parser {

	
	public void parse()
	{
		String currentTokenTypeSeq = "";
		ArrayList<Token> currentTokenSeq = new ArrayList<Token>();
		String patVariableGlobalDecl = "KEYWORD\\sIDENTIFIER\\s(COMMA IDENTIFIER)*(\\sKEYWORD){2,5}\\sSEMICOLON";
		
		for (Token t : LexerDataHandler.getTokens())
		{
			currentTokenTypeSeq += t.getType() + " ";
			
			System.out.println(currentTokenTypeSeq);
			if (currentTokenTypeSeq.trim().matches(patVariableGlobalDecl)) System.out.println("Ketemu");
			
		}
		
	}
}
