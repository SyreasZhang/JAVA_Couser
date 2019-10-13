package thread;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MatrixTest {

	static int dimension = 10000;
	static int threadNum = 8;
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		int[][] matrix1 = new int[dimension][dimension];
		int[][] matrix2 = new int[dimension][dimension];
		int[][] matrix3 = new int[dimension][dimension];
		RandomMaticx(matrix1, matrix2, matrix3);
		//long time1 = singleThread(matrix1, matrix2, matrix3, dimension);
		long time2 = multiyThread(matrix1, matrix2, matrix3, dimension);
		long time3 = threadPool(matrix1, matrix2, matrix3, dimension,threadNum);
		//System.out.println("���߳�:"+time1+"ms");
		System.out.println("���߳��߳�:"+time2+"ms");
		System.out.println(threadNum+"�߳��̳߳�:"+time3+"ms");
	}
	
	public static long singleThread(int [][]matrix1,int [][]matrix2,int [][]matrix3,int dimension) {	
		long starTime = System.currentTimeMillis();
		// ����
		for(int i = 0;i<dimension;i++) {
			for(int j = 0;j<dimension;j++) {
				int sum = 0;
				for (int k = 0; k < dimension; k++) {
					sum+= matrix1[i][k]*matrix2[k][j];
				}
				matrix3[i][j] = sum;
			}
		}
		long endTime = System.currentTimeMillis();
		return endTime - starTime;
	}
	
	
	static int i,j,k;			// ѭ��ʹ��
	public static long multiyThread(int [][]matrix1,int [][]matrix2,int [][]matrix3,int dimension) {
		// ��ÿһ������ֳ�4�飬��8������
		// �����ǽ�4��С����ϲ���ʱ��
		ArrayList<Thread> threads = new ArrayList<Thread>();
		long starTime = System.currentTimeMillis();
		for(i = 0;i<2;i++) {
			for(j = 0;j<2;j++) {
				for(k = 0;k<2;k++) {		
					// ����С����
					Thread temp = new Thread(new Runnable() {
						int rowStart =  i*dimension/2;
						int columnStart = j*dimension/2;
						int countStart = k*dimension/2;
						int offset = dimension/2;
						@Override
						public void run() {
							for(int o =rowStart;o<rowStart+offset;o++) {
								for(int m = columnStart;m<columnStart+offset;m++) {
									int sum = 0;
									for (int n = countStart; n < countStart+offset; n++) {
										sum+= matrix1[o][n]*matrix2[n][m];
									}
									matrix3[k][m] += sum;
								}
							}
//
						}
					});
					temp.start();
					threads.add(temp);
				}
			}
		}
		
		for (Thread thread : threads) {
			if(thread.isAlive()) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		long endTime = System.currentTimeMillis();
		return endTime - starTime;
	}
	
	public static long threadPool(int [][]matrix1,int [][]matrix2,int [][]matrix3,int dimension,int threadNum) {
		long startTime = System.currentTimeMillis();
		
		ExecutorService fixPool = Executors.newFixedThreadPool(threadNum);
		for(i = 0;i<2;i++) {
			for(j = 0;j<2;j++) {
				for(k = 0;k<2;k++) {		
					// ����С����
					Runnable temp = new Runnable() {
						int rowStart =  i*dimension/2;
						int columnStart = j*dimension/2;
						int countStart = k*dimension/2;
						int offset = dimension/2;
						@Override
						public void run() {
							for(int o =rowStart;o<rowStart+offset;o++) {
								for(int m = columnStart;m<columnStart+offset;m++) {
									int sum = 0;
									for (int n = countStart; n < countStart+offset; n++) {
										sum+= matrix1[o][n]*matrix2[n][m];
									}
									matrix3[k][m] += sum;
								}
							}
//
						}
					};
					fixPool.execute(temp);
				}
				
			}
		}
		fixPool.shutdown();
		while(true) {
			if(fixPool.isTerminated()) {
				break;
			}
			else {
				
			}
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	
	public static void RandomMaticx(int [][]matrix1,int [][]matrix2,int [][]matrix3) {		// ������ֵ
		for(int i = 0 ;i<dimension;i++) {
			for(int j =0 ;j<dimension;j++) {
				matrix1[i][j] = new Random().nextInt();
				matrix2[i][j] = new Random().nextInt();
				matrix3[i][j] = 0;
			}
		}
	}
	
//	public static void MatrixAdd(int [][]matrix1,int [][]matrix2,int dimension) {
//		int a;
//		// ��Ϊ�����Ǻϲ�����û�н�����ֵ���أ�ֻ����
//		for(int i = 0 ;i<dimension;i++) {
//			for(int j =0 ;j<dimension;j++) {
//				a = matrix1[i][j] + matrix2[i][j];
//			}
//		}
//	}
}
