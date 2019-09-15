package matrix;

public class TestRationalMatrix {

	public static void main(String[] args) {
			Integer [][]matrix1 = new Integer[][] {{1,0,1},
													{1,0,1},
													{1,1,0}};
													
			Integer [][]matrix2 = new Integer[][] {{1,1,1},
													{0,0,0},
													{0,0,0}};
			
			Number [][]addResult = new RationalMatrix().addMatrix(matrix1, matrix2);
			// *不能使用Integer[][]来接收
			Number [][]multiplyResult = new RationalMatrix().multiplyMatrix(matrix1, matrix2);
			RationalMatrix.printResult(addResult, '|');
			RationalMatrix.printResult(multiplyResult, '|');
	}

}
