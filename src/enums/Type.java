package enums;

public enum Type 
{
	INTEGER( "int" ), DOUBLE( "double" ), BOOL( "bool" ),
	STRING( "string" ), BTREE( "bTree" ), ARRAYLIST( "arrayList" ),
	LINKEDLIST( "linkedList" ), GRAPH( "graph" ), ANY( "any" ),
	GRAPHNODE( "gNode" ), BTNODE( "btNode" );
	
	private String value;
	
	private Type( String value )
	{
		this.value = value;
	}
	
	public String getValue( )
	{
		return value;
	}
	
	public static Type getEnum( final String VALUE )
	{
		for ( Type key : values( ) )
			if( key.value.equalsIgnoreCase( VALUE ) )
				return key;
		return null;
	}
}
