package Processor;

import java.io.File;

public class ClassFileLoader {
	public static Class loadClass(File file) throws ClassNotFoundException {

		String pathName = file.toString();								// 将路径转换成包名
		String fileName = pathName.substring(pathName.indexOf("bin")+4, pathName.lastIndexOf(".class"));
		// System.out.println(fileName);
		
		return Class.forName(fileName.replace('\\', '.'));
	}
}
