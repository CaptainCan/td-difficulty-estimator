package gamepipelab.usc.tdgame.readers;

import gamepipelab.usc.tdgame.entities.Monster;
//import gamepipelab.usc.tdgame.naiveEstimator.WaveMonsterData;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadMonster {
	public Map<String, Monster> monsters;
//	private final String excelFilePath = "/home/ashton/Desktop/aae_TDDesignData.xlsm";
	//public final String filePath = "C:\\Users\\Mohit\\Documents\\Github\\td-difficulty-estimator\\src\\test\\resources\\aae_TDDesignData.xlsm";
	public final String filePath = FindExcel.findPath(new File("src/test"));
	
	//System.out.println(obj);
//	public final String filePath = "../../src/test/resources/aae_TDDesignData.xlsm";
	public ReadMonster(){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Sheet monsterSheet = workbook.getSheetAt(3);
        monsters = new HashMap<String, Monster>();
        // Zombie1
        ReadMonster.setMonster(monsterSheet, 8, monsters);
        // Zombie2
        ReadMonster.setMonster(monsterSheet, 9, monsters);
        // Zombie3
        ReadMonster.setMonster(monsterSheet, 10, monsters);
        // Mickey1
        ReadMonster.setMonster(monsterSheet, 15, monsters);
        // Mickey2
        ReadMonster.setMonster(monsterSheet, 16, monsters);
        // Mickey3
        ReadMonster.setMonster(monsterSheet, 17, monsters);
        // Tommy1
        ReadMonster.setMonster(monsterSheet, 23, monsters);
        // Tommy2
        ReadMonster.setMonster(monsterSheet, 24, monsters);
        // Tommy3
        ReadMonster.setMonster(monsterSheet, 25, monsters);
        // Franken1
        ReadMonster.setMonster(monsterSheet, 31, monsters);
        // Franken2
        ReadMonster.setMonster(monsterSheet, 32, monsters);
        // Franken3
        ReadMonster.setMonster(monsterSheet, 33, monsters);
        // Susie1
        ReadMonster.setMonster(monsterSheet, 38, monsters);
        // Susie2
        ReadMonster.setMonster(monsterSheet, 39, monsters);
        // Susie3
        ReadMonster.setMonster(monsterSheet, 40, monsters);
        // Shaman1
        ReadMonster.setMonster(monsterSheet, 45, monsters);
        // Shaman2
        ReadMonster.setMonster(monsterSheet, 46, monsters);
        // Shaman3
        ReadMonster.setMonster(monsterSheet, 47, monsters);
        // Reggie1
        ReadMonster.setMonster(monsterSheet, 53, monsters);
        // Reggie2
        ReadMonster.setMonster(monsterSheet, 54, monsters);
        // Reggie3
        ReadMonster.setMonster(monsterSheet, 55, monsters);
        // Mummy1
        ReadMonster.setMonster(monsterSheet, 60, monsters);
        // Mummy2
        ReadMonster.setMonster(monsterSheet, 61, monsters);
        // Mummy3
        ReadMonster.setMonster(monsterSheet, 62, monsters);
        // Wereffalo1
        ReadMonster.setMonster(monsterSheet, 67, monsters);
        // Wereffalo2
        ReadMonster.setMonster(monsterSheet, 68, monsters);
        // Wereffalo3
        ReadMonster.setMonster(monsterSheet, 69, monsters);
        // finish 
//        System.out.println(monsters.size() + " monsters have been read successfully!");
	}
    
    private static void setMonster(Sheet monsterSheet, int rowIndex, Map<String, Monster> monsters) {
    	Monster monster = new Monster();
    	Row row = monsterSheet.getRow(rowIndex);
    	for (Cell cell : row) {
    		int columIndex = cell.getColumnIndex();
    		switch (columIndex) {
    		case 1: monster.setName(cell.getStringCellValue()); break;
    		case 2: monster.setDamage((float)cell.getNumericCellValue()); break;
    		case 3: monster.setHP((float)cell.getNumericCellValue()); break;
    		case 4: monster.setArmor((float)cell.getNumericCellValue()); break;
    		case 5: monster.setRange((float)cell.getNumericCellValue());	break;
    		case 6: monster.setRateOfFire((float)cell.getNumericCellValue()); break;
    		case 7: monster.setGain((float)cell.getNumericCellValue()); break;
    		case 8: monster.setSpeed((float)cell.getNumericCellValue()); break;
    		case 9: monster.setMana((float)cell.getNumericCellValue()); break;
    		case 10: monster.setReference(cell.getStringCellValue()); break;
    		case 11: monster.setAirUnit((int)cell.getNumericCellValue() == 1); break;
    		}
    	}
    	
//    	System.out.println("monster name = " + monster.getName() + " isAir = " + monster.isAirUnit());
    	if (!monsters.containsKey(monster.getName())) {
    		monsters.put(monster.getName(), monster);
    	}
//    	System.out.println("  HashMap.size = " + monsters.size());
    }
    /*public static void main(String[] args) {
    	new ReadMonster();
	}*/
    
}
