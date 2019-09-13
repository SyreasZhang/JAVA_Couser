package staticProxy;

import DanamicProxy.Setter;

public class Person  implements Setter{
	private String name;
	private String sex;
	private Integer age;
	private String idNo;
	private Boolean isMerried;
	
	// get set方法
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public Boolean getIsMerried() {
		return isMerried;
	}
	public void setIsMerried(Boolean isMerried) {
		this.isMerried = isMerried;
	}
	
	// setter getter
	public void setter(String name,String sex,
	Integer age,String idNo,
	Boolean isMerried) {
		setName(name);
		setSex(sex);
		setAge(age);
		setIdNo(idNo);
		setIsMerried(isMerried);
	}
	
	public void getter(String name,String sex,
			Integer age,String idNo,
			Boolean isMerried) {
				name += this.name;
				sex += this.sex;
				age = this.age;
				idNo += this.idNo;
				isMerried = this.isMerried;
			}
	
	// 构造方法
	
	public void canDo(){
		System.out.println("无所不能");
	}
	public Person(String idNo) {
		super();
		this.idNo = idNo;
	}
	
	public Person() {
		super();
	}
	
	@Override
	public String toString() {
		return name + " " + sex + " " + age + " " + idNo + " " + isMerried; 
	}
}


