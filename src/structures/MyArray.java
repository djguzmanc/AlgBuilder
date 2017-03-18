package structures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import components.ArrayIdentifier;
import components.ArrayMarker;
import enums.Colors;
import enums.Type;
import main.AlgBuilder;
import processing.core.PApplet;
import utilities.Value;

public class MyArray extends Structure
{
	public Object arr[];
	public Type type;
	
	public int lenght;
	int offset;
	
	CopyOnWriteArrayList<ArrayMarker> markers;
	CopyOnWriteArrayList<ArrayIdentifier> identifiers;

	public MyArray( AlgBuilder applet, float x, float y, HashMap<String, StructureMethod> methods, boolean visible, 
				Object[] arr, Type type, String name )
	{
		super( applet, x, y, methods, visible, name );
		this.arr = arr;
		this.type = type;
		this.lenght = arr.length;
		
		markers = new CopyOnWriteArrayList<>( );
		identifiers = new CopyOnWriteArrayList<>( );
		
		offset = 0;
		
		while ( ( lenght - offset ) % 19 != 0 )
			offset++;
	}
	
	@Override
	public String toString( ) 
	{
		return "AR: " + name;
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
			
			if ( lenght <= 19 )
			{
				for ( int i = 0; i < lenght; i++ )
				{
					applet.textSize( 15 );
					applet.noFill( );
					applet.rect( x + 30 * i, y, 30, 30 );
					applet.textAlign( PApplet.CENTER );
					applet.fill( 0 );
					applet.text( i, ( x + 15 ) + 30 * i, y + 45 );
					
					if ( arr[ i ] != null )
					{
						String s = arr[ i ].toString( );
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
				int lim = lenght / 19;
				
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
						
						if ( arr[ j ] != null )
						{
							String s = arr[ j ].toString( );
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
				
				for ( int i = lenght - offset, cnt = 0; i < lenght; i++, cnt++ )
				{
					applet.textSize( 15 );
					applet.noFill( );
					applet.rect( x + 30 * cnt, y + 70 * lim, 30, 30 );
					applet.textAlign( PApplet.CENTER );
					applet.fill( 0 );
					applet.text( i, ( x + 15 ) + 30 * cnt, y + 70 * lim + 45 );
					
					if ( arr[ i ] != null )
					{
						String s = arr[ i ].toString( );
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
			
			for ( ArrayIdentifier ai : identifiers )
			{
				if ( ai.value != null )
				{
					int pos = ( int ) ai.value.object;
					
					float yMar = ( int ) ( pos / 19 );
					float xMar = pos - 19 * yMar;
					
					applet.fill( 0 );
					applet.textAlign( PApplet.CENTER );
					applet.text( ai.name, x + 30 * xMar + 15, y + 70 * yMar - 6 );
					
					applet.noFill( );
					applet.stroke( 237, 28, 36 );
					applet.rect( x + 30 * xMar, y + 70 * yMar, 30, 30 );
				}
			}
		}
	}
	
	public Object[] sort( )
	{
		Object a[] = new Object[ arr.length ];
		System.arraycopy( arr, 0, a, 0, arr.length );
		Arrays.sort( a );
		return a;
	}
	
	public void swap( int i, int j )
	{		
		markers.add( new ArrayMarker( i, 200,  applet.color( 173, 22, 27 ) ) );
		markers.add( new ArrayMarker( j, 200, applet.color( 173, 22, 27 ) ) );
		
		try 
		{
			if ( !applet.jump )
				Thread.sleep( ( long ) ( applet.sliderValue * 1.3 ) );
			else
				Thread.sleep( 0 );
		}
		catch ( InterruptedException e ) 
		{
			e.printStackTrace( );
		}
		
		Object tmp = arr[ i ];
		arr[ i ] = arr[ j ];
		arr[ j ] = tmp;
		
		removeMarker( i, applet.color( 173, 22, 27 ) );
		removeMarker( j, applet.color( 173, 22, 27 ) );
	}
	
	public void removeMarker( int i, int color )
	{
		int index = markers.indexOf( new ArrayMarker( i, 0, color ) );
		if ( index != -1 )
			markers.remove( index );
	}
	
	public void clearMarkers( )
	{
		markers = new CopyOnWriteArrayList<>( );
	}
	
	public void addOneMarker( int pos, Colors color )
	{		
		switch( color )
		{
		case BLUE:
			markers.add( new ArrayMarker( pos, 80, applet.color( 0, 162, 232 ) ) );
			break;
		case GRAY:
			markers.add( new ArrayMarker( pos, 80, applet.color( 127, 127, 127 ) ) );
			break;
		case GREEN:
			markers.add( new ArrayMarker( pos, 80, applet.color( 65, 187, 23 ) ) );
			break;
		case ORANGE:
			markers.add( new ArrayMarker( pos, 80, applet.color( 255, 127, 39 ) ) );
			break;
		case PINK:
			markers.add( new ArrayMarker( pos, 80, applet.color( 255, 174, 201 ) ) );
			break;
		case PURPLE:
			markers.add( new ArrayMarker( pos, 80, applet.color( 163, 73, 164 ) ) );
			break;
		case RED:
			markers.add( new ArrayMarker( pos, 80, applet.color( 237, 28, 36 ) ) );
			break;
		case YELLOW:
			markers.add( new ArrayMarker( pos, 80, applet.color( 255, 242, 0 ) ) );
			break;
		case BLACK:
			markers.add( new ArrayMarker( pos, 80, applet.color( 0 ) ) );
			break;
		case DEFAULT:
			markers.add( new ArrayMarker( pos, 80, applet.color( 255 ) ) );
			break;
		case WHITE:
			markers.add( new ArrayMarker( pos, 80, applet.color( 255 ) ) );
			break;		
		}
	}
	
	public void addOneAlphaMarker( int pos, int alpha, Colors color )
	{		
		switch( color )
		{
		case BLUE:
			markers.add( new ArrayMarker( pos, alpha, applet.color( 0, 162, 232 ) ) );
			break;
		case GRAY:
			markers.add( new ArrayMarker( pos, alpha, applet.color( 127, 127, 127 ) ) );
			break;
		case GREEN:
			markers.add( new ArrayMarker( pos, alpha, applet.color( 65, 187, 23 ) ) );
			break;
		case ORANGE:
			markers.add( new ArrayMarker( pos, alpha, applet.color( 255, 127, 39 ) ) );
			break;
		case PINK:
			markers.add( new ArrayMarker( pos, alpha, applet.color( 255, 174, 201 ) ) );
			break;
		case PURPLE:
			markers.add( new ArrayMarker( pos, alpha, applet.color( 163, 73, 164 ) ) );
			break;
		case RED:
			markers.add( new ArrayMarker( pos, alpha, applet.color( 237, 28, 36 ) ) );
			break;
		case YELLOW:
			markers.add( new ArrayMarker( pos, alpha, applet.color( 255, 242, 0 ) ) );
			break;
		case BLACK:
			markers.add( new ArrayMarker( pos, alpha, applet.color( 0 ) ) );
			break;
		case DEFAULT:
			markers.add( new ArrayMarker( pos, alpha, applet.color( 255 ) ) );
			break;
		case WHITE:
			markers.add( new ArrayMarker( pos, alpha, applet.color( 255 ) ) );
			break;	
		}
	}
	
	public void addMultipleMarkers( int i, int e, Colors color )
	{		
		int c = 0;
		
		switch( color )
		{
		case BLUE:
			c = applet.color( 0, 162, 232 );
			break;
		case GRAY:
			c = applet.color( 127, 127, 127 );
			break;
		case GREEN:
			c = applet.color( 65, 187, 23 );
			break;
		case ORANGE:
			c = applet.color( 255, 127, 39 );
			break;
		case PINK:
			c = applet.color( 255, 174, 201 );
			break;
		case PURPLE:
			c = applet.color( 163, 73, 164 );
			break;
		case RED:
			c = applet.color( 237, 28, 36 );
			break;
		case YELLOW:
			c = applet.color( 255, 242, 0 );
			break;
		case BLACK:
			c = applet.color( 0 );
			break;
		case DEFAULT:
			c = applet.color( 255 );
			break;
		case WHITE:
			c = applet.color( 255 );
			break;	
		}
		
		for ( int k = i; k <= e; k++ )
		{
			markers.add( new ArrayMarker( k, 80, c ) );
		}
	}
	
	public void addMultipleAlphaMarkers( int i, int e, Colors color, int alpha )
	{		
		int c = 0;
		
		switch( color )
		{
		case BLUE:
			c = applet.color( 0, 162, 232 );
			break;
		case GRAY:
			c = applet.color( 127, 127, 127 );
			break;
		case GREEN:
			c = applet.color( 65, 187, 23 );
			break;
		case ORANGE:
			c = applet.color( 255, 127, 39 );
			break;
		case PINK:
			c = applet.color( 255, 174, 201 );
			break;
		case PURPLE:
			c = applet.color( 163, 73, 164 );
			break;
		case RED:
			c = applet.color( 237, 28, 36 );
			break;
		case YELLOW:
			c = applet.color( 255, 242, 0 );
			break;
		case BLACK:
			c = applet.color( 0 );
			break;
		case DEFAULT:
			c = applet.color( 255 );
			break;
		case WHITE:
			c = applet.color( 255 );
			break;	
		}
		
		for ( int k = i; k <= e; k++ )
		{
			markers.add( new ArrayMarker( k, alpha, c ) );
		}
	}
	
	public void addFollower( String name, Value value )
	{
		if ( !identifiers.contains( new ArrayIdentifier( value, name ) ) )
			identifiers.add( new ArrayIdentifier( value, name ) );
	}
	
	public void dropFollowers( )
	{
		identifiers = new CopyOnWriteArrayList<>( );
	}
}
