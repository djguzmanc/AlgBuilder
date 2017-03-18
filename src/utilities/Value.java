package utilities;

import enums.Type;

public class Value
{
	public Object object;
	public Type type;
	
	public Value( Object obj, Type type )
	{
		this.object = obj;
		this.type = type;
	}
	
	public Value( )
	{
		object = null;
		type = null;
	}
}
