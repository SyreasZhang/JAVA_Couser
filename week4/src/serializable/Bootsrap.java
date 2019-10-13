package serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Bootsrap {
	public static void main(String[] args) throws Exception {
		File f = new File("serializable");
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		// 1�����ࡢ���඼�����л�
		son1 test1 = new son1();
		test1.Name = "����"; 	test1.StudentID = "001";
		
		oos.writeObject(test1);
		oos.flush();
		long f1 = f.length(); // ��һ���ļ���С
		System.out.println("1�����ࡢ���඼�����л�");
		System.out.println(test1);
		System.out.println("�ļ���С�仯:"+f1);
		
		// 2������δ���л����������л�
		son2 test2 = new son2();
		test2.Name = "����"; 	test2.StudentID = "002";

		oos.writeObject(test2);
		oos.flush();
		long f2 = f.length();		// �ڶ����ļ���С
		System.out.println("2������δ���л����������л�");
		System.out.println(test2);
		System.out.println("�ļ���С�仯:"+(f2-f1));
		
		// 3���������л�,����δ���л�
		son3 test3 = new son3();
		test3.Name = "����"; 	test3.StudentID = "003";
		
		oos.writeObject(test3);
		oos.flush();
		long f3 = f.length();
		System.out.println("3���������л�,����δ���л�");
		System.out.println(test3);
		System.out.println("�ļ���С�仯:"+(f3-f2));
		oos.close();
		
		
		
		// ����Ϣȡ����
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream  ois = new ObjectInputStream(fis);
		son1 test12 = new son1();
		son2 test22 = new son2();
		son3 test32 = new son3();
		test12 = (son1)ois.readObject();
		test22 = (son2)ois.readObject();
		test32 = (son3)ois.readObject();
		System.out.println("��ȡ�Ľ��");
		System.out.println(test12);
		System.out.println(test22);
		System.out.println(test32);
		ois.close();
		
		/*
		 ���н��
		1�����ࡢ���඼�����л�
			����001
			�ļ���С�仯:133
		2������δ���л����������л�
			����002
			�ļ���С�仯:57
		3���������л�,����δ���л�
			����003
			�ļ���С�仯:113
		��ȡ�Ľ��
			����001
			null002
			����003
		 */
		/*/
		  �����н����֪����δ���л������еĸ������Բ����������л�
		  �������л������಻�̳����л���û��Ӱ�죬���ҿ��Խ�Լ�ռ�
		 */
	}
}
