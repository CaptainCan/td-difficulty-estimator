package gamepipelab.usc.tdgame.naiveEstimator;

import gamepipelab.usc.tdgame.entities.Tower;
import gamepipelab.usc.tdgame.readers.TowerStatsReader;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * For a naive difficulty estimate, we just select air and ground towers
 * based on the player's initial money. This selection is made by choosing
 * the cheapest air tower, then choosing the cheapest ground tower, and so on,
 * until all player money is exhausted. Towers which slow down the enemy are ignored.
 *
 * Created by ashton on 10/9/15.
 */
public class CostBasedTowerSelector {

    private Comparator<Tower> towerComparator = new Comparator<Tower>() {
        public int compare(Tower t1, Tower t2) {
            if(t1.getCost() < t2.getCost())
                return -1;
            else if(t1.getCost() > t2.getCost())
                return 1;
            else
                return 0;
        }
    };

    /*public static void main(String args[]) throws Exception
    {
        new CostBasedTowerSelector().selectTowers(1000, 5);
    }*/

    public List<Tower> selectTowers(int initialMoney, int totalTowerPositions) {
        List<Tower> allTowers = new TowerStatsReader().readTowerStats();

        PriorityQueue<Tower> airTowers = new PriorityQueue<Tower>(allTowers.size(), towerComparator);
        PriorityQueue<Tower> groundTowers = new PriorityQueue<Tower>(allTowers.size(), towerComparator);

       for(Tower tower : allTowers) {
            if(tower.isAirUnit())
                airTowers.add(tower);
            else
                groundTowers.add(tower);
        }

        List<Tower> selectedTowers = new LinkedList<Tower>();

        int remainingMoney = initialMoney;
        boolean chooseAirTower = true;
        boolean wasTowerChosen = true;

        int totalTowersChosen = 0;

        while(wasTowerChosen)
        {
            wasTowerChosen = false;

            // Time to choose air tower AND we have money remaining
            if(chooseAirTower && totalTowersChosen < totalTowerPositions && remainingMoney-airTowers.peek().getCost() >= 0)
            {
                Tower airTower = airTowers.remove();
                selectedTowers.add(airTower);
                remainingMoney -= airTower.getCost();
                chooseAirTower = false;
                wasTowerChosen = true;
                totalTowersChosen++;
            }

            // Time to choose air tower AND we have money remaining
            if(!chooseAirTower && totalTowersChosen < totalTowerPositions && remainingMoney-groundTowers.peek().getCost() >= 0)
            {
                Tower groundTower = groundTowers.remove();
                selectedTowers.add(groundTower);
                remainingMoney -= groundTower.getCost();
                chooseAirTower = true;
                wasTowerChosen = true;
                totalTowersChosen++;
            }
        }

        /*for(Tower tower : selectedTowers)
            System.out.println(tower);*/

        return selectedTowers;
    }

}
