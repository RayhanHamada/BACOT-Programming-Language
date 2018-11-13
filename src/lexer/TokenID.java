package lexer;

public enum TokenID {
	
	IDENTIFIER,
	OPERATOR,
	
	// literal
	NUM_LITERAL,
	LOG_LITERAL,
	CHAR_LITERAL,
	STRING_LITERAL,
	
	//for NUM_LITERAL
	FLOAT_LITERAL,
	INTEGER_LITERAL,
	
	//Assignment Operator
	ASSIGN,
	SHORTHAND_ASSIGN,
	
	//for Compare Operator
	RELATIONAL,
	
	//for atom token (SEPARATOR)
	LBRACKET,
	RBRACKET,
	LPAREN,
	RPAREN,
	SEMICOLON,
	COLON,
	COMMA,
	EQUALS,
	
	//for property modifier (functions, classes and variables)
	PRIMITIVE_TYPE, // integer32, integer64, float32, float64, karakter, boolean, string
	DEFINED_TYPE,
	ACCESS_MODIFIER, // publik, privat, terproteksi
	
	//for Keyword
	VAR,
	SEBAGAI,
	STATIC,
	CONSTANT,
	IMPOR,
	
	
	
	
	COMMENTS

}
