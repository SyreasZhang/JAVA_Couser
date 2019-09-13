package DanamicProxy;

import java.lang.reflect.Proxy;

import staticProxy.Person;

public class Bootsrap {
	public static void main(String[] args) {
		Person person = new Person();
		Setter MyPersonProxy = (Setter)Proxy.newProxyInstance(Person.class.getClassLoader(),
				Person.class.getInterfaces(),			// 由于Person类没有实现任何接口
				new MyProxy(person));
		
		MyPersonProxy.setAge(23);
		MyPersonProxy.setIdNo("1234556");
		
	}
}
