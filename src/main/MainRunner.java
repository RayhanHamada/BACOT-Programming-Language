package main;

import lexer.Lexer;
import lexer.LexerDataHandler;
import parser.Parser;

public class MainRunner {
	
	public static void main(String...args) throws Exception
	{
		
		Lexer lg = new Lexer("test.txt");
		lg.startLex();
		
		for (String s : LexerDataHandler.getStringRepToken()) System.out.println(s);
		
		Parser p = new Parser();
		p.parse();
		
		
	}
}
