package lexer;

import java.util.ArrayList;
import java.util.Arrays;

import util.CustomRegex;
import util.FileUtil;

public class Lexer {
	
	private CustomRegex cr;
	
	private String lexeme;
	//keyword list for the language
	private String patKeyword = "^(var|sebagai|integer32|integer64|statis|string|konstan|privat|publik)";
	
	//pola token
	private String patIdentifier = "^[A-Za-z_][\\w]*";
	private String patAngka = "^(\\d+\\.\\d*f?|^\\d*\\.\\d+f?|^\\d+)";
	private String patString = "^((?<![\\\\])[\"])((?:.(?!(?<![\\\\])\\1))*.?)\\1";
	private String patChar = "^'.'";
	private String patLogika = "^(true|false)";
	private String patOperator = "^(\\+|-|\\*|\\/|>>|<<|>|<|==|>=|<=|>|<|=|&|\\||%|!|\\^|\\(|\\))";
//	private String patNull = "^nil";
	private String patComment = "^//.*//";
	private String patSeparator = "^[\\(\\)\\{\\}\\[\\];:,]";
	
	public Lexer(String path)
	{
		cr = new CustomRegex();
		lexeme = FileUtil.fileToText(path);
	}
	
	public void startLex()
	{
		String currentToken;
		int currentLine = 1, currentRow = 1;
		lexeme = lexeme.replaceAll(patComment, ""); // ignore comments
		ArrayList<String> lines = new ArrayList<>(Arrays.asList(lexeme.split(System.lineSeparator())));
		
		for (String line : lines) {
			
			while (line.length() > 0) {
				
				if (line.matches("^\\s.*"))
				{
					currentRow++;
					line = line.replaceFirst("\\s", "");
				}
				else if (line.matches(patIdentifier + ".*") && !line.matches(patKeyword + ".*")) {
					currentToken = cr.getFirstOccur(patIdentifier, line);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.IDENTIFIER, currentLine, currentRow));
					currentRow += currentToken.length();
					line = line.replaceFirst(currentToken, "");
				} 
				else if (line.matches(patKeyword + ".*")) 
				{

					if (line.matches("^var.*")) 
					{
						currentToken = cr.getFirstOccur("var", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.VAR, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					} 
					else if (line.matches("^sebagai.*")) 
					{
						currentToken = cr.getFirstOccur("sebagai", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.SEBAGAI, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					} 
					else if (line.matches("^konstan.*")) 
					{
						currentToken = cr.getFirstOccur("konstan", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.CONSTANT, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					} 
					else if (line.matches("^(privat|publik|terproteksi).*")) 
					{
						currentToken = cr.getFirstOccur("(privat|publik|terproteksi)", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.ACCESS_MODIFIER, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					} 
					else if (line.matches("^(integer(32|64)|float(32|64)|karakter|string).*")) 
					{
						currentToken = cr.getFirstOccur("(integer(32|64)|float(32|64)|karakter|string)", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.PRIMITIVE_TYPE, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					}
				} 
				else if (line.matches(patAngka + ".*")) 
				{
					currentToken = cr.getFirstOccur(patAngka, line);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.NUM_LITERAL, currentLine, currentRow));
					currentRow += currentToken.length();
					line = line.replaceFirst(currentToken, "");
				}  
				else if (line.matches(patString + ".*")) 
				{
					currentToken = cr.getFirstOccur(patString, line).replaceFirst("^\"", "").replaceFirst("\"$", "");
					LexerDataHandler.addToken(new Token(currentToken, TokenID.STRING_LITERAL, currentLine, currentRow));
					currentRow += currentToken.length() + 2;
					line = line.substring(currentToken.length() + 2);
				} 
				else if (line.matches(patChar + ".*")) 
				{
					currentToken = cr.getFirstOccur(patChar, line);
					LexerDataHandler.addToken(new Token(Character.toString(currentToken.toCharArray()[1]), TokenID.CHAR_LITERAL, currentLine, currentRow));
					currentRow += 3;
					line = line.replaceFirst(currentToken, "");
				} 
				else if (line.matches(patSeparator + ".*")) 
				{
					if (line.matches("^\\{.*")) 
					{
						currentToken = cr.getFirstOccur("\\{", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.LBRACKET, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst("\\\\" + currentToken, "");
					} 
					else if (line.matches("^\\}.*")) 
					{
						currentToken = cr.getFirstOccur("\\}", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.RBRACKET, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst("\\\\" + currentToken, "");
					} 
					else if (line.matches("^\\(.*")) 
					{
						currentToken = cr.getFirstOccur("\\(", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.LPAREN, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst("\\\\" + currentToken, "");
					}
					else if (line.matches("^\\).*")) 
					{
						currentToken = cr.getFirstOccur("\\)", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.RPAREN, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst("\\\\" + currentToken, "");
					}
					else if (line.matches("^;.*")) 
					{
						currentToken = cr.getFirstOccur(";", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.SEMICOLON, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					}
					else if (line.matches("^:.*")) 
					{
						currentToken = cr.getFirstOccur(":", line);
						LexerDataHandler.addToken(new Token(":", TokenID.COLON, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					} 
					else if (line.matches("^\\,.*")) 
					{
						currentToken = cr.getFirstOccur(",", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.COMMA, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					}

				} 
				else if (line.matches(patLogika + ".*")) 
				{
					currentToken = cr.getFirstOccur(patLogika, line);
					LexerDataHandler.addToken(new Token(currentToken, TokenID.LOG_LITERAL, currentLine, currentRow));
					currentRow += currentToken.length();
					line = line.replaceFirst(patLogika, "");
				} 
				else if (line.matches(patOperator + ".*")) 
				{
					if (line.matches("^((=|>|<|!)=|>|<).*")) 
					{
						currentToken = cr.getFirstOccur("((=|>|<|!)=|>|<)", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.RELATIONAL, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					} 
					else if (line.matches("^(\\+|-|\\*|/|%)=.*")) 
					{
						currentToken = cr.getFirstOccur("(\\+|\\-|\\*|/|%)=", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.SHORTHAND_ASSIGN, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					} 
					else if (line.matches("^=.*")) 
					{
						currentToken = cr.getFirstOccur("=", line);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.ASSIGN, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					}
					else if (line.matches("^(\\+|\\-|\\*|/).*")) 
					{
						currentToken = cr.getFirstOccur("(\\+|\\-|\\*|/)", lexeme);
						LexerDataHandler.addToken(new Token(currentToken, TokenID.OPERATOR, currentLine, currentRow));
						currentRow += currentToken.length();
						line = line.replaceFirst(currentToken, "");
					}
						
				}

				currentToken = null;
			}
			currentLine++;
		}
		for (Token t : LexerDataHandler.getTokens())
		{
			LexerDataHandler.addStringRepToken(t);
		}
		
	}
	
}
