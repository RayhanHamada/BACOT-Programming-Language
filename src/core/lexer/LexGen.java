package core.lexer;

import java.util.ArrayList;

import core.util.CustomRegex;
import core.util.FileUtil;

public class LexGen {
	
	private CustomRegex cr;
	private FileUtil fu;
	
	private String lexeme;
	private ArrayList<Token> tokens;
	
	//pola token
	private String patIdentifier = "^[A-Za-z_][\\w]*";
	private String patAngka = "^\\d+\\.\\d*f?|\\d*\\.\\d+f?|\\d+";
	private String patString = "^((?<![\\\\])[\"])((?:.(?!(?<![\\\\])\\1))*.?)\\1";
	private String patChar = "^'.+'";
	private String patSeparator = "^[\\(\\)\\{\\}\\[\\]]";
	private String patLogika = "^(true|false)";
	private String patOperator = "^(\\+|-|\\*|\\/|=|==|>>|<<|>|<|>=|<=|&|\\||%|!|\\^|\\(|\\))";
	private String patNull = "^nil";
	private String patComment = "^//.*//";
	
	public LexGen(String path)
	{
		cr = new CustomRegex();
		fu = new FileUtil();
		
		lexeme = fu.fileToText(path);
		this.tokens = new ArrayList<Token>();

	}
	
	public ArrayList<Token> startLex()
	{
		String currentToken = null;
		lexeme = lexeme.replaceAll(patComment, ""); // ignore comments
		lexeme = lexeme.replaceAll("\\r\\n", " "); // ignore crlf
		
		while (lexeme.length() > 0)
		{
			if (lexeme.matches(patIdentifier+".*") && !lexeme.matches("^(true|false|nil).*"))
			{
				currentToken = cr.getFirstOccur(patIdentifier, lexeme);
				tokens.add(new Token(currentToken, TokenType.IDENTIFIER));
				lexeme = lexeme.replaceFirst(currentToken, "");
				
			}
			else if (lexeme.matches(patAngka+".*"))
			{
				currentToken = cr.getFirstOccur(patAngka, lexeme);
				tokens.add(new Token(currentToken, TokenType.NUM_LITERAL));
				lexeme = lexeme.replaceFirst(currentToken, "");
				
			}
			else if (lexeme.matches("^\\s+.*"))
			{
				lexeme = lexeme.replaceFirst("^\\s+", "");
			} 
			else if (lexeme.matches(patString + ".*"))
			{
				currentToken = cr.getFirstOccur(patString, lexeme).replaceFirst("^\"", "").replaceFirst("\"$", "");
				tokens.add(new Token(currentToken, TokenType.STRING_LITERAL));
				lexeme = lexeme.substring(currentToken.length()+2);
			}
			else if (lexeme.matches(patChar+".*"))
			{
				currentToken = cr.getFirstOccur(patChar, lexeme);
				tokens.add(new Token(Character.toString(currentToken.toCharArray()[1]), TokenType.CHAR_LITERAL));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches(patSeparator+".*"))
			{
				currentToken = cr.getFirstOccur(patSeparator, lexeme);
				tokens.add(new Token(currentToken, TokenType.SEPARATOR));
				lexeme = lexeme.replaceFirst(patSeparator, "");
				
			}
			else if (lexeme.matches(patLogika+".*"))
			{
				currentToken = cr.getFirstOccur(patLogika, lexeme);
				tokens.add(new Token(currentToken, TokenType.LOG_LITERAL));
				lexeme = lexeme.replaceFirst(patLogika, "");
			}
			else if (lexeme.matches(patOperator+".*"))
			{
				currentToken = cr.getFirstOccur(patOperator, lexeme);
				tokens.add(new Token(currentToken, TokenType.OPERATOR));
				lexeme = lexeme.replaceFirst(patOperator, "");
			}
			else if (lexeme.matches(patNull+".*"))
			{
				currentToken = cr.getFirstOccur(patNull, lexeme);
				tokens.add(new Token(currentToken, TokenType.NULL_LITERAL));
				lexeme = lexeme.replaceFirst(patNull, "");
			}
			
			
			currentToken = null;
		}
		for (Token t: tokens) System.out.println(t.getType());
		
		return tokens;
	}
	
	public static void main(String[] args)
	{
		LexGen lg = new LexGen("D:\\rayhan\\Kodingan\\Latihan\\Java\\Proyek Eclipse\\WS_PRO_L\\HamadaProgLang\\src\\test.txt");
		lg.startLex();
	}
	
}
