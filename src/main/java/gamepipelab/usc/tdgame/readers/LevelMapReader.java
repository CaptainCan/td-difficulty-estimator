package gamepipelab.usc.tdgame.readers;

import gamepipelab.usc.tdgame.specs.LevelMap;
import gamepipelab.usc.tdgame.specs.MapElements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.ExtendedColor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LevelMapReader
{
	//private final String excelFilePath = "C:\\Users\\Ashton\\Desktop\\aae_TDDesignData.xlsm";
//	private final String excelFilePath = "C:\\GoogleDrive\\Courses\\590\\estimator\\td-difficulty-estimator-master\\src\\main\\resources\\aae_TDDesignData.xlsm";
//	private final String excelFilePath = "../../src/test/resources/aae_TDDesignData.xlsm";
	//private final String excelFilePath = "C:\\Users\\Mohit\\Documents\\Github\\td-difficulty-estimator\\src\\test\\resources\\aae_TDDesignData.xlsm";
//	
	public final String excelFilePath = FindExcel.findPath(new File("src/test"));
	//private final String excelFilePath = "/Users/zmt/Documents/USC courses/DR/aae_TDDesignData.xlsm";
	private final String sheetName = "Level2";
	
	// Map boundaries
	private final int startCol = 0, startRow = 0, endCol = 11, endRow = 7;
	
	// Color meanings
	private final int legendStartRow = 16, legendEndRow = 21, legendColorCol = 0, legendColorMeaningCol = 1;
	private Map<String, Integer> colorMeanings = new HashMap<String, Integer>();
	private final String monsterPath = "Monster's Path";
	private final String indestructable = "Indestructible Elements";
	private final String destructable = "Destructible Elements";
	private final String tower = "Towers Placement";
	private final String entrance = "Path Entrance";
	private final String objective = "Objective to defend";
	
	/*public static void main(String args[]) throws Exception
	{
		LevelMapReader lmr = new LevelMapReader();
		lmr.readMap();
	}*/
	
	public LevelMap readMap()
	{
		FileInputStream fis;
		Workbook workbk = null;
		try
		{
//			System.out.println(LevelMapReader.class.getProtectionDomain().getCodeSource().getLocation());
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
		
		Sheet levelSheet = workbk.getSheet(sheetName);
		
		//Read the map legend, i.e which color means what.
		String cellColor = null;
		String colorMeaning = null;
		
		for(int i=legendStartRow; i<=legendEndRow; i++)
		{
			// Color on map
			cellColor = ((ExtendedColor)levelSheet.getRow(i).getCell(legendColorCol).getCellStyle().getFillForegroundColorColor()).getARGBHex();
			
			// Meaning of the above color
			colorMeaning = levelSheet.getRow(i).getCell(legendColorMeaningCol).getStringCellValue();
			
			if(colorMeaning.equals(monsterPath))
				colorMeanings.put(cellColor, MapElements.MONSTER_PATH);
			else if(colorMeaning.equals(indestructable))
				colorMeanings.put(cellColor, MapElements.INDESTRUCTABLE);
			else if(colorMeaning.equals(destructable))
				colorMeanings.put(cellColor, MapElements.DESTRUCTABLE);
			else if(colorMeaning.equals(tower))
				colorMeanings.put(cellColor, MapElements.TOWER);
			else if(colorMeaning.equals(entrance))
				colorMeanings.put(cellColor, MapElements.ENTRANCE);
			else if(colorMeaning.equals(objective))
				colorMeanings.put(cellColor, MapElements.GOAL);
		}
		
		LevelMap lvlMap = new LevelMap(endCol-startCol+1, endRow-startRow+1);
		
		int len = 0;
		for(int i=startRow; i<=endRow; i++)
		{
			for(int j=startCol; j<=endCol; j++)
			{
				cellColor = ((ExtendedColor)levelSheet.getRow(i).getCell(j).getCellStyle().getFillForegroundColorColor()).getARGBHex();
				
				if(colorMeanings.containsKey(cellColor))
				{
					lvlMap.setCell(i, j, colorMeanings.get(cellColor));
					if (lvlMap.getCell(i, j) == 1) {
						len++;
					}
				}
			}
		}
		// set monster path length
		lvlMap.setLength(len);
		//Printing the map
		/*for(int i=startRow; i<=endRow; i++) {
			for (int j = startCol; j <= endCol; j++) {
				System.out.print(lvlMap.getCell(i, j)+" ");
			}
			System.out.println();
		}*/

		try
		{
			workbk.close();
		} 
		catch (IOException e) {e.printStackTrace();}
				
		return lvlMap;
	}
}
