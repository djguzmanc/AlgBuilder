package main;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;

import org.antlr.v4.runtime.ParserRuleContext;

import main.AlgListener;
import structures.BinaryTree;
import structures.Graph;
import structures.MyArray;
import structures.MyArrayList;
import structures.MyLinkedList;
import structures.StructureMethod;
import utilities.FunctionType;
import utilities.ScopeTable;
import utilities.Value;
import components.BinaryTreeNode;
import components.GraphNode;
import components.TextArea;
import components.VariableViewer;
import enums.ArrayListMethods;
import enums.ArrayMethods;
import enums.BTMethods;
import enums.BTNodeMethods;
import enums.Colors;
import enums.GraphMethods;
import enums.GraphNodeMethods;
import enums.LinkedListMethods;
import enums.Methods;
import enums.Type;
import antlr.AlgBuilderLanguageBaseVisitor;
import antlr.AlgBuilderLanguageParser.AssignmentContext;
import antlr.AlgBuilderLanguageParser.DeclarationContext;
import antlr.AlgBuilderLanguageParser.ExprContext;
import antlr.AlgBuilderLanguageParser.ForloopContext;
import antlr.AlgBuilderLanguageParser.IfstatementContext;
import antlr.AlgBuilderLanguageParser.PrintContext;
import antlr.AlgBuilderLanguageParser.ReturnstatementContext;
import antlr.AlgBuilderLanguageParser.WhileloopContext;

public class AlgVisitor<T> extends AlgBuilderLanguageBaseVisitor<T> implements Runnable
{
	HashMap<String, FunctionType> functions;	
	public ScopeTable table;
	
	AlgListener walkerTable;
	AlgBuilder applet;
	Thread hilo;
	TextArea code;
	
	public int tabs = 0;
	public final float defaultX;
	public final float defaultY;
	
	Object returnOb = null;
	
	public boolean stepDone = true;
	
	boolean returnSomething = false;
	
	public HashMap<String, StructureMethod> arrayMethods;
	public HashMap<String, StructureMethod> methods;
	public HashMap<String, StructureMethod> arrayListMethods;
	public HashMap<String, StructureMethod> linkedListMethods;
	public HashMap<String, StructureMethod> graphMethods;
	public HashMap<String, StructureMethod> graphNodeMethods;
	public HashMap<String, StructureMethod> bTreeMethods;
	public HashMap<String, StructureMethod> bTreeNodeMethods;

	public AlgVisitor( HashMap<String, FunctionType> functions2, AlgBuilder applet, AlgListener walkerTable, TextArea code ) 
	{
		functions = functions2;
		this.applet = applet;
		this.walkerTable = walkerTable;
		this.code = code;
		
		defaultX = applet.visualCanvas.x;
		defaultY = applet.visualCanvas.y;
		
		table = new ScopeTable( applet, applet.variableList );
		
		arrayMethods = new HashMap<>( );
		arrayMethods.put( ArrayMethods.LENGHT.getValue( ), new StructureMethod( 0, true ) );
		arrayMethods.put( ArrayMethods.SWAP.getValue( ), new StructureMethod( 2, false ) );
		arrayMethods.put( ArrayMethods.SORT.getValue( ), new StructureMethod( 0, true ) );
		arrayMethods.put( ArrayMethods.PUTMARKERAT.getValue( ), new StructureMethod( -1, false ) );
		arrayMethods.put( ArrayMethods.CLEARMARKERS.getValue( ), new StructureMethod( 0, false ) );
		arrayMethods.put( ArrayMethods.REMOVEMARKER.getValue( ), new StructureMethod( -1, false ) );
		arrayMethods.put( ArrayMethods.ADDFOLLOWER.getValue( ), new StructureMethod( 1, false ) );
		arrayMethods.put( ArrayMethods.DROPFOLLOWERS.getValue( ), new StructureMethod( 0, false ) );
		
		methods = new HashMap<>( );
		methods.put( Methods.RANDOM.getValue( ), new StructureMethod( -1, true ) );
		methods.put( Methods.ARRAYLIST.getValue( ), new StructureMethod( -1, true ) );
		methods.put( Methods.RANDOMARRAY.getValue( ), new StructureMethod( -1, true ) );
		methods.put( Methods.LINKEDLIST.getValue( ), new StructureMethod( 0, true ) );
		methods.put( Methods.GRAPH.getValue( ), new StructureMethod( 0, true ) );
		methods.put( Methods.TREE.getValue( ), new StructureMethod( 1, true ) );
		
		arrayListMethods = new HashMap<>( );
		arrayListMethods.put( ArrayListMethods.ADD.getValue( ), new StructureMethod( -1, false ) );
		arrayListMethods.put( ArrayListMethods.REMOVE.getValue( ), new StructureMethod( 1, false ) );
		arrayListMethods.put( ArrayListMethods.SIZE.getValue( ), new StructureMethod( 0, false ) );
		arrayListMethods.put( ArrayListMethods.GET.getValue( ), new StructureMethod( 1, false ) );
		arrayListMethods.put( ArrayListMethods.ISEMPTY.getValue( ), new StructureMethod( 0, false ) );
		arrayListMethods.put( ArrayListMethods.SETMULTIPLIER.getValue( ), new StructureMethod( 1, false ) );
		arrayListMethods.put( ArrayListMethods.ANIM.getValue( ), new StructureMethod( 1, false ) );
		arrayListMethods.put( ArrayListMethods.CONTAINS.getValue( ), new StructureMethod( 1, false ) );
		
		linkedListMethods = new HashMap<>( );
		linkedListMethods.put( LinkedListMethods.ADD.getValue( ), new StructureMethod( -1, false ) );
		linkedListMethods.put( LinkedListMethods.REMOVE.getValue( ), new StructureMethod( 1, false ) );
		linkedListMethods.put( LinkedListMethods.SIZE.getValue( ), new StructureMethod( 0, false ) );
		linkedListMethods.put( LinkedListMethods.GET.getValue( ), new StructureMethod( 1, false ) );
		linkedListMethods.put( LinkedListMethods.ISEMPTY.getValue( ), new StructureMethod( 0, false ) );
		linkedListMethods.put( LinkedListMethods.ANIM.getValue( ), new StructureMethod( 1, false ) );
		linkedListMethods.put( LinkedListMethods.CONTAINS.getValue( ), new StructureMethod( 1, false ) );
		
		graphMethods = new HashMap<>( );
		graphMethods.put( GraphMethods.ADDNODE.getValue( ), new StructureMethod( 1, false ) );
		graphMethods.put( GraphMethods.GETNODE.getValue( ), new StructureMethod( 1, false ) );
		graphMethods.put( GraphMethods.MAKELINK.getValue( ), new StructureMethod( 2, false ) );
		graphMethods.put( GraphMethods.REMOVELINK.getValue( ), new StructureMethod( 2, false ) );
		graphMethods.put( GraphMethods.REMOVENODE.getValue( ), new StructureMethod( 1, false ) );
		
		graphNodeMethods = new HashMap<>( );
		graphNodeMethods.put( GraphNodeMethods.SETCOLOR.getValue( ), new StructureMethod( 1, false ) );
		graphNodeMethods.put( GraphNodeMethods.VISITING.getValue( ), new StructureMethod( 1, false ) );
		graphNodeMethods.put( GraphNodeMethods.GETELEM.getValue( ), new StructureMethod( 0, false ) );
		graphNodeMethods.put( GraphNodeMethods.GETNBRS.getValue( ), new StructureMethod( 0, false ) );
		
		bTreeMethods = new HashMap<>( );
		bTreeMethods.put( BTMethods.ISEMPTY.getValue( ), new StructureMethod( 0, false ) );
		bTreeMethods.put( BTMethods.HEIGHT.getValue( ), new StructureMethod( 0, false ) );
		bTreeMethods.put( BTMethods.GETROOT.getValue( ), new StructureMethod( 0, false ) );
		bTreeMethods.put( BTMethods.SETROOT.getValue( ), new StructureMethod( 1, false ) );
		bTreeMethods.put( BTMethods.SIZE.getValue( ), new StructureMethod( 0, false ) );
		bTreeMethods.put( BTMethods.SETCOLOR.getValue( ), new StructureMethod( 1, false ) );
		
		bTreeNodeMethods = new HashMap<>( );
		bTreeNodeMethods.put( BTNodeMethods.SETCOLOR.getValue( ), new StructureMethod( 1, false ) );
		bTreeNodeMethods.put( BTNodeMethods.GETINDEX.getValue( ), new StructureMethod( 0, false ) );
		bTreeNodeMethods.put( BTNodeMethods.GETLEVEL.getValue( ), new StructureMethod( 0, false ) );
		bTreeNodeMethods.put( BTNodeMethods.SETRIGHTCHILD.getValue( ), new StructureMethod( 1, false ) );
		bTreeNodeMethods.put( BTNodeMethods.SETLEFTCHILD.getValue( ), new StructureMethod( 1, false ) );
		bTreeNodeMethods.put( BTNodeMethods.GETRIGHTCHILD.getValue( ), new StructureMethod( 0, false ) );
		bTreeNodeMethods.put( BTNodeMethods.GETLEFTCHILD.getValue( ), new StructureMethod( 0, false ) );
		bTreeNodeMethods.put( BTNodeMethods.REMOVERIGHTCHILD.getValue( ), new StructureMethod( 0, false ) );
		bTreeNodeMethods.put( BTNodeMethods.REMOVELEFTCHILD.getValue( ), new StructureMethod( 0, false ) );
		bTreeNodeMethods.put( BTNodeMethods.HASRIGHTCHILD.getValue( ), new StructureMethod( 0, false ) );
		bTreeNodeMethods.put( BTNodeMethods.HASLEFTCHILD.getValue( ), new StructureMethod( 0, false ) );
		bTreeNodeMethods.put( BTNodeMethods.HEIGHT.getValue( ), new StructureMethod( 0, false ) );
		bTreeNodeMethods.put( BTNodeMethods.VISITING.getValue( ), new StructureMethod( 1, false ) );
		bTreeNodeMethods.put( BTNodeMethods.SETVALUE.getValue( ), new StructureMethod( 1, false ) );
		bTreeNodeMethods.put( BTNodeMethods.GETELEM.getValue( ), new StructureMethod( 0, false ) );
		bTreeNodeMethods.put( BTNodeMethods.SIZE.getValue( ), new StructureMethod( 0, false ) );
	}
	
	public void start( )
	{
		hilo = new Thread( this );
		table = new ScopeTable( applet, applet.variableList );
		
		returnOb = null;
		stepDone = true;
		returnSomething = false;
		
		hilo.start( );
	}
	
	@SuppressWarnings( "static-access" )
	public void sleepThread( long millis )
	{
		if ( !applet.stepping )
			try 
			{
				hilo.sleep( millis );
			} catch ( InterruptedException e ) 
			{
				e.printStackTrace( );
			}
		else
			try 
			{
				hilo.sleep( 0 );
			} catch ( InterruptedException e ) 
			{
				e.printStackTrace( );
			}
	}
	
	@Override
	public void run( )
	{		
		_wait( -1 );
		FunctionType t = walkerTable.table.get( "main" );
		
		updateMarker( t.ctx );
		while ( code.lineUpperBound > applet.walker.table.get( "main" ).ctx.getStart( ).getLine( ) - 8 && code.canScrollUp( ) )
			code.scrollUp( );
		
		visit( t.statements );
		
		code.yMarker = 20 * applet.executableLines.get( applet.executableLines.size( ) - 1 ) - 4;
		while ( code.yMarker + code.markerOffset > code.y + code.height - 100 && code.canScrollDown( ) )
			code.scrollDown( );
	}
	
	// TODO wait
	public void _wait( int line )
	{
		if ( !hilo.isInterrupted( ) )
		{
			stepDone = true;
			if ( applet.restartAlgo )
			{
				hilo.interrupt( );
			}
			
			if ( code.breakpoints.contains( line ) )
			{
				if ( !applet.pause )
					applet.playButton.toggle( );
				
				applet.jump = false;				
			}
			
			while ( applet.pause && !applet.jump )
			{ 
				sleepThread( 1 );
				if ( applet.restartAlgo )
				{
					hilo.interrupt( );
					break;
				}
			}
		}
	}
	
	// TODO stepOn
	public void stepOn( int line )
	{
		if ( applet.stepping )
			applet.pause = true;
		
		stepDone = false;
	}
	
	// TODO error
	void showError( String error, ParserRuleContext ctx )
	{
		String message = "";
		message += "\t" + error + "\n";
		message += "At line: " + ctx.getStart( ).getLine( ) + "\n";
		
		JOptionPane.showMessageDialog( applet.frame, message, "Ups! I found an error :(", JOptionPane.ERROR_MESSAGE );
		
		applet.codeError = true;
		hilo.interrupt( );
	}
	
	// TODO updateMarker
	public void updateMarker( ParserRuleContext ctx )
	{
		code.yMarker = 20 * ctx.getStart( ).getLine( ) - 4;
		
		while ( code.yMarker + code.markerOffset > code.y + code.height - 100 && code.canScrollDown( ) )
			code.scrollDown( );
		
		while( code.yMarker + code.markerOffset < code.y )
			code.scrollUp( );
	}
	
	// TODO print
	@Override
	public T visitPrint( PrintContext ctx ) 
	{
		_wait( ctx.getStart( ).getLine( ) );
		if ( hilo.isInterrupted( ) || returnSomething )
			return null;
		updateMarker( ctx );
		
		String toPrint = "";
		 
		for ( ExprContext e : ctx.expr( ) )
		{
			T ob = visitExpr( e );
			if ( ob == null )
				return null;
			if ( ob instanceof SemanticError )
			{
				showError( ( ( SemanticError ) ob ).error, ctx );
				return null;
			}
			toPrint += ob.toString( ) + " ";
		}
		
		applet.code.printedLines.add( toPrint );
		
		if ( applet.code.printedLines.size( ) <= 4 )
			applet.code.consoleLineLowerBound = applet.code.printedLines.size( );
		else
		{
			applet.code.consoleLineLowerBound = applet.code.consoleLineUpperBound + 4;
			if ( applet.code.consoleLineUpperBound == applet.code.printedLines.size( ) - 5 )
				applet.code.scrollConsoleDown( );
		}
		
		sleepThread( applet.sliderValue );
		stepOn( ctx.getStart( ).getLine( ) );
		
		return null;
	}
	
	// TODO declaration
	@Override
	public T visitDeclaration( DeclarationContext ctx )
	{
		_wait( ctx.getStart( ).getLine( ) );
		if ( hilo.isInterrupted( ) || returnSomething )
			return null;
		updateMarker( ctx );
		
		Type type = Type.getEnum( ctx.TYPE( ).getText( ) );
		String name = ctx.ID( ).getText( );
		
		Value val = table.getData( name );
		if ( val != null )
		{
			showError( "The variable \"" + name + "\" is already defined.", ctx );
			return null;
		}
		
		Object o = applet.walker.table.get( name );
		if ( o != null )
		{
			showError( "\"" + name + "\" is a function.", ctx );
			return null;
		}
		
		T ob;
		if ( ctx.expr( ).size( ) == 0 )
		{
			ob = null;
			
			table.pushData( name, new Value( ob, type ) );
			
			CopyOnWriteArrayList<VariableViewer> list = applet.variableList.list;
			
			for ( VariableViewer vv : list )
			{
				if ( vv.variableName.equals( name ) )
				{
					vv.value = ob;
					vv.alive = true;
					break;
				}
			}
		}
		else if ( ctx.expr( ).size( ) == 1 )
		{
			ob = visitExpr( ctx.expr( 0 ) );
			if ( ob == null )
				return null;
			if ( ob instanceof SemanticError )
			{
				showError( ( ( SemanticError ) ob ).error, ctx );
				return null;
			}
			
			if ( ctx.BIZQ( ) != null )
			{				
				if ( !( ob instanceof Integer ) )
				{
					showError( "Array size must be integer.", ctx );
					return null;
				}
				
				if ( ( int ) ob < 0 )
				{ 
					showError( "Array size must be greater than 0.", ctx );
					return null;
				}
				
				if ( ( int ) ob >= 134 )
				{ 
					showError( "We're sorry, array's lenght must be lower than 134.", ctx );
					return null;
				}
				
				MyArray array = null;
				if ( tabs++ == 0 )
					array = new MyArray( applet, defaultX + 40, defaultY + 30, arrayMethods, true, new Object[ ( int ) ob ], type, name );
				else
					array = new MyArray( applet, defaultX + 40, defaultY + 30, arrayMethods, false, new Object[ ( int ) ob ], type, name );
				
				table.pushData( name, new Value( array, type ) );
		
				applet.addStructure( array );
			}
			else
			{
				switch ( type )
				{
				case INTEGER:
					if ( !( ob instanceof Integer ) && !( ob instanceof Double ) )
					{
						showError( "Data type mismatch, i was expecting a numeric value.", ctx );
						return null;
					}
					
					if ( ob instanceof Integer )
					{
						table.pushData( name, new Value( ob, type ) );
					}
					else
					{
						Integer i =  ( ( Double ) ob ).intValue( );
						table.pushData( name, new Value( i, type ) );
					}
					
					break;
				case DOUBLE:
					if ( !( ob instanceof Double ) && !( ob instanceof Integer ) )
					{
						showError( "Data type mismatch, i was expecting a numeric value.", ctx );
						return null;
					}
					
					if ( ob instanceof Double )
					{
						table.pushData( name, new Value( ob, type ) );
					}
					else
					{
						Double i =  ( ( Integer ) ob ).doubleValue( );
						table.pushData( name, new Value( i, type ) );
					}
					
					break;
				case BOOL:
					if ( !( ob instanceof Boolean ) )
					{
						showError( "Data type mismatch, i was expecting a Boolean.", ctx );
						return null;
					}
					
					table.pushData( name, new Value( ob, type ) );
					break;
				case STRING:
					if ( !( ob instanceof String ) )
					{
						showError( "Data type mismatch, i was expecting a String.", ctx );
						return null;
					}
					
					table.pushData( name, new Value( ob, type ) );
					break;
					
				case GRAPH:
					if ( !( ob instanceof Graph ) )
					{
						showError( "Data type mismatch, i was expecting a Graph.", ctx );
						return null;
					}
					
					Graph graph = ( Graph ) ob;
					
					graph.name = name;
					
					applet.addStructure( graph );
					
					table.pushData( name, new Value( ob, type ) );
					
					break;
					
				case ARRAYLIST:
					
					if ( !( ob instanceof MyArrayList ) )
					{
						showError( "Data type mismatch, i was expecting an ArrayList.", ctx );
						return null;
					}
					
					MyArrayList list = ( MyArrayList ) ob;
					
					list.name = name;
					
					applet.addStructure( list );
					
					table.pushData( name, new Value( ob, type ) );
					
					break;
					
				case ANY:
					
					table.pushData( name, new Value( ob, type ) );
					
					break;
					
				case LINKEDLIST:
					
					if ( !( ob instanceof MyLinkedList ) )
					{
						showError( "Data type mismatch, i was expecting a LinkedList.", ctx );
						return null;
					}
					
					MyLinkedList list2 = ( MyLinkedList ) ob;
					
					list2.name = name;
					
					applet.addStructure( list2 );
					
					table.pushData( name, new Value( ob, type ) );
					
					break;
				case GRAPHNODE:
					
					if ( !( ob instanceof GraphNode ) )
					{
						showError( "Data type mismatch, i was expecting a graph node.", ctx );
						return null;
					}
					
					table.pushData( name, new Value( ob, type ) );
					
					break;
					
					
				case BTREE:
					
					if ( !( ob instanceof BinaryTree ) )
					{
						showError( "Data type mismatch, i was expecting a BinaryTree.", ctx );
						return null;
					}
					
					BinaryTree bt = ( BinaryTree ) ob;
					
					bt.name = name;
					
					applet.addStructure( bt );
					
					table.pushData( name, new Value( ob, type ) );
					
					break;
					
				case BTNODE:
					
					if ( !( ob instanceof BinaryTreeNode ) )
					{
						showError( "Data type mismatch, i was expecting a binary tree node.", ctx );
						return null;
					}
					
					table.pushData( name, new Value( ob, type ) );
					
					break;
				}
				
				CopyOnWriteArrayList<VariableViewer> list = applet.variableList.list;
				
				for ( VariableViewer vv : list )
				{
					if ( vv.variableName.equals( name ) )
					{
						vv.value = ob;
						vv.alive = true;
						break;
					}
				}
			}
		}
		else
		{
			ob = visitExpr( ctx.expr( 0 ) );
			if ( ob == null )
				return null;
			if ( ob instanceof SemanticError )
			{
				showError( ( ( SemanticError ) ob ).error, ctx );
				return null;
			}
			
			if ( !( ob instanceof Integer ) )
			{
				showError( "Array size must be integer.", ctx );
				return null;
			}
			
			if ( ( int ) ob < 0 )
			{ 
				showError( "Array size must be greater than 0.", ctx );
				return null;
			}
			
			if ( ( int ) ob >= 134 )
			{ 
				showError( "We're sorry, array's lenght must be lower than 134.", ctx );
				return null;
			}
			
			if ( ctx.expr( 1 ) != null )
			{
				Object ass = visitExpr( ctx.expr( 1 ) );
				if ( ass == null )
					return null;
				if ( ass instanceof SemanticError )
				{
					showError( ( ( SemanticError ) ob ).error, ctx );
					return null;
				}
				
				if ( !( ass instanceof Object[] ) )
				{
					showError( "I was expecting an array.", ctx );
					return null;
				}
				
				Object arr[] = ( Object[] ) ass;
				
				if ( arr.length != ( int ) ob )
				{
					showError( "Array values amount don't match with given size.", ctx );
					return null;
				}
				
				switch ( type )
				{
				case ARRAYLIST:
					if ( !( arr[ 0 ] instanceof MyArrayList ) )
					{
						showError( "I was expecting array list values.", ctx );
						return null;
					}
					break;
				case BOOL:
					if ( !( arr[ 0 ] instanceof Boolean ) )
					{
						showError( "I was expecting boolean values.", ctx );
						return null;
					}
					break;
				case DOUBLE:
					if ( !( arr[ 0 ] instanceof Double ) )
					{
						showError( "I was expecting double values.", ctx );
						return null;
					}
					break;
				case GRAPH:
					if ( !( arr[ 0 ] instanceof Graph ) )
					{
						showError( "I was expecting graph values.", ctx );
						return null;
					}
					break;
				case INTEGER:
					if ( !( arr[ 0 ] instanceof Integer ) )
					{
						showError( "I was expecting integer values.", ctx );
						return null;
					}
					break;
				case STRING:
					if ( !( arr[ 0 ] instanceof String ) )
					{
						showError( "I was expecting string values.", ctx );
						return null;
					}
					break;
				case ANY:
					break;
				case LINKEDLIST:
					if ( !( arr[ 0 ] instanceof MyLinkedList ) )
					{
						showError( "I was expecting string values.", ctx );
						return null;
					}
					break;
				case GRAPHNODE:
					if ( !( arr[ 0 ] instanceof GraphNode ) )
					{
						showError( "I was expecting graph node values.", ctx );
						return null;
					}
					break;
				case BTREE:
					if ( !( arr[ 0 ] instanceof BinaryTree ) )
					{
						showError( "I was expecting binary tree values.", ctx );
						return null;
					}
					break;
				case BTNODE:
					if ( !( arr[ 0 ] instanceof BinaryTreeNode ) )
					{
						showError( "I was expecting binary tree node values.", ctx );
						return null;
					}
					break;
				}
				
				MyArray array = null;
				if ( tabs++ == 0 )
					array = new MyArray( applet, defaultX + 40, defaultY + 30, arrayMethods, true, arr, type, name );
				else
					array = new MyArray( applet, defaultX + 40, defaultY + 30, arrayMethods, false, arr, type, name );
				
				table.pushData( name, new Value( array, type ) );
		
				applet.addStructure( array );
			}
		}
		
		sleepThread( applet.sliderValue );
		stepOn( ctx.getStart( ).getLine( ) );
		
		return null;
	}
	
	// TODO assigment
	@Override
	public T visitAssignment( AssignmentContext ctx ) 
	{
		_wait( ctx.getStart( ).getLine( ) );
		if ( hilo.isInterrupted( ) || returnSomething )
			return null;
		updateMarker( ctx );
		
		String name = ctx.ID( ).getText( );
		
		Object o = applet.walker.table.get( name );
		if ( o != null )
		{
			showError( "\"" + name + "\" is a function.", ctx );
			return null;
		}
		
		Value value = table.getData( name );
		
		if ( value != null )
		{
			if ( ctx.BIZQ( ) == null )
			{
				if ( value.object instanceof MyArray )
				{
					showError( "The variable \"" + name + "\" is an array.", ctx );
					return null;
				}
				
				T ob = visitExpr( ctx.expr( 0 ) );
				if ( ob == null )
					return null;
				if ( ob instanceof SemanticError )
				{
					showError( ( ( SemanticError ) ob ).error, ctx );
					return null;
				}
				
				switch ( value.type )
				{
				case INTEGER:
					if ( !( ob instanceof Integer ) && !( ob instanceof Double ) )
					{
						showError( "Data type mismatch, i was expecting numeric values.", ctx );
						return null;
					}
					
					if ( ob instanceof Integer )
					{
						value.object = ob;
					}
					else
					{
						Integer i =  ( ( Double ) ob ).intValue( );
						value.object = i;
					}
					
					break;
				case DOUBLE:
					if ( !( ob instanceof Double ) )
					{
						showError( "Data type mismatch, i was expecting a Double.", ctx );
						return null;
					}
					
					if ( ob instanceof Double )
					{
						value.object = ob;
					}
					else
					{
						Double i =  ( ( Integer ) ob ).doubleValue( );
						value.object = i;
					}
					break;
				case BOOL:
					if ( !( ob instanceof Boolean ) )
					{
						showError( "Data type mismatch, i was expecting a Boolean.", ctx );
						return null;
					}
					value.object = ob;
					break;
				case STRING:
					if ( !( ob instanceof String ) )
					{
						showError( "Data type mismatch, i was expecting a String.", ctx );
						return null;
					}
					value.object = ob;
					break;
				case GRAPH:
					
					if ( !( ob instanceof Graph ) )
					{
						showError( "Data type mismatch, i was expecting a Graph.", ctx );
						return null;
					}
					
					Graph graph = ( Graph ) ob;
					
					graph.name = name;
					
					applet.addStructure( graph );
					value.object = ob;
					
					break;
				case ARRAYLIST:
					
					if ( !( ob instanceof MyArrayList ) )
					{
						showError( "Data type mismatch, i was expecting an ArrayList.", ctx );
						return null;
					}
					
					MyArrayList list = ( MyArrayList ) ob;
					
					list.name = name;
					
					applet.addStructure( list );
					
					value.object = ob;
					
					break;
				case ANY:
					value.object = ob;
					break;
				case LINKEDLIST:
					if ( !( ob instanceof MyLinkedList ) )
					{
						showError( "Data type mismatch, i was expecting an ArrayList.", ctx );
						return null;
					}
					
					MyLinkedList list2 = ( MyLinkedList ) ob;
					
					list2.name = name;
					
					applet.addStructure( list2 );
					value.object = ob;
					break;
				case GRAPHNODE:
					
					if ( !( ob instanceof GraphNode ) )
					{
						showError( "Data type mismatch, i was expecting a graph node.", ctx );
						return null;
					}
					value.object = ob;
					break;
					
				case BTREE:
					
					if ( !( ob instanceof BinaryTree ) )
					{
						showError( "Data type mismatch, i was expecting a BinaryTree.", ctx );
						return null;
					}
					
					BinaryTree bt = ( BinaryTree ) ob;
					
					bt.name = name;
					
					applet.addStructure( bt );
					value.object = ob;
					
					break;
					
				case BTNODE:
					if ( !( ob instanceof BinaryTreeNode ) )
					{
						showError( "Data type mismatch, i was expecting a BinaryTreeNode.", ctx );
						return null;
					}
					value.object = ob;
					break;
				}
				
				CopyOnWriteArrayList<VariableViewer> list = applet.variableList.list;
				for ( VariableViewer vv : list )
				{
					if ( vv.variableName.equals( ctx.ID( ).getText( ) ) )
					{
						vv.value = ob;
						break;
					}
				}
			}
			else
			{
				T index = visitExpr( ctx.expr( 0 ) );
				if ( index == null )
					return null;
				if ( index instanceof SemanticError )
				{
					showError( ( ( SemanticError ) index ).error, ctx );
					return null;
				}
				
				if ( !( index instanceof Integer ) )
				{
					showError( "Array index must be Integer.", ctx );
					return null;
				}
				
				T ob = visitExpr( ctx.expr( 1 ) );
				if ( ob == null )
					return null;
				if ( ob instanceof SemanticError )
				{
					showError( ( ( SemanticError ) ob ).error, ctx );
					return null;
				}
				
				switch ( value.type )
				{
				case INTEGER:
					if ( !( ob instanceof Integer ) )
					{
						showError( "Data type mismatch, i was expecting an Integer.", ctx );
						return null;
					}
					break;
				case DOUBLE:
					if ( !( ob instanceof Double ) )
					{
						showError( "Data type mismatch, i was expecting a Double.", ctx );
						return null;
					}
					break;
				case BOOL:
					if ( !( ob instanceof Boolean ) )
					{
						showError( "Data type mismatch, i was expecting a Boolean.", ctx );
						return null;
					}
					break;
				case STRING:
					if ( !( ob instanceof String ) )
					{
						showError( "Data type mismatch, i was expecting a String.", ctx );
						return null;
					}
					break;
				case GRAPH:
					if ( !( ob instanceof Graph ) )
					{
						showError( "Data type mismatch, i was expecting a Graph.", ctx );
						return null;
					}
					
					Graph graph = ( Graph ) ob;
					
					graph.name = name;
					
					applet.addStructure( graph );
					break;
				case ARRAYLIST:
					
					if ( !( ob instanceof MyArrayList ) )
					{
						showError( "Data type mismatch, i was expecting an ArrayList.", ctx );
						return null;
					}
					
					MyArrayList list = ( MyArrayList ) ob;
					
					list.name = name;
					
					applet.addStructure( list );
					
					break;
					
				case ANY:
					break;
				case LINKEDLIST:
					
					if ( !( ob instanceof MyArrayList ) )
					{
						showError( "Data type mismatch, i was expecting an ArrayList.", ctx );
						return null;
					}
					
					MyLinkedList list2 = ( MyLinkedList ) ob;
					
					list2.name = name;
					
					applet.addStructure( list2 );
					
					break;
				case GRAPHNODE:
					if ( !( ob instanceof GraphNode ) )
					{
						showError( "Data type mismatch, i was expecting a graph node.", ctx );
						return null;
					}
					break;
				case BTNODE:
					if ( !( ob instanceof BinaryTreeNode ) )
					{
						showError( "Data type mismatch, i was expecting a BinaryTreeNode.", ctx );
						return null;
					}
					
					break;
				case BTREE:
					if ( !( ob instanceof BinaryTree ) )
					{
						showError( "Data type mismatch, i was expecting a BinaryTree.", ctx );
						return null;
					}
					
					BinaryTree bt = ( BinaryTree ) ob;
					
					bt.name = name;
					
					applet.addStructure( bt );
					break;
				}
				
				Object t = value.object;

				if ( !( t instanceof MyArray ) )
				{
					showError( "The variable \"" + name + "\" is not an array.", ctx );
					return null;
				}
				
				MyArray arr = ( MyArray ) value.object;
				
				if ( ( int ) index < 0 || ( int ) index >= arr.arr.length )
				{
					showError( "Invalid index buddy: " + index, ctx );
					return null;
				}
				
				arr.arr[ ( Integer ) index ] = ob;
			}
		}
		else
		{
			showError( "The variable \"" + name + "\" is not declared.", ctx );
			return null;
		}
		
		sleepThread( applet.sliderValue );
		stepOn( ctx.getStart( ).getLine( ) );
		
		return null;
	}
	
	// TODO for
	@SuppressWarnings( "incomplete-switch" )
	@Override
	public T visitForloop( ForloopContext ctx ) 
	{			
		_wait( ctx.getStart( ).getLine( ) );
		if ( hilo.isInterrupted( ) || returnSomething )
			return null;
		updateMarker( ctx );
		sleepThread( applet.sliderValue );
		stepOn( ctx.getStart( ).getLine( ) );
		
		if ( ctx.TYPE( ) != null )
		{
			Type type = Type.getEnum( ctx.TYPE( ).getText( ) );
			
			String name = ctx.ID( ).getText( );
			Object o = applet.walker.table.get( name );
			if ( o != null )
			{
				showError( "\"" + name + "\" is a function.", ctx );
				return null;
			}
			
			Value test = table.getData( name );
			if ( test != null )
			{
				showError( "The variable \"" + name + "\" is already defined.", ctx );
				return null;
			}
			
			T ob = visitExpr( ctx.expr( 0 ) );
			if ( ob == null )
				return null;
			if ( ob instanceof SemanticError )
			{
				showError( ( ( SemanticError ) ob ).error, ctx );
				return null;
			}
			switch ( type )
			{
			case INTEGER:
				if ( !( ob instanceof Integer ) )
				{
					showError( "Data type mismatch, i was expecting an Integer.", ctx );
					return null;
				}
				break;
			case DOUBLE:
				if ( !( ob instanceof Double ) )
				{
					showError( "Data type mismatch, i was expecting a Double.", ctx );
					return null;
				}
				break;
			case BOOL:
				if ( !( ob instanceof Boolean ) )
				{
					showError( "Data type mismatch, i was expecting a Boolean.", ctx );
					return null;
				}
				break;
			case STRING:
				if ( !( ob instanceof String ) )
				{
					showError( "Data type mismatch, i was expecting a String.", ctx );
					return null;
				}
				break;
			}
			
			CopyOnWriteArrayList<VariableViewer> list = applet.variableList.list;
			for ( VariableViewer vv : list )
			{
				if ( vv.variableName.equals( ctx.ID( ).getText( ) ) )
				{
					vv.value = ob;
					vv.alive = true;
					break;
				}
			}
			
			Object pass = visitExpr( ctx.expr( 2 ) );
			if ( pass == null )
				return null;
			if ( pass instanceof SemanticError )
			{
				showError( ( ( SemanticError ) pass ).error, ctx );
				return null;
			}
			Type thePassType;
			
			if ( pass instanceof Integer )
				thePassType = Type.INTEGER;
			else if ( pass instanceof Double )
				thePassType = Type.DOUBLE;
			else
			{
				showError( "For update must be Integer or Double.", ctx );
				return null;
			}
			
			table.newContext( );
			table.pushData( ctx.ID( ).getText( ), new Value( ob, type ) );
			Value val = table.getData( ctx.ID( ).getText( ) );
			
			Object b = visitExpr( ctx.expr( 1 ) );
			if ( b == null )
				return null;
			if ( b instanceof SemanticError )
			{
				showError( ( ( SemanticError ) b ).error, ctx );
				return null;
			}
			if ( !( b instanceof Boolean ) )
			{
				showError( "For condition must be Boolean.", ctx );
				return null;
			}
			
			for ( ; ( boolean ) b; )
			{				
				table.newContext( );
				
				visitCommand( ctx.command( ) );
				
				switch ( val.type )
				{
				case INTEGER:
					if ( thePassType == Type.INTEGER )
						val.object = ( int ) val.object + ( int ) pass;
					else
						val.object = ( int ) val.object + ( double ) pass;
					break;
				case DOUBLE:
					if ( thePassType == Type.INTEGER )
						val.object = ( double ) val.object + ( int ) pass;
					else
						val.object = ( double ) val.object + ( double ) pass;
					break;
				}
				
				b = visitExpr( ctx.expr( 1 ) );
				if ( b == null )
					return null;
				if ( b instanceof SemanticError )
				{
					showError( ( ( SemanticError ) b ).error, ctx );
					return null;
				}
				
				pass = visitExpr( ctx.expr( 2 ) );
				if ( pass == null )
					return null;
				if ( pass instanceof SemanticError )
				{
					showError( ( ( SemanticError ) pass ).error, ctx );
					return null;
				}
				
				_wait( ctx.getStart( ).getLine( ) );
				if ( hilo.isInterrupted( ) )
					return null;
				updateMarker( ctx );
				
				CopyOnWriteArrayList<VariableViewer> list2 = applet.variableList.list;
				for ( VariableViewer vv : list2 )
				{
					if ( vv.variableName.equals( ctx.ID( ).getText( ) ) )
					{
						vv.value = val.object;
						break;
					}
				}
				
				sleepThread( applet.sliderValue );
				stepOn( ctx.getStart( ).getLine( ) );
				
				table.popContext( );
			}
			table.popContext( );
		}
		else
		{
			String name = ctx.ID( ).getText( );
			Object o = applet.walker.table.get( name );
			if ( o != null )
			{
				showError( "\"" + name + "\" is a function.", ctx );
				return null;
			}
			
			Value val = table.getData( name );
			
			if ( val == null )
			{
				showError( "The variable \"" + name + "\" is not declared.", ctx );
				return null;
			}
			
			Type type = val.type;
			
			T ob = visitExpr( ctx.expr( 0 ) );
			if ( ob == null )
				return null;
			if ( ob instanceof SemanticError )
			{
				showError( ( ( SemanticError ) ob ).error, ctx );
				return null;
			}
			
			switch ( type )
			{
			case INTEGER:
				if ( !( ob instanceof Integer ) )
				{
					showError( "Data type mismatch, i was expecting an Integer.", ctx );
					return null;
				}
				break;
			case DOUBLE:
				if ( !( ob instanceof Double ) )
				{
					showError( "Data type mismatch, i was expecting a Double.", ctx );
					return null;
				}
				break;
			case BOOL:
				if ( !( ob instanceof Boolean ) )
				{
					showError( "Data type mismatch, i was expecting a Boolean.", ctx );
					return null;
				}
				break;
			case STRING:
				if ( !( ob instanceof String ) )
				{
					showError( "Data type mismatch, i was expecting a String.", ctx );
					return null;
				}
				break;
			}
			
			val.object = ob;
			
			CopyOnWriteArrayList<VariableViewer> list = applet.variableList.list;
			for ( VariableViewer vv : list )
			{
				if ( vv.variableName.equals( ctx.ID( ).getText( ) ) )
				{
					vv.value = ob;
					vv.alive = true;
					break;
				}
			}
			
			Object pass = visitExpr( ctx.expr( 2 ) );
			if ( pass == null )
				return null;
			if ( pass instanceof SemanticError )
			{
				showError( ( ( SemanticError ) pass ).error, ctx );
				return null;
			}
			Type thePassType;
			
			if ( pass instanceof Integer )
				thePassType = Type.INTEGER;
			else if ( pass instanceof Double )
				thePassType = Type.DOUBLE;
			else
			{
				showError( "For update must be Integer or Double.", ctx );
				return null;
			}
			
			table.newContext( );
			table.pushData( ctx.ID( ).getText( ), new Value( ob, type ) );
			
			Object b = visitExpr( ctx.expr( 1 ) );
			if ( b == null )
				return null;
			if ( b instanceof SemanticError )
			{
				showError( ( ( SemanticError ) b ).error, ctx );
				return null;
			}
			if ( !( b instanceof Boolean ) )
			{
				showError( "For condition must be Boolean.", ctx );
				return null;
			}
			
			for ( ; ( boolean ) b; )
			{				
				table.newContext( );
				
				visitCommand( ctx.command( ) );
				
				switch ( val.type )
				{
				case INTEGER:
					if ( thePassType == Type.INTEGER )
						val.object = ( int ) val.object + ( int ) pass;
					else
						val.object = ( int ) val.object + ( double ) pass;
					break;
				case DOUBLE:
					if ( thePassType == Type.INTEGER )
						val.object = ( double ) val.object + ( int ) pass;
					else
						val.object = ( double ) val.object + ( double ) pass;
					break;
				}
				
				b = visitExpr( ctx.expr( 1 ) );
				if ( b == null )
					return null;
				if ( b instanceof SemanticError )
				{
					showError( ( ( SemanticError ) b ).error, ctx );
					return null;
				}
				
				pass = visitExpr( ctx.expr( 2 ) );
				if ( pass == null )
					return null;
				if ( pass instanceof SemanticError )
				{
					showError( ( ( SemanticError ) pass ).error, ctx );
					return null;
				}
				
				_wait( ctx.getStart( ).getLine( ) );
				if ( hilo.isInterrupted( ) )
					return null;
				updateMarker( ctx );
				
				CopyOnWriteArrayList<VariableViewer> list2 = applet.variableList.list;
				for ( VariableViewer vv : list2 )
				{
					if ( vv.variableName.equals( ctx.ID( ).getText( ) ) )
					{
						vv.value = val.object;
						break;
					}
				}
				
				sleepThread( applet.sliderValue );
				stepOn( ctx.getStart( ).getLine( ) );
				
				table.popContext( );
			}
			table.popContext( );
		}
		
		stepOn( ctx.getStart( ).getLine( ) );
		
		return null;
	}
	
	// TODO while
	@Override
	public T visitWhileloop( WhileloopContext ctx ) 
	{
		_wait( ctx.getStart( ).getLine( ) );
		if ( hilo.isInterrupted( ) || returnSomething )
			return null;
		updateMarker( ctx );
		sleepThread( applet.sliderValue );
		stepOn( ctx.getStart( ).getLine( ) );
		
		Object b = visitExpr( ctx.expr( ) );
		if ( b == null )
			return null;
		if ( b instanceof SemanticError )
		{
			showError( ( ( SemanticError ) b ).error, ctx );
			return null;
		}
		if ( !( b instanceof Boolean ) )
		{
			showError( "While condition must be Boolean.", ctx );
			return null;
		}
		
		while ( ( boolean ) b )
		{				
			table.newContext( );
			
			visitCommand( ctx.command( ) );
						
			b = visitExpr( ctx.expr( ) );
			if ( b == null )
				return null;
			if ( b instanceof SemanticError )
			{
				showError( ( ( SemanticError ) b ).error, ctx );
				return null;
			}
			
			_wait( ctx.getStart( ).getLine( ) );
			if ( hilo.isInterrupted( ) )
				return null;
			updateMarker( ctx );
			
			sleepThread( applet.sliderValue );
			stepOn( ctx.getStart( ).getLine( ) );
			
			table.popContext( );
		}
		
		stepOn( ctx.getStart( ).getLine( ) );
		
		return null;
	}
	
	// TODO if
	@Override
	public T visitIfstatement( IfstatementContext ctx ) 
	{
		_wait( ctx.getStart( ).getLine( ) );
		if ( hilo.isInterrupted( ) || returnSomething )
			return null;
		updateMarker( ctx );
		sleepThread( applet.sliderValue );
		stepOn( ctx.getStart( ).getLine( ) );
		
		Object b = visitExpr( ctx.expr( ) );
		if ( b == null )
			return null;
		if ( b instanceof SemanticError )
		{
			showError( ( ( SemanticError ) b ).error, ctx );
			return null;
		}
		if ( !( b instanceof Boolean ) )
		{
			showError( "If condition must be Boolean.", ctx );
			return null;
		}
		
		if ( ctx.command( ).size( ) > 1 )
		{
			if ( ( boolean ) b )
			{
				table.newContext( );
				visitCommand( ctx.command( 0 ) );
				table.popContext( );
			}
			else
			{
				table.newContext( );
				visitCommand( ctx.command( 1 ) );
				table.popContext( );
			}
		}
		else
		{
			if ( ( boolean ) b )
			{
				table.newContext( );
				visitCommand( ctx.command( 0 ) );
				table.popContext( );
			}
		}
		
		stepOn( ctx.getStart( ).getLine( ) );
		
		return null;
	}
	
	// TODO return
	@Override
	public T visitReturnstatement( ReturnstatementContext ctx )  
	{
		_wait( ctx.getStart( ).getLine( ) );
		if ( hilo.isInterrupted( ) || returnSomething )
			return null;
		updateMarker( ctx );
		
		if ( ctx.expr( ) != null )
		{
			T ob = visitExpr( ctx.expr( ) );
			if ( ob == null )
				return null;
			if ( ob instanceof SemanticError )
			{
				showError( ( ( SemanticError ) ob ).error, ctx );
				return null;
			}
			
			returnOb = ob;
		}
		else
		{
			returnOb = new SemanticError( "The function called did not return a value :(." );
		}
		
		stepOn( ctx.getStart( ).getLine( ) );
		
		returnSomething = true;
		
		return null;
	}
	
	// TODO expression
	@SuppressWarnings( "unchecked" )
	@Override
	public T visitExpr( ExprContext ctx ) 
	{		
		if ( hilo.isInterrupted( ) || ctx == null || returnSomething )
			return null;
		
		if ( ctx.SUMOP( ) != null && ctx.expr( ).size( ) == 1 )
		{
			Object o = visitExpr( ctx.expr( 0 ) );
			if ( o == null )
				return null;
			if ( o instanceof SemanticError )
			{
				showError( ( ( SemanticError ) o ).error, ctx );
				return null;
			}
			if ( !( o instanceof Integer ) && !( o instanceof Double ) )
			{
				showError( "Minus operator works only with numeric values.", ctx );
				return null;
			}
			if ( ctx.SUMOP( ).getText( ).equals( "-" ) )
			{
				if ( o instanceof Integer )
					return ( T ) ( Object ) ( -( int ) o );
				else
					return ( T ) ( Object ) ( -( double ) o );
			}
			else
			{
				if ( o instanceof Integer )
					return ( T ) ( Object ) ( ( int ) o );
				else
					return ( T ) ( Object ) ( ( double ) o );
			}
		}
		else if ( ctx.INTEGER( ) != null )
		{
			Integer i = new Integer( ctx.INTEGER( ).getText( ) );
			return ( T ) i;
		}
		if ( ctx.DOUBLE( ) != null )
		{
			Double num = new Double( ctx.DOUBLE( ).getText( ) );
			Integer tmp = (int) Math.abs( num );
			
			if ( Math.abs( num - tmp ) <= 1e-9 )
				return ( T ) tmp;
			
			return ( T ) num;
		} 
		else if ( ctx.STRING( ) != null )
		{
			String s = new String( ctx.STRING( ).getText( ) );
			return ( T ) s.substring( 1, s.length( ) - 1 );
		}
		else if ( ctx.LLIZ( ) != null )
		{
			Object e1 = visitExpr( ctx.expr( 0 ) );
			if ( e1 == null )
				return null;
			if ( e1 instanceof SemanticError )
			{
				showError( ( ( SemanticError ) e1 ).error, ctx );
				return null;
			}
			
			Type t = null;
			
			if ( e1 instanceof Integer )
				t = Type.INTEGER;
			else if ( e1 instanceof Double )
				t = Type.DOUBLE;
			if ( e1 instanceof Boolean )
				t = Type.BOOL;
			else if ( e1 instanceof String )
				t = Type.STRING;
			if ( e1 instanceof MyArrayList )
				t = Type.ARRAYLIST;
			
			if ( ctx.expr( ).size( ) == 1 )
			{
				Object arr[] = { e1 };
				return ( T ) arr;
			}
			else
			{
				Object arr[] = new Object[ ctx.expr( ).size( ) ];
				arr[ 0 ] = e1;
				for ( int i = 1; i < ctx.expr( ).size( ); i++ )
				{
					Object en = visitExpr( ctx.expr( i ) );
					if ( en == null )
						return null;
					if ( en instanceof SemanticError )
					{
						showError( ( ( SemanticError ) en ).error, ctx );
						return null;
					}
					
					switch ( t )
					{
					case ARRAYLIST:
						if ( !( en instanceof MyArrayList ) )
						{
							showError( "Expressions in array definition must be the same type.", ctx );
							return null;
						}
						break;
					case BOOL:
						if ( !( en instanceof Boolean ) )
						{
							showError( "Expressions in array definition must be the same type.", ctx );
							return null;
						}
						break;
					case DOUBLE:
						if ( !( en instanceof Double ) )
						{
							showError( "Expressions in array definition must be the same type.", ctx );
							return null;
						}
						break;
					case GRAPH:
						break;
					case INTEGER:
						if ( !( en instanceof Integer ) )
						{
							showError( "Expressions in array definition must be the same type.", ctx );
							return null;
						}
						break;
					case STRING:
						if ( !( en instanceof String ) )
						{
							showError( "Expressions in array definition must be the same type.", ctx );
							return null;
						}
						break;
					case BTREE:
						break;
					case ANY:
						break;
					case LINKEDLIST:
						if ( !( en instanceof MyLinkedList ) )
						{
							showError( "Expressions in array definition must be the same type.", ctx );
							return null;
						}
						break;
					case GRAPHNODE:
						break;
					case BTNODE:
						break;
					}
					
					arr[ i ] = en;
				}
				
				return ( T ) arr;
			}
		}
		else if ( ctx.PIZQ( ) != null )
		{
			return visitExpr( ctx.expr( 0 ) );
		}
		else if ( ctx.NOOP( ) != null )
		{
			T obj = visitExpr( ctx.expr( 0 ) );
			if ( obj == null )
				return null;
			if ( obj instanceof SemanticError )
			{
				showError( ( ( SemanticError ) obj ).error, ctx );
				return null;
			}
			if ( obj instanceof Boolean )
			{
				Boolean o = ( Boolean ) obj;
				o = !o;
				return ( T ) o;
			} 
			else
			{
				showError( "The \"!\" operator works only with boolean variables.", ctx );
				return null;
			}
		}
		else if ( ctx.BOOLEAN( ) != null )
		{
			Boolean ans = ctx.BOOLEAN( ).getText( ).toLowerCase( ).trim( ).equals( "true" ) ? true : false;
			return ( T ) ans;
		}
		if ( ctx.ID( ) != null && ctx.funcall( ) == null )
		{			
			String name = ctx.ID( ).getText( );
			Value value = table.getData( name );
			
			if ( value == null )
			{
				showError( "The variable \"" + name + "\" is not declared.", ctx );
				return null;
			}
			else if ( value.object == null )
			{
				showError( "The variable \"" + name + "\" is not initialized.", ctx );
				return null;
			}
			
			if ( ctx.BIZQ( ) != null )
			{
				Object k = visitExpr( ctx.expr( 0 ) );
				if ( k == null )
					return null;
				if ( k instanceof SemanticError )
				{
					showError( ( ( SemanticError ) k ).error, ctx );
					return null;
				}
				if ( !( k instanceof Integer ) )
				{
					showError( "Array index must be Integer.", ctx );
					return null;
				}
				
				Integer index = ( Integer ) k;
				
				if ( !( value.object instanceof MyArray ) )
				{
					showError( "The variable \"" + name + "\" is not an array.", ctx );
					return null;
				}
				
				MyArray arr = ( MyArray ) value.object;
				if ( ( int ) index < 0 || ( int ) index >= arr.arr.length )
				{
					showError( "Invalid index buddy: " + index, ctx );
					return null;
				}
				
				if ( arr.arr[ ( int ) index ] == null )
				{
					showError( "The array at position " + index + " is not initialized.", ctx );
					return null;
				}
				else
					return ( T ) arr.arr[ ( int ) index ];
			}
			else if ( ctx.objcall( ) != null )
			{
				String funcName = ctx.objcall( ).ID( ).getText( );
				
				if ( value.object instanceof MyArray )
				{					
					_wait( ctx.getStart( ).getLine( ) );
					if ( hilo.isInterrupted( ) || returnSomething )
						return null;
					updateMarker( ctx );
					
					MyArray arr = ( MyArray ) value.object;
					
					StructureMethod sm = arrayMethods.get( funcName );
					
					if ( sm == null )
					{
						showError( "Undefined method \"" + funcName + "\" for \"" + name +"\".", ctx );
						return null;
					}
					
					if ( ctx.objcall( ).expr( ).size( ) != sm.params && sm.params != -1 )
					{
						showError( "Parameters amount mismatch for function \"" + funcName + "\".", ctx );
						return null;
					}
					
					switch( ArrayMethods.getEnum( funcName ) )
					{
					case LENGHT:
						return ( T ) ( Object ) arr.lenght;
					case SORT:
						sleepThread( applet.sliderValue );
						stepOn( ctx.getStart( ).getLine( ) );
						return ( T ) arr.sort( );
					case SWAP:
						Object par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						Object par2 = visitExpr( ctx.objcall( ).expr( 1 ) );
						if ( par2 == null )
							return null;
						if ( par2 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par2 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof Integer ) || !( par2 instanceof Integer ) )
						{
							showError( "Function \"" + funcName + "\" expects Integer params.", ctx );
							return null;
						}
						
						arr.swap( ( int ) par1, ( int ) par2 );
						
						sleepThread( ( long ) ( applet.sliderValue * 0.2 ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
						
					case PUTMARKERAT:
						
						if ( ctx.objcall( ).expr( ).size( ) != 2 && ctx.objcall( ).expr( ).size( ) != 3 
								&& ctx.objcall( ).expr( ).size( ) != 4 )
						{
							showError( "Parameters amount mismatch for function \"" + funcName + "\".", ctx );
							return null;
						}
						
						if ( ctx.objcall( ).expr( ).size( ) == 2 )
						{
							par1 = visitExpr( ctx.objcall( ).expr( 0 ) );	
							if ( par1 == null )
								return null;
							if ( par1 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par1 ).error, ctx );
								return null;
							}
							par2 = visitExpr( ctx.objcall( ).expr( 1 ) );
							if ( par2 == null )
								return null;
							if ( par2 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par2 ).error, ctx );
								return null;
							}
							
							if ( !( par1 instanceof Integer ) )
							{
								showError( "First param of \"" + funcName + "\" must be an integer.", ctx );
								return null;
							}
							if ( !( par2 instanceof String ) )
							{
								showError( "Color param of \"" + funcName + "\" must be an string.", ctx );
								return null;
							}
							
							if ( ( int ) par1 < 0 || ( int ) par1 >= arr.lenght )
							{
								showError( "Invalid index buddy: " + par1, ctx );
								return null;
							}
							
							Colors c = Colors.getEnum( ( String ) par2 );
							if ( c == null )
							{
								showError( "I don't know that color :(!", ctx );
								return null;
							}
							
							arr.addOneMarker( ( int ) par1, c );
						}
						else if ( ctx.objcall( ).expr( ).size( ) == 3 )
						{
							par1 = visitExpr( ctx.objcall( ).expr( 0 ) );		
							if ( par1 == null )
								return null;
							if ( par1 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par1 ).error, ctx );
								return null;
							}
							par2 = visitExpr( ctx.objcall( ).expr( 1 ) );
							if ( par2 == null )
								return null;
							if ( par2 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par2 ).error, ctx );
								return null;
							}
							Object par3 = visitExpr( ctx.objcall( ).expr( 2 ) );
							if ( par3 == null )
								return null;
							if ( par3 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par3 ).error, ctx );
								return null;
							}
							
							if ( !( par1 instanceof Integer ) )
							{
								showError( "Function \"" + funcName + "\" expects Integer params.", ctx );
								return null;
							}
							
							if ( par2 instanceof Integer )
							{
								if ( ( int ) par1 < 0 || ( int ) par1 >= arr.lenght )
								{
									showError( "Invalid index buddy: " + par1, ctx );
									return null;
								}
								else if ( ( int ) par2 < 0 || ( int ) par2 >= arr.lenght )
								{
									showError( "Invalid index buddy: " + par2, ctx );
									return null;
								}
								
								if ( !( par3 instanceof String ) )
								{
									showError( "Color param of \"" + funcName + "\" must be an string.", ctx );
									return null;
								}
								
								Colors c = Colors.getEnum( ( String ) par3 );
								if ( c == null )
								{
									showError( "I don't know that color :(!", ctx );
									return null;
								}
								
								arr.addMultipleMarkers( ( int ) par1, ( int ) par2, c );
							}
							else if ( par2 instanceof String )
							{
								if ( !( par2 instanceof String ) )
								{
									showError( "Color param of \"" + funcName + "\" must be an string.", ctx );
									return null;
								}
								
								Colors c = Colors.getEnum( ( String ) par2 );
								if ( c == null )
								{
									showError( "I don't know that color :(!", ctx );
									return null;
								}
								
								if ( !( par3 instanceof Integer ) )
								{
									showError( "Third param of \"" + funcName + "\" must be integer.", ctx );
									return null;
								}
								if ( ( int ) par3 < 0 || ( int ) par3 > 255 )
								{
									showError( "Alpha value must be between 0 and 255: " + par3, ctx );
									return null;
								}
								
								arr.addOneAlphaMarker( ( int ) par1, ( int ) par3, c );
							}
							else
							{
								showError( "Data types for function \"" + funcName + "\" do not match.", ctx );
								return null;
							}
						}
						else
						{
							par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
							if ( par1 == null )
								return null;
							if ( par1 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par1 ).error, ctx );
								return null;
							}
							par2 = visitExpr( ctx.objcall( ).expr( 1 ) );
							if ( par2 == null )
								return null;
							if ( par2 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par2 ).error, ctx );
								return null;
							}
							Object par3 = visitExpr( ctx.objcall( ).expr( 2 ) );
							if ( par3 == null )
								return null;
							if ( par3 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par3 ).error, ctx );
								return null;
							}
							Object par4 = visitExpr( ctx.objcall( ).expr( 3 ) );
							if ( par4 == null )
								return null;
							if ( par4 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par4 ).error, ctx );
								return null;
							}
							
							if ( !( par1 instanceof Integer ) || !( par2 instanceof Integer ) || !( par4 instanceof Integer ) )
							{
								showError( "Function \"" + funcName + "\" expects Integer params.", ctx );
								return null;
							}
							
							if ( ( int ) par1 < 0 || ( int ) par1 >= arr.lenght )
							{
								showError( "Invalid index buddy: " + par1, ctx );
								return null;
							}
							else if ( ( int ) par2 < 0 || ( int ) par2 >= arr.lenght )
							{
								showError( "Invalid index buddy: " + par2, ctx );
								return null;
							}
							else if ( ( int ) par4 < 0 || ( int ) par4 > 255 )
							{
								showError( "Alpha value must be between 0 and 255: " + par4, ctx );
								return null;
							}
							
							if ( !( par3 instanceof String ) )
							{
								showError( "Color param of \"" + funcName + "\" must be an string.", ctx );
								return null;
							}
							
							Colors c = Colors.getEnum( ( String ) par3 );
							if ( c == null )
							{
								showError( "I don't know that color :(!", ctx );
								return null;
							}
							
							arr.addMultipleAlphaMarkers( ( int ) par1, ( int ) par2, c, ( int ) par4 );
						}
						
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
						
					case CLEARMARKERS:
						
						arr.clearMarkers( );
						
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
						
					case REMOVEMARKER:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						par2 = visitExpr( ctx.objcall( ).expr( 1 ) );
						if ( par2 == null )
							return null;
						if ( par2 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par2 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof Integer ) )
						{
							showError( "Function \"" + funcName + "\" expects Integer params.", ctx );
							return null;
						}
						
						if ( !( par2 instanceof String ) )
						{
							showError( "Second param of \"" + funcName + "\" must be an string.", ctx );
							return null;
						}
						
						if ( ( int ) par1 < 0 || ( int ) par1 >= arr.lenght )
						{
							showError( "Invalid index buddy: " + par1, ctx );
							return null;
						}
						
						Colors c = Colors.getEnum( ( String ) par2 );
						if ( c == null )
						{
							showError( "I don't know that color :(!", ctx );
							return null;
						}
						
						int co = 0;
						
						switch( c )
						{
						case BLUE:
							co = applet.color( 0, 162, 232 );
							break;
						case GRAY:
							co = applet.color( 127, 127, 127 );
							break;
						case GREEN:
							co = applet.color( 65, 187, 23 );
							break;
						case ORANGE:
							co = applet.color( 255, 127, 39 );
							break;
						case PINK:
							co = applet.color( 255, 174, 201 );
							break;
						case PURPLE:
							co = applet.color( 163, 73, 164 );
							break;
						case RED:
							co = applet.color( 237, 28, 36 );
							break;
						case YELLOW:
							co = applet.color( 255, 242, 0 );
							break;
						case BLACK:
							co = applet.color( 0 );
							break;
						case WHITE:
							co = applet.color( 255 );
							break;
						default:
							break;	
						}
						
						arr.removeMarker( ( int ) par1, ( int ) co ); 
						
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
						
					case ADDFOLLOWER:
						
							par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
							if ( par1 == null )
								return null;
							if ( par1 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par1 ).error, ctx );
								return null;
							}
							
							if ( !( par1 instanceof String ) )
							{
								showError( "First param of function \"" + funcName + "\" must be a string.", ctx );
								return null;
							}
							
							Value val = table.getData( ( String ) par1 );
							
							if ( val == null )
							{
								showError( "I couldn't find a variable named \"" + par1 + "\".", ctx );
								return null;
							}
							
							if ( val.object != null && !(  val.object instanceof Integer ) )
							{
								showError( "The variable \"" + par1 + "\" must contain an integer value.", ctx );
								return null;
							}
							
							arr.addFollower( ( String ) par1,  val );
						
							return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
						
					case DROPFOLLOWERS:
						
						arr.dropFollowers( );
						
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
					}
					
					return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
				}
				else if ( value.object instanceof MyArrayList )
				{
					_wait( ctx.getStart( ).getLine( ) );
					if ( hilo.isInterrupted( ) || returnSomething )
						return null;
					updateMarker( ctx );
					
					MyArrayList list = ( MyArrayList ) value.object;
					
					StructureMethod sm = arrayListMethods.get( funcName );
					
					if ( sm == null )
					{
						showError( "Undefined method \"" + funcName + "\" for \"" + name + "\".", ctx );
						return null;
					}
					
					if ( ctx.objcall( ).expr( ).size( ) != sm.params && sm.params != -1 )
					{
						showError( "Params amount for function \"" + funcName + "\" mismatch.", ctx );
						return null;
					}
					
					switch ( ArrayListMethods.getEnum( funcName ) )
					{
					case ADD:
						
						if ( ctx.objcall( ).expr( ).size( ) != 1 && ctx.objcall( ).expr( ).size( ) != 2 )
						{
							showError( "Params amount for function \"" + funcName + "\" mismatch.", ctx );
							return null;
						}
						
						if ( ctx.objcall( ).expr( ).size( ) == 1 )
						{
							Object par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
							if ( par1 == null )
								return null;
							if ( par1 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par1 ).error, ctx );
								return null;
							}
							
							list.add( par1 );
						}
						else
						{
							Object par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
							if ( par1 == null )
								return null;
							if ( par1 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par1 ).error, ctx );
								return null;
							}
							Object par2 = visitExpr( ctx.objcall( ).expr( 1 ) );
							if ( par2 == null )
								return null;
							if ( par2 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par2 ).error, ctx );
								return null;
							}
							
							if ( !( par1 instanceof Integer ) )
							{
								showError( "First param for function \"" + funcName + "\" muest be integer.", ctx );
								return null;
							}
							
							int index = ( int ) par1;
							if ( index < 0 || index > list.size )
							{
								showError( "Invalid index buddy: " + index, ctx );
								return null;
							}
							
							if ( list.size == list.elems.length && list.size * list.multiplier >= 134 )
							{
								showError( "We're sorry, your array list exceeded the maximum array size.", ctx );
								return null;
							}
							
							list.add( index, par2 );
						}
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
					case ANIM:
						
						Object par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						if ( !( par1 instanceof Boolean ) )
						{
							showError( "The function \"" + funcName + "\" expects a boolean parameter.", ctx );
							return null;
						}
						
						list.setAnim( ( boolean ) par1 ); 
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
					case GET:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						if ( !( par1 instanceof Integer ) )
						{
							showError( "The function \"" + funcName + "\" expects an integer parameter.", ctx );
							return null;
						}
						
						if ( ( int ) par1 < 0 || ( int ) par1 >= list.size( ) )
						{
							showError( "Invalid index buddy: " + par1, ctx );
							return null;
						}
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) list.get( ( int ) par1 );
					case ISEMPTY:
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) ( Object ) list.isEmpty( );
						
					case REMOVE:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						if ( !( par1 instanceof Integer ) )
						{
							showError( "The function \"" + funcName + "\" expects an integer parameter.", ctx );
							return null;
						}
						
						if ( ( int ) par1 < 0 || ( int ) par1 >= list.size( ) )
						{
							showError( "Invalid index buddy: " + par1, ctx );
							return null;
						}
						
						list.remove( ( int ) par1 );
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
						
					case SETMULTIPLIER:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof Integer ) )
						{
							showError( "The function \"" + funcName + "\" expects an integer parameter.", ctx );
							return null;
						}
						
						if ( ( int ) par1 <= 1 )
						{
							showError( "That's an invalid multiplier.", ctx );
							return null;
						}
						
						list.setMultiplier( ( int ) par1 );
						
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
					case SIZE:
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) ( Object ) list.size( );
						
					case CONTAINS:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						return ( T ) ( Object ) list.contains( par1 );
					}
					return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
				}
				else if ( value.object instanceof MyLinkedList )
				{
					_wait( ctx.getStart( ).getLine( ) );
					if ( hilo.isInterrupted( ) || returnSomething )
						return null;
					updateMarker( ctx );
					
					MyLinkedList list = ( MyLinkedList ) value.object;
					
					StructureMethod sm = linkedListMethods.get( funcName );
					
					if ( sm == null )
					{
						showError( "Undefined method \"" + funcName + "\" for \"" + name + "\".", ctx );
						return null;
					}
					
					if ( ctx.objcall( ).expr( ).size( ) != sm.params && sm.params != -1 )
					{
						showError( "Params amount for function \"" + funcName + "\" mismatch.", ctx );
						return null;
					}
					
					switch ( LinkedListMethods.getEnum( funcName ) )
					{
					case ADD:
						
						if ( ctx.objcall( ).expr( ).size( ) != 1 && ctx.objcall( ).expr( ).size( ) != 2 )
						{
							showError( "Params amount for function \"" + funcName + "\" mismatch.", ctx );
							return null;
						}
						
						if ( ctx.objcall( ).expr( ).size( ) == 1 )
						{
							Object par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
							if ( par1 == null )
								return null;
							if ( par1 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par1 ).error, ctx );
								return null;
							}
							
							list.add( par1 );
						}
						else
						{
							Object par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
							if ( par1 == null )
								return null;
							if ( par1 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par1 ).error, ctx );
								return null;
							}
							Object par2 = visitExpr( ctx.objcall( ).expr( 1 ) );
							if ( par2 == null )
								return null;
							if ( par2 instanceof SemanticError )
							{
								showError( ( ( SemanticError ) par2 ).error, ctx );
								return null;
							}
							
							if ( !( par1 instanceof Integer ) )
							{
								showError( "First param for function \"" + funcName + "\" muest be integer.", ctx );
								return null;
							}
							
							int index = ( int ) par1;
							if ( index < 0 || index > list.size( ) )
							{
								showError( "Invalid index buddy: " + index, ctx );
								return null;
							}
							
							if ( list.size == 108 )
							{
								showError( "We're sorry, your linked list exceeded the maximum linked list size.", ctx );
								return null;
							}
							
							list.add( index, par2 );
						}
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
					case ANIM:
						
						Object par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						if ( !( par1 instanceof Boolean ) )
						{
							showError( "The function \"" + funcName + "\" expects a boolean parameter.", ctx );
							return null;
						}
						
						list.setAnim( ( boolean ) par1 ); 
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
					case GET:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						if ( !( par1 instanceof Integer ) )
						{
							showError( "The function \"" + funcName + "\" expects an integer parameter.", ctx );
							return null;
						}
						
						if ( ( int ) par1 < 0 || ( int ) par1 >= list.size( ) )
						{
							showError( "Invalid index buddy: " + par1, ctx );
							return null;
						}
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) list.get( ( int ) par1 );
					case ISEMPTY:
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) ( Object ) list.isEmpty( );
						
					case REMOVE:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						if ( !( par1 instanceof Integer ) )
						{
							showError( "The function \"" + funcName + "\" expects an integer parameter.", ctx );
							return null;
						}
						
						if ( ( int ) par1 < 0 || ( int ) par1 >= list.size( ) )
						{
							showError( "Invalid index buddy: " + par1, ctx );
							return null;
						}
						
						list.remove( ( int ) par1 );
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
						
					case SIZE:
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) ( Object ) list.size( );
					case CONTAINS:
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						return ( T ) ( Object ) list.contains( par1 );
					}
					return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
				}
				else if ( value.object instanceof Graph )
				{
					_wait( ctx.getStart( ).getLine( ) );
					if ( hilo.isInterrupted( ) || returnSomething )
						return null;
					updateMarker( ctx );
					
					Graph graph = ( Graph ) value.object;
					
					StructureMethod sm = graphMethods.get( funcName );
					
					if ( sm == null )
					{
						showError( "Undefined method \"" + funcName + "\" for \"" + name + "\".", ctx );
						return null;
					}
					
					if ( ctx.objcall( ).expr( ).size( ) != sm.params && sm.params != -1 )
					{
						showError( "Params amount for function \"" + funcName + "\" mismatch.", ctx );
						return null;
					}
					
					switch ( GraphMethods.getEnum( funcName ) )
					{
					case ADDNODE:
						
						Object par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						graph.addNode( par1 );
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						break;
					case GETNODE:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						GraphNode gp = graph.getNode( par1 );
						if ( gp == null )
						{
							showError( "The graph does not contain this node: " + par1, ctx );
							return null;
						}
						
						return ( T ) gp;
				
					case MAKELINK:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						Object par2 = visitExpr( ctx.objcall( ).expr( 1 ) );
						if ( par2 == null )
							return null;
						if ( par2 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par2 ).error, ctx );
							return null;
						}
						
						graph.makeLink( par1, par2 );
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						break;
					case REMOVELINK:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						par2 = visitExpr( ctx.objcall( ).expr( 1 ) );
						if ( par2 == null )
							return null;
						if ( par2 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par2 ).error, ctx );
							return null;
						}
						
						GraphNode a = graph.getNode( par1 );
						if ( a == null )
						{
							showError( "The graph does not contain that node: " + par1, ctx );
							return null;
						}
						GraphNode b = graph.getNode( par2 );
						if ( b == null )
						{
							showError( "The graph does not contain that node: " + par2, ctx );
							return null;
						}
						
						graph.removeLink( par1, par2 );
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						break;
						
					case REMOVENODE:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						a = graph.getNode( par1 );
						if ( a == null )
						{
							showError( "The graph does not contain that node: " + par1, ctx );
							return null;
						}
						
						graph.removeNode( par1 );
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						break;
					case AUTOPLACEMENT:
						// TODO ultra challenge
						break;
					}
					
					return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
				}
				else if ( value.object instanceof GraphNode )
				{
					_wait( ctx.getStart( ).getLine( ) );
					if ( hilo.isInterrupted( ) || returnSomething )
						return null;
					updateMarker( ctx );
					
					GraphNode graphNode = ( GraphNode ) value.object;
					
					StructureMethod sm = graphNodeMethods.get( funcName );
					
					if ( sm == null )
					{
						showError( "Undefined method \"" + funcName + "\" for \"" + name + "\".", ctx );
						return null;
					}
					
					if ( ctx.objcall( ).expr( ).size( ) != sm.params && sm.params != -1 )
					{
						showError( "Params amount for function \"" + funcName + "\" mismatch.", ctx );
						return null;
					}
					
					switch ( GraphNodeMethods.getEnum( funcName ) )
					{
					case GETELEM:
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) graphNode.getElem( );
						
					case GETNBRS:
						
						ConcurrentHashMap<GraphNode, Integer> nbrs = graphNode.getNbrs( );
						
						MyArrayList list = null;
						
						if ( tabs++ == 0 )
							list = new MyArrayList( applet, defaultX + 40, defaultY + 30, arrayListMethods, true, ctx.ID( ).getText( ), 10 );
						else
							list = new MyArrayList( applet, defaultX + 40, defaultY + 30, arrayListMethods, false, ctx.ID( ).getText( ), 10 );
						
						for ( GraphNode gn : nbrs.keySet( ) )
							list.add( gn );
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) list;
						
					case SETCOLOR:
						Object par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof String ) )
						{
							showError( "The function \"" + funcName + "\" expects a string param.", ctx );
							return null;
						}
						
						Colors c = Colors.getEnum( ( String ) par1 );
						if ( c == null )
						{
							showError( "I don't know that color :(.", ctx );
							return null;
						}
						
						graphNode.setColor( c );
						
						break;
						
					case VISITING:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof Boolean ) )
						{
							showError( "The function \"" + funcName + "\" expects a boolean param.", ctx );
							return null;
						}
						
						graphNode.visiting( ( Boolean ) par1 );
						
						break;
					}
					
					return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
				}
				else if ( value.object instanceof BinaryTree )
				{
					_wait( ctx.getStart( ).getLine( ) );
					if ( hilo.isInterrupted( ) || returnSomething )
						return null;
					updateMarker( ctx );
					
					BinaryTree tree = ( BinaryTree ) value.object;
					
					StructureMethod sm = bTreeMethods.get( funcName );
					
					if ( sm == null )
					{
						showError( "Undefined method \"" + funcName + "\" for \"" + name + "\".", ctx );
						return null;
					}
					
					if ( ctx.objcall( ).expr( ).size( ) != sm.params && sm.params != -1 )
					{
						showError( "Params amount for function \"" + funcName + "\" mismatch.", ctx );
						return null;
					}
					
					switch ( BTMethods.getEnum( funcName ) )
					{
					case GETROOT:
						if ( tree.isEmpty( ) )
						{
							showError( "The tree \"" + name + "\" is not initialized.", ctx );
							return null;
						}
						return ( T ) tree.getRoot( );
					case HEIGHT:
						return ( T ) ( Object ) tree.height( );
					case ISEMPTY:
						return ( T ) ( Object ) tree.isEmpty( );
					case SETROOT:
						
						Object par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						tree.setRoot( par1 );
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						break;
						
					case SIZE:
						return ( T ) ( Object ) tree.size( );
						
					case SETCOLOR:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof String ) )
						{
							showError( "The function \"" + funcName + "\" expects a string param.", ctx );
							return null;
						}
						
						Colors c = Colors.getEnum( ( String ) par1 );
						if ( c == null )
						{
							showError( "I don't know that color :(.", ctx );
							return null;
						}
						
						tree.setColor( c );
						
						break;
					}
					
					return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
				}
				else if ( value.object instanceof BinaryTreeNode )
				{
					_wait( ctx.getStart( ).getLine( ) );
					if ( hilo.isInterrupted( ) || returnSomething )
						return null;
					updateMarker( ctx );
					
					BinaryTreeNode btn = ( BinaryTreeNode ) value.object;
					
					StructureMethod sm = bTreeNodeMethods.get( funcName );
					
					if ( sm == null )
					{
						showError( "Undefined method \"" + funcName + "\" for \"" + name + "\".", ctx );
						return null;
					}
					
					if ( ctx.objcall( ).expr( ).size( ) != sm.params && sm.params != -1 )
					{
						showError( "Params amount for function \"" + funcName + "\" mismatch.", ctx );
						return null;
					}
					
					switch ( BTNodeMethods.getEnum( funcName ) )
					{
					case GETINDEX:
						return ( T ) ( Object ) btn.getIndex( );
					case GETLEFTCHILD:
						if ( !btn.hasLeftChild( ) )
						{
							showError( "The binary tree node \"" + name + "\" has no left child.", ctx );
							return null;
						}
						
						return ( T ) btn.getLeftChild( );
					case GETLEVEL:
						return ( T ) ( Object )  btn.getLevel( );
					case GETRIGHTCHILD:
						if ( !btn.hasRightChild( ) )
						{
							showError( "The binary tree node \"" + name + "\" has no right child.", ctx );
							return null;
						}
						
						return ( T ) btn.getRightChild( );
					case HASLEFTCHILD:
						return ( T ) ( Object ) btn.hasLeftChild( );
					case HASRIGHTCHILD:
						return ( T ) ( Object ) btn.hasRightChild( );
					case HEIGHT:
						return ( T ) ( Object ) btn.height( );
					case REMOVELEFTCHILD:
						
						btn.removeLeftChild( );
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						break;
					case REMOVERIGHTCHILD:
						
						btn.removeRightChild( );
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						break;
					case SETCOLOR:
						
						Object par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof String ) )
						{
							showError( "The function \"" + funcName + "\" expects a string param.", ctx );
							return null;
						}
						
						Colors c = Colors.getEnum( ( String ) par1 );
						if ( c == null )
						{
							showError( "I don't know that color :(.", ctx );
							return null;
						}
						
						btn.setColor( c );
						
						break;
					case SETLEFTCHILD:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) btn.setLeftChild( par1 );
	
					case SETRIGHTCHILD:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						sleepThread( ( long ) ( applet.sliderValue ) );
						stepOn( ctx.getStart( ).getLine( ) );
						
						return ( T ) btn.setRightChild( par1 );
						
					case VISITING:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof Boolean ) )
						{
							showError( "The function \"" + funcName + "\" expects a boolean param.", ctx );
							return null;
						}
						
						btn.visiting( ( Boolean ) par1 );
						
						break;
					case SETVALUE:
						
						par1 = visitExpr( ctx.objcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}					
						
						btn.setValue( par1 );
						
						break;
					case GETELEM:
						
						return ( T ) btn.getElem( );
						
					case SIZE:
						
						return ( T ) ( Object ) btn.size( );
					}
					
					return ( T ) new SemanticError( "The function \"" + funcName + "\" does not return a value." );
				}
				else
				{
					showError( "Undefined method \"" + funcName + "\" for \"" + name +"\".", ctx );
					return null;
				}
			}
			else
				return ( T ) value.object;
		}
		else if ( ctx.funcall( ) != null )
		{
			_wait( ctx.getStart( ).getLine( ) );
			if ( hilo.isInterrupted( ) || returnSomething )
				return null;
			updateMarker( ctx );
			sleepThread( applet.sliderValue );
			stepOn( ctx.getStart( ).getLine( ) );
			
			String name = ctx.ID( ).getText( );
			
			FunctionType ft = applet.walker.table.get( name );
			
			StructureMethod sm = methods.get( name );
			
			if ( ft == null && sm == null )
			{
				showError( "The function \"" + name + "\" is not defined.", ctx );
				return null;
			}
			
			if ( ft != null )
			{
				table.newContext( );
				
				if ( ft.names.size( ) != ctx.funcall( ).expr( ).size( ) )
				{
					showError( "Parameters amount mismatch for function \"" + name + "\".", ctx );
					return null;
				}
				
				for ( int i = 0; i < ft.names.size( ); i++ )
				{
					T ob = visitExpr( ctx.funcall( ).expr( i ) );
					if ( ob == null )
						return null;
					if ( ob instanceof SemanticError )
					{
						showError( ( ( SemanticError ) ob ).error, ctx );
						return null;
					}
					
					Type type = null;
					if ( ob instanceof Integer )
						type = Type.INTEGER;
					else if ( ob instanceof Double )
						type = Type.DOUBLE;
					else if ( ob instanceof String )
						type = Type.STRING;
					else if ( ob instanceof Boolean )
						type = Type.BOOL;
					else if ( ob instanceof MyArray )
					{
						type = ( ( MyArray ) ob ).type;
					}
					
					table.pushData( ft.names.get( i ), new Value( ob, type ) );
				}		
				
				table.setRestrictor( table.table.size( ) - 1 );
				
				_wait( ctx.getStart( ).getLine( ) );
				if ( hilo.isInterrupted( ) || returnSomething )
					return null;
				updateMarker( ft.ctx );
				sleepThread( applet.sliderValue );
				stepOn( ctx.getStart( ).getLine( ) );
				
				visit( ft.statements );
				
				table.popContext( );
				
				stepOn( ctx.getStart( ).getLine( ) );
				
				Object theReturn = returnOb;
				
				returnOb = null;
				
				returnSomething = false;
				
				if ( theReturn != null )
					return ( T ) theReturn;
				else
					return ( T ) new SemanticError( "The function \"" + name + "\" does not return a value." );
			}
			else
			{
				if ( ctx.funcall( ).expr( ).size( ) != sm.params && sm.params != -1 )
				{
					showError( "Params amount for function \"" + name + "\" don't match.", ctx );
					return null;
				}
				
				Methods method = Methods.getEnum( name );
				switch ( method )
				{
				case RANDOM:
					
					if ( ctx.funcall( ).expr( ).size( ) != 2 && ctx.funcall( ).expr( ).size( ) != 1 )
					{
						showError( "Params amount for function \"" + name + "\" don't match.", ctx );
						return null;
					}
					
					if ( ctx.funcall( ).expr( ).size( ) == 2 )
					{
						Object par1 = visitExpr( ctx.funcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						Object par2 = visitExpr( ctx.funcall( ).expr( 1 ) );
						if ( par2 == null )
							return null;
						if ( par2 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par2 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof Integer ) )
						{
							showError( "First param of function \"" + name + "\" must be integer.", ctx );
							return null;
						}
						if ( !( par2 instanceof Integer ) )
						{
							showError( "Second param of function \"" + name + "\" must be integer.", ctx );
							return null;
						}
						
						int random = ( int ) applet.random( ( int ) par1, ( int ) par2 );
						return ( T ) ( Object ) random;
					}
					else
					{
						Object par1 = visitExpr( ctx.funcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof Integer ) )
						{
							showError( "First param of function \"" + name + "\" must be integer.", ctx );
							return null;
						}
						
						int random = ( int ) applet.random( ( int ) par1 );
						return ( T ) ( Object ) random;
					}
					
				case ARRAYLIST:
					
					if ( ctx.funcall( ).expr( ).size( ) != 1 && ctx.funcall( ).expr( ).size( ) != 0 )
					{
						showError( "Params amount for function \"" + name + "\" mismatch.", ctx );
						return null;
					}
					
					if ( ctx.funcall( ).expr( ).size( ) == 0 )
					{
						MyArrayList list = null;
						
						if ( tabs++ == 0 )
							list = new MyArrayList( applet, defaultX + 40, defaultY + 30, arrayListMethods, true, ctx.ID( ).getText( ), 10 );
						else
							list = new MyArrayList( applet, defaultX + 40, defaultY + 30, arrayListMethods, false, ctx.ID( ).getText( ), 10 );
						
						return ( T ) list;
					}
					else
					{
						Object par1 = visitExpr( ctx.funcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof Integer ) )
						{
							showError( "ArrayList size must be integer.", ctx );
							return null;
						}
						
						if ( ( int ) par1 < 0 )
						{
							showError( "ArrayList size must be greather than 0.", ctx );
							return null;
						}
						
						if ( ( int ) par1 >= 134 )
						{
							showError( "ArrayList size must be lower than 134.", ctx );
							return null;
						}
						
						MyArrayList list = null;
						
						if ( tabs++ == 0 )
							list = new MyArrayList( applet, defaultX + 40, defaultY + 30, arrayListMethods, true, ctx.ID( ).getText( ), ( int ) par1 );
						else
							list = new MyArrayList( applet, defaultX + 40, defaultY + 30, arrayListMethods, false, ctx.ID( ).getText( ), ( int ) par1 );
						
						return ( T ) list;
					}
					
				case RANDOMARRAY:
					
					if ( ctx.funcall( ).expr( ).size( ) == 2 )
					{
						Object par1 = visitExpr( ctx.funcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						Object par2 = visitExpr( ctx.funcall( ).expr( 1 ) );
						if ( par2 == null )
							return null;
						if ( par2 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par2 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof Integer ) || !( par2 instanceof Integer ) )
						{
							showError( "The function \"" + name + "\" expects integer params.", ctx );
							return null;
						}
						
						if ( ( int ) par1 <= 0 )
						{
							showError( "Array size must be greather than 0.", ctx );
							return null;
						}
						
						Object arr[] = new Object[ ( int ) par1 ];
						for ( int i = 0; i < arr.length; i++ )
							arr[ i ] = ( int ) applet.random( ( int ) par2 ); 
						
						return ( T ) arr;
					}
					else if ( ctx.funcall( ).expr( ).size( ) == 3 )
					{
						Object par1 = visitExpr( ctx.funcall( ).expr( 0 ) );
						if ( par1 == null )
							return null;
						if ( par1 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par1 ).error, ctx );
							return null;
						}
						Object par2 = visitExpr( ctx.funcall( ).expr( 1 ) );
						if ( par2 == null )
							return null;
						if ( par2 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par2 ).error, ctx );
							return null;
						}
						Object par3 = visitExpr( ctx.funcall( ).expr( 2 ) );
						if ( par3 == null )
							return null;
						if ( par3 instanceof SemanticError )
						{
							showError( ( ( SemanticError ) par3 ).error, ctx );
							return null;
						}
						
						if ( !( par1 instanceof Integer ) || !( par2 instanceof Integer ) || !( par3 instanceof Integer ) )
						{
							showError( "The function \"" + name + "\" expects integer params.", ctx );
							return null;
						}
						
						if ( ( int ) par1 <= 0 )
						{
							showError( "Array size must be greather than 0.", ctx );
							return null;
						}
						
						Object arr[] = new Object[ ( int ) par1 ];
						for ( int i = 0; i < arr.length; i++ )
							arr[ i ] = ( int ) applet.random( ( int ) par2, ( int ) par3 ); 
						
						return ( T ) arr;
					}
					else
					{
						showError( "Params amount for function \"" + name + "\" mismatch.", ctx );
						return null;
					}
				case LINKEDLIST:
					
					MyLinkedList list2 = null;
					
					if ( tabs++ == 0 )
						list2 = new MyLinkedList( applet, defaultX + 50, defaultY + 35, linkedListMethods, true, ctx.ID( ).getText( ) );
					else
						list2 = new MyLinkedList( applet, defaultX + 40, defaultY + 30, linkedListMethods, false, ctx.ID( ).getText( ) );
					
					return ( T ) list2;
				case GRAPH:
					
					Graph graph = null;
					
					if ( tabs++ == 0 )
						graph = new Graph( applet, defaultX, defaultY, graphMethods, true, name );
					else
						graph = new Graph( applet, defaultX, defaultY, graphMethods, false, name );
					
					return ( T ) graph;
				case TREE:
					
					Object par1 = visitExpr( ctx.funcall( ).expr( 0 ) );
					if ( par1 == null )
						return null;
					if ( par1 instanceof SemanticError )
					{
						showError( ( ( SemanticError ) par1 ).error, ctx );
						return null;
					}
					
					BinaryTree tree = null;
					
					if ( tabs++ == 0 )
						tree = new BinaryTree( applet, defaultX, defaultY, bTreeMethods, true, name, par1 );
					else
						tree = new BinaryTree( applet, defaultX, defaultY, bTreeMethods, false, name, par1 );
					
					return ( T ) tree;
				}
				return ( T ) new SemanticError( "The function \"" + name + "\" does not return a value." );
			}
		}
		else
		{
			String op = null;
			
			if ( ctx.OROP( ) != null )
				op = ctx.OROP( ).getText( );
			else if ( ctx.ANDOP( ) != null )
				op = ctx.ANDOP( ).getText( );
			else if ( ctx.EQOP( ) != null )
				op = ctx.EQOP( ).getText( );
			else if ( ctx.RELOP( ) != null )
				op = ctx.RELOP( ).getText( );
			else if ( ctx.SUMOP( ) != null )
				op = ctx.SUMOP( ).getText( );
			else if ( ctx.MULOP( ) != null )
				op = ctx.MULOP( ).getText( );
			else if ( ctx.POTOP( ) != null )
				op = ctx.POTOP( ).getText( );
			
			Object oper1 = visitExpr( ctx.expr( 0 ) );
			if ( oper1 == null )
				return null;
			if ( oper1 instanceof SemanticError )
			{
				showError( ( ( SemanticError ) oper1 ).error, ctx );
				return null;
			}
			Object oper2 = visitExpr( ctx.expr( 1 ) );
			if ( oper2 == null )
				return null;
			if ( oper2 instanceof SemanticError )
			{
				showError( ( ( SemanticError ) oper2 ).error, ctx );
				return null;
			}
			
			Object ans = null;
			
			switch ( op )
			{
			case "||":
				if ( !( oper1 instanceof Boolean ) || !( oper2 instanceof Boolean ) )
				{
					showError( "Operands mismatch, i was expecting a Boolean.", ctx );
					return null;
				}
				
				ans = ( Boolean ) oper1 || ( Boolean ) oper2;
				break;
				
			case "&&":
				if ( !( oper1 instanceof Boolean ) || !( oper2 instanceof Boolean ) )
				{
					showError( "Operands mismatch, i was expecting a Boolean.", ctx );
					return null;
				}
				
				ans = ( Boolean ) oper1 && ( Boolean ) oper2;
				break;

			case "==":
				if ( oper1 instanceof Integer )
				{
					if ( oper2 instanceof Integer )
					{
						ans = oper1.equals( oper2 );
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Integer ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = true;
						else
							ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Double )
				{
					if ( oper2 instanceof Integer )
					{
						if ( Math.abs( ( Double ) oper1 - ( Integer ) oper2 ) <= 1e-9 )
							ans = true;
						else
							ans = false;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Double ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = true;
						else
							ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Boolean )
				{
					if ( !( oper2 instanceof Boolean ) )
					{
						showError( "Operands mismatch, i was expecting a Boolean.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof String )
				{
					if ( !( oper2 instanceof String ) )
					{
						showError( "Operands mismatch, i was expecting a String.", ctx );
						return null;
					}
				}
				
				ans = oper1.equals( oper2 );
				break;
			
			case "!=":
				if ( oper1 instanceof Integer )
				{
					if ( oper2 instanceof Integer )
					{
						ans = !oper1.equals( oper2 );
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Integer ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = false;
						else
							ans = true;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Double )
				{
					if ( oper2 instanceof Integer )
					{
						if ( Math.abs( ( Double ) oper1 - ( Integer ) oper2 ) <= 1e-9 )
							ans = false;
						else
							ans = true;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Double ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = false;
						else
							ans = true;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Boolean )
				{
					if ( !( oper2 instanceof Boolean ) )
					{
						showError( "Operands mismatch, i was expecting a Boolean.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof String )
				{
					if ( !( oper2 instanceof String ) )
					{
						showError( "Operands mismatch, i was expecting a String.", ctx );
						return null;
					}
				}
				
				ans = !oper1.equals( oper2 );
				break;
				
			case "<":
				if ( oper1 instanceof Integer )
				{
					if ( oper2 instanceof Integer )
					{
						ans = ( Integer ) oper1 < ( Integer ) oper2;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Integer ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = false;
						else
							if ( ( Integer ) oper1 < ( Double ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Double )
				{
					if ( oper2 instanceof Integer )
					{
						if ( Math.abs( ( Double ) oper1 - ( Integer ) oper2 ) <= 1e-9 )
							ans = false;
						else
							if ( ( Double ) oper1 < ( Integer ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Double ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = false;
						else
							if ( ( Double ) oper1 < ( Double ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof String )
				{
					if ( oper2 instanceof String )
					{
						int p = ( ( String ) oper1 ).compareTo( ( String ) oper2 );
						if ( p < 0 )
							ans = true;
						else
							ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting a String.", ctx );
						return null;
					}
				}
				else
				{
					showError( "Operands mismatch, i was expecting an Integer, a Double or a String.", ctx );
					return null;
				}
				
			case "<=":
				if ( oper1 instanceof Integer )
				{
					if ( oper2 instanceof Integer )
					{
						ans = ( Integer ) oper1 <= ( Integer ) oper2;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Integer ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = true;
						else
							if ( ( Integer ) oper1 < ( Double ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Double )
				{
					if ( oper2 instanceof Integer )
					{
						if ( Math.abs( ( Double ) oper1 - ( Integer ) oper2 ) <= 1e-9 )
							ans = true;
						else
							if ( ( Double ) oper1 < ( Integer ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Double ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = true;
						else
							if ( ( Double ) oper1 < ( Double ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof String )
				{
					if ( oper2 instanceof String )
					{
						int p = ( ( String ) oper1 ).compareTo( ( String ) oper2 );
						if ( p < 1 )
							ans = true;
						else
							ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting a String.", ctx );
						return null;
					}
				}
				else
				{
					showError( "Operands mismatch, i was expecting an Integer, a Double or a String.", ctx );
					return null;
				}
				
			case ">":
				if ( oper1 instanceof Integer )
				{
					if ( oper2 instanceof Integer )
					{
						ans = ( Integer ) oper1 > ( Integer ) oper2;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Integer ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = false;
						else
							if ( ( Integer ) oper1 > ( Double ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Double )
				{
					if ( oper2 instanceof Integer )
					{
						if ( Math.abs( ( Double ) oper1 - ( Integer ) oper2 ) <= 1e-9 )
							ans = false;
						else
							if ( ( Double ) oper1 > ( Integer ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Double ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = false;
						else
							if ( ( Double ) oper1 > ( Double ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof String )
				{
					if ( oper2 instanceof String )
					{
						int p = ( ( String ) oper1 ).compareTo( ( String ) oper2 );
						if ( p > 0 )
							ans = true;
						else
							ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting a String.", ctx );
						return null;
					}
				}
				else
				{
					showError( "Operands mismatch, i was expecting an Integer, a Double or a String.", ctx );
					return null;
				}
				
			case ">=":
				if ( oper1 instanceof Integer )
				{
					if ( oper2 instanceof Integer )
					{
						ans = ( Integer ) oper1 >= ( Integer ) oper2;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Integer ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = true;
						else
							if ( ( Integer ) oper1 > ( Double ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Double )
				{
					if ( oper2 instanceof Integer )
					{
						if ( Math.abs( ( Double ) oper1 - ( Integer ) oper2 ) <= 1e-9 )
							ans = true;
						else
							if ( ( Double ) oper1 > ( Integer ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						if ( Math.abs( ( Double ) oper1 - ( Double ) oper2 ) <= 1e-9 )
							ans = true;
						else
							if ( ( Double ) oper1 > ( Double ) oper2 )
								ans = true;
							else
								ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof String )
				{
					if ( oper2 instanceof String )
					{
						int p = ( ( String ) oper1 ).compareTo( ( String ) oper2 );
						if ( p > 0 )
							ans = true;
						else
							ans = false;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting a String.", ctx );
						return null;
					}
				}
				else
				{
					showError( "Operands mismatch, i was expecting an Integer, a Double or a String.", ctx );
					return null;
				}
				
			case "+":
				
				if ( oper1 instanceof Integer )
				{
					if ( oper2 instanceof Integer )
					{
						ans = ( Integer ) oper1 + ( Integer ) oper2;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						ans = ( Integer ) oper1 + ( Double ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;						
					}
					else if ( oper2 instanceof String )
					{
						ans = oper1.toString( ) + oper2;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Double )
				{
					if ( oper2 instanceof Integer )
					{
						ans = ( Double ) oper1 + ( Integer ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;
					}
					else if ( oper2 instanceof Double )
					{
						ans = ( Double ) oper1 + ( Double ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;
					}
					else if ( oper2 instanceof String )
					{
						ans = oper1.toString( ) + oper2;
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof String )
				{
					if ( oper2 instanceof Integer )
					{
						ans = oper1.toString( ) + ( ( Integer ) oper2 ).toString( );
						break;
					}
					else if ( oper2 instanceof Double )
					{
						ans = oper1.toString( ) + ( ( Double ) oper2 ).toString( );
						break;
					}
					else if ( oper2 instanceof String )
					{
						ans = oper1.toString( ) + oper2.toString( );
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else
				{
					showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
					return null;
				}
				
			case "-":
				if ( oper1 instanceof Integer )
				{
					if ( oper2 instanceof Integer )
					{
						ans = ( Integer ) oper1 - ( Integer ) oper2;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						ans = ( Integer ) oper1 - ( Double ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;						
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Double )
				{
					if ( oper2 instanceof Integer )
					{
						ans = ( Double ) oper1 - ( Integer ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;
					}
					else if ( oper2 instanceof Double )
					{
						ans = ( Double ) oper1 - ( Double ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else
				{
					showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
					return null;
				}
				
			case "*":
				if ( oper1 instanceof Integer )
				{
					if ( oper2 instanceof Integer )
					{
						ans = ( Integer ) oper1 * ( Integer ) oper2;
						break;
					}
					else if ( oper2 instanceof Double )
					{
						ans = ( Integer ) oper1 * ( Double ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;						
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Double )
				{
					if ( oper2 instanceof Integer )
					{
						ans = ( Double ) oper1 * ( Integer ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						break;
					}
					else if ( oper2 instanceof Double )
					{
						ans = ( Double ) oper1 * ( Double ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else
				{
					showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
					return null;
				}
				
			case "/":
				if ( oper1 instanceof Integer )
				{
					if ( oper2 instanceof Integer )
					{
						Double castedOper1 = Double.parseDouble( oper1.toString( ) );
						Double castedOper2 = Double.parseDouble( oper2.toString( ) );
						
						ans = castedOper1 / castedOper2;
						
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;
					}
					else if ( oper2 instanceof Double )
					{
						ans = ( Integer ) oper1 / ( Double ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;						
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Double )
				{
					if ( oper2 instanceof Integer )
					{
						ans = ( Double ) oper1 / ( Integer ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;
					}
					else if ( oper2 instanceof Double )
					{
						ans = ( Double ) oper1 / ( Double ) oper2;
						Integer tmp = ( int ) Math.floor( ( Double ) ans );
						
						if ( Math.abs( ( Double ) ans - tmp ) < 1e-9 )
						{
							ans = tmp;
						}
						
						break;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else
				{
					showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
					return null;
				}
				
			case "%":
				if ( oper1 instanceof Integer )
				{
					if( oper2 instanceof Integer )
					{
						ans = ( Integer ) oper1 % ( Integer ) oper2;
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer.", ctx );
						return null;
					}
				}
				else
				{
					showError( "Operands mismatch, i was expecting an Integer.", ctx );
					return null;
				}
				break;
			
			case "^":
				if ( oper1 instanceof Integer )
				{
					if ( oper2 instanceof Integer )
					{
						ans = Math.pow( ( Integer ) oper1, ( Integer ) oper2 );
						int tmp = ((Double) ans).intValue( );
						ans = tmp;
					}
					else if ( oper2 instanceof Double )
					{
						ans = Math.pow( ( Integer ) oper1, ( Double ) oper2 );
						int tmp = ((Double) ans).intValue( );
						if ( Math.abs( ( Double ) ans - tmp ) <= 1e-9 )
						{
							ans = tmp;
						}
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else if ( oper1 instanceof Double )
				{
					if ( oper2 instanceof Integer )
					{
						ans = Math.pow( ( Double ) oper1, ( Integer ) oper2 );
						int tmp = ((Double) ans).intValue( );
						if ( Math.abs( ( Double ) ans - tmp ) <= 1e-9 )
						{
							ans = tmp;
						}
					}
					else if ( oper2 instanceof Double )
					{
						ans = Math.pow( ( Double ) oper1, ( Double ) oper2 );
						int tmp = ((Double) ans).intValue( );
						if ( Math.abs( ( Double ) ans - tmp ) <= 1e-9 )
						{
							ans = tmp;
						}
					}
					else
					{
						showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
						return null;
					}
				}
				else
				{
					showError( "Operands mismatch, i was expecting an Integer or a Double.", ctx );
					return null;
				} 
				break;
			}
			
			return ( T ) ans;			
		}
	}
}
