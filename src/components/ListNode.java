package components;

import main.AlgBuilder;

public class ListNode extends Node 
{
	public ListNode next;
	public int index;
	
	public ListNode( AlgBuilder applet, float x, float y, boolean moveable, Integer color, Integer alpha, 
			Object elem, String tag, int index ) 
	{		
		super( applet, x, y, moveable, color, alpha, elem, tag );
		this.index = index;
		next = null;
	}
	
	@Override
	public String toString( ) 
	{
		return elem.toString( );
	}
}
