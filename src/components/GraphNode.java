package components;

import java.util.concurrent.ConcurrentHashMap;

import enums.Colors;
import main.AlgBuilder;
import processing.core.PApplet;

public class GraphNode extends Node 
{	
	public ConcurrentHashMap<GraphNode, Integer> nbrs;
	int n;
	
	boolean visiting;
	
	public GraphNode( AlgBuilder applet, float x, float y, Integer color, Integer alpha, Object elem,
			String tag ) 
	{
		super( applet, x, y, true, color, alpha, elem, tag );
		nbrs = new ConcurrentHashMap<>( );
		n = 0;
		visiting = false;
	}
	
	public Object getElem( )
	{
		return elem;
	}
	
	public void setColor( Colors c )
	{
		switch ( c )
		{
		case BLUE:
			color = applet.color( 0, 162, 232 );
			break;
		case GRAY:
			color = applet.color( 127, 127, 127 );
			break;
		case GREEN:
			color = applet.color( 65, 187, 23 );
			break;
		case ORANGE:
			color = applet.color( 255, 127, 39 );
			break;
		case PINK:
			color = applet.color( 255, 174, 201 );
			break;
		case PURPLE:
			color = applet.color( 163, 73, 164 );
			break;
		case RED:
			color = applet.color( 237, 28, 36 );
			break;
		case YELLOW:
			color = applet.color( 255, 242, 0 );
			break;
		case BLACK:
			color = applet.color( 0 );
			break;
		case DEFAULT:
			color = applet.color( 0, 162, 232 );
			break;
		case WHITE:
			color = applet.color( 255 );
			break;
		}
	}
	
	public void visiting( boolean v )
	{
		visiting = v;
	}
	
	public ConcurrentHashMap<GraphNode, Integer> getNbrs( )
	{
		return nbrs;
	}
	
	public void makeLink( GraphNode n )
	{
		Integer x = nbrs.get( n );
		
		if ( x != null )
			nbrs.put( n, nbrs.get( n ) + 1 );
		else
		{
			this.n++;
			nbrs.put( n, 1 );
		}
	}
	
	public void removeLink( GraphNode n )
	{
		Integer x = nbrs.get( n );
		if ( x == null )
			return;
		
		if ( x == 1 )
		{
			nbrs.remove( n );
			this.n--;
		}
		else
			nbrs.put( n, nbrs.get( n ) - 1 );
	}
	
	public void drawNode( )
	{		
		if ( !visiting )
			applet.noStroke( );
		else
			applet.stroke( 237, 28, 36 );
		
		applet.fill( 255 );
		applet.ellipse( x, y, 30, 30 );
		
		applet.fill( color, alpha );
		applet.ellipse( x, y, 30, 30 );
		
		applet.textAlign( PApplet.CENTER );
		applet.fill( 0 );
		applet.text( elem.toString( ), x, y + 5 );
	}

	@Override
	public boolean equals( Object obj ) 
	{
		if ( elem.equals( ( ( GraphNode ) obj ).elem ) )
			return true;
		return false;
	}
	
	public void reallocateX( float x )
	{
		this.x = x;
	}
	
	public void reallocateY( float y )
	{
		this.y = y;
	}
	
	public boolean mouseOver( float mouseX, float mouseY )
	{
		double distance = Math.sqrt( Math.pow( x - mouseX, 2 ) + Math.pow( y - mouseY, 2 ) );
		
		if ( distance < 30 / 2 )
			return true;
		return false;
	}
	
	@Override
	public String toString( ) 
	{
		return elem.toString( );
	}
}
