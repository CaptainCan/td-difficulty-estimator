package gamepipelab.usc.tdgame.readers;
import java.util.*;
import java.io.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MonsterTower {
    private HashMap<String,HashMap<String,Float>> relations;

    public MonsterTower(){
        relations = new HashMap<String,HashMap<String,Float>>();
    }

    // Returns number of shots a Tower requires to kill that monster
    public Float getRelation(String monsterName, String towerName)
    {
        if(relations.containsKey(monsterName))
        {
            if(relations.get(monsterName).containsKey(towerName))
            {
                return relations.get(monsterName).get(towerName);
            }
        }
        return null;
    }

    //Reads Monster-Tower relations from the Excel sheet at the specified path
    public boolean setRelations(String path)
    {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(fis);
            //int numberOfSheets = workbook.getNumberOfSheets();
            //System.out.println(numberOfSheets);
            Sheet relationSheet = workbook.getSheet("Monster-Tower");
            //System.out.println(relationSheet);
            Iterator rowIterator = relationSheet.iterator();
            while(rowIterator.hasNext())
            {
                Row row = (Row) rowIterator.next();
                if(row.getRowNum()==0 || row.getRowNum()==1 || row.getRowNum()==2)
                    continue;
                //System.out.println(row.getRowNum());
                Iterator cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();
                    if(cell.getColumnIndex()==0 || cell.getColumnIndex()==1)
                        continue;
                    //System.out.println(cell.getCachedFormulaResultType());
                    String tower = relationSheet.getRow(2).getCell(cell.getColumnIndex()).getStringCellValue();
                    String monster = row.getCell(1).getStringCellValue();
                    if(tower.equals("Damage")||tower.equals("HP")||tower.equals("Armor")||tower.equals("Range")||tower.equals("Rate of Fire")||tower.equals("Gain")||tower.equals("Speed")||tower.equals("Mana")||tower.equals("Monster Air/Ground"))
                        continue;
                    if(1==cell.getCachedFormulaResultType())
                    {
                        HashMap<String,Float> inner;
                        if(relations.containsKey(monster))
                        {
                            inner = relations.get(monster);
                        }
                        else
                        {
                            inner = new HashMap<String,Float>();
                            relations.put(monster, inner);
                        }
                        inner.put(tower,(float) -1);
                        //System.out.println(monster+":"+tower+":"+cell.getStringCellValue());
                    }
                    else
                    {
                        HashMap<String,Float> inner;
                        if(relations.containsKey(monster))
                        {
                            inner = relations.get(monster);
                        }
                        else
                        {
                            inner = new HashMap<String,Float>();
                            relations.put(monster, inner);
                        }
                        inner.put(tower,(float) cell.getNumericCellValue());
                        //System.out.println(monster+":"+tower+":"+cell.getNumericCellValue());

                    }
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
