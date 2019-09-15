package matrix;

public class TestIntegerMaxtix {
	public static void main(String[] args) {
		Integer [][]matrix1 = new Integer[][] {{1,1,1},
												{2,2,2},
												{3,3,3}};
//		System.out.println("row:"+matrix1.length);					
//		System.out.println("column:"+matrix1[0].length);
		Integer [][]matrix2 = new Integer[][] {{1,1,1},
												{2,2,2},
												{3,3,3}};
		
		Number [][]addResult = new IntegerMatric().addMatrix(matrix1, matrix2);
		// *不能使用Integer[][]来接收
		Number [][]multiplyResult = new IntegerMatric().multiplyMatrix(matrix1, matrix2);
		IntegerMatric.printResult(addResult, '|');
		IntegerMatric.printResult(multiplyResult, '|');

	}
}
