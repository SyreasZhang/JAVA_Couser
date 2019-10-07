package Processor;

@Entity
public class Person {
	@Column("uName")
	public String name;
	@Column
	public Integer age;
	@ID()
	public String ID;
}
