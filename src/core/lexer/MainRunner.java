package core.lexer;

public class MainRunner {

	public static void main(String...args)
	{
		Lexer lg = new Lexer("E:\\Rayhan Hamada\\Kodingan\\Latihan\\Java\\Ws_Eclipse\\HamadaProgLang\\test.txt");
		lg.startLex();
		
		for (String s : LexerDataHandler.getStringRepToken()) System.out.println(s);
		
		Parser s = new Parser();
		s.parse();
	}
}
