package enums;

public enum GraphMethods 
{
	ADDNODE( "addNode" ), MAKELINK( "makeLink" ), REMOVELINK( "removeLink" ),
	GETNODE( "getNode" ), REMOVENODE( "removeNode" ), AUTOPLACEMENT( "autoPlacement" );
	
	private String value;
	
	private GraphMethods( String value )
	{
		this.value = value;
	}
	
	public String getValue( )
	{
		return value;
	}
	
	public static GraphMethods getEnum( String value )
	{
		for ( GraphMethods key : values( ) )
			if ( key.value.equals( value ) )
				return key;
		return null;
	}
}
