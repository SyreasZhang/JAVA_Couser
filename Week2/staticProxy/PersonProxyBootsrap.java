package staticProxy;

public class PersonProxyBootsrap {

	public static void main(String[] args) {
		PersonProxy pp  = new PersonProxy(new Person());
		pp.setter("张小平", "Male", 23, "5122245566", true);
		System.out.println("---------运行成功--------");
	}

}
