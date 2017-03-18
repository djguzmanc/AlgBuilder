package main;

import processing.core.*;
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*;
import structures.BinaryTree;
import structures.Graph;
import structures.Structure;
import utilities.MouseUtilities;
import utilities.Value;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import components.BinaryTreeNode;
import components.DropdownVariableList;
import components.GraphNode;
import components.StructureTab;
import components.TextArea;
import components.VariableViewer;
import components.VisualCanvas;
import main.AlgListener;
import main.AlgVisitor;
import antlr.AlgBuilderLanguageLexer;
import antlr.AlgBuilderLanguageParser;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import controlP5.*;
import g4p_controls.*;

@SuppressWarnings( "unused" )
public class AlgBuilder extends PApplet 
{
	public CopyOnWriteArrayList<StructureTab> mainList = new CopyOnWriteArrayList<>( );
	public ArrayList<Integer> executableLines;
	
	public static final String IMAGE_FOLDER = "imageButtons/"; 
	
	public boolean pause = true;
	
	public boolean restartAlgo = false;
	public boolean stepping = true;
	public boolean jump = false;
	
	public boolean codeError = false;
	
	public boolean sintaxError;
	
	public long passedSeconds = 0;
	
	public File file;
	public Textlabel tagLabel;
	
	public DropdownVariableList variableList;
	
	public GTimer timer;
	
	public MouseUtilities mouseUtilities;
	
	public TextArea code;
	
	public VisualCanvas visualCanvas;
	
	public ControlP5 cp5;
	
	public Toggle playButton;
	public Button restart, selectFile, stepButton, jumpButton, tagRect, openFile, reloadFile;
	public Slider slider;
	
	public int sliderValue = 1000;
	
	public AlgVisitor<Object> loader;
	public AlgListener walker;
	
	public void settings( )
	{
		size( 1300, 600 );
		//fullScreen( );
		//made a change :v
	}
	
	public void setup( )
	{	 	
		//surface.setResizable( true );
		
		mouseUtilities = new MouseUtilities( this );
		
		cp5 = new ControlP5( this );
		
		variableList = new DropdownVariableList( this, cp5, 1045, 30, 240, 30 );
		
		visualCanvas = new VisualCanvas( 640, 50, 650, 540 );
		
		selectFile = cp5.addButton( "selectAFile" ).setCaptionLabel( "Select a file" )
				.setPosition( visualCanvas.x + 1, visualCanvas.y - 45 ).setSize( 100, 40 )
				.setColorActive( color( 254, 213, 71 ) ).setColorBackground( color( 100 ) )
				.setColorForeground( color( 253, 117, 26 ) )
				;
		
		openFile = cp5.addButton( "openFile" ).setCaptionLabel( "Open current file" )
				.setPosition( visualCanvas.x + 110, visualCanvas.y - 46 ).setSize( 100, 18 ).lock( )
				.setColorActive( color( 254, 213, 71 ) ).setColorBackground( color( 100 ) )
				.setColorForeground( color( 253, 117, 26 ) )
				;
		
		reloadFile = cp5.addButton( "reloadFile" ).setCaptionLabel( "Reload current file" )
				.setPosition( visualCanvas.x + 110, visualCanvas.y - 24 ).setSize( 100, 18 ).lock( )
				.setColorActive( color( 254, 213, 71 ) ).setColorBackground( color( 100 ) )
				.setColorForeground( color( 253, 117, 26 ) )
				;
		
		PImage a = loadImage( IMAGE_FOLDER + "play.png" );
		a.resize( 40, 40 );
		PImage b = loadImage( IMAGE_FOLDER + "pause.png" );
		b.resize( 40, 40 );
		
		playButton = cp5.addToggle( "playAlgo" ).setSize( 40, 40 )
				.setPosition( 860, visualCanvas.y - 45 )
				.setImages( a, b ).lock( )
				;
		
		a = loadImage( IMAGE_FOLDER + "restart.png" );
		a.resize( 40, 40 );
		b = loadImage( IMAGE_FOLDER + "restartPressed.png" );
		b.resize( 40, 40 );
		
		restart = cp5.addButton( "restart" ).setSize( 40, 40 )
				.setPosition( 905, 5 )
				.setImages( a, a, b ).lock( )
				;
		
		a = loadImage( IMAGE_FOLDER + "step.png" );
		a.resize( 40, 40 );
		b = loadImage( IMAGE_FOLDER + "stepPressed.png" );
		b.resize( 40, 40 );
		
		stepButton = cp5.addButton( "stepButton" ).setSize( 40, 40 )
				.setPosition( 950, 5 )
				.setImages( a, a, b ).lock( )
				;
		
		a = loadImage( IMAGE_FOLDER + "jump.png" );
		a.resize( 40, 40 );
		b = loadImage( IMAGE_FOLDER + "jumpPressed.png" );
		b.resize( 40, 40 );
		
		jumpButton = cp5.addButton( "jump" ).setSize( 40, 40 )
				.setPosition( 995, 5 )
				.setImages( a, a, b ).lock( )
				;
	 	
	 	code = new TextArea( this, 10, 15, 600, 460 );
	 	
	 	slider = cp5.addSlider( "sliderValue" ).setLabel( "Delay" ).setPosition( 1045, 5 )
	 			.setRange( 1, 1500 ).setSize( 220, 20 ).setValue( 1000 ).setColorBackground( color( 100 ) )
	 			.setColorLabel( 255 ).setColorActive( color( 254, 213, 71 ) )
				.setColorForeground( color( 253, 117, 26 ) )
	 			;
	 	
	 	tagRect = cp5.addButton( "" ).setSize( 110, 20 ).setColorBackground( color( 238, 235, 106 ) ).setVisible( false );
	 	
	 	tagLabel = cp5.addTextlabel( "label2" ).setColorValue( 0 )
	 			.setFont( createFont( "Consolas", 10 ) ).setVisible( false )
	 			;
	 	
	 	timer = new GTimer( this, this, "countSeconds", 1000 );
	 	
	 	prepareExitHandler( );
	 	
	 	ellipseMode( CENTER );
	}
	
	public int tagSizeMachete( int stringLenght )
	{
		return ( int ) ( 5.6667 * stringLenght + 2.3333 );
	}
	
	public void countSeconds( GTimer timer )
	{
		passedSeconds++;
	}
	
	int currentTab = -1;
	public void addStructure( Structure st )
	{
		for ( StructureTab s : mainList )
			if ( s.structure.name.equals( st.name ) )
			{
				if ( s.structure.visible )
					st.visible = true;
				
				s.structure = st;
				
				loader.tabs--;
				return;
			}
		
		int tabs = loader.tabs - 1;
		
		if ( tabs == 0 )
			currentTab = 0;
		
		StructureTab structTab = new StructureTab( visualCanvas.x + 1 + tabs * 80, visualCanvas.y + visualCanvas.height - 29,
				80, 29, st, this, loadImage( IMAGE_FOLDER + "tab.png" ), loadImage( IMAGE_FOLDER + "tabOver.png" ), 
				loadImage( IMAGE_FOLDER + "tabPressed.png" ), loadImage( IMAGE_FOLDER + "tabSelected.png" ) );
		
		mainList.add( structTab );
	}
	
	boolean flag = true;
	
	// TODO DRAW
	public void draw( )
	{				
		if ( flag )
		{
			surface.setLocation( 30, 40 );
			flag = false;
		}
		
		background( 0 );
		
		mouseUtilities.mouseOverMainButtons( );
		
		fill( 255 );
		stroke( 255, 127, 39 );
		rect( visualCanvas.x, visualCanvas.y, visualCanvas.width, visualCanvas.height );
		
		for ( StructureTab st : mainList )
			st.drawStructure( mouseX, mouseY, mousePressed );
		
		code.drawCode( );
		variableList.paintViewers( );
		
		abort( );
	} 
	
	public void abort( )
	{
		if ( codeError )
		{
			try 
			{
				println( "Terminating Antlr thread (error)..." );
				loader.hilo.join( );
				println( "terminated correctly." );
			}
			catch ( InterruptedException e ) 
			{
				e.printStackTrace( );
			}
			
			JOptionPane.showMessageDialog( frame, "Fix your error and reload the file!", "Fix it!", JOptionPane.INFORMATION_MESSAGE );
		}
		
		codeError = false;
	}
	
	public void controlEvent( ControlEvent theEvent )
	{
		if ( theEvent.isController( ) && theEvent.getController( ) instanceof DropdownList )
		{
			DropdownList variables = ( DropdownList ) theEvent.getController( ); 
			
			String name = ( String ) variables.getItem( ( int ) variables.getValue( ) ).get( "value" );
			
			Value ob = loader.table.getData( name );
			if ( ob != null )
				variableList.addViewer( visualCanvas.x, visualCanvas.y, name, ob.object );
			else
				variableList.addViewer( visualCanvas.x, visualCanvas.y, name, "" );
		}
	}
	
	public String[] fixLines( String[] lines )
	{
	 	for ( int i = 0; i < lines.length; i++ )
	 	{
	 		String s = "";
	 		for ( int j = 0; j < lines[ i ].length( ); j++ )
	 		{
	 			if ( lines[ i ].charAt( j ) == '\t' )
	 				s += "    ";
	 			else
	 				s += lines[ i ].charAt( j );
	 		}
	 		
	 		lines[ i ] = s;
	 	}
	 	
	 	return lines;
	}
	
	public void selectAFile( )
	{
		selectInput( "Select your code file", "fileSelected" );
	}
	
	public void fileSelected( File selection )
	{
		if ( selection == null )
		   {
		      println( "Window was closed or the user hit cancel." );
		   } else 
		   {
				file = selection;
				
				if ( !selection.getName( ).endsWith( "algb" ) )
				{
					JOptionPane.showMessageDialog( this.frame, "Ups, we cannot read that file :(" );
					selection = null;
					return;
				}
				
				surface.setTitle( "AlgBuilder: " + selection.getName( ).substring( 0, selection.getName( ).length( ) - 5 ) );
				
				playButton.unlock( );
				restart.unlock( );
				stepButton.unlock( );
				jumpButton.unlock( );
				openFile.unlock( );
				reloadFile.unlock( );
				variableList.dropList.unlock( );
				
			 	code.initCode( fixLines( loadStrings( file ) ) );
				  
				ANTLRInputStream input = null;
				try 
				{
					input = new ANTLRInputStream( new FileInputStream( file ) );
				}
				catch ( IOException e ) 
				{
					e.printStackTrace( );
				}
				
				AlgBuilderLanguageLexer lexer = new AlgBuilderLanguageLexer( input );
				CommonTokenStream tokens = new CommonTokenStream( lexer );
				AlgBuilderLanguageParser parser = new AlgBuilderLanguageParser( tokens );
				ParseTree tree = parser.start( );
				
				walker = new AlgListener( );
			 	ParseTreeWalker.DEFAULT.walk( walker, tree );
			 	 
			 	if ( loader != null && loader.hilo != null )
			 	{
			 		try 
			 		{
			 			restartAlgo = true;
			 			
			 			loader.hilo.join( );
			 			
			 			loader.tabs = 0;
			 			
			 			mainList = new CopyOnWriteArrayList<>( );
			 			code.restart( );
			 			
			 			if ( playButton.getBooleanValue( ) )
			 				playButton.toggle( );
			 			
			 			stepping = pause = true;
			 			
			 			loader.updateMarker( walker.table.get( "main" ).ctx );
			 			
			 			while ( code.lineUpperBound > walker.table.get( "main" ).ctx.getStart( ).getLine( ) - 8 && code.canScrollUp( ) )
			 				code.scrollUp( );
			 			
			 			restartAlgo = false;
					}
			 		catch ( InterruptedException e )
			 		{
			 			System.err.println( "Couldn't reload new file :(." );
						e.printStackTrace( );
					}
			 	}
			 	
			 	variableList.clearList( );
			 	variableList.cleanViewers( );
			 	variableList.fixOffset( );
			 	variableList.dropList.setLabel( "Variables" );
			 	
			 	executableLines = new ArrayList<>( );
			 	LineIdentifier<Object> lineI = new LineIdentifier<>( this, executableLines, variableList );
			 	lineI.visit( tree );
			 	
			 	jump = false;
			 	
			 	loader = new AlgVisitor<Object>( walker.table, this, walker, code );
			 	loader.updateMarker( walker.table.get( "main" ).ctx );
			 	loader.start( );
			}
	}
	
	public void openFile( )
	{
		if ( file != null )
		{
			try 
			{
				Desktop.getDesktop( ).open( file );
			}
			catch ( Exception e ) 
			{
				JOptionPane.showMessageDialog( null, "We couldn't open the file :(" );
			}
		}
	}
	
	public void reloadFile( ) throws InterruptedException
	{
		file = new File( file.getAbsolutePath( ) );
		
		if ( !file.getName( ).endsWith( "algb" ) )
		{
			JOptionPane.showMessageDialog( this.frame, "Ups, we cannot read that file :(" );
			return;
		}
	 	
	 	code.initCode( fixLines( loadStrings( file ) ) );
		
		ANTLRInputStream input = null;
		try 
		{
			input = new ANTLRInputStream( new FileInputStream( file ) );
		}
		catch ( IOException e ) 
		{
			e.printStackTrace( );
		}
		
		AlgBuilderLanguageLexer lexer = new AlgBuilderLanguageLexer( input );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		AlgBuilderLanguageParser parser = new AlgBuilderLanguageParser( tokens );
		ParseTree tree = parser.start( );
		
		walker = new AlgListener( );
	 	ParseTreeWalker.DEFAULT.walk( walker, tree );
	 	 
	 	if ( loader != null && loader.hilo != null )
	 	{
	 		try 
	 		{
	 			restartAlgo = true;
	 			
	 			loader.hilo.join( );
	 			
	 			loader.tabs = 0;
	 			
	 			mainList = new CopyOnWriteArrayList<>( );
	 			code.restart( );
	 			
	 			if ( playButton.getBooleanValue( ) )
	 				playButton.toggle( );
	 			
	 			stepping = pause = true;
	 			
	 			loader.updateMarker( walker.table.get( "main" ).ctx );
	 			
	 			while ( code.lineUpperBound > walker.table.get( "main" ).ctx.getStart( ).getLine( ) - 8 && code.canScrollUp( ) )
	 				code.scrollUp( );
	 			
	 			restartAlgo = false;
			}
	 		catch ( InterruptedException e )
	 		{
	 			System.err.println( "Couldn't reload new file :(." );
				e.printStackTrace( );
			}
	 	}
	 	
	 	jump = false;
	 	
	 	variableList.clearList( );
	 	
	 	executableLines = new ArrayList<>( );
	 	LineIdentifier<Object> lineI = new LineIdentifier<>( this, executableLines, variableList );
	 	lineI.visit( tree );
	 	
	 	variableList.updateViewers( );
	 	variableList.clearViewers( );
	 	variableList.dropList.setLabel( "Variables" );
	 	
	 	loader = new AlgVisitor<Object>( walker.table, this, walker, code );	
	 	loader.updateMarker( walker.table.get( "main" ).ctx );
	 	loader.start( );
	}
	
	public void restart( ) throws InterruptedException
	{		
		restartAlgo = true;
		jump = true;
		
		loader.hilo.join( );
		
		loader.tabs = 0;
		
		mainList = new CopyOnWriteArrayList<>( );
		code.restart( );
		
		if ( playButton.getBooleanValue( ) )
			playButton.toggle( );
		
		stepping = pause = true;
		
		loader.updateMarker( walker.table.get( "main" ).ctx );
		
		while ( code.lineUpperBound > walker.table.get( "main" ).ctx.getStart( ).getLine( ) - 8 && code.canScrollUp( ) )
			code.scrollUp( );
		
		restartAlgo = false;
		jump = false;
		
		variableList.clearViewers( );
		
		loader.start( );
	}
	
	public void playAlgo( )
	{		
		stepping = pause = !pause;
		if ( pause )
			jumpButton.unlock( );
		else
			jumpButton.lock( );
	}
	
	public void jump( )
	{
		jump = true;	
	}
	
	public void stepButton( ) throws InterruptedException
	{		
		if ( playButton.getBooleanValue( ) )
			playButton.toggle( );
		
		pause = false;
		Thread.sleep( 50 );
		
		pause = true;
	}
	
	public void upArrow( )
	{
		if ( code.lines != null )
			code.scrollUp( );
	}
	
	public void downArrow( )
	{
		if ( code.lines != null )
			code.scrollDown( );
	}
	
	public void upConsoleArrow( )
	{
		if ( code.lines != null )
			code.scrollConsoleUp( );
	}
	
	public void downConsoleArrow( )
	{
		if ( code.lines != null )
			code.scrollConsoleDown( );
	}
	
	public void mouseWheel( MouseEvent event )
	{
		float e = event.getCount( );
		if ( code.lines != null )
		{
			if ( code.mouseInCode( ) )
			{
				if ( e > 0 )
					for ( int i = 0; i < e; i++ )
						code.scrollDown( );	
				else if ( e < 0 )
					for ( int i = ( int ) e; i < 0; i++ )
						code.scrollUp( );
			}
			else if ( code.mouseInConsole( ) )
			{
				if ( e > 0 )
					for ( int i = 0; i < e; i++ )
						code.scrollConsoleDown( );
				else if ( e < 0 )
					for ( int i = ( int ) e; i < 0; i++ )
						code.scrollConsoleUp( );
			}
		}
		if ( mouseX >= visualCanvas.x && mouseX <= visualCanvas.x + visualCanvas.width )
			if ( mouseY >= visualCanvas.y && mouseY <= visualCanvas.y + visualCanvas.height )
			{
				if ( e > 0 )
				{
					int next = ( currentTab + 1 ) % mainList.size( );
					
					mainList.get( currentTab ).structure.visible = false;
					mainList.get( next ).structure.visible = true;
					
					currentTab = next;
				}
				else if ( e < 0 )
				{
					mainList.get( currentTab ).structure.visible = false;
					
					currentTab--;
					if ( currentTab == -1 )
						currentTab = mainList.size( ) - 1;
					
					mainList.get( currentTab ).structure.visible = true;
				}
			}
	}
	
	VariableViewer varView;
	GraphNode gp;
	
	public void mousePressed( )
	{	
		for ( StructureTab st : mainList )
			if ( st.structure instanceof Graph )
			{
				Graph cg = ( Graph ) st.structure;
				
				for ( GraphNode n : cg.graph.values( ) )
					if ( n.mouseOver( mouseX, mouseY ) )
					{
						gp = n;
						return;
					}
			}
		
		for ( int i = variableList.list.size( ) - 1; i >= 0; i-- )
		{
			if ( variableList.list.get( i ).mouseOverRemoveButton( mouseX, mouseY ) )
			{
				varView = variableList.list.get( i );
				break;
			}
			
			if ( variableList.list.get( i ).mouseOver( mouseX, mouseY ) )
			{
				varView = variableList.list.get( i );
				return;
			}
		}
		
		variableList.list.remove( varView );
	}
	
	public void mouseDragged( )
	{
		if ( varView != null )
		{
			if ( mouseX <= visualCanvas.x + visualCanvas.width - varView.width / 2 && mouseX >= visualCanvas.x + varView.width / 2 )
				varView.reallocateX( mouseX );
			if ( mouseY <= visualCanvas.y + visualCanvas.height - varView.height && mouseY >= visualCanvas.y + varView.height )
				varView.reallocateY( mouseY );
		}
		
		if ( gp != null )
		{
			if ( mouseX <= visualCanvas.x + visualCanvas.width - 15 && mouseX >= visualCanvas.x + 15 )
				gp.reallocateX( mouseX );
			if ( mouseY <= visualCanvas.y + visualCanvas.height - 15 && mouseY >= visualCanvas.y + 15 )
				gp.reallocateY( mouseY );
		}
	}
	
	public void mouseReleased( )
	{
		varView = null;
		gp = null;
	}
	
	public void mouseClicked( )
	{
		timer.stop( );
		passedSeconds = 0;
		
		tagLabel.setVisible( false );
		tagRect.setVisible( false );
		
		if ( code.mouseInNumbers( mouseX, mouseY ) && code.lines != null )
		{
			code.addBreakpoint( mouseX, mouseY );
			return;
		}
		
		if ( !variableList.dropList.isMouseOver( ) )
			variableList.dropList.close( );
		
		StructureTab st = null;
		for ( StructureTab stt : mainList )
			if ( stt.mouseOver( mouseX, mouseY ) )
			{
				st = stt;
				break;
			}
		if ( st == null )
			return;
		
		for ( StructureTab stt : mainList )
			if ( stt == st )
				stt.structure.visible = true;
			else
				stt.structure.visible = false;
	}
	
	public void mouseMoved( )
	{
		timer.stop( );
		passedSeconds = 0;
		
		tagLabel.setVisible( false );
		tagRect.setVisible( false );
	}
	
	private void prepareExitHandler( ) 
	{
		Runtime.getRuntime( ).addShutdownHook( new Thread( new Runnable( )
		{
			public void run ( )
			{
				println( "User closed the window." );
				if ( loader != null && loader.hilo != null )
					try
					{
						println( "Terminating Antlr thread..." );
						restartAlgo = true;
						jump = true;
						loader.hilo.join( );
						println( "Thread terminated correctly." );
					} catch ( InterruptedException e )
					{
						System.err.println( "Thread not killed :( �FUCK!, eclipse doesn't like this." );
						e.printStackTrace( );
					}
			}
		} ) );
	}
	
	public void keyPressed( )
	{
		
	}
	
	public void keyReleased( )
	{
		if ( keyCode == ' ' && !playButton.isLock( ) )
			playButton.toggle( );
		else if ( keyCode == BACKSPACE )
			try {
				restart( );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		else if ( keyCode == ENTER )
			try {
				stepButton( );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		else if ( keyCode == UP )
			variableList.dropList.close( );
		else if ( keyCode == DOWN )
			variableList.dropList.open( );
		else if ( keyCode == RIGHT )
		{
			int next = ( currentTab + 1 ) % mainList.size( );
			
			mainList.get( currentTab ).structure.visible = false;
			mainList.get( next ).structure.visible = true;
			
			currentTab = next;
		}
		else if ( keyCode == LEFT )
		{
			mainList.get( currentTab ).structure.visible = false;
			
			currentTab--;
			if ( currentTab == -1 )
				currentTab = mainList.size( ) - 1;
			
			mainList.get( currentTab ).structure.visible = true;
		}
	}
	
	static public void main( String[] passedArgs )
	{
		String[] appletArgs = new String[] { "main.AlgBuilder" };
		if ( passedArgs != null ) 
		{
			PApplet.main( concat( appletArgs, passedArgs ) );
		} 
		else
		{
			PApplet.main( appletArgs );
		}
	}
}
