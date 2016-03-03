package gamepipelab.usc.tdgame.readers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindExcel {
	static List<String> files = new ArrayList<String>();
	static String myfile;
		public static void main(String args[]) {
		// TODO Auto-generated constructor stub
			myfile = findPath(new File("src/test"));
			System.out.println(myfile);
			}

		public static String findPath(File node){
			String excelFile = "";
			if(node.isFile())
			{
				files.add(node.getAbsolutePath());
				
			}
			if(node.isDirectory()){
				String[] subNote = node.list();
				for(String filename : subNote){
					findPath(new File(node, filename));
				}
			}
			for (String file : files){
				if((file.endsWith("xlsm") || file.endsWith(".xls")) && !file.contains("~"))
					excelFile = file;
		}
		return excelFile;
	}
}