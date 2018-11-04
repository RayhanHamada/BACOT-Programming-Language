package core.lexer;

import core.util.CustomRegex;
import core.util.FileUtil;

public class Lexer {
	
	private CustomRegex cr;
	private FileUtil fu;
	
	private String lexeme;
	//keyword list for the language
	private String patKeyword = "^(var|sebagai|Integer|Statis|Konstan|Privat|Publik)";
	
	//pola token
	private String patIdentifier = "^[A-Za-z_][\\w]*";
	private String patAngka = "^(\\d+\\.\\d*f?|^\\d*\\.\\d+f?|^\\d+)";
	private String patString = "^((?<![\\\\])[\"])((?:.(?!(?<![\\\\])\\1))*.?)\\1";
	private String patChar = "^'.+'";
	private String patLogika = "^(true|false)";
	private String patOperator = "^(\\+|-|\\*|\\/|=|==|>>|<<|>|<|>=|<=|&|\\||%|!|\\^|\\(|\\))";
	private String patNull = "^nil";
	private String patComment = "^//.*//";
	private String patSeparator = "^[\\(\\)\\{\\}\\[\\];:,]";
	
	
	public Lexer(String path)
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
				LexerDataHandler.addToken(new Token(currentToken, TokenType.IDENTIFIER));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches(patKeyword + ".*"))
			{
				currentToken = cr.getFirstOccur(patKeyword, lexeme);
				LexerDataHandler.addToken(new Token(currentToken, TokenType.KEYWORD));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches(patAngka+".*"))
			{
				currentToken = cr.getFirstOccur(patAngka, lexeme);
				LexerDataHandler.addToken(new Token(currentToken, TokenType.NUM_LITERAL));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches("^\\s+.*"))
			{
				lexeme = lexeme.replaceFirst("^\\s+", "");
			} 
			else if (lexeme.matches(patString + ".*"))
			{
				currentToken = cr.getFirstOccur(patString, lexeme).replaceFirst("^\"", "").replaceFirst("\"$", "");
				LexerDataHandler.addToken(new Token(currentToken, TokenType.STRING_LITERAL));
				lexeme = lexeme.substring(currentToken.length()+2);
			}
			else if (lexeme.matches(patChar+".*"))
			{
				currentToken = cr.getFirstOccur(patChar, lexeme);
				LexerDataHandler.addToken(new Token(Character.toString(currentToken.toCharArray()[1]), TokenType.CHAR_LITERAL));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches(patSeparator+".*"))
			{
				if (lexeme.matches("^\\{.*"))
				{
					currentToken = cr.getFirstOccur("\\{", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenType.LBRACKET));
					lexeme = lexeme.replaceFirst("\\" + currentToken, "");
				}
				else if (lexeme.matches("^\\}.*"))
				{
					currentToken = cr.getFirstOccur("\\}", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenType.RBRACKET));
					lexeme = lexeme.replaceFirst("\\" + currentToken, "");
				}
				else if (lexeme.matches("^\\(.*"))
				{
					currentToken = cr.getFirstOccur("\\(", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenType.LPAREN));
					lexeme = lexeme.replaceFirst("\\" + currentToken, "");
				}
				else if (lexeme.matches("^\\).*"))
				{
					currentToken = cr.getFirstOccur("\\)", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenType.RPAREN));
					lexeme = lexeme.replaceFirst("\\" + currentToken, "");
				}
				else if (lexeme.matches("^;.*"))
				{
					currentToken = cr.getFirstOccur(";", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenType.SEMICOLON));
					lexeme = lexeme.replaceFirst(currentToken, "");
				}
				else if (lexeme.matches("^:.*"))
				{
					currentToken = cr.getFirstOccur(":", lexeme);
					LexerDataHandler.addToken(new Token(":", TokenType.COLON));
					lexeme = lexeme.replaceFirst(currentToken, "");
				}
				else if (lexeme.matches("^\\,.*"))
				{
					currentToken = cr.getFirstOccur(",", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenType.COMMA));
					lexeme = lexeme.replaceFirst(currentToken, "");
				}
				
			}
			else if (lexeme.matches(patLogika+".*"))
			{
				currentToken = cr.getFirstOccur(patLogika, lexeme);
				LexerDataHandler.addToken(new Token(currentToken, TokenType.LOG_LITERAL));
				lexeme = lexeme.replaceFirst(patLogika, "");
			}
			else if (lexeme.matches(patOperator+".*"))
			{
				currentToken = cr.getFirstOccur(patOperator, lexeme);
				LexerDataHandler.addToken(new Token(currentToken, TokenType.OPERATOR));
				lexeme = lexeme.replaceFirst(patOperator, "");
			}
			else if (lexeme.matches(patNull+".*"))
			{
				currentToken = cr.getFirstOccur(patNull, lexeme);
				LexerDataHandler.addToken(new Token(currentToken, TokenType.NULL_LITERAL));
				lexeme = lexeme.replaceFirst(patNull, "");
			}
			
			currentToken = null;
		}
		
		for (Token t : LexerDataHandler.getTokens())
		{
			LexerDataHandler.addStringRepToken(t);
		}
		
		
		
	}
	
}
