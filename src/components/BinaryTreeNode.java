package components;

import java.util.LinkedList;

import enums.Colors;
import main.AlgBuilder;
import processing.core.PApplet;

public class BinaryTreeNode extends Node 
{
	BinaryTreeNode leftChild;
	BinaryTreeNode rightChild;
	
	int level;
	int index;
	
	boolean visiting;

	public BinaryTreeNode( AlgBuilder applet, float x, float y, boolean moveable, Integer color, Integer alpha, Object elem,
			String tag, BinaryTreeNode leftChild, BinaryTreeNode rightChild, int level, int index )
	{
		super( applet, x, y, moveable, color, alpha, elem, tag );
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.level = level;
		this.index = index;
	}
	
	@Override
	public String toString( ) 
	{
		return elem.toString( );
	}
	
	public int size( )
	{		
		BinaryTreeNode k = this;
		
		LinkedList<BinaryTreeNode> l = new LinkedList<>( );
		l.add( k );
		
		int c = 0;
		
		while ( l.size( ) > 0 )
		{
			k = l.removeFirst( );
			c++;
			
			if ( k.hasLeftChild( ) )
				l.addFirst( k.getLeftChild( ) );
			if ( k.hasRightChild( ) )
				l.addFirst( k.getRightChild( ) );
		}
		
		return c;
	}
	
	public void setValue( Object elem )
	{
		this.elem = elem;
	}
	
	public int getLevel( )
	{
		return level;
	}
	
	public Object getElem( )
	{
		return elem;
	}
	
	public int getIndex( )
	{
		return index;
	}
	
	public BinaryTreeNode setRightChild( Object o )
	{
		BinaryTreeNode nn = new BinaryTreeNode( applet, x, y, false, color, alpha, o, o.toString( ), 
				null, null, level + 1, index * 2 + 1 );
		
		rightChild = nn;
		return this;
	}
	
	public BinaryTreeNode setLeftChild( Object o )
	{
		BinaryTreeNode nn = new BinaryTreeNode( applet, x, y, false, color, alpha, o, o.toString( ), 
				null, null, level + 1, index * 2 );
		
		leftChild = nn;
		return this;
	}
	
	public BinaryTreeNode getRightChild( )
	{
		return rightChild;
	}
	
	public BinaryTreeNode getLeftChild(  )
	{
		return leftChild;
	}
	
	public void removeRightChild( )
	{
		rightChild = null;
	}
	
	public void removeLeftChild( )
	{
		leftChild = null;
	}
	
	public boolean hasLeftChild( )
	{
		return leftChild != null;
	}
	
	public boolean hasRightChild( )
	{
		return rightChild != null;
	}
	
	public int height( )
	{
		return theHeight( this );
	}
	
	private int theHeight( BinaryTreeNode t )
	{
		if ( t == null )
			return 0;
		
		int hl = theHeight( t.getLeftChild( ) );
		int hr = theHeight( t.getRightChild( ) );
		
		if ( hl > hr ) return ++hl;
		return ++hr;
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
			color = applet.color( 255, 242, 0 );
			break;
		case WHITE:
			color = applet.color( 255 );
			break;
		}
	}
	
	public void visiting( boolean b )
	{
		visiting = b;
	}
	
	public void draw( )
	{
		if ( !visiting )
			applet.noStroke( );
		else
			applet.stroke( 237, 28, 36 );
		
		String s = elem.toString( );
		if ( s.length( ) > 3 )
			s = s.substring( 0, 3 );
		
		applet.textAlign( PApplet.CENTER );

		switch ( level )
		{
		case 4:
			applet.fill( 255 );
			applet.ellipse( x + 40 * ( index % 16 ) + 25, 500, 30, 30 );
			applet.fill( color, alpha );
			applet.ellipse( x + 40 * ( index % 16 ) + 25, 500, 30, 30 );
			applet.fill( 0 );
			applet.text( s, x + 40 * ( index % 16 ) + 25, 505 );
			break;
		case 3:
			applet.fill( 255 );
			applet.ellipse( x + 80 * ( index % 8 ) + 45, 400, 30, 30 );
			applet.fill( color, alpha );
			applet.ellipse( x + 80 * ( index % 8 ) + 45, 400, 30, 30 );
			applet.fill( 0 );
			applet.text( s, x + 80 * ( index % 8 ) + 45, 405 );
			break;
		case 2:
			applet.fill( 255 );
			applet.ellipse( x + 160 * ( index % 4 ) + 85, 300, 30, 30 );
			applet.fill( color, alpha );
			applet.ellipse( x + 160 * ( index % 4 ) + 85, 300, 30, 30 );
			applet.fill( 0 );
			applet.text( s, x + 160 * ( index % 4 ) + 85, 305 );
			break;
		case 1:
			applet.fill( 255 );
			applet.ellipse( x + 320 * ( index % 2 ) + 165, 200, 30, 30 );
			applet.fill( color, alpha );
			applet.ellipse( x + 320 * ( index % 2 ) + 165, 200, 30, 30 );
			applet.fill( 0 );
			applet.text( s, x + 320 * ( index % 2 ) + 165, 205 );
			break;
		case 0:
			applet.fill( 255 );
			applet.ellipse( x + 325, 100, 30, 30 );
			applet.fill( color, alpha );
			applet.ellipse( x + 325, 100, 30, 30 );
			applet.fill( 0 );
			applet.text( s, x + 325, 105 );
			break;
		}
	}
}
