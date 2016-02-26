package gamepipelab.usc.tdgame.naiveEstimator;

import gamepipelab.usc.tdgame.entities.Monster;
import gamepipelab.usc.tdgame.readers.LevelMapReader;
import gamepipelab.usc.tdgame.readers.ReadMonster;
import gamepipelab.usc.tdgame.readers.WaveReader;
import gamepipelab.usc.tdgame.specs.MapElements;
import gamepipelab.usc.tdgame.specs.MiniWave;
import gamepipelab.usc.tdgame.specs.Wave;

import java.util.Map;
import java.util.List;

public class WaveMonsterData {

	public static float avgTimeGround;
	public static float totalHPGround;
	public static float totalGainGround;
	
	public static float avgTimeAir;
	public static float totalHPAir;
	public static float totalGainAir;

	
	/*public static void main(String[] args) {
		WaveReader waveReader = new WaveReader();
		List<Wave> waves = waveReader.readWaves();
		getWaveRecord(waves.get(0));
	}	*/

	public static void getWaveRecord(Wave wave) {
//		WaveMonsterData test = new WaveMonsterData();
		ReadMonster monsterReader =  new ReadMonster();
		Map<String, Monster> monsters = monsterReader.monsters;
//		WaveReader waveReader = new WaveReader();
//		List<Wave> waves = waveReader.readWaves();
		LevelMapReader mapReader = new LevelMapReader();
		int[][] map = mapReader.readMap().getEntireMap();
		float pathLength = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == MapElements.MONSTER_PATH) {
					pathLength += map[i][j];
				}
			}
		}
		
		float totalTimeGround = 0;
		float totalTimeAir = 0;
		
		int totalMonsterGround = 0;
		int totalMonsterAir = 0;
		
		totalHPGround = 0;
		totalHPAir = 0;
		
		totalGainGround = 0;
		totalGainAir = 0;
		
			for (MiniWave mini: wave.getMiniWaves()) {
				Monster m = monsters.get(mini.getMonsterName());
//				System.out.println(m.isAirUnit());
				if (!m.isAirUnit()) {
					totalHPGround += (m.getHP() + m.getArmor()) * mini.getAmount() ;
					for (int i = 1; i <= mini.getAmount(); i++) {
						totalTimeGround += mini.getStartTime() * i + pathLength / m.getSpeed();
					}
					totalMonsterGround += mini.getAmount();
					totalGainGround += m.getGain() * mini.getAmount();
				} else {
					totalHPAir += (m.getHP() + m.getArmor()) * mini.getAmount() ;
					for (int i = 1; i <= mini.getAmount(); i++) {
						totalTimeAir += mini.getStartTime() * i + pathLength / m.getSpeed();
					}
					totalMonsterAir += mini.getAmount();
					
					totalGainAir += m.getGain() * mini.getAmount();
				}
			}

		avgTimeGround = totalTimeGround / totalMonsterGround;
		if (totalMonsterAir == 0) {
			avgTimeAir = 0;
		} else {
			avgTimeAir = totalTimeAir / totalMonsterAir;
		}
		
		/*System.out.println("path length: " + pathLength);

		System.out.println("totalMonsterGround: " + totalMonsterGround);
		System.out.println("totalHPGround: " + totalHPGround);
		System.out.println("totalTimeGround: " + totalTimeGround);
		System.out.println("avgTimeGround: " + avgTimeGround);
		System.out.println("totalGainGround: " + totalGainGround);
		
		System.out.println("totalMonsterAir: " + totalMonsterAir);
		System.out.println("totalHPAir: " + totalHPAir);
		System.out.println("totalTimeAir: " + totalTimeAir);
		System.out.println("avgTimeAir: " + avgTimeAir);
		System.out.println("totalGainAir: " + totalGainAir);*/
		
	}
}
