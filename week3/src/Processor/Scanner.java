package Processor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public  class Scanner {
	public static List<File> getClassFile(String url){
		File path = new File(url);
		List<File> result = new ArrayList<File>();
		if(!path.exists()) {
			System.out.println("文件不存在，请检查路径");
			return result;
		}
		
		if(path.isDirectory()) {
			String []subFiles = path.list();
			for (String string : subFiles) {
				result.addAll(getClassFile(path.toString()+"\\"+string));
			}
		}
		
		else {
			if(url.endsWith(".class")) {
				System.out.println("扫描到"+url);
				result.add(path);
			}
		}
		
		return result;
	}
}
