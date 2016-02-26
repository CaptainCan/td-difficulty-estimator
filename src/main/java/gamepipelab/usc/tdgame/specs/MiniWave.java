package gamepipelab.usc.tdgame.specs;


public class MiniWave
{
	private int startTime;
	private int amount;
	private String monsterName;
	
	public MiniWave(int startTime, int amount, String monsterName)
	{
		super();
		this.startTime = startTime;
		this.amount = amount;
		this.monsterName = monsterName;
	}
	
	public MiniWave()
	{
		
	}

	public int getStartTime()
	{
		return startTime;
	}

	public void setStartTime(int startTime)
	{
		this.startTime = startTime;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public String getMonsterName()
	{
		return monsterName;
	}

	public void setMonsterName(String monsterName)
	{
		this.monsterName = monsterName;
	}

	@Override
	public String toString()
	{
		return startTime+" "+amount+" "+monsterName;
	}	
}
