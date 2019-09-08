package com.Document;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Document <E> {
	public  List<E> Doc;
	
	
	public Document(){
		Doc = new ArrayList<E>();
	}
	
	public boolean save(String...path) throws IOException {			// 保存文件
		if(path[0] == null) {	
			path[0] = "./doc.pic"; 
		}
		
		try(ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream(path[0])))	{
			for (E e : Doc) {
				oop.writeObject(e);
			}
		}
		return true;
	}
	
	public boolean open(String ...path) throws IOException, Throwable {			// 打卡文件
		if(path[0] == null) {
			path[0] = "./doc.pic"; 
		}
		
		try(ObjectInputStream oip = new ObjectInputStream(new FileInputStream(path[0])))	{
			Object object;
			while((object = oip.readObject())!= null) {
				Doc.add((E)object);
			}
		}
		return true;
	}
}
