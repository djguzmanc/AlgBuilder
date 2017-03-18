package components;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

import controlP5.ControlP5;
import controlP5.DropdownList;
import main.AlgBuilder;

public class DropdownVariableList 
{
	public float x;
	public float y;
	
	public float width;
	public float height;
	
	public DropdownList dropList;
	public CopyOnWriteArrayList<VariableViewer> list;
	
	public TreeSet<String> availableVariables;
	
	public AlgBuilder applet;
	
	float xOffset = 0, yOffset;
	
	public DropdownVariableList( AlgBuilder applet, ControlP5 cp5, float x, float y, float width, float height )
	{
		this.applet = applet;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		dropList = cp5.addDropdownList( "Variables" ).setPosition( x, y )
				.setSize( 240, 200 ).setColorActive( applet.color( 254, 213, 71 ) )
				.setColorBackground( applet.color( 100 ) ).setColorForeground( applet.color( 253, 117, 26 ) )
				.lock( )
				;
		dropList.setItemHeight( 30 );
		dropList.setBarHeight( 15 );
		
		availableVariables = new TreeSet<>( );
		
		list = new CopyOnWriteArrayList<>( );
	}
	
	public void addVariable( String name, Object value )
	{		
		if ( !availableVariables.contains( name.split( "    " )[ 0 ] ) )
		{
			dropList.addItem( name, name.split( "    " )[ 0 ] );
			dropList.close( );
			
			availableVariables.add( name.split( "    " )[ 0 ] );
		}
	}
	
	public void clearList( )
	{
		dropList.clear( );
		availableVariables = new TreeSet<>( );
	}
	
	public void addViewer( float x, float y, String name, Object value )
	{		
		if ( !list.contains( new VariableViewer( name ) ) )
		{
			VariableViewer vv = new VariableViewer( x + xOffset * 50, y + yOffset, applet, name, value );
			if ( applet.loader.table.getData( name ) != null )
				vv.alive = true;
			
			list.add( vv );
			if ( xOffset == 12 )
				yOffset += height;
			xOffset = ( xOffset + 1 ) % 13;
		}
	}
	
	public void paintViewers( )
	{
		for ( VariableViewer vv : list )
		{
			vv.paintViewer( );
		}
	}
	
	public void clearViewers( )
	{
		for ( VariableViewer vv : list )
		{
			vv.value = "";
			vv.alive = false;
		}
	}
	
	public void cleanViewers( )
	{
		list = new CopyOnWriteArrayList<>( );
	}
	
	public void fixOffset( )
	{
		xOffset = 0;
		yOffset = 0;
	}
	
	public void updateViewers( )
	{
		ArrayList<VariableViewer> toRemove = new ArrayList<>( );
		
		for ( VariableViewer vv : list )
		{
			if ( !availableVariables.contains( vv.variableName ) )
				toRemove.add( vv );
		}
		
		for ( VariableViewer vv : toRemove )
			list.remove( vv );
	}
}
