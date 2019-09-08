package com.shape;


import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;

public abstract class Shape implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected ShapeType shapeType;// 形状
	protected Point orgPoint;		// 中心坐标
	protected Color borderColor;	// 边界颜色
	protected Color fillColor;	// 填充颜色
	protected BorderType borderType;	// 边界风格
	protected BrushType fillType;		// 填充风格
	
	public abstract void draw(Shell s);		// 绘图
	public abstract void isMatched(Point p);// p是否在图形内
	
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





