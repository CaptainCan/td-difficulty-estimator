package gamepipelab.usc.tdgame.naiveEstimator;

import gamepipelab.usc.tdgame.entities.Tower;
import gamepipelab.usc.tdgame.readers.WaveReader;
import gamepipelab.usc.tdgame.specs.Wave;

import java.util.LinkedList;
import java.util.List;

/**
 * Generates a difference of (total firepower of a tower) - (total HP of monsters)
 *
 * Created by ashton on 10/16/15.
 */
public class DifferenceCalculator {

    public static void main(String args[]) throws Exception 
    {
    	// test code
//    	System.out.println(DifferenceCalculator.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
//    	System.out.println(ClassLoader.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
    	
        List<Wave> waves = new WaveReader().readWaves();
        List<Tower> selectedTowers = new CostBasedTowerSelector().selectTowers(500, 5);
        DifferenceCalculator diffCal = new DifferenceCalculator();
        
        String[] estimates = new String[waves.size()];
        for(int i=0; i<waves.size(); i++)
        {
        	estimates[i] = diffCal.calculateDifference(waves.get(i), selectedTowers);
        }   
        
        for(int i=0; i<estimates.length; i++)
        {
        	System.out.print(estimates[i]+" ");
        }
    }

    public String calculateDifference(Wave wave, List<Tower> towers) {

        //Calculate monster statistics
        WaveMonsterData.getWaveRecord(wave);

        //Separate selected towers into air and ground towers
        List<Tower> groundTowers = new LinkedList<Tower>();
        List<Tower> airTowers = new LinkedList<Tower>();

        for(Tower tower : towers)
            if(tower.isAirUnit())
                airTowers.add(tower);
            else
                groundTowers.add(tower);

        //System.out.println();
        //System.out.println("GROUND UNITS: Total firepower of towers: "+(calculateTotalFirePower(groundTowers, WaveMonsterData.avgTimeGround)+" Total HP of monsters: "+WaveMonsterData.totalHPGround));
        //System.out.println("AIR UNITS: Total firepower of towers: "+(calculateTotalFirePower(airTowers, WaveMonsterData.avgTimeAir)+" Total HP of monsters: "+WaveMonsterData.totalHPAir));

        int pcOfGroundUnitsDestroyed = (int) (WaveMonsterData.totalHPGround==0? 100 : (calculateTotalFirePower(groundTowers, WaveMonsterData.avgTimeGround)/WaveMonsterData.totalHPGround)*100);
        int pcOfAirUnitsDestroyed = (int) (WaveMonsterData.totalHPAir==0? 100 : (calculateTotalFirePower(airTowers, WaveMonsterData.avgTimeAir)/WaveMonsterData.totalHPAir)*100);
        
        //System.out.println("\nPERCENTAGE: "+pcOfGroundUnitsDestroyed+" "+pcOfAirUnitsDestroyed);
        
        return "G:"+pcOfGroundUnitsDestroyed+"%,A:"+pcOfAirUnitsDestroyed+"%";
    }

    /**
     * Calculates the total firepower generated by the towers in the specified time
     * @return total firepower
     */
    public float calculateTotalFirePower(List<Tower> towers, float time) {
        float totalFirePower = 0f;

        for(Tower tower : towers) {
            totalFirePower += (tower.getRateOfFire() * tower.getDamage()) * time;
        }

        return totalFirePower;
    }
}