package gamepipelab.usc.tdgame.readers;

import gamepipelab.usc.tdgame.specs.MiniWave;
import gamepipelab.usc.tdgame.specs.Wave;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WaveReader
{
	//private final String excelFilePath = "C:\\Users\\Mohit\\Documents\\Github\\td-difficulty-estimator\\src\\test\\resources\\aae_TDDesignData.xlsm";
//	private final String excelFilePath = "../../src/test/resources/aae_TDDesignData.xlsm";
	private final String sheetName = "Level1";
	public final String excelFilePath = FindExcel.findPath(new File("src/test"));
	// Wave definitions
	private final int startCol = 14, endCol = 31;
	private final int startRow = 1, endRow = 6;
	
	public List<Wave> readWaves()
	{
		List<Wave> waves = new LinkedList<Wave>();
		
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
		
		Sheet lvlSheet = workbk.getSheet(sheetName);	
		
		int currentCol = startCol;
		while(currentCol < endCol)
		{
			Wave wave = new Wave();
			List<MiniWave> miniWaves = new LinkedList<MiniWave>();
			for(int i=startRow; i<=endRow; i++)
			{
				int second = (int)lvlSheet.getRow(i).getCell(currentCol).getNumericCellValue();
				int amount = (int)lvlSheet.getRow(i).getCell(currentCol+1).getNumericCellValue();
				String monsterName = lvlSheet.getRow(i).getCell(currentCol+2).getStringCellValue();
				MiniWave miniWave = new MiniWave(second, amount, monsterName);
				miniWaves.add(miniWave);
			}
			
			wave.setMiniWaves(miniWaves);
			waves.add(wave);
			
			// next wave
			currentCol += 3;
		}
		
		try
		{
			workbk.close();
		} 
		catch (IOException e)
		{e.printStackTrace();}
		
		return waves;
	}
}
