package components;


import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

import controlP5.Button;
import main.AlgBuilder;
import processing.core.PConstants;
import processing.core.PFont;

public class TextArea 
{
	private final int MAX_LINES = 23; 
	
	public float x;
	public float y;
	public int width;
	public int height;
	
	public float yMarker;
	public boolean markerVisible;
	
	public AlgBuilder applet;
	public PFont font;
	
	public int lineUpperBound = 0;
	public int lineLowerBound;
	
	public int markerOffset = 0;
	public int numberOffset = 0;
	
	public String[] lines;
	public CopyOnWriteArrayList<String> printedLines = new CopyOnWriteArrayList<>( );
	
	public int consoleLineUpperBound = 0;
	public int consoleLineLowerBound = printedLines.size( );
	
	public Button upArrow, upConsoleArrow;
	public Button downArrow, downConsoleArrow;
	
	public TreeSet<Integer> breakpoints;
	boolean addingBreakPoint = false;
	
	public TextArea( AlgBuilder applet, float x, float y, int width, int height ) 
	{
		this.applet = applet;
		this.x = x;
		this.y = y;
		this.yMarker = y + 1;
		this.width = width;
		this.height = height;
		this.font = this.applet.createFont( "Consolas", 15 );
		
		markerVisible = false;
		
		upArrow = applet.cp5.addButton( "upArrow" )
				.setSize( 20, 20 ).setPosition( x + width - 20, y + 1 )
				.setImages( applet.loadImage( AlgBuilder.IMAGE_FOLDER + "upArrow.png" ), 
						applet.loadImage( AlgBuilder.IMAGE_FOLDER + "upArrow.png" ), 
						applet.loadImage( AlgBuilder.IMAGE_FOLDER + "upArrowPressed.png" ) )
				;
		
		downArrow = applet.cp5.addButton( "downArrow" )
				.setSize( 20, 20 ).setPosition( x + width - 20, y + height - 20 )
				.setImages( applet.loadImage( AlgBuilder.IMAGE_FOLDER + "downArrow.png" ), 
						applet.loadImage( AlgBuilder.IMAGE_FOLDER + "downArrow.png" ), 
						applet.loadImage( AlgBuilder.IMAGE_FOLDER + "downArrowPressed.png" ) )
				;
		
		upConsoleArrow = applet.cp5.addButton( "upConsoleArrow" )
				.setSize( 20, 20 ).setPosition( x + width - 20, y + height + 30 + 1 )
				.setImages( applet.loadImage( AlgBuilder.IMAGE_FOLDER + "upArrow.png" ),
						applet.loadImage( AlgBuilder.IMAGE_FOLDER + "upArrow.png" ),
						applet.loadImage( AlgBuilder.IMAGE_FOLDER + "upArrowPressed.png" ) )
				;
		
		downConsoleArrow = applet.cp5.addButton( "downConsoleArrow" )
				.setSize( 20, 20 ).setPosition( x + width - 20, y + height + 90 )
				.setImages( applet.loadImage( AlgBuilder.IMAGE_FOLDER + "downArrow.png" ),
						applet.loadImage( AlgBuilder.IMAGE_FOLDER + "downArrow.png" ),
						applet.loadImage( AlgBuilder.IMAGE_FOLDER + "downArrowPressed.png" ) )
				;
	}
	
	public void initCode( String lines[] )
	{
		if ( lines.length > MAX_LINES )
			this.lineLowerBound = MAX_LINES;
		else
			this.lineLowerBound = lines.length;
		
		this.lines = lines;
		
		lineUpperBound = 0;
		markerOffset = 0;
		numberOffset = 0;
		
		breakpoints = new TreeSet<>( );
		markerVisible = true;
	}
	
	private int fixedPosition( int i )
	{
		return 20 * i - 4;
	}
	
	public void drawCode( )
	{
		applet.stroke( 0 );
		
		//whole block
		applet.strokeWeight( 1 );
		if ( lines != null )
			applet.fill( 255 );
		else
			applet.fill( 100 );
		applet.rect( x, y, width, height );
		
		//line number rect
		applet.fill( 255, 135, 55 );
		applet.rect( x, y, 18, height );
		
		if ( markerVisible )
		{
			//current line
			//green marker rect
			if ( yMarker + markerOffset < y + height && yMarker + 2 + markerOffset >= y )
			{
				applet.noStroke( );
				applet.fill( applet.color( 146, 235, 86 ), 100 );
				applet.rect( x + 19, yMarker + markerOffset, width - 19, 19 );
			}
			
			//green marker number rect
			if ( yMarker + markerOffset < y + height && yMarker + 2 + markerOffset >= y )
			{
				applet.noStroke( );
				applet.fill( applet.color( 146, 235, 86 ) );
				applet.rect( x + 1, yMarker + markerOffset, 17, 19 );
			}
			
			//breakpoints
			while ( addingBreakPoint ){}
			for ( Integer i : breakpoints )
			{
				int yPos = fixedPosition( i );
				
				//red marker rect
				if ( yPos + markerOffset < y + height && yPos + 2 + markerOffset >= y )
				{
					applet.noStroke( );
					applet.fill( applet.color( 241, 86, 94 ), 100 );
					applet.rect( x + 19, yPos + markerOffset, width - 19, 19 );
				}
				
				//red marker number rect
				if ( yPos + markerOffset < y + height && yPos + 2 + markerOffset >= y )
				{
					applet.noStroke( );
					applet.fill( applet.color( 241, 86, 94 ) );
					applet.rect( x + 1, yPos + markerOffset, 17, 19 );
				}
			}
		}
		
		applet.stroke( 0 );
		applet.fill( 0 );
		applet.textAlign( PConstants.CENTER );
		
		applet.textSize( 12 );
		for ( int i = ( int ) ( y + 20 ), j = lineUpperBound + 1; i <= y + height; i += 20, j++ )
		{
			if ( j >= 100 )
				applet.textSize( 9 );
			applet.text( j, x + 10, i - 5 );
			applet.line( x, i, x + 18, i );
		}
		
		applet.textSize( 20 );
		applet.textAlign( PConstants.CUSTOM );

		applet.textFont( font );		
		
		blockComment = false;
		for ( int i = lineUpperBound, pos = ( int ) y + 15; i < lineLowerBound; i++, pos += 20 )
		{
			String line = lines[ i ];
			drawLine( line, pos );
		}
		
		//console rect
		applet.fill( 255, 135, 55 );
		applet.rect( x, y + height + 10, width, 20 );
		applet.fill( 0 );
		applet.text( "Console", x + 10, y + height + 25 );
		if ( lines != null )
			applet.fill( 255 );
		else
			applet.fill( 100 );
		applet.rect( x, y + height + 30, width, 80 );
		
		//console lines
		applet.fill( 0 );
		applet.textFont( font );		
		for ( int i = consoleLineUpperBound, pos = ( int ) y + height + 45; i < consoleLineLowerBound; i++, pos += 20 )
		{
			applet.text( printedLines.get( i ), x + 5, pos );
		}		
	}
	
	boolean blockComment;
	private void drawLine( String line, int pos )
	{		
		if ( line.contains( "/*" ) && line.contains( "*/" ) )
		{
			int k = line.indexOf( "/*" );
			int k2 = line.indexOf( "*/" );
			
			applet.fill( 0 );
			applet.text( line.substring( 0, k ), x + 24, pos );
			
			applet.fill( 53, 143, 88 );
			
			String offset = "";
			for ( int j = 0; j < k; j++ )
				offset += " ";
			
			applet.text( offset + line.substring( k, k2 + 2 ), x + 24, pos );
			
			offset = "";
			for ( int j = 0; j < k2 + 2; j++ )
				offset += " ";
			
			applet.fill( 0 );
			applet.text( offset + line.substring( k2 + 2, line.length( ) ), x + 24, pos );
		}
		else if ( line.contains( "/*" ) )
		{
			blockComment = true;
			
			int k = line.indexOf( "/*" );
			
			applet.fill( 0 );
			applet.text( line.substring( 0, k ), x + 24, pos );
			
			applet.fill( 53, 143, 88 );
			
			String offset = "";
			for ( int j = 0; j < k; j++ )
				offset += " ";
			
			applet.text( offset + line.substring( k, line.length( ) ), x + 24, pos );
		}
		else if ( line.contains( "*/" ) )
		{
			blockComment = false;
			
			int k = line.indexOf( "*/" );
			
			applet.fill( 53, 143, 88 );
			applet.text( line.substring( 0, k + 2 ), x + 24, pos );
			
			applet.fill( 0 );
			
			String offset = "";
			for ( int j = 0; j < k; j++ )
				offset += " ";
			
			applet.text( offset + line.substring( k + 2, line.length( ) ), x + 24, pos );
		}
		else if ( line.contains( "//" ) )
		{
			int k = line.indexOf( "//" );
			applet.fill( 0 );
			applet.text( line.substring( 0, k ), x + 24, pos );
			
			applet.fill( 90, 198, 85 );
			
			String offset = "";
			for ( int j = 0; j < k; j++ )
				offset += " ";
			
			applet.text( offset + line.substring( k, line.length( ) ), x + 24, pos );
		}
		else
		{
			if ( blockComment )
				applet.fill( 53, 143, 88 );
			else
				applet.fill( 0 );
			applet.text( line, x + 24, pos );
		}
	}
	
	public boolean mouseInCode( )
	{
		if ( applet.mouseX >= x && applet.mouseX <= x + width && applet.mouseY >= y && applet.mouseY <= y + height )
			return true;
		return false;
	}
	
	public boolean mouseInConsole( )
	{
		if ( applet.mouseX >= x && applet.mouseX <= x + width && applet.mouseY >= y + height + 30 && applet.mouseY <= y + height + 110 )
			return true;
		return false;
	}
	
	public void scrollDown( )
	{
		if ( lineLowerBound == lines.length )
			return;
		
		lineLowerBound++;
		lineUpperBound++;
		
		markerOffset -= 20;
		numberOffset++;
	}
	
	public void scrollUp( )
	{
		if ( lineUpperBound == 0 )
			return;
		
		lineLowerBound--;
		lineUpperBound--;
		
		markerOffset += 20;
		numberOffset--;
	}
	
	public boolean canScrollUp( )
	{
		if ( lineUpperBound == 0 )
			return false;
		return true;
	}
	
	public void scrollConsoleDown( )
	{
		if ( consoleLineLowerBound == printedLines.size( ) )
			return;
		
		consoleLineLowerBound++;
		consoleLineUpperBound++;
	}
	
	public boolean canScrollDown( )
	{
		if ( lineLowerBound == lines.length )
			return false;
		return true;
	}
	
	public void scrollConsoleUp( )
	{
		if ( consoleLineUpperBound == 0 )
			return;
		
		consoleLineLowerBound--;
		consoleLineUpperBound--;
	}
	
	public void restart( )
	{
		printedLines = new CopyOnWriteArrayList<>( );
		consoleLineUpperBound = 0;
		consoleLineLowerBound = printedLines.size( );
	}
	
	public boolean mouseInNumbers( float mouseX, float mouseY )
	{
		if ( mouseX >= x && mouseX <= x + 18 && mouseY >= y && mouseY <= y + height )
			return true;
		
		return false;
	}
	
	public void addBreakpoint( float mouseX, float mouseY )
	{
		addingBreakPoint = true;
		
		int pos = -1;
		for ( int i = ( int ) y, j = 1 + numberOffset; i < y + height; i += 20, j++ )
			if ( mouseY >= i && mouseY < i + 20 )
				pos = j;
		
		if ( pos != -1 )
		{
			if ( !applet.executableLines.contains( pos ) )
				while ( !applet.executableLines.contains( pos ) ) 
					pos++;
			
			if ( !breakpoints.contains( pos ) )
				breakpoints.add( pos );
			else
				breakpoints.remove( pos );
		}
		
		addingBreakPoint = false;
	}
}
