package DanamicProxy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyProxy implements InvocationHandler {

	static String DEFAULT_LOG_PATH = new String("MyProxyLog.txt");	// 默认保存路径
	Object proxied;
	
	public  MyProxy(Object object) {
		proxied = object;	
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		method.invoke(proxied, args);
		System.out.println("调用"+method.getName()+" 参数:"+args[0]);
		writerDown(method.getName(), args[0]);
		return null;
	}
	
	private void writerDown(String methodName,Object value) {		//记录
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(DEFAULT_LOG_PATH),true));
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
