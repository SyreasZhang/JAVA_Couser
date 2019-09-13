package staticProxy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;


public class Reflection {
	
	static String DEFAULT_FILE_PATH = new String("save.txt");	// 默认保存路径
	
	public static void main(String[] args) {
		try {
			File file = new File(DEFAULT_FILE_PATH);
			BufferedReader br = new BufferedReader(new FileReader(file));
			Class<?> clazz = Class.forName(br.readLine());
			Object object = clazz.newInstance();
			
			// 读取保存信息并且赋值给object
			String tempLine = new String();
			while((tempLine = br.readLine())!=null) {
				String filedName = tempLine.substring(0,tempLine.indexOf('='));			//属性名
				Field f = clazz.getDeclaredField(filedName);	// 获取属性
				f.setAccessible(true);	// 设置权限
				
				// 用字符串保存下来f的类型(包含包信息java.xxx.xxx)
				String  fieldType = f.toString().substring(f.toString().indexOf(" ")+1, f.toString().lastIndexOf(" "));
				String fieldValue = tempLine.substring(tempLine.indexOf('=')+1);	 // 属性值

				// 1、通过field设置属性值
// 判断类型并设置值(灵活性太差，第一次想到的可以实现的办法)
//				if(fieldType.equals(Integer.class.toString().substring(6))){			// 如果是Integer型
//					f.set(object,new Integer(fieldValue));
//				}else if(fieldType.equals(Boolean.class.toString().substring(6))) {	// 如果是Boolean类型
//					f.set(object,new Boolean(fieldValue));
//				}else {																// 字符串类型
//					f.set(object,fieldValue);
//				}
				// 利用反射获取相应类型的构造方法，从而没有必要判断
				f.set(object, Class.forName(fieldType).getConstructor(String.class).newInstance(fieldValue));
//				f.set(object, fieldValue);		// IllegalArgumentException
				
				// 2、获取相应属性的set方法设置值(不能运行)
//				String methodName = "set"+filedName.substring(0,1).toUpperCase()+filedName.substring(1);	//方法名
//				Method setMethod = clazz.getMethod(methodName,Class.forName(fieldType));			// set方法
//				setMethod.invoke(object, fieldValue);
				// 还是需要判断属性的类型
			}
			br.close();
		
			// 输出信息
			System.out.println(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
