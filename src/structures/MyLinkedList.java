package structures;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import components.ListNode;
import components.NodeMarker;
import main.AlgBuilder;
import processing.core.PApplet;

public class MyLinkedList extends Structure 
{
	ListNode head;
	public int size;
	
	boolean anim;

	private CopyOnWriteArrayList<NodeMarker> markers;
	
	float xp = -1;
	float yp = -1;

	float nx = -1;
	float ny = -1;
	Object o;
	
	int nlink;
	
	boolean flink = false;
	boolean slink = false;
	boolean tlink = false;
	boolean rlink = false;
	
	public MyLinkedList( AlgBuilder applet, float x, float y, HashMap<String, StructureMethod> methods, boolean visible,
			String name )
	{
		super( applet, x, y, methods, visible, name );
		head = new ListNode( applet, x, y, false, applet.color( 0, 162, 232 ), 100, null, null, -1 );

		size = 0;
		anim = true;
		
		markers = new CopyOnWriteArrayList<>( );
	}
	
	@Override
	public String toString( ) 
	{
		return "LL: " + name;
	}
	
	private void sleepThread( double p )
	{
		try 
		{
			if ( !applet.jump )
				Thread.sleep( ( long ) ( applet.sliderValue * p ) );
			else
				Thread.sleep( 0 );
		}
		catch ( Exception e ) 
		{
			e.printStackTrace( );
		}
	}
	
	public void setAnim( boolean b )
	{
		anim = b;
	}
	
	public boolean isEmpty( )
	{
		return size == 0;
	}
	
	public int size( )
	{
		return size;
	}
	
	public void add( Object elem )
	{
		add( size, elem );
	}
	
	public void add( int index, Object elem )
	{
		if ( size == 0 )
		{
			if ( anim )
			{
				markers.add( new NodeMarker( 0, 100, applet.color( 65, 187, 23 ) ) );
				sleepThread( 0.4 );
			}
			
			head = new ListNode( applet, x, y, false, applet.color( 0, 162, 232 ), 100, null, null, -1 );
			head = new ListNode( applet, x, y, false, head.color, head.alpha, elem, elem.toString( ), index );
			
			if ( anim )
				markers.remove( new NodeMarker( 0, 100, applet.color( 65, 187, 23 ) ) );
		}
		else if ( index == 0 )
		{
			ListNode p = head;
			
			if ( anim )
			{
				ny = head.index / 12;
				nx = -1;
				
				o = elem;
				
				markers.add( new NodeMarker( 0, 100, applet.color( 65, 187, 23 ) ) );
				sleepThread( 0.4 );

				slink = true;
				sleepThread( 0.8 );
			}
			
			ListNode newNode = new ListNode( head.applet, head.x, head.y, false, head.color, head.alpha, elem, elem.toString( ), index );
			newNode.next = head;
			
			if ( anim )
				markers.remove( new NodeMarker( 0, 100, applet.color( 65, 187, 23 ) ) );
			
			head = newNode;
			
			while ( p != null )
			{
				p.index += 1;
				p = p.next;
			}
		}
		else
		{
			if ( anim )
				markers.add( new NodeMarker( index, 100, applet.color( 65, 187, 23 ) ) );
			
			ListNode p = head;
			
	         for( int i = 0; i < index - 1; i++ )
	         {
	        	 if ( anim )
	        	 {
	        		 yp = p.index / 12;
	 				
	        		 if ( yp % 2 == 0 )
	 					 xp = p.index % 12;
	 				 else
	 					 xp = 11 - p.index % 12;
	        		 
		        	 markers.add( new NodeMarker( p.index, 100, applet.color( 255, 127, 39 ) ) );
		        	 sleepThread( 0.6 );
	        		 markers.remove( new NodeMarker( p.index, 100, applet.color( 255, 127, 39 ) ) );
	        	 }
	        	 
	        	 p = p.next;
	         }
	         
	         if ( anim )
        	 {
	        	 yp = p.index / 12;
 				
        		 if ( yp % 2 == 0 )
 					 xp = p.index % 12;
 				 else
 					 xp = 11 - p.index % 12;
        		 
	        	 markers.add( new NodeMarker( p.index, 100, applet.color( 255, 127, 39 ) ) );
	        	 sleepThread( 0.6 );
	        	 
	        	 ny = p.index / 12;
					
				 if ( ny % 2 == 0 )
					 nx = p.index % 12;
				 else
					 nx = 11 - p.index % 12;
				 o = elem;

				 flink = true;
				 sleepThread( 0.8 );
				 
				 if ( index != size )
				 {
					 slink = true;
					 sleepThread( 0.8 );
					 
					 tlink = true;
					 nlink = index - 1;
					 sleepThread( 0.8 );
				 }
	        	 
        		 markers.remove( new NodeMarker( p.index, 100, applet.color( 255, 127, 39 ) ) );
        	 }
	         
	         ListNode q = p.next;

	         p.next = new ListNode( head.applet, head.x, head.y, false, head.color, head.alpha, elem, elem.toString( ), index );
	         p.next.next = q;
	         
	         while ( q != null )
	         {
	        	 q.index += 1;
	        	 q = q.next;
	         }
	         
	         if ( anim  )
	        	 markers.remove( new NodeMarker( index, 100, applet.color( 65, 187, 23 ) ) );
		}

		size++;
		markers = new CopyOnWriteArrayList<>( );
		ny = yp = -1;
		nx = xp = -2;
		nlink = -1;
		flink = false;
		slink = false;
		tlink = false;
	}
	
	public void remove( int index )
	{
		if ( anim )
			markers.add( new NodeMarker( index, 150, applet.color( 237, 28, 36 ) ) );
		
		if ( index == 0 )
		{			
			sleepThread( 0.8 );
			
			head = head.next;
			ListNode q = head;
			
			while ( q != null )
			{
				q.index -= 1;
				q = q.next;
			}
		}
		else
		{
			ListNode q = head;
	         for( int i = 0; i < index - 1; i++ )
	         {
	        	 if ( anim )
	        	 {
	        		 yp = q.index / 12;
	 				
	        		 if ( yp % 2 == 0 )
	 					 xp = q.index % 12;
	 				 else
	 					 xp = 11 - q.index % 12;
	        		 
		        	 markers.add( new NodeMarker( q.index, 100, applet.color( 255, 127, 39 ) ) );
		        	 sleepThread( 0.6 );
	        		 markers.remove( new NodeMarker( q.index, 100, applet.color( 255, 127, 39 ) ) );
	        	 }
	        	 
	             q = q.next;
	         }
	         
	         if ( anim )
        	 {
	        	 yp = q.index / 12;
 				
        		 if ( yp % 2 == 0 )
 					 xp = q.index % 12;
 				 else
 					 xp = 11 - q.index % 12;
        		 
	        	 markers.add( new NodeMarker( q.index, 100, applet.color( 255, 127, 39 ) ) );
	        	 sleepThread( 0.6 );
	        	 
	        	 rlink = true;
        		 sleepThread( 0.8 );
        		 
        		 tlink = true;
        		 nlink = index - 1;
        		 sleepThread( 0.8 );
	        	 
        		 markers.remove( new NodeMarker( q.index, 100, applet.color( 255, 127, 39 ) ) );
        	 }

	         q.next = q.next.next;
	         q = q.next;
	         
	         while ( q != null )
	         {
	        	 q.index -= 1;
	        	 q = q.next;
	         }
		}
		
		size--;
		
		if ( anim )
			markers.remove( new NodeMarker( index, 150, applet.color( 237, 28, 36 ) ) );
		
		markers = new CopyOnWriteArrayList<>( );
		ny = yp = -1;
		nx = xp = -2;
		nlink = -1;
		
		tlink = false;
		rlink = false;
	}
	
	public Object get( int index )
	{
		ListNode currentNode = head;
		
		for( int i = 0; i < index; i++ )
			currentNode = currentNode.next;

		return currentNode.elem;
	}
	
	public boolean contains( Object o )
	{
		ListNode p = head;
		while ( p != null )
		{
			if ( p.elem.equals( o ) )
				return true;
			p = p.next;
		}
		
		return false;
	}
	
	@Override
	public void drawStructure( ) 
	{
		if ( visible )
		{		
			if ( flink )
			{				
				if ( ny % 2 == 0 )
				{
					if ( nx != 11 )
					{
						float cx = ( x + nx * 50 + x + nx * 50 + 25 ) / 2 - 7;
						float cy = ( y + ny * 56 + y + ny * 56 - 25 ) / 2 - 9;
						
						applet.bezier( x + nx * 50, y + ny * 56, cx, cy, cx, cy, x + nx * 50 + 25, y + ny * 56 - 25 );
					}
					else
					{
						float cx = x + nx * 50 + 20;
						float cy = ( y + ny * 56 + y + ny * 56 + 25 ) / 2 - 5;
						
						applet.bezier( x + nx * 50, y + ny * 56, cx, cy, cx, cy, x + nx * 50 + 25, y + ny * 56 + 25 );
					}
				}
				else
				{
					if ( nx != 0 )
					{
						float cx = ( x + nx * 50 + x + nx * 50 - 25 ) / 2 + 7;
						float cy = ( y + ny * 56 + y + ny * 56 - 25 ) / 2 - 9;
						
						applet.bezier( x + nx * 50, y + ny * 56, cx, cy, cx, cy, x + nx * 50 - 25, y + ny * 56 - 25 );
					}
					else
					{
						float cx = x + nx * 50 - 20;
						float cy = ( y + ny * 56 + y + ny * 56 + 25 ) / 2 - 5;
						
						applet.bezier( x + nx * 50, y + ny * 56, cx, cy, cx, cy, x + nx * 50 - 25, y + ny * 56 + 25 );
					}
				}
			}
			
			if ( slink )
			{
				if ( ny % 2 == 0 )
				{
					if ( nx != 11 )
					{
						float cx = ( x + nx * 50 + 50 + x + nx * 50 + 25 ) / 2 + 7;
						float cy = ( y + ny * 56 + y + ny * 56 - 25 ) / 2 - 9;
						
						applet.bezier( x + nx * 50 + 25, y + ny * 56 - 25, cx, cy, cx, cy, x + nx * 50 + 50, y + ny * 56 );
					}
					else
					{
						float cx = x + nx * 50 + 20;
						float cy = ( y + ny * 56 + 56 + y + ny * 56 + 25 ) / 2 + 5;
						
						applet.bezier( x + nx * 50 + 25, y + ny * 56 + 25, cx, cy, cx, cy, x + nx * 50, y + ny * 56 + 56 );
					}
				}
				else
				{
					if ( nx != 0 )
					{
						float cx = ( x + nx * 50 - 50 + x + nx * 50 - 25 ) / 2 - 7;
						float cy = ( y + ny * 56 + y + ny * 56 - 25 ) / 2 - 9;
						
						applet.bezier( x + nx * 50 - 25, y + ny * 56 - 25, cx, cy, cx, cy, x + nx * 50 - 50, y + ny * 56 );
					}
					else
					{
						float cx = x + nx * 50 - 20;
						float cy = ( y + ny * 56 + 56 + y + ny * 56 + 25 ) / 2 + 5;
						
						applet.bezier( x + nx * 50 - 25, y + ny * 56 + 25, cx, cy, cx, cy, x + nx * 50, y + ny * 56 + 56 );
					}
				}
			}
			
			if ( rlink )
			{
				applet.stroke( 237, 28, 36 );
				applet.noFill( );
				
				if ( yp % 2 == 0 )
				{
					if ( xp != 10 && xp != 11 )
					{
						float cx = ( x + xp * 50 + x + xp * 50 + 100 ) / 2;
						float cy = y + yp * 56 - 35;
						
						applet.bezier( x + xp * 50, y + yp * 56, cx - 15, cy, cx + 15, cy, x + xp * 50 + 100, y + yp * 56 );
					}
					else if ( xp == 10 )
					{
						float cx = ( x + xp * 50 + x + xp * 50 + 50 ) / 2;
						float cy = ( y + yp * 56 + y + yp * 56 + 56 ) / 2;
						
						applet.bezier( x + xp * 50, y + yp * 56, cx - 15, cy + 15, cx + 15, cy - 15, x + xp * 50 + 50, y + yp * 56 + 56 );
					}
					else
					{
						float cx = ( x + xp * 50 + x + xp * 50 - 50 ) / 2;
						float cy = ( y + yp * 56 + y + yp * 56 + 56 ) / 2;
						
						applet.bezier( x + xp * 50, y + yp * 56, cx + 15, cy + 15, cx - 15, cy - 15, x + xp * 50 - 50, y + yp * 56 + 56 );
					}
				}
				else
				{
					if ( xp != 0 && xp != 1 )
					{
						float cx = ( x + xp * 50 + x + xp * 50 - 100 ) / 2;
						float cy = y + yp * 56 - 35;
						
						applet.bezier( x + xp * 50, y + yp * 56, cx + 15, cy, cx - 15, cy, x + xp * 50 - 100, y + yp * 56 );
					}
					else if ( xp == 1 )
					{
						float cx = ( x + xp * 50 + x + xp * 50 - 50 ) / 2;
						float cy = ( y + yp * 56 + y + yp * 56 + 56 ) / 2;
						
						applet.bezier( x + xp * 50, y + yp * 56, cx + 15, cy + 15, cx - 15, cy - 15, x + xp * 50 - 50, y + yp * 56 + 56 );
					}
					else
					{
						float cx = ( x + xp * 50 + x + xp * 50 + 50 ) / 2;
						float cy = ( y + yp * 56 + y + yp * 56 + 56 ) / 2;
						
						applet.bezier( x + xp * 50, y + yp * 56, cx - 15, cy + 15, cx + 15, cy - 15, x + xp * 50 + 50, y + yp * 56 + 56 );
					}
				}
			}
			
			if ( nx >= -1 && ny >= 0 )
			{
				applet.noStroke( );
				applet.fill( 65, 187, 23, 200 );
				
				applet.textSize( 7 );
				
				String na = o.toString( );
				if ( na.length( ) > 4 )
					na = na.substring( 4 );
				
				if ( ny % 2 == 0 )
				{
					if ( nx != 11 )
					{
						applet.ellipse( x + nx * 50 + 25, y + ny * 56 - 25, 15, 15 );
						
						applet.stroke( 0 );
						applet.fill( 0 );
						applet.text( na, x + nx * 50 + 25, y + ny * 56 + 2 - 25 );
					}
					else
					{
						applet.ellipse( x + nx * 50 + 25, y + ny * 56 + 25, 15, 15 );
						
						applet.stroke( 0 );
						applet.fill( 0 );
						applet.text( na, x + nx * 50 + 25, y + ny * 56 + 2 + 25 );
					}
				}
				else
				{
					if ( nx != 0 )
					{
						applet.ellipse( x + nx * 50 - 25, y + ny * 56 - 25, 15, 15 );
						
						applet.stroke( 0 );
						applet.fill( 0 );
						applet.text( na, x + nx * 50 - 25, y + ny * 56 + 2 - 25 );
					}
					else
					{
						applet.ellipse( x + nx * 50 - 25, y + ny * 56 + 25, 15, 15 );
						
						applet.stroke( 0 );
						applet.fill( 0 );
						applet.text( na, x + nx * 50 - 25, y + ny * 56 + 2 + 25 );
					}
				}
			}
			
			if ( xp >= 0 && yp >= 0 )
			{
				applet.noFill( );
				
				float cpy = ( y - 25 + yp * 56 + y + yp * 56 ) / 2;
				float cpx;
				
				if ( yp % 2 == 0 )
					cpx = ( x - 20 + xp * 50 + x + xp * 50 ) / 2;
				else
					cpx = ( x + 20 + xp * 50 + x + xp * 50 ) / 2;
				
				applet.stroke( 0 );
				
				if ( yp % 2 == 0 )
					applet.bezier( x - 20 + xp * 50, y - 25 + yp * 56, cpx + 5, cpy - 5, cpx + 5, cpy - 5, x + xp * 50, y + yp * 56 );
				else
					applet.bezier( x + 20 + xp * 50, y - 25 + yp * 56, cpx - 5, cpy - 5, cpx - 5, cpy - 5, x + xp * 50, y + yp * 56 );
				
				applet.noStroke( );
				applet.fill( 255, 0, 0 );
				
				if ( yp % 2 == 0 )
					applet.ellipse( x - 20 + xp * 50, y - 25 + yp * 56, 14, 14 );
				else
					applet.ellipse( x + 20 + xp * 50, y - 25 + yp * 56, 14, 14 );
				
				applet.fill( 0 );
				applet.textSize( 12 );
				if ( yp % 2 == 0 )
					applet.text( "p", x - 20 + xp * 50, y - 23 + yp * 56 );
				else
					applet.text( "p", x + 20 + xp * 50, y - 23 + yp * 56 );
			}
			
			ListNode h = head;
			
			applet.stroke( 0 );
			while ( h != null && size > 0 )
			{				
				applet.fill( 0 );
				
				if ( h.next != null )
				{
					if ( !tlink || h.index != nlink )
					{
						float yMar = h.index / 12;
						float yMarN = h.next.index / 12;
						
						if ( yMar % 2 == 0 )
						{		
							float xMar = h.index % 12;
							
							if ( yMarN % 2 == 0 )
							{
								float xMarN = h.next.index % 12;
								
								float _x = x + xMar * 50;
								float _y = y + yMar * 56;
								float _x2 = x + xMarN * 50;
								float _y2 = y + yMarN * 56;
								
								float cx = ( _x + _x2 ) / 2;
								float cy = ( _y + _y2 ) / 2;
								
								applet.noFill( );
								if ( _y == _y2 )
									applet.bezier( _x, _y, cx, cy - 10, cx, cy - 10, _x2, _y2 );
								else
								{
									if ( xMarN == 11 )
										applet.bezier( _x, _y, cx + 10, cy, cx + 10, cy, _x2, _y2 );
									else
										applet.bezier( _x, _y, cx - 10, cy, cx - 10, cy, _x2, _y2 );
								}
							}
							else
							{
								float xMarN = 11 - h.next.index % 12;
								
								float _x = x + xMar * 50;
								float _y = y + yMar * 56;
								float _x2 = x + xMarN * 50;
								float _y2 = y + yMarN * 56;
								
								float cx = ( _x + _x2 ) / 2;
								float cy = ( _y + _y2 ) / 2;
								
								applet.noFill( );
								if ( _y == _y2 )
									applet.bezier( _x, _y, cx, cy - 10, cx, cy - 10, _x2, _y2 );
								else
								{
									if ( xMarN == 11 )
										applet.bezier( _x, _y, cx + 10, cy, cx + 10, cy, _x2, _y2 );
									else
										applet.bezier( _x, _y, cx - 10, cy, cx - 10, cy, _x2, _y2 );
								}
							}
						}
						else
						{
							float xMar = 11 - h.index % 12;
	
							if ( yMarN % 2 == 0 )
							{
								float xMarN = h.next.index % 12;
								
								float _x = x + xMar * 50;
								float _y = y + yMar * 56;
								float _x2 = x + xMarN * 50;
								float _y2 = y + yMarN * 56;
								
								float cx = ( _x + _x2 ) / 2;
								float cy = ( _y + _y2 ) / 2;
								
								applet.noFill( );
								if ( _y == _y2 )
									applet.bezier( _x, _y, cx, cy - 10, cx, cy - 10, _x2, _y2 );
								else
								{
									if ( xMarN == 11 )
										applet.bezier( _x, _y, cx + 10, cy, cx + 10, cy, _x2, _y2 );
									else
										applet.bezier( _x, _y, cx - 10, cy, cx - 10, cy, _x2, _y2 );
								}
							}
							else
							{
								float xMarN = 11 - h.next.index % 12;
								
								float _x = x + xMar * 50;
								float _y = y + yMar * 56;
								float _x2 = x + xMarN * 50;
								float _y2 = y + yMarN * 56;
								
								float cx = ( _x + _x2 ) / 2;
								float cy = ( _y + _y2 ) / 2;
								
								applet.noFill( );
								if ( _y == _y2 )
									applet.bezier( _x, _y, cx, cy - 10, cx, cy - 10, _x2, _y2 );
								else
								{
									if ( xMarN == 11 )
										applet.bezier( _x, _y, cx + 10, cy, cx + 10, cy, _x2, _y2 );
									else
										applet.bezier( _x, _y, cx - 10, cy, cx - 10, cy, _x2, _y2 );
								}
							}
						}
					}
				}
				
				h = h.next;
			}
			
			h = head;
			
			applet.textSize( 10 );
			for ( int i = 0; i < size; i++ )
			{
				float yMar = i / 12;
				
				if ( yMar % 2 == 0 )
				{
					float xMar = i % 12;
					
					applet.stroke( 0 );
					applet.fill( 80 );
					applet.textAlign( PApplet.CUSTOM );
					applet.text( i, x + xMar * 50 + 15, y + yMar * 56 + 20 );
					
					applet.noStroke( );
					applet.fill( 255 );
					applet.ellipse( x + xMar * 50, y + yMar * 56, h.rad, h.rad );
					
					applet.fill( h.color, h.alpha );
					applet.ellipse( x + xMar * 50, y + yMar * 56, h.rad, h.rad );
				}
				else
				{
					float xMar = 11 - i % 12;
					
					applet.stroke( 0 );
					applet.fill( 80 );
					applet.textAlign( PApplet.CUSTOM );
					applet.text( i, x + xMar * 50 + 15, y + yMar * 56 + 20 );
					
					applet.noStroke( );
					applet.fill( 255 );
					applet.ellipse( x + xMar * 50, y + yMar * 56, h.rad, h.rad );
					
					applet.fill( h.color, h.alpha );
					applet.ellipse( x + xMar * 50, y + yMar * 56, h.rad, h.rad );
				}
			}
			
			applet.textSize( 12 );
			while ( h != null && size > 0 )
			{				
				String n = h.tag;
				
				if ( n.length( ) > 4 )
					n = n.substring( 4 );
				
				float yMar = h.index / 12;
				
				if ( yMar % 2 == 0 )
				{
					float xMar = h.index % 12;
					
					applet.fill( 0 );
					applet.textAlign( PApplet.CENTER );
					applet.text( n, x + xMar * 50, y + yMar * 56 + 4 );
					
					applet.fill( 0, 255 );
					applet.text( n, x + xMar * 50, y + yMar * 56 + 4 );
				}
				else
				{
					float xMar = 11 - h.index % 12;
					
					applet.fill( 0 );
					applet.textAlign( PApplet.CENTER );
					applet.text( n, x + xMar * 50, y + yMar * 56 + 4 );
					
					applet.fill( 0, 255 );
					applet.text( n, x + xMar * 50, y + yMar * 56 + 4 );
				}
				
				h = h.next;
			}
			
			for ( NodeMarker nm : markers )
			{
				float yNm = nm.pos / 12;
				
				if ( yNm % 2 == 0 )
				{
					float xNm = nm.pos % 12;
					
					applet.noStroke( );
					applet.fill( nm.color, nm.alpha );
					applet.ellipse( x + xNm * 50, y + yNm * 56, head.rad - 1, head.rad - 1 );
				}
				else
				{
					float xNm = 11 - nm.pos % 12;
					
					applet.noStroke( );
					applet.fill( nm.color, nm.alpha );
					applet.ellipse( x + xNm * 50, y + yNm * 56, head.rad - 1, head.rad - 1 );
				}
			}
		}
	}
}
