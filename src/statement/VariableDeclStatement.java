package statement;

import java.util.ArrayList;

import lexer.Token;
import lexer.TokenID;

public class VariableDeclStatement extends LineStatement {

	private ArrayList<Token> tokens;
	private int line, row;
	public VariableDeclStatement(ArrayList<Token> tokens)
	{
		this.tokens = tokens;
		this.line = tokens.get(0).getLinePos();
		this.row = tokens.get(0).getRowPos();
	}
	
	
	
	public int evaluate() {
		ArrayList<Token> identifierTokens = new ArrayList<Token>();
		boolean errorOcurred = false;
		int identifierCount = 0;
		//check for how many identifier declared (pararrel)
		for (int i = 1; i < tokens.size(); i++)
		{
			if (tokens.get(i).getID().equals(TokenID.IDENTIFIER))
				identifierCount++;
			else
				break;
		}
		//check if there's duplicate identifier(s) in current statement
		for (int i = 0; i < tokens.size(); i++)
		{
			for (int j = 1; j < i; j++)
			{
				if (i != j)
				{
					if (tokens.get(i).getVal().equals(tokens.get(i).getVal()))
					{
						System.out.println("Error Deklarasi Variabel : 2 atau lebih variabel bernama identik pada scope ini. (" + tokens.get(j).getRowPos() + ", " + tokens.get(j).getLinePos() + ")" );
						return 1;
					}
				}
			}
		}
		
		//check if there's duplicate identifier in current scope
		
		
		return 0;
		
		
		
		
		
	}

	public void getMessage(String message) {
		System.out.println("VariableDeclStatement Error: " + message);
	}

}
