package enums;

public enum BTNodeMethods 
{
	GETLEVEL( "getLevel" ), GETINDEX( "getIndex" ), SETRIGHTCHILD( "setRightChild" ),
	SETLEFTCHILD( "setLeftChild" ), GETRIGHTCHILD( "getRightChild" ), GETLEFTCHILD( "getLeftChild" ),
	REMOVERIGHTCHILD( "removeRightChild" ), REMOVELEFTCHILD( "removeLeftChild" ),
	HASLEFTCHILD( "hasLeftChild" ), HASRIGHTCHILD( "hasRightChild" ), HEIGHT( "height" ),
	SETCOLOR( "setColor" ), VISITING( "visiting" ), SETVALUE( "setValue" ), GETELEM( "getElem" ),
	SIZE( "size" );
	
	private String value;
	
	private BTNodeMethods( String value )
	{
		this.value = value;
	}
	
	public String getValue( )
	{
		return value;
	}
	
	public static BTNodeMethods getEnum( String value )
	{
		for ( BTNodeMethods key : values( ) )
			if ( key.value.equals( value ) )
				return key;
		return null;
	}
}
