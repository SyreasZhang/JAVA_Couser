package matrix;

public abstract class GenericMatrix <E extends Number>{
	protected abstract E add(E e1,E e2);
	protected abstract E multiply(E e1,E e2);
	protected abstract E zero();
	
	public  E[][] addMatrix(E[][] matrix1, E[][] matrix2	 ) {
		int row1 = matrix1.length;				// 行数
		int column1 = matrix1[0].length;		// 列数
		int row2 = matrix2.length;
		int column2 = matrix2[0].length;
		
		if(row1 != row2||column1!=column2) {	// 判读行列是否一致
			System.out.println("行列不一致无法运算");
			return matrix1;
		}
		
		@SuppressWarnings("unchecked")
		E[][] result = (E[][])new Number[row1][column1];
		for(int i = 0;i<row1;i++) {
			for(int j = 0;j<column1;j++) {
				result[i][j] = add(matrix1[i][j] , matrix2[i][j]);
			}
		}
		return result;
	}
	
	public E[][] multiplyMatrix(E[][] matrix1, E[][] matrix2	 ) {
		int row1 = matrix1.length;				// 行数
		int column1 = matrix1[0].length;		// 列数
		int row2 = matrix2.length;
		int column2 = matrix2[0].length;
		
		if(row2 != column1) {	// 判读是否能进行运算
			System.out.println("无法运算");
			return matrix1;
		}
		
		@SuppressWarnings("unchecked")
		E[][] result = (E[][])new Number[row1][column2]; 
		for(int i = 0;i<row1;i++) {
			for(int j = 0;j<column2;j++) {
			//	E sum = (E)new Number();  错误
				E sum = zero();
				for(int k = 0; k <column1 ;k++) {
					sum = add(sum,multiply(matrix1[i][k],matrix2[k][j]));
				}
				result[i][j] = sum;
			}
		}
		return result;
	}
	
	 public static void printResult(Number[][] matrix,char option) {
		int row = matrix.length;				
		int column = matrix[0].length;		
		System.out.println();
		for(int i = 0 ;i<row;i++) {
			System.out.print(option+" ");
			for (int j = 0; j < column; j++) {
				System.out.print(matrix[i][j].toString()+" "+option+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
