package gamepipelab.usc.tdgame.specs;

public class LevelMap
{
	private int width;
	private int height;	
	private int[][] map;
	private int length; // monster path length
	
	public LevelMap(int width, int height)
	{
		this.width = width;
		this.height = height;
		map = new int[height][width];
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getCell(int i, int j)
	{
		return map[i][j];
	}

	public void setCell(int i, int j, int value)
	{
		this.map[i][j] = value;
	}
	
	public int[][] getEntireMap()
	{
		return this.map;
	}
	
	public void setLength(int len) 
	{
		this.length = len;
	}
	
	public int getLength() 
	{
		return this.length;
	}
	
}
