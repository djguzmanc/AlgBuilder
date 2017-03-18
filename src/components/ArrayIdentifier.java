package components;

import utilities.Value;

public class ArrayIdentifier 
{
	public Value value;
	public String name;
	
	public ArrayIdentifier( Value value, String name ) 
	{
		this.value = value;
		this.name = name;
	}
	
	@Override
	public boolean equals( Object obj ) 
	{
		ArrayIdentifier ai = ( ArrayIdentifier ) obj;
		if ( ai.name.equals( name ) )
			return true;
		return false;
	}
}
