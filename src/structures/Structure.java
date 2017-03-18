package structures;

import java.util.HashMap;

import main.AlgBuilder;

public class Structure implements Drawable
{
	public float x;
	public float y;
	
	public HashMap<String, StructureMethod> methods;
	
	public boolean visible;
	
	AlgBuilder applet;
	public String name;

	public Structure( AlgBuilder applet, float x, float y, HashMap<String, StructureMethod> methods, boolean visible, String name )
	{
		this.applet = applet;
		this.x = x;
		this.y = y;
		this.methods = methods;
		this.visible = visible;
		this.name = name;
	}

	@Override
	public void drawStructure( )
	{
		// TODO Auto-generated method stub		
	}
}
