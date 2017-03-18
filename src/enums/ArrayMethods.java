package enums;

public enum ArrayMethods 
{
	LENGHT( "lenght" ), SWAP( "swap" ), SORT( "sort" ),
	PUTMARKERAT( "putMarkerAt" ), REMOVEMARKER( "removeMarkerAt" ),
	CLEARMARKERS( "clearMarkers" ),	ADDFOLLOWER( "addFollower" ),
	DROPFOLLOWERS( "dropFollowers" );
	
	private String value;
	
	private ArrayMethods( String value )
	{
		this.value = value;
	}
	
	public String getValue( )
	{
		return value;
	}
	
	public static ArrayMethods getEnum( final String VALUE )
	{
		for ( ArrayMethods key : values( ) )
			if( key.value.equalsIgnoreCase( VALUE ) )
				return key;
		return null;
	}
}
