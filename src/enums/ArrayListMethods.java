package enums;

public enum ArrayListMethods 
{
	ADD( "add" ), REMOVE( "remove" ), SIZE( "size" ),
	GET( "get" ), ISEMPTY( "isEmpty" ), ANIM( "setAnim" ),
	SETMULTIPLIER( "setMultiplier" ), CONTAINS( "contains" );
	
	private String value;
	private ArrayListMethods( String value )
	{
		this.value = value;
	}
	
	public String getValue( )
	{
		return value;
	}
	
	public static ArrayListMethods getEnum( String value )
	{
		for ( ArrayListMethods alm : values( ) )
			if ( alm.value.equals( value ) )
				return alm;
		return null;
	}
}
