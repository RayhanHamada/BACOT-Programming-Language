package core.lexer;

import java.util.ArrayList;

public class ParserDataHandler {

	private static ArrayList<Statement> statements;
	
	public static ArrayList<Statement> getStatements()
	{
		return statements;
	}
	
	public static void addStatement(Statement s)
	{
		statements.add(s);
	}
	
	public static void evaluateAllStatement()
	{
		for (Statement s : statements) s.evaluate();
	}
}
