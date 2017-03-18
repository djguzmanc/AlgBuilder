package enums;

public enum BTMethods 
{
	ISEMPTY( "isEmpty" ), HEIGHT( "height" ), GETROOT( "getRoot" ),
	SETROOT( "setRoot" ), SIZE( "size" ), SETCOLOR( "setColor" );
	
	private String value;
	
	private BTMethods( String value )
	{
		this.value = value;
	}
	
	public String getValue( )
	{
		return value;
	}
	
	public static BTMethods getEnum( String value )
	{
		for ( BTMethods key : values( ) )
			if ( key.value.equals( value ) )
				return key;
		return null;
	}
}
