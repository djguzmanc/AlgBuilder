package structures;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import components.GraphNode;
import main.AlgBuilder;

public class Graph extends Structure
{
	public ConcurrentHashMap<Object, GraphNode> graph;
	int nodes;

	public Graph( AlgBuilder applet, float x, float y, HashMap<String, StructureMethod> methods, boolean visible,
			String name ) 
	{
		super( applet, x, y, methods, visible, name );
		graph = new ConcurrentHashMap<>( );
		nodes = 0;
	}
	
	@Override
	public String toString( ) 
	{
		return "Gp: " + name;
	}
	
	public void addNode( Object elem )
	{
		GraphNode node = graph.get( elem );
		
		if ( node == null )
		{
			float x;
			float y;
			
			do
			{
				x = applet.random( applet.visualCanvas.x + 15, applet.visualCanvas.x + applet.visualCanvas.width - 15 );
				y = applet.random( applet.visualCanvas.y + 15, applet.visualCanvas.y + applet.visualCanvas.height - 15 );
				
			} while ( overlapping( x, y ) );
			
			
			graph.put( elem, new GraphNode( applet, x, y, applet.color( 0, 162, 232 ), 100, elem, elem.toString( ) ) );
		}
	}
	
	public void makeLink( Object elemA, Object elemB )
	{		
		addNode( elemA );
		addNode( elemB );
		
		GraphNode a = graph.get( elemA );
		GraphNode b = graph.get( elemB );
		
		a.makeLink( b );
		b.makeLink( a );
	}
	
	public void removeLink( Object elemA, Object elemB )
	{
		GraphNode a = graph.get( elemA );
		GraphNode b = graph.get( elemB );
		
		a.removeLink( b );
		b.removeLink( a );
	}
	
	public GraphNode getNode( Object elem )
	{
		return graph.get( elem );
	}
	
	public void removeNode( Object elem )
	{
		GraphNode toRemove = graph.get( elem );
		
		for ( GraphNode nbr : toRemove.nbrs.keySet( ) )
			nbr.nbrs.remove( toRemove );
		
		graph.remove( elem );
	}
	
	public boolean overlapping( float x, float y )
	{
		for ( GraphNode gn : graph.values( ) )
		{
			double d = Math.sqrt( Math.pow( gn.x - x, 2 ) + Math.pow( gn.y - y, 2 ) );
			if ( d < 45 )
				return true;
		}
		
		return false;
	}
	
	@Override
	public void drawStructure( )  
	{
		if ( visible )
		{			
			applet.stroke( 0 );
			for ( GraphNode gn : graph.values( ) )
				for ( GraphNode nbr : gn.nbrs.keySet( ) )
				{
					Integer links = gn.nbrs.get( nbr );
					double d = Math.sqrt( Math.pow( gn.x - nbr.x, 2 ) + Math.pow( gn.y - nbr.y, 2 ) );
					
					if ( links % 2 == 0 )
					{
						float xm = ( gn.x + nbr.x ) / 2;
						float ym = ( gn.y + nbr.y ) / 2;
						
						float m = ( gn.y - nbr.y ) / ( gn.x - nbr.x );
						
						float mi = -1 / m;
						
						float blx = ( gn.x + xm ) / 2;
						float bly = ( gn.y + ym ) / 2;
						
						float brx = ( nbr.x + xm ) / 2;
						float bry = ( nbr.y + ym ) / 2;
						
						applet.noFill( );
						
						for ( int i = 0; i < links / 2; i++ )
						{
							if ( Math.abs( mi ) > 1 )
							{
								float flx = ( float ) ( blx + ( d / 10 ) * ( i + 1 ) / mi );
								float fly = mi * ( flx - blx ) + bly;
								
								float frx = ( float ) ( brx + ( d / 10 ) * ( i + 1 ) / mi );
								float fry = mi * ( frx - brx ) + bry;
								
								applet.bezier( gn.x, gn.y, flx, fly, frx, fry, nbr.x, nbr.y );
								
								float flx2 = ( float ) ( blx - ( d / 10 ) * ( i + 1 ) / mi );
								float fly2 = mi * ( flx2 - blx ) + bly;
								
								float frx2 = ( float ) ( brx - ( d / 10 ) * ( i + 1 ) / mi );
								float fry2 = mi * ( frx2 - brx ) + bry;
								
								applet.bezier( gn.x, gn.y, flx2, fly2, frx2, fry2, nbr.x, nbr.y );
							}
							else
							{
								float flx = ( float ) ( blx + ( d / 10 ) * ( i + 1 ) );
								float fly = mi * ( flx - blx ) + bly;
								
								float frx = ( float ) ( brx + ( d / 10 ) * ( i + 1 ) );
								float fry = mi * ( frx - brx ) + bry;
								
								applet.bezier( gn.x, gn.y, flx, fly, frx, fry, nbr.x, nbr.y );
								
								float flx2 = ( float ) ( blx - ( d / 10 ) * ( i + 1 ) );
								float fly2 = mi * ( flx2 - blx ) + bly;
								
								float frx2 = ( float ) ( brx - ( d / 10 ) * ( i + 1 ) );
								float fry2 = mi * ( frx2 - brx ) + bry;
								
								applet.bezier( gn.x, gn.y, flx2, fly2, frx2, fry2, nbr.x, nbr.y );
							}
						}
					}
					else
					{
						applet.line( gn.x, gn.y, nbr.x, nbr.y );
						
						float xm = ( gn.x + nbr.x ) / 2;
						float ym = ( gn.y + nbr.y ) / 2;
						
						float m = ( gn.y - nbr.y ) / ( gn.x - nbr.x );
						
						float mi = -1 / m;
						
						float blx = ( gn.x + xm ) / 2;
						float bly = ( gn.y + ym ) / 2;
						
						float brx = ( nbr.x + xm ) / 2;
						float bry = ( nbr.y + ym ) / 2;
						
						applet.noFill( );
						
						for ( int i = 0; i < links / 2; i++ )
						{
							if ( Math.abs( mi ) > 1 )
							{
								float flx = ( float ) ( blx + ( d / 15 ) * ( i + 1 ) / mi );
								float fly = mi * ( flx - blx ) + bly;
								
								float frx = ( float ) ( brx + ( d / 15 ) * ( i + 1 ) / mi );
								float fry = mi * ( frx - brx ) + bry;
								
								applet.bezier( gn.x, gn.y, flx, fly, frx, fry, nbr.x, nbr.y );
								
								float flx2 = ( float ) ( blx - ( d / 15 ) * ( i + 1 ) / mi );
								float fly2 = mi * ( flx2 - blx ) + bly;
								
								float frx2 = ( float ) ( brx - ( d / 15 ) * ( i + 1 ) / mi );
								float fry2 = mi * ( frx2 - brx ) + bry;
								
								applet.bezier( gn.x, gn.y, flx2, fly2, frx2, fry2, nbr.x, nbr.y );
							}
							else
							{
								float flx = ( float ) ( blx + ( d / 15 ) * ( i + 1 ) );
								float fly = mi * ( flx - blx ) + bly;
								
								float frx = ( float ) ( brx + ( d / 15 ) * ( i + 1 ) );
								float fry = mi * ( frx - brx ) + bry;
								
								applet.bezier( gn.x, gn.y, flx, fly, frx, fry, nbr.x, nbr.y );
								
								float flx2 = ( float ) ( blx - ( d / 15 ) * ( i + 1 ) );
								float fly2 = mi * ( flx2 - blx ) + bly;
								
								float frx2 = ( float ) ( brx - ( d / 15 ) * ( i + 1 ) );
								float fry2 = mi * ( frx2 - brx ) + bry;
								
								applet.bezier( gn.x, gn.y, flx2, fly2, frx2, fry2, nbr.x, nbr.y );
							}
						}
					}
				}
			
			for ( GraphNode gn : graph.values( ) )
				gn.drawNode( );
		}
	}
}
