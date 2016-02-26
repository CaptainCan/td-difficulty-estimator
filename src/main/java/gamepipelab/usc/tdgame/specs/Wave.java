package gamepipelab.usc.tdgame.specs;

import java.util.List;

public class Wave
{	
	private List<MiniWave> miniWaves;
	
	public Wave(List<MiniWave> miniWaves)
	{
		super();
		this.miniWaves = miniWaves;
	}
	
	public Wave()
	{
		
	}

	public List<MiniWave> getMiniWaves()
	{
		return miniWaves;
	}

	public void setMiniWaves(List<MiniWave> miniWaves)
	{
		this.miniWaves = miniWaves;
	}
}
