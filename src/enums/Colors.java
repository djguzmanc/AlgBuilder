package enums;

public enum Colors 
{
	GRAY( "gray" ), RED( "red" ), ORANGE( "orange" ),
	YELLOW( "yellow" ), GREEN( "green" ), BLUE( "blue" ),
	PURPLE( "purple" ), PINK( "pink" ), BLACK( "black" ),
	WHITE( "white" ), DEFAULT( "default" );
	
	private String value;
	
	private Colors( String value )
	{
		this.value = value;
	}
	
	public String getValue( )
	{
		return value;
	}
	
	public static Colors getEnum( final String VALUE )
	{
		for ( Colors key : values( ) )
			if( key.value.equalsIgnoreCase( VALUE ) )
				return key;
		return null;
	}
}
