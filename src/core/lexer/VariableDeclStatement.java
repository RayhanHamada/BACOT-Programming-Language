package core.lexer;

import java.util.ArrayList;

public class VariableDeclStatement extends LineStatement{

	private ArrayList<Token> tokens;
	public VariableDeclStatement(ArrayList<Token> tokens)
	{
		this.tokens = tokens;
	}
	
	
	
	public void evaluate() {
		// evaluasi deklarasi variables
		
	}

}
