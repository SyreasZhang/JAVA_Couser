package com.shape;


import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;

public abstract class Shape implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected ShapeType shapeType;// ��״
	protected Point orgPoint;		// ��������
	protected Color borderColor;	// �߽���ɫ
	protected Color fillColor;	// �����ɫ
	protected BorderType borderType;	// �߽���
	protected BrushType fillType;		// �����
	
	public abstract void draw(Shell s);		// ��ͼ
	public abstract void isMatched(Point p);// p�Ƿ���ͼ����
	
	public void setType(ShapeType st){
		shapeType = st;
	}
	
	public void setOrgPoint(Point p){
		if(p != null){
			orgPoint = p;
		}else{
			orgPoint = new Point(0,0);
		}
	}
	
	public void setBorder(BorderType bt,Color bc){
		borderType = bt;
		borderColor = bc;
	}
	
	public void setFill(BrushType ft,Color fc){
		fillType = ft;
		fillColor = fc;
	}
	
	public Shape(){
		
	}
	
	public Shape(Point p,
			BorderType bt,Color bc,
			BrushType ft,Color fc){
	setOrgPoint(p);
	setBorder(bt, bc);
	setFill(ft, fc);
}
}





