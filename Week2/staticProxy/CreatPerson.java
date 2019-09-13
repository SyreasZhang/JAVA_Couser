package staticProxy;
// 创造一个person对象，并且记录数据

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class CreatPerson {

	static String DEFAULT_FILE_PATH = new String("save.txt");	// 默认保存路径
	
	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {
		Person ZXP = new Person("5122245566");
		ZXP.setter("张小平", "Male", 23, "5122245566", true);
		save(ZXP);
		
	}
	
	static public void save(Person p) throws IOException, IllegalArgumentException, IllegalAccessException {
	
		FileWriter bos = new FileWriter(new File(DEFAULT_FILE_PATH));
//		bos.write(p.getClass().getCanonicalName()+"\r\n");
//		bos.write("idNo=" +p.getIdNo()+"\r\n");
//		bos.write("name=" +p.getName()+"\r\n");
//		bos.write("age=" +p.getAge()+"\r\n");
//		bos.write("sex=" +p.getSex()+"\r\n");
//		bos.write("isMerried=" +p.getIsMerried()+"\r\n");
		
		Class<?> clazz = p.getClass();
		bos.write(clazz.getCanonicalName()+"\r\n");
		Field []fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			bos.write(field.getName()+"="+field.get(p)+"\r\n");
		}
		bos.close();
		
	}
}
