package parser;

import java.util.ArrayList;
import lexer.LexerDataHandler;
import lexer.Token;

public class Parser {

	public void parse()
	{
		String currentTokenTypeSeq = "";
		ArrayList<Token> currentTokenSeq = new ArrayList<Token>();
		// statement context
		String patVariableGlobalDecl = "VAR\\sIDENTIFIER\\s(COMMA\\sIDENTIFIER\\s)*SEBAGAI\\sPRIMITIVE_TYPE(\\sSTATIS)?(\\sCONSTANT)?\\sSEMICOLON\\s?";
		
		
		for (Token t : LexerDataHandler.getTokens())
		{
			currentTokenTypeSeq += t.getType() + " ";
			
			System.out.println(currentTokenTypeSeq);
			if (currentTokenTypeSeq.trim().matches(patVariableGlobalDecl)) System.out.println("Ketemu");
			
		}
		
//		ParserDataHandler.evaluateAllStatement();
		
	}
}
