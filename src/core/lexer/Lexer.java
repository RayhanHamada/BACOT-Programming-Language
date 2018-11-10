package core.lexer;

import core.util.CustomRegex;
import core.util.FileUtil;

public class Lexer {
	
	private CustomRegex cr;
	private FileUtil fu;
	
	private String lexeme;
	//keyword list for the language
	private String patKeyword = "^(var|sebagai|integer32|integer64|statis|konstan|privat|publik)";
	
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
				LexerDataHandler.addToken(new Token(currentToken, TokenID.IDENTIFIER));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches(patKeyword + ".*"))
			{
				if (lexeme.matches("^var.*"))
				{
					currentToken = cr.getFirstOccur("var", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.VAR));
					lexeme = lexeme.replaceFirst(currentToken, "");
				}
				else if (lexeme.matches("^sebagai.*"))
				{
					currentToken = cr.getFirstOccur("sebagai", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.SEBAGAI));
					lexeme = lexeme.replaceFirst(currentToken, "");
				}
				else if (lexeme.matches("^konstan.*"))
				{
					currentToken = cr.getFirstOccur("konstan", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.CONSTANT));
					lexeme = lexeme.replaceFirst(currentToken, "");
				}
				else if (lexeme.matches("^(privat|publik|terproteksi).*"))
				{
					currentToken = cr.getFirstOccur("(privat|publik|terproteksi)", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.ACCESS_MODIFIER));
					lexeme = lexeme.replaceFirst(currentToken, "");
				}
				else if (lexeme.matches("^(integer(32|64)|float(32|64)|karakter|string).*"))
				{
					currentToken = cr.getFirstOccur("(integer(32|64)|float(32|64)|karakter|string)", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.PRIMITIVE_DATA_TYPE));
					lexeme = lexeme.replaceFirst(currentToken, "");
				}
			}
			else if (lexeme.matches(patAngka+".*"))
			{
				currentToken = cr.getFirstOccur(patAngka, lexeme);
				LexerDataHandler.addToken(new Token(currentToken, TokenID.NUM_LITERAL));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches("^\\s+.*"))
			{
				lexeme = lexeme.replaceFirst("^\\s+", "");
			} 
			else if (lexeme.matches(patString + ".*"))
			{
				currentToken = cr.getFirstOccur(patString, lexeme).replaceFirst("^\"", "").replaceFirst("\"$", "");
				LexerDataHandler.addToken(new Token(currentToken, TokenID.STRING_LITERAL));
				lexeme = lexeme.substring(currentToken.length()+2);
			}
			else if (lexeme.matches(patChar+".*"))
			{
				currentToken = cr.getFirstOccur(patChar, lexeme);
				LexerDataHandler.addToken(new Token(Character.toString(currentToken.toCharArray()[1]), TokenID.CHAR_LITERAL));
				lexeme = lexeme.replaceFirst(currentToken, "");
			}
			else if (lexeme.matches(patSeparator+".*"))
			{
				if (lexeme.matches("^\\{.*"))
				{
					currentToken = cr.getFirstOccur("\\{", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.LBRACKET));
					lexeme = lexeme.replaceFirst("\\" + currentToken, "");
				}
				else if (lexeme.matches("^\\}.*"))
				{
					currentToken = cr.getFirstOccur("\\}", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.RBRACKET));
					lexeme = lexeme.replaceFirst("\\" + currentToken, "");
				}
				else if (lexeme.matches("^\\(.*"))
				{
					currentToken = cr.getFirstOccur("\\(", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.LPAREN));
					lexeme = lexeme.replaceFirst("\\" + currentToken, "");
				}
				else if (lexeme.matches("^\\).*"))
				{
					currentToken = cr.getFirstOccur("\\)", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.RPAREN));
					lexeme = lexeme.replaceFirst("\\" + currentToken, "");
				}
				else if (lexeme.matches("^;.*"))
				{
					currentToken = cr.getFirstOccur(";", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.SEMICOLON));
					lexeme = lexeme.replaceFirst(currentToken, "");
				}
				else if (lexeme.matches("^:.*"))
				{
					currentToken = cr.getFirstOccur(":", lexeme);
					LexerDataHandler.addToken(new Token(":", TokenID.COLON));
					lexeme = lexeme.replaceFirst(currentToken, "");
				}
				else if (lexeme.matches("^\\,.*"))
				{
					currentToken = cr.getFirstOccur(",", lexeme);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.COMMA));
					lexeme = lexeme.replaceFirst(currentToken, "");
				}
				
			}
			else if (lexeme.matches(patLogika+".*"))
			{
				currentToken = cr.getFirstOccur(patLogika, lexeme);
				LexerDataHandler.addToken(new Token(currentToken, TokenID.LOG_LITERAL));
				lexeme = lexeme.replaceFirst(patLogika, "");
			}
			else if (lexeme.matches(patOperator+".*"))
			{
				currentToken = cr.getFirstOccur(patOperator, lexeme);
				LexerDataHandler.addToken(new Token(currentToken, TokenID.OPERATOR));
				lexeme = lexeme.replaceFirst(patOperator, "");
			}
			
			
			currentToken = null;
		}
		
		for (Token t : LexerDataHandler.getTokens())
		{
			LexerDataHandler.addStringRepToken(t);
		}
		
		
		
	}
	
}
