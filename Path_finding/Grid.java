
public class Grid {
	
	private Cell grid[][];
	Cell startPosition;
	Cell current;
	Cell finish;
	final int horizontalObsticalLength = 5;
	final int vertaicalObsticalLength = 5;
	final int boxLength = 2;
	
	/**
	 * @Purpose: 
	 * 		makes the grid, obsticals, start and finish
	 * @param width
	 * 		the width of the grid
	 * @param height
	 * 		the height of the grid 
	 */
	public Grid(int width, int height)
	{
		grid = new Cell[width][height];
		
		for( int y = 0; y< height; y++)
		{
			for( int x = 0; x< width; x++)
			{
				grid[x][y] = new Cell(x,y);
			}
		}
		makeObsticals();
		startPosition = grid[0][9];
		startPosition.setCordinates(0, 9);
		startPosition.setMarker("S");
		current = startPosition;
		
		int finishX = (int)(Math.random()*width);
		int finishY = (int)(Math.random()*height);
		finish = grid[finishX][finishY];
		finish.setBlocked(false);
		finish.setMarker("F");		
		
		for(Cell[] c: grid)
		{
			for(Cell cc : c)
			{
				cc.setDistance(finish.getXCordinate(), finish.getYCordinate());
			}
		}
		
	}
	
	/**
	 * 
	 * @Purpose:
	 *		Makes 3 obsticals in the grid, one vertical, one horizontal and one
	 *		box of 2x2.
	 */
	private void makeObsticals()
	{
		//randomly selects a starting position for the horizontal obstical
		int xLocation = (int)(Math.random()*(grid.length - horizontalObsticalLength));
		int yLocation = (int)(Math.random()*grid[0].length);
		
		for(int x = xLocation; x <= (xLocation + horizontalObsticalLength); x++)
		{
			grid[x][yLocation].setBlocked(true);
		}
		
		//randomly selects a vertical section to be blocked
		xLocation = (int)(Math.random()*grid.length);
		yLocation = (int)(Math.random()*(grid[0].length- vertaicalObsticalLength));
		
		for(int y = yLocation; y <= (yLocation + vertaicalObsticalLength); y++)
		{
			grid[xLocation][y].setBlocked(true);
		}
		
		//makes a box to be blocked
		
		xLocation = (int)(Math.random()*(grid.length - boxLength));
		yLocation = (int)(Math.random()*(grid[0].length - boxLength));
		
		for(int x = xLocation; x < (xLocation+boxLength); x++)
		{
			for(int y = yLocation; y < (yLocation + boxLength); y++)
			{
				grid[x][y].setBlocked(true);
			}
		}
		
	}
	
	
	public void findBestPath()
	{
		//need to find best path from current cell
		//break options into four quadrents
				
		Cell winningCell = current;		
		Cell temp;
		int blockCounter = 0;

		while(!finish.equals(current))
		{
			for(int y = -1;y < 2; y++)
			{
				for(int x = -1; x < 2; x++)
				{
					try
					{
					 temp = grid[current.getXCordinate()+x]
							 [current.getYCordinate()+y];
					 
					 if (temp.getDistance()==Integer.MAX_VALUE)
						 blockCounter++;
						 if(blockCounter==8)
						 {
							System.out.println("There is no Solution"); 
							current = finish;
							break;
						 }
						 
										
					if(temp.getDistance() < winningCell.getDistance())
						winningCell = temp;
					}
					catch(Exception e)
					{
						
					}
				}
			}
			
			current = winningCell;
			current.setMarker("A");
			current.setDistance(100, 100);
			blockCounter = 0;
		}				
		
	}
	
	public boolean checkCell(int xShift, int yShift)
	{
		Cell temp;
		temp = grid[current.getXCordinate() + xShift][current.getYCordinate() + yShift]; 
		if(!temp.isBlocked())
		{
			current = temp;
			current.setMarker("O");
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	 * 
	 * @Purpose:
	 *		To print the grid and the obsticals
	 */
	
	public void printGrid()
	{
		
		for(int y = 0; y < grid[0].length; y++)
		{
			for(int x = 0; x < grid.length; x++)
			{
				System.out.print("|" + grid[x][y].getMatker());
								
			}
			System.out.println("|");
		}
		
	}

}
