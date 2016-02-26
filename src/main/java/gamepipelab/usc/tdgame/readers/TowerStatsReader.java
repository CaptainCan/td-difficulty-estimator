package gamepipelab.usc.tdgame.readers;

import gamepipelab.usc.tdgame.entities.Tower;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TowerStatsReader
{
	private final String excelFilePath = "Z:\\Dropbox\\TD-Re\\td-difficulty-estimator-master\\src\\test\\resources\\aae_TDDesignData.xlsm";
//	private final String excelFilePath = "../../src/test/resources/aae_TDDesignData.xlsm";
	private final String sheetName = "TowerStats";
	
	// Map containing key=from, value=to. For eg. <27, 33> means from line 27 to 33 (inclusive), there exists tower definitions.
	private final Map<Integer, Integer> lines = new HashMap<Integer, Integer>();
	
	// Which columns contain which tower property. (0-based index)
	private final int towerNameColNum = 0;
	private final int damageColNum = 1;
	private final int hpColNum = 2;
	private final int armorColNum = 3;
	private final int rangeColNum = 4;
	private final int rateOfFireCOlNum = 5;
	private final int costColNum = 6;
	private final int armorPenetrationColNum = 7;
	private final int rebootTimeColNum = 8;
	private final int isAirUnitColNum = 8;
	
	public TowerStatsReader()
	{
		lines.put(27, 33);  // Gun (Sheriff) tower stats
		lines.put(38, 44);  // Cannon (Blacksmith) tower stats
		lines.put(52, 58);  // Poison (Saloon) tower stats
		lines.put(67, 73);  // Electrical (Snake oil) tower stats
		lines.put(82, 88);  // Scythe tower stats
		lines.put(98, 104); // Towernaut stats
	}	
	
	public List<Tower> readTowerStats()
	{
		List<Tower> towers = new LinkedList<Tower>();
		
		FileInputStream fis;
		Workbook workbk = null;
		try
		{
			fis = new FileInputStream(excelFilePath);
			workbk = new XSSFWorkbook(fis);
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Excel file not found. Please check the path.");
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			System.out.println("Error while opening Excel file.");
			e.printStackTrace();
		}
		
		Sheet towersSheet = workbk.getSheet(sheetName);
		
		String name;
		float damage,HP,armor,range,cost,armorPenetration,rebootTime;
		float rateOfFire;
		boolean isAirUnit;
		for(int startLine : lines.keySet())
		{
			int endLine = lines.get(startLine);
			
			isAirUnit = towersSheet.getRow(startLine-1).getCell(isAirUnitColNum).toString().equals("Air");			
			
			// Skip the line containing the column headers (Damage, HP, Armor...)
			startLine++;
			
			Row row;
			for(int rowNum=startLine+1; rowNum<=endLine; rowNum++)
			{
				Tower tower = null;
				try
				{
					row = towersSheet.getRow(rowNum-1);
					
					name = row.getCell(towerNameColNum).getStringCellValue();
					damage = (float) row.getCell(damageColNum).getNumericCellValue();
					HP = (float) row.getCell(hpColNum).getNumericCellValue();
					armor = (float) row.getCell(armorColNum).getNumericCellValue();
					range = (float) row.getCell(rangeColNum).getNumericCellValue();
					rateOfFire = (float) row.getCell(rateOfFireCOlNum).getNumericCellValue();
					cost = (float) row.getCell(costColNum).getNumericCellValue();
					armorPenetration = (float) row.getCell(armorPenetrationColNum).getNumericCellValue();
					rebootTime = (float) row.getCell(rebootTimeColNum).getNumericCellValue();
					
					tower = new Tower(name, damage, HP, armor, range, rateOfFire, cost, armorPenetration, rebootTime, isAirUnit);
				}
				catch(Exception e)
				{
					System.out.println("Exception while parsing tower stats. Line "+rowNum+" in Excel sheet.");
					e.printStackTrace();
				}				
				
				towers.add(tower);
			}		
		}
		
		try
		{
			workbk.close();
		} 
		catch (IOException e)
		{e.printStackTrace();}
		
		return towers;
	}
}
