
public class Cell {
	
	boolean blocked;
	String marker;
	int xCordinate;
	int yCordinate;
	double distanceFromFinish;

	
	public Cell(int x, int y)
	{
		blocked = false;
		marker = " ";
		xCordinate = x;
		yCordinate = y;
	}
	public void setDistance(int val)
	{
		distanceFromFinish = val;
	}
	public void setDistance(int x, int y)
	{
		double xval = Math.pow(xCordinate - x, 2);
		double yval = Math.pow(yCordinate - y, 2);
		
		distanceFromFinish = Math.sqrt(xval + yval);
		
		if(blocked)
			distanceFromFinish = Integer.MAX_VALUE;
	}
	
	public double getDistance()
	{
		return distanceFromFinish;
	}
	
	public void setBlocked(boolean b)
	{
		blocked = b;
		if(b)
		{
			marker = "X";
			distanceFromFinish = Integer.MAX_VALUE;
		}
	}
	
	public boolean isBlocked()
	{
		return blocked;
	}
	
	public void setMarker(String m)
	{
		if(!marker.equalsIgnoreCase("F"))
			marker = m;
		if(!m.equalsIgnoreCase("X"))
		{
			blocked = false;
		}
	}
	
	public void setCordinates(int x, int y)
	{
		xCordinate = x;
		yCordinate = y;
	}
	
	public int getXCordinate()
	{
		return xCordinate;
	}
	
	public int getYCordinate()
	{
		return yCordinate;
	}
	
	public String getMatker()
	{
		return marker;
	}
}
