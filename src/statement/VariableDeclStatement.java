package statement;

import java.util.ArrayList;

import lexer.Token;

public class VariableDeclStatement extends LineStatement{

	private ArrayList<Token> tokens;
	private int line;
	public VariableDeclStatement(ArrayList<Token> tokens, int line)
	{
		this.tokens = tokens;
		this.line = line;
	}
	
	
	
	public void evaluate() {
		ArrayList<Token> identifierTokens = new ArrayList<Token>();
		boolean shouldBreak = false;
		int evalPosition = 1;
		//check if there's same identifier name when declaring variables (pararrel declaration)
		for (int i = 1; i < tokens.size(); i++)
		{
			if (identifierTokens.isEmpty())
			{
				identifierTokens.add(tokens.get(i));
				continue;
			}
			
			for (Token j : identifierTokens)
			{
				if (tokens.get(i) == j)
				{
					shouldBreak = true;
					break;
				}
			}
			
			if (shouldBreak)
			{
				
				break;
			}
		}
		
		if (shouldBreak) return;
		
		
		
		
		
	}

}
