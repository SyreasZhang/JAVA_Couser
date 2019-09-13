package staticProxy;
// person代理类

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonProxy extends Person{ 
	static String DEFAULT_LOG_PATH = new String("log.txt");	// 默认保存路径
	private Person person;		// 代理对象

	public PersonProxy(Person p) {
		person = p;
	}
	
	@Override
	public void setName(String name) {
		person.setName(name);
		writerDown("setName", name);
	}

	@Override
	public void setSex(String sex) {
		person.setSex(sex);
		writerDown("setSex",sex);
	}

	@Override
	public void setAge(Integer age) {
		person.setAge(age);
		writerDown("setAge", age);
	}

	@Override
	public void setIdNo(String idNo) {
		person.setIdNo(idNo);
		writerDown("setIdNo", idNo);
	}

	@Override
	public void setIsMerried(Boolean isMerried) {
		person.setIsMerried(isMerried);
		writerDown("setIsmerried", isMerried);
	}

	@Override
	public void setter(String name, String sex, Integer age, String idNo, Boolean isMerried) {
		setName(name);
		setSex(sex);
		setAge(age);
		setIdNo(idNo);
		setIsMerried(isMerried);
	}
	
	private void writerDown(String methodName,Object value) {		//记录
		File file = new File(DEFAULT_LOG_PATH);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
			bw.write("时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))
					+"；方法名称："+methodName
					+"；参数："+value.toString());
			bw.newLine();
			bw.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	
	
}
