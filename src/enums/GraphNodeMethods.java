package enums;

public enum GraphNodeMethods 
{
	SETCOLOR( "setColor" ), VISITING( "visiting" ),
	GETNBRS( "getNbrs" ), GETELEM( "getElem" );
	
	private String value;
	
	private GraphNodeMethods( String value )
	{
		this.value = value;
	}
	
	public String getValue( )
	{
		return value;
	}
	
	public static GraphNodeMethods getEnum( String value )
	{
		for ( GraphNodeMethods key : values( ) )
			if ( key.value.equals( value ) )
				return key;
		return null;
	}
}
