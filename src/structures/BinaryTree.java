package structures;

import java.util.HashMap;
import java.util.LinkedList;

import components.BinaryTreeNode;
import enums.Colors;
import main.AlgBuilder;

public class BinaryTree extends Structure
{
	BinaryTreeNode root;

	public BinaryTree( AlgBuilder applet, float x, float y, HashMap<String, StructureMethod> methods, boolean visible,
			String name, Object elem )
	{
		super( applet, x, y, methods, visible, name );

		root = new BinaryTreeNode( applet, x, y + 55, false, 
				applet.color( 255, 242, 0 ), 150, elem, elem.toString( ), null, null, 0, 1 );
	}
	
	@Override
	public String toString( ) 
	{
		return "BT: " + name;
	}
	
	public void setColor( Colors c )
	{		
		if ( root == null )
			return;
		
		BinaryTreeNode k = root;
		
		LinkedList<BinaryTreeNode> l = new LinkedList<>( );
		l.add( k );
		
		while ( l.size( ) > 0 )
		{
			k = l.removeFirst( );
			k.setColor( c );
			
			if ( k.hasLeftChild( ) )
				l.addFirst( k.getLeftChild( ) );
			if ( k.hasRightChild( ) )
				l.addFirst( k.getRightChild( ) );
		}
		
	}
	
	public int size( )
	{
		if ( root == null )
			return 0;
		
		BinaryTreeNode k = root;
		
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
	
	public boolean isEmpty( )
	{
		return root == null;
	}
	
	public void setRoot( Object elem )
	{
		root.elem = elem;
	}
	
	public BinaryTreeNode getRoot( )
	{
		return root;
	}
	
	public int height( )
	{
		return theHeight( root );
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
	
	@Override
	public void drawStructure( ) 
	{
		if ( visible && !isEmpty( ) )
		{
			LinkedList<BinaryTreeNode> l = new LinkedList<>( );
			
			l.add( root );
			
			while ( l.size( ) > 0 )
			{
				BinaryTreeNode k = l.removeFirst( );
				
				if ( k.getLevel( ) < 5 )
				{	
					applet.stroke( 0 );
					if ( k.hasLeftChild( ) && k.getLevel( ) < 4 )
					{
						int parentIndex = k.getIndex( );
						int lcIndex = parentIndex * 2;
						
						float x1 = 0, y1 = 0, x2 = 0, y2 = 0;
						
						switch ( k.getLevel( ) )
						{
						case 4:
							x1 = x + 40 * ( parentIndex % 16 ) + 25;
							y1 = 500;
							break;
						case 3:
							x1 = x + 80 * ( parentIndex % 8 ) + 45;
							y1 = 400;
							break;
						case 2:
							x1 = x + 160 * ( parentIndex % 4 ) + 85;
							y1 = 300;
							break;
						case 1:
							x1 = x + 320 * ( parentIndex % 2 ) + 165;
							y1 = 200;
							break;
						case 0:
							x1 = x + 325;
							y1 = 100;
							break;
						}
						
						switch ( k.getLevel( ) + 1 )
						{
						case 4:
							x2 = x + 40 * ( lcIndex % 16 ) + 25;
							y2 = 500;
							break;
						case 3:
							x2 = x + 80 * ( lcIndex % 8 ) + 45;
							y2 = 400;
							break;
						case 2:
							x2 = x + 160 * ( lcIndex % 4 ) + 85;
							y2 = 300;
							break;
						case 1:
							x2 = x + 320 * ( lcIndex % 2 ) + 165;
							y2 = 200;
							break;
						case 0:
							x2 = x + 325;
							y2 = 100;
							break;
						}
						
						float xm = ( x1 + x2 ) / 2;
						float ym = ( y1 + y2 ) / 2;
						
						applet.bezier( x1, y1, xm - 10, ym - 10, xm - 10, ym - 10, x2, y2 );
						
					}
					if ( k.hasRightChild( ) && k.getLevel( ) < 4 )
					{
						int parentIndex = k.getIndex( );
						int lcIndex = parentIndex * 2 + 1;
						
						float x1 = 0, y1 = 0, x2 = 0, y2 = 0;
						
						switch ( k.getLevel( ) )
						{
						case 4:
							x1 = x + 40 * ( parentIndex % 16 ) + 25;
							y1 = 500;
							break;
						case 3:
							x1 = x + 80 * ( parentIndex % 8 ) + 45;
							y1 = 400;
							break;
						case 2:
							x1 = x + 160 * ( parentIndex % 4 ) + 85;
							y1 = 300;
							break;
						case 1:
							x1 = x + 320 * ( parentIndex % 2 ) + 165;
							y1 = 200;
							break;
						case 0:
							x1 = x + 325;
							y1 = 100;
							break;
						}
						
						switch ( k.getLevel( ) + 1 )
						{
						case 4:
							x2 = x + 40 * ( lcIndex % 16 ) + 25;
							y2 = 500;
							break;
						case 3:
							x2 = x + 80 * ( lcIndex % 8 ) + 45;
							y2 = 400;
							break;
						case 2:
							x2 = x + 160 * ( lcIndex % 4 ) + 85;
							y2 = 300;
							break;
						case 1:
							x2 = x + 320 * ( lcIndex % 2 ) + 165;
							y2 = 200;
							break;
						case 0:
							x2 = x + 325;
							y2 = 100;
							break;
						}
						
						float xm = ( x1 + x2 ) / 2;
						float ym = ( y1 + y2 ) / 2;
						
						applet.bezier( x1, y1, xm + 10, ym - 10, xm + 10, ym - 10, x2, y2 );
					}
				}
				
				if ( k.hasLeftChild( ) )
					l.addFirst( k.getLeftChild( ) );
				if ( k.hasRightChild( ) )
					l.addFirst( k.getRightChild( ) );
			}
			
			l = new LinkedList<>( );
			
			l.add( root );
			
			while ( l.size( ) > 0 )
			{
				BinaryTreeNode k = l.removeFirst( );
				
				if ( k.getLevel( ) < 5 )
					k.draw( );
				
				if ( k.hasLeftChild( ) )
					l.addFirst( k.getLeftChild( ) );
				if ( k.hasRightChild( ) )
					l.addFirst( k.getRightChild( ) );
			}
		}
	}		
}
