package components;

public class NodeMarker 
{
	public Integer color;
	public Integer alpha;
	public Integer pos;
	
	public NodeMarker( Integer pos, Integer alpha, Integer color ) 
	{
		this.color = color;
		this.alpha = alpha;
		this.pos = pos;
	}
	
	@Override
	public boolean equals( Object obj ) 
	{
		NodeMarker o = ( NodeMarker ) obj;
		if ( o.pos.equals( pos ) && o.color.equals( color ) )
			return true;
		return false;
	}
}