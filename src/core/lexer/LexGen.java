package core.lexer;

import java.util.ArrayList;

import core.util.CustomRegex;
import core.util.FileUtil;

public class LexGen {
	
	private CustomRegex cr;
	private FileUtil fu;
	
	private String lexeme;
	
	
	//pola token
	private String patIdentifier = "^[A-Za-z_][\\w]*";
	private String patAngka = "^\\d+\\.\\d*f?|^\\d*\\.\\d+f?|^\\d+";
	private String patString = "^((?<![\\\\])[\"])((?:.(?!(?<![\\\\])\\1))*.?)\\1";
	private String patChar = "^'.+'";
	private String patSeparator = "^[\\(\\)\\{\\}\\[\\];:,]";
	private String patLogika = "^(true|false)";
	private String patOperator = "^(\\+|-|\\*|\\/|=|==|>>|<<|>|<|>=|<=|&|\\||%|!|\\^|\\(|\\))";
	private String patNull = "^nil";
	private String patComment = "^//.*//";
	
	private String patKeyword = "^(var|sebagai|Integer|Statis|Konstan|Privat|Publik)";
	
	public LexGen(String path)
	{
		cr = new CustomRegex();
		fu = new FileUtil();
		lexeme = fu.fileToText(path);
	}
	
	public void startLex()
	{
		String currentToken = null;
		lexeme = lexeme.replaceAll(patComment, ""); // ignore comments
		lexeme = lexeme.replaceAll("\\r\\n", " "); // ignore crlf
		
		while (lexeme.length() > 0)
		{
			if (lexeme.matches(patIdentifier+".*") && !lexeme.matches(patKeyword + ".*"))
			{
				currentToken = cr.getFirstOccur(patIdentifier, lexeme);
				DataHandler.addToken(new Token(currentToken, TokenType.IDENTIFIER));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches(patKeyword + ".*"))
			{
				currentToken = cr.getFirstOccur(patKeyword, lexeme);
				DataHandler.addToken(new Token(currentToken, TokenType.KEYWORD));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches(patAngka+".*"))
			{
				currentToken = cr.getFirstOccur(patAngka, lexeme);
				DataHandler.addToken(new Token(currentToken, TokenType.NUM_LITERAL));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches("^\\s+.*"))
			{
				lexeme = lexeme.replaceFirst("^\\s+", "");
			} 
			else if (lexeme.matches(patString + ".*"))
			{
				currentToken = cr.getFirstOccur(patString, lexeme).replaceFirst("^\"", "").replaceFirst("\"$", "");
				DataHandler.addToken(new Token(currentToken, TokenType.STRING_LITERAL));
				lexeme = lexeme.substring(currentToken.length()+2);
			}
			else if (lexeme.matches(patChar+".*"))
			{
				currentToken = cr.getFirstOccur(patChar, lexeme);
				DataHandler.addToken(new Token(Character.toString(currentToken.toCharArray()[1]), TokenType.CHAR_LITERAL));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches(patSeparator+".*"))
			{
				currentToken = cr.getFirstOccur(patSeparator, lexeme);
				DataHandler.addToken(new Token(currentToken, TokenType.SEPARATOR));
				lexeme = lexeme.replaceFirst(patSeparator, "");
				
			}
			else if (lexeme.matches(patLogika+".*"))
			{
				currentToken = cr.getFirstOccur(patLogika, lexeme);
				DataHandler.addToken(new Token(currentToken, TokenType.LOG_LITERAL));
				lexeme = lexeme.replaceFirst(patLogika, "");
			}
			else if (lexeme.matches(patOperator+".*"))
			{
				currentToken = cr.getFirstOccur(patOperator, lexeme);
				DataHandler.addToken(new Token(currentToken, TokenType.OPERATOR));
				lexeme = lexeme.replaceFirst(patOperator, "");
			}
			else if (lexeme.matches(patNull+".*"))
			{
				currentToken = cr.getFirstOccur(patNull, lexeme);
				DataHandler.addToken(new Token(currentToken, TokenType.NULL_LITERAL));
				lexeme = lexeme.replaceFirst(patNull, "");
			}
			
			currentToken = null;
		}
		
		for (Token t : DataHandler.getTokens())
		{
			DataHandler.addStringRepToken(t);
		}
		
	}
	
	public static void main(String[] args)
	{
		LexGen lg = new LexGen("E:\\Rayhan Hamada\\Kodingan\\Latihan\\Java\\Ws_Eclipse\\HamadaProgLang\\test.txt");
		lg.startLex();

		Parser parser = new Parser();
		parser.evaluate();
		
		
		
		
	}
	
}
