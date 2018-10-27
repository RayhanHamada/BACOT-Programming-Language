package core.lexer;

import java.util.ArrayList;

public class Block {

	protected Block parentBlock;
	protected ArrayList<Block> childBlocks;
	
	public Block(Block parentBlock, ArrayList<Block> childBlocks)
	{
		this.parentBlock = parentBlock;
		this.childBlocks = childBlocks;
	}
	
	public Block getParentBlock()
	{
		return parentBlock;
	}
	
	public ArrayList<Block> getChildBlocks()
	{
		return childBlocks;
	}
	
	public void setParentBlock(Block block)
	{
		this.parentBlock = block;
	}
	
	public void setChildBlocks(ArrayList<Block> blocks)
	{
		this.childBlocks = blocks;
	}
	
}
