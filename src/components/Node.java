package components;

import main.AlgBuilder;

public class Node 
{
	public float x;
	public float y;
	
	public float rad; 
	
	boolean moveable;
	
	public Integer color;
	public Integer alpha;
	
	public Object elem;
	public String tag;
	
	public AlgBuilder applet;
	
	public Node( AlgBuilder applet, float x, float y, boolean moveable, Integer color, Integer alpha, Object elem, String tag ) 
	{
		this.applet = applet;
		this.x = x;
		this.y = y;
		this.moveable = moveable;
		rad = 30;
		this.color = color;
		this.alpha = alpha;
		this.elem = elem;
		this.tag = tag;
	}
}
