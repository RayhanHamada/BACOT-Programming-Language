package error;

public class VariableDeclStatementError implements ErrorHandler{
	
	private int line, row;
	
	public VariableDeclStatementError(int line)
	{
		this.line = line;
	}
	
	public void getErrorMessage()
	{
		System.out.println("VariableDeclStatementError : 2 atau lebih identifier dengan identitas yang sama terdeteksi pada line ke-" + line + "baris ke-");
	}

}
