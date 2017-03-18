package enums;

public enum LinkedListMethods 
{
	ADD( "add" ), REMOVE( "remove" ), SIZE( "size" ),
	GET( "get" ), ISEMPTY( "isEmpty" ), ANIM( "setAnim" ),
	CONTAINS( "contains" );
	
	private String value;
	private LinkedListMethods( String value )
	{
		this.value = value;
	}
	
	public String getValue( )
	{
		return value;
	}
	
	public static LinkedListMethods getEnum( String value )
	{
		for ( LinkedListMethods alm : values( ) )
			if ( alm.value.equals( value ) )
				return alm;
		return null;
	}
}
