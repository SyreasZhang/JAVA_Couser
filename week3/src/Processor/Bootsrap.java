package Processor;

public class Bootsrap {
	public static void main(String[] args) throws Exception {
		TableProcessor tp = new TableProcessor();
		String sql = tp.process("./bin/Processor");
		System.out.println(sql);
	}
}
