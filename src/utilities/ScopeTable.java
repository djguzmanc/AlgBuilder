package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import components.DropdownVariableList;
import components.StructureTab;
import components.VariableViewer;
import main.AlgBuilder;

public class ScopeTable 
{
	public ArrayList<HashMap<String,Value>> table;
	DropdownVariableList dropList;
	Stack<Integer> restrictor = new Stack<>( );
	
	AlgBuilder applet;
	
	public ScopeTable( AlgBuilder applet, DropdownVariableList dropList )
	{
		table = new ArrayList<>( );
		table.add( new HashMap<>( ) );
		restrictor.push( 0 );
		
		this.dropList = dropList;
		this.applet = applet;
	}
	
	public void setRestrictor( int res )
	{
		restrictor.push( res );
	}
	
	public void newContext( )
	{
		table.add( new HashMap<>( ) );
	}
	
	public void popContext( )
	{
		for ( String s : table.get( table.size( ) - 1 ).keySet( ) )
		{
			if ( dropList.list.contains( new VariableViewer( s ) ) )
			{
				int index =  dropList.list.indexOf( new VariableViewer( s ) );
				VariableViewer vv = dropList.list.get( index );
				
				vv.alive = false;
			}
		}
		
		for ( String s : table.get( table.size( ) - 1 ).keySet( ) )
		{
			for ( StructureTab st : applet.mainList )
			{
				if ( s.equals( st.structure.name ) )
				{										
					applet.mainList.remove( st );
					applet.loader.tabs--;
					break;
				}
			}
		}
		
		table.remove( table.size( ) - 1 );
		if ( restrictor.size( ) > 1 )
			restrictor.pop( );
	}
	
	public void pushData( String name, Value value )
	{
		table.get( table.size( ) - 1 ).put( name, value );
	}
	
	public Value getData( String name )
	{
		for ( int i = table.size( ) - 1; i >= restrictor.peek( ); i-- )
		{
			Value val = table.get( i ).get( name );
			if ( val != null )
				return val;
		}
		return null;
	}
}
