package enums;

public enum Methods 
{
	RANDOM( "random" ), ARRAYLIST( "ArrayList" ), RANDOMARRAY( "randomArray" ),
	LINKEDLIST( "LinkedList" ), GRAPH( "Graph" ), TREE( "BinaryTree" );
	
	private String value;
	
	private Methods( String value )
	{
		this.value = value;
	}
	
	public String getValue( )
	{
		return value;
	}
	
	public static Methods getEnum( final String VALUE )
	{
		for ( Methods key : values( ) )
			if( key.value.equalsIgnoreCase( VALUE ) )
				return key;
		return null;
	}
}
