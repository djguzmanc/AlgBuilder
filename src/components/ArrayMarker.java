package components;

public class ArrayMarker 
{
	public Integer color;
	public Integer alpha;
	public Integer pos;
	
	public ArrayMarker( Integer pos, Integer alpha, Integer color ) 
	{
		this.color = color;
		this.alpha = alpha;
		this.pos = pos;
	}
	
	@Override
	public boolean equals( Object obj ) 
	{
		ArrayMarker o = ( ArrayMarker ) obj;
		if ( o.pos.equals( pos ) && o.color.equals( color ) )
			return true;
		return false;
	}
}
