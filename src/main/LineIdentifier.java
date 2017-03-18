package main;

import java.util.ArrayList;

import antlr.AlgBuilderLanguageBaseVisitor;
import antlr.AlgBuilderLanguageParser.CommandContext;
import components.DropdownVariableList;

public class LineIdentifier<T> extends AlgBuilderLanguageBaseVisitor<T> 
{
	ArrayList<Integer> executableLines;
	DropdownVariableList dropList;
	AlgBuilder applet;
	
	public LineIdentifier( AlgBuilder applet, ArrayList<Integer> list, DropdownVariableList dropList )
	{
		this.applet = applet;
		executableLines = list;
		this.dropList = dropList;
	}
	
	@Override
	public T visitCommand( CommandContext ctx ) 
	{
		executableLines.add( ctx.getStart( ).getLine( ) );
		
		if ( ctx.declaration( ) != null )
		{
			String name = ctx.declaration( ).ID( ).getText( );
			String type = ctx.declaration( ).TYPE( ).getText( );
			
			dropList.addVariable( name + "    " + type, null );
		}
		else if ( ctx.forloop( ) != null )
		{
			if ( ctx.forloop( ).TYPE( ) != null )
			{
				String name = ctx.forloop( ).ID( ).getText( );
				String type = ctx.forloop( ).TYPE( ).getText( );
				
				dropList.addVariable( name + "    " + type, null );
			}
		}
		
		return super.visitCommand( ctx );
	}
}
