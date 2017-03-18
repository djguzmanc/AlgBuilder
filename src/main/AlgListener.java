package main;

import java.util.ArrayList;
import java.util.HashMap;

import antlr.AlgBuilderLanguageParser.FunctionContext;
import antlr.AlgBuilderLanguageParser.MainprocessContext;
import utilities.FunctionType;

public class AlgListener extends antlr.AlgBuilderLanguageBaseListener
{

	HashMap<String,FunctionType> table;
	
	public AlgListener( )
	{
		table = new HashMap<>( );
	}	
	
	@Override
	public void enterFunction( FunctionContext ctx )
	{
		String name = ctx.ID( 0 ).getText( );
		ArrayList<String> names = new ArrayList<>( );
		
		if ( ctx.command( ).returnstatement( ) != null )
		{
			if ( ctx.ID( ).size( ) == 1 )
			{
				table.put( name, new FunctionType( ctx, names, ctx.getChild( ctx.getChildCount( ) - 2 ), true ) );
			}
			else
			{
				for ( int i = 1; i < ctx.ID( ).size( ); i++ )
					names.add( ctx.ID( ).get( i ).getText( ) );
				table.put( name, new FunctionType( ctx, names, ctx.getChild( ctx.getChildCount( ) - 2 ), true ) );
			}
		}
		else
		{
			if ( ctx.ID( ).size( ) == 1 )
			{
				table.put( name, new FunctionType( ctx, names, ctx.getChild( ctx.getChildCount( ) - 2 ), false ) );
			}
			else
			{
				for ( int i = 1; i < ctx.ID( ).size( ); i++ )
					names.add( ctx.ID( ).get( i ).getText( ) );
				table.put( name, new FunctionType( ctx, names, ctx.getChild( ctx.getChildCount( ) - 2 ), false ) );
			}
		}
	}
	
	@Override
	public void enterMainprocess( MainprocessContext ctx ) 
	{
		table.put( "main" , new FunctionType( ctx, null, ctx.getChild( ctx.getChildCount( ) - 2 ), false ) );
	}
}
