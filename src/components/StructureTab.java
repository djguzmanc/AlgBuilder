package components;

import main.AlgBuilder;
import processing.core.PApplet;
import processing.core.PImage;
import structures.Structure;

public class StructureTab 
{	
	float x;
	float y;
	
	float width;
	float height;
	
	public Structure structure;
	AlgBuilder applet;
	
	PImage button;
	PImage buttonMouseOver;
	PImage buttonPressed;
	PImage buttonSelected;
	
	public StructureTab( float x, float y, float width, float height, Structure structure, AlgBuilder applet,
			PImage button, PImage buttonMouseOver, PImage buttonPressed, PImage buttonSelected ) 
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.structure = structure;
		this.applet = applet;
		this.button = button;
		this.buttonMouseOver = buttonMouseOver;
		this.buttonPressed = buttonPressed;
		this.buttonSelected = buttonSelected;
	}

	public void drawStructure( float mouseX, float mouseY, boolean mousePressed )
	{
		if ( mouseOver( mouseX, mouseY ) && !mousePressed )
			applet.image( buttonMouseOver, x, y );
		else if ( mouseOver( mouseX, mouseY ) && mousePressed )
			applet.image( buttonPressed, x, y );
		else
		{
			if ( !structure.visible )
				applet.image( button, x, y );
			else
				applet.image( buttonSelected, x, y );
		}
		
		applet.textAlign( PApplet.CENTER );
		applet.fill( 255 );
		applet.textSize( 12 );
		applet.text( structure.name, x + 40, y + 18 );
		
		structure.drawStructure( );
	}
	
	public boolean mouseOver( float mouseX, float mouseY )
	{
		if ( mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height )
			return true;
		return false;
	}
}
