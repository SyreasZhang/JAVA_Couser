package serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Bootsrap {
	public static void main(String[] args) throws Exception {
		File f = new File("serializable");
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		// 1、子类、父类都被序列化
		son1 test1 = new son1();
		test1.Name = "张三"; 	test1.StudentID = "001";
		
		oos.writeObject(test1);
		oos.flush();
		long f1 = f.length(); // 第一次文件大小
		System.out.println("1、子类、父类都被序列化");
		System.out.println(test1);
		System.out.println("文件大小变化:"+f1);
		
		// 2、父类未序列化，子类序列化
		son2 test2 = new son2();
		test2.Name = "张四"; 	test2.StudentID = "002";

		oos.writeObject(test2);
		oos.flush();
		long f2 = f.length();		// 第二次文件大小
		System.out.println("2、父类未序列化，子类序列化");
		System.out.println(test2);
		System.out.println("文件大小变化:"+(f2-f1));
		
		// 3、父类序列化,子类未序列化
		son3 test3 = new son3();
		test3.Name = "张五"; 	test3.StudentID = "003";
		
		oos.writeObject(test3);
		oos.flush();
		long f3 = f.length();
		System.out.println("3、父类序列化,子类未序列化");
		System.out.println(test3);
		System.out.println("文件大小变化:"+(f3-f2));
		oos.close();
		
		
		
		// 将信息取出来
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream  ois = new ObjectInputStream(fis);
		son1 test12 = new son1();
		son2 test22 = new son2();
		son3 test32 = new son3();
		test12 = (son1)ois.readObject();
		test22 = (son2)ois.readObject();
		test32 = (son3)ois.readObject();
		System.out.println("读取的结果");
		System.out.println(test12);
		System.out.println(test22);
		System.out.println(test32);
		ois.close();
		
		/*
		 运行结果
		1、子类、父类都被序列化
			张三001
			文件大小变化:133
		2、父类未序列化，子类序列化
			张四002
			文件大小变化:57
		3、父类序列化,子类未序列化
			张五003
			文件大小变化:113
		读取的结果
			张三001
			null002
			张五003
		 */
		/*/
		  由运行结果可知父类未序列化子类中的父类属性不能正常序列化
		  父类序列化，子类不继承序列化，没有影响，而且可以节约空间
		 */
	}
}
