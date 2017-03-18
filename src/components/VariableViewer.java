package components;

import main.AlgBuilder;
import processing.core.PApplet;
import processing.core.PImage;

public class VariableViewer 
{
	public float x;
	public float y;
	
	public float width = 50;
	public float height = 15;
	
	public boolean alive = false; 
	
	AlgBuilder applet;
	
	public String variableName;
	public Object value;
	
	PImage removeButton;
	
	public VariableViewer( float x, float y, AlgBuilder applet, String variableName, Object value ) 
	{
		this.x = x;
		this.y = y;
		this.applet = applet;
		this.variableName = variableName;
		this.value = value;
		
		removeButton = applet.loadImage( AlgBuilder.IMAGE_FOLDER + "removeViewer.png" );
	}
	
	public VariableViewer( String name )
	{
		variableName = name;
	}
	
	@Override
	public boolean equals( Object obj ) 
	{
		VariableViewer o = ( VariableViewer ) obj;
		
		if ( o.variableName.equals( variableName ) )
			return true;
		return false;
	}
	
	public void paintViewer( )
	{
		if ( alive )
			applet.fill( 238, 235, 106 );
		else
			applet.fill( 180 );
	
		//name rect
		applet.rect( x, y, width, height );
		
		//remove viewer button
		applet.image( removeButton, x + width - 7, y );
		
		//value rect
		applet.rect( x, y + height, width, height );
		
		applet.textAlign( PApplet.CENTER );
		applet.fill( 0 );
		applet.textSize( 9 );
		
		//name text
		if ( variableName.length( ) > 9 )
		{
			String fixedName = variableName.substring( 0, 6 );
			fixedName += "...";
			
			applet.text( fixedName, x + 25, y + 10 );
		}
		else
			applet.text( variableName, x + 25, y + 10 );
		
		//value text
		if ( value != null )
		{
			if ( value.toString( ).length( ) > 9 )
			{
				String fixedValue = value.toString( ).substring( 0, 6 );
				fixedValue += "...";
				
				applet.text( fixedValue, x + 25, y + 26 );
			}
			else
				applet.text( value.toString( ), x + 25, y + 26 );
		}
		else
			applet.text( "null", x + 25, y + 26 );
	}
	
	public void reallocateX( float mouseX )
	{
		x = mouseX - 25;
	}
	
	public void reallocateY( float mouseY )
	{
		y = mouseY - 15;
	}
	
	public boolean mouseOverRemoveButton( float mouseX, float mouseY )
	{
		if ( mouseX >= x + width - 7 && mouseX <= x + width && mouseY >= y && mouseY <= y + 8 )
			return true;
		return false;
	}
	
	public boolean mouseOver( float mouseX, float mouseY )
	{
		if ( mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height * 2 )
			return true;
		return false;
	}
}
