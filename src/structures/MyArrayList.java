package structures;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import components.ArrayMarker;
import main.AlgBuilder;
import processing.core.PApplet;

public class MyArrayList extends Structure
{
	public Integer size;
	public Object elems[];
	
	int offset;
	
	public int multiplier;
	
	public boolean anim;
	
	CopyOnWriteArrayList<ArrayMarker> markers;
	
	public MyArrayList( AlgBuilder applet, float x, float y, HashMap<String, StructureMethod> methods, boolean visible,
			String name, int size ) 
	{
		super( applet, x, y, methods, visible, name );

		elems = new Object[ size ];
		
		this.size = 0;
		
		offset = 0;
		while ( ( elems.length - offset ) % 19 != 0 )
			offset++;
		
		multiplier = 2;
		
		this.anim = true;
		
		markers = new CopyOnWriteArrayList<>( );
	}
	
	@Override
	public String toString( ) 
	{
		return "AL: " + name;
	}
	
	private void sleepThread( double d )
	{
		try 
		{
			if ( !applet.jump )
				Thread.sleep( ( long ) ( applet.sliderValue * d ) );
			else
				Thread.sleep( 0 );
		}
		catch ( Exception e ) 
		{
			// TODO: handle exception
		}
	}
	
	public boolean isEmpty( )
	{
		return size == 0;
	}
	
	public int size( )
	{
		return size;
	}
	
	public void setAnim( boolean b )
	{
		anim = b;
	}
	
	private void checkSize( )
	{
		if ( size == elems.length )
		{
			Object[] old = elems;
	        elems = new Object[ multiplier * size ];
	        System.arraycopy( old, 0, elems, 0, size );
	        
	        offset = 0;
			while ( ( elems.length - offset ) % 19 != 0 )
				offset++;
			
			if ( anim )
			{
		        for ( int i = size; i < multiplier * size; i++ )
		        	markers.add( new ArrayMarker( i, 150, applet.color( 237, 28, 36 ) ) );
			        
		        sleepThread( 0.8 );
		        
		        for ( int i = size; i < multiplier * size; i++ )
		        	markers.remove( new ArrayMarker( i, 150, applet.color( 237, 28, 36 ) ) );
			}
        }
		markers = new CopyOnWriteArrayList<>( );
	}
	
	public void add( Object elem )
	{
		checkSize( );
		add( size, elem );
	}
	
	public void add( int index, Object elem )
	{
		checkSize( );
		
		if ( anim )
		{		
			markers.add( new ArrayMarker( index, 180, applet.color( 65, 187, 23 ) ) );
		}
		
		for ( int i = size - 1; i >= index; i-- )
		{
			if ( anim )
			{				
				markers.add( new ArrayMarker( i, 140, applet.color( 237, 28, 36 ) ) );
				markers.add( new ArrayMarker( i + 1, 140, applet.color( 237, 28, 36 ) ) );
				
				sleepThread( 1.2 );
				
				markers.remove( new ArrayMarker( i, 90, applet.color( 237, 28, 36 ) ) );
				markers.remove( new ArrayMarker( i + 1, 90, applet.color( 237, 28, 36 ) ) );
			}
			
	        elems[ i + 1 ] = elems[ i ];
		}

		elems[ index ] = elem;
		size++;
		
		if ( anim )
		{
			sleepThread( 0.8 );
			
			markers.remove( new ArrayMarker( index, 90, applet.color( 65, 187, 23 ) ) );
		}
		
		markers = new CopyOnWriteArrayList<>( );
	}
	
	public Object get( int index )
	{
		return elems[ index ];
	}
	
	public boolean contains( Object o )
	{
		for ( int i = 0; i < size; i++ )
			if ( elems[ i ].equals( o ) )
				return true;
		
		return false;
	}
	
	public void setMultiplier( int m )
	{
		multiplier = m;
	}
	
	public void remove( int index )
	{
		if ( anim )
		{		
			markers.add( new ArrayMarker( index, 180, applet.color( 65, 187, 23 ) ) );
		}
		
		for( int i = index + 1; i < size; i++ )
		{
			if ( anim )
			{				
				markers.add( new ArrayMarker( i, 140, applet.color( 237, 28, 36 ) ) );
				markers.add( new ArrayMarker( i + 1, 140, applet.color( 237, 28, 36 ) ) );
				
				sleepThread( 0.7 );
				
				markers.remove( new ArrayMarker( i, 90, applet.color( 237, 28, 36 ) ) );
				markers.remove( new ArrayMarker( i + 1, 90, applet.color( 237, 28, 36 ) ) );
			}
			
			elems[ i - 1 ] = elems[ i ];
		}
		
		if ( anim )
		{
			sleepThread( 0.8 );
			
			markers.remove( new ArrayMarker( index, 90, applet.color( 65, 187, 23 ) ) );
		}
		
		elems[ --size ] = null;
		
		markers = new CopyOnWriteArrayList<>( );
	}

	@Override
	public void drawStructure( )
	{
		if ( visible )
		{			
			applet.stroke( 0 );
			for ( ArrayMarker am : markers )
			{
				float yMar = ( int ) ( am.pos / 19 );
				float xMar = am.pos - 19 * yMar;
				
				applet.fill( am.color, am.alpha );
				applet.rect( x + 30 * xMar, y + 70 * yMar, 30, 30 );
			}
			
			for ( int i = 0; i < size; i++ )
			{
				float yMar = ( int ) ( i / 19 );
				float xMar = i - 19 * yMar;
				
				applet.fill( applet.color( 0, 162, 232 ), 35 );
				applet.rect( x + 30 * xMar, y + 70 * yMar, 30, 30 );
			}
			
			if ( elems.length <= 19 )
			{
				for ( int i = 0; i < elems.length; i++ )
				{
					applet.textSize( 15 );
					applet.noFill( );
					applet.rect( x + 30 * i, y, 30, 30 );
					applet.textAlign( PApplet.CENTER );
					applet.fill( 0 );
					applet.text( i, ( x + 15 ) + 30 * i, y + 45 );
					
					if ( elems[ i ] != null )
					{
						String s = elems[ i ].toString( );
						if ( s.length( ) > 3 )
							s = s.substring( 0, 3 ) + ".";
						
						applet.textSize( 13 );
						applet.text( s, ( x + 15 ) + 30 * i, y + 20 );
					}
					else
					{
						applet.textSize( 10 );
						applet.text( "null", ( x + 15 ) + 30 * i, y + 20 );
					}
				}
			}
			else
			{
				int lim = elems.length / 19;
				
				for ( int i = 0; i < lim; i++ )
				{
					int cnt = 0;
					for ( int j = i * 19; j < i * 19 + 19; j++ )
					{
						applet.textSize( 15 );
						applet.noFill( );
						applet.rect( x + 30 * cnt, y + 70 * i, 30, 30 );
						applet.textAlign( PApplet.CENTER );
						applet.fill( 0 );
						applet.text( j, ( x + 15 ) + 30 * cnt, y + 70 * i + 45 );
						
						if ( elems[ j ] != null )
						{
							String s = elems[ j ].toString( );
							if ( s.length( ) > 3 )
								s = s.substring( 0, 3 ) + ".";
							
							applet.textSize( 13 );
							applet.text( s, ( x + 15 ) + 30 * cnt, y + 70 * i + 20 );
						}
						else
						{
							applet.textSize( 10 );
							applet.text( "null", ( x + 15 ) + 30 * cnt, y + 70 * i + 20 );
						}
						cnt++;
					}
				}
				
				for ( int i = elems.length - offset, cnt = 0; i < elems.length; i++, cnt++ )
				{
					applet.textSize( 15 );
					applet.noFill( );
					applet.rect( x + 30 * cnt, y + 70 * lim, 30, 30 );
					applet.textAlign( PApplet.CENTER );
					applet.fill( 0 );
					applet.text( i, ( x + 15 ) + 30 * cnt, y + 70 * lim + 45 );
					
					if ( elems[ i ] != null )
					{
						String s = elems[ i ].toString( );
						if ( s.length( ) > 3 )
							s = s.substring( 0, 3 ) + ".";
						
						applet.textSize( 13 );
						applet.text( s, ( x + 15 ) + 30 * cnt, y + 70 * lim + 20 );
					}
					else
					{
						applet.textSize( 10 );
						applet.text( "null", ( x + 15 ) + 30 * cnt, y + 70 * lim + 20 );
					}
				}
			}
			
			float yMar = ( int ) ( size / 19 );
			float xMar = size - 19 * yMar;
			
			applet.fill( 0 );
			applet.textAlign( PApplet.CENTER );
			applet.text( "size", x + 30 * xMar + 15, y + 70 * yMar - 6 );
		}
	}	
}
