package utilities;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class FunctionType 
{
	public ArrayList<String> names;
	public ParseTree statements;
	public boolean ret;
	public ParserRuleContext ctx;
	
	public FunctionType( ParserRuleContext ctx, ArrayList<String> names, ParseTree statements, boolean ret )
	{
		this.ctx = ctx;
		this.names = names;
		this.statements = statements;
		this.ret = ret;
	}
}
