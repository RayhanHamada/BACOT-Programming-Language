package core.lexer;

public enum TokenType {
	
	IDENTIFIER,
	KEYWORD,
	SEPARATOR,
	OPERATOR,
	
	// literal
	NUM_LITERAL,
	LOG_LITERAL,
	CHAR_LITERAL,
	STRING_LITERAL,
	NULL_LITERAL,
	
	//for atom token (SEPARATOR
	LBRACKET,
	RBRACKET,
	LPAREN,
	RPAREN,
	SEMICOLON,
	COLON,
	COMMA,
	
	
	COMMENTS

}
