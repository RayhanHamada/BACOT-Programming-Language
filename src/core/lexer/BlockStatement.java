package core.lexer;

/*
 * untuk:
 * ClassDefStatement	: ClassGlobalDefStatement, ClassLocalDefStatement
 * FunctionDefStatement :FunctionGlobalDefStatement, FunctionLocalDefStatement
 * ConditionalStatement : IfStatement, IfElseStatement, CaseStatement 
 * LoopStatement : ForLoopStatement, WhileLoopStatement, DoWhileLoopStatement
 * 
 * 
 */
import java.util.ArrayList;

public abstract class BlockStatement implements Statement{

	protected BlockStatement superBlockStatement;
	protected ArrayList<LineStatement> lineStatements;
	protected ArrayList<BlockStatement> blockStatements;
	
}
