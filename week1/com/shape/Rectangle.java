package com.shape;

import java.awt.Point;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

public class Rectangle extends Shape {


	private static final long serialVersionUID = 13L;
	private int height;
	private int width;
	
	

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public  Rectangle() {
		this.shapeType = ShapeType.RECTANGlE;
	}
	
	public Rectangle(int heigth,int width,Point p,
			BorderType bt,Color bc,
			BrushType ft,Color fc){
		super(p, bt, bc, ft, fc);
		setType(ShapeType.SQUARE);
		setWidth(width);
		setHeight(heigth);
	}
	@Override
	public void draw(Shell shell) {
		// TODO Auto-generated method stub
		if(width == 0) {
			width = 100;
		}
		if(height == 0) {
			height = 100;
		}
		final  Canvas canvas = new Canvas(shell,SWT.NO_REDRAW_RESIZE);
        canvas.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setBackground(new Color(null, 255,255,0));
				e.gc.fillOval(0,0,width,height);	
			}
		});
	}

	@Override
	public void isMatched(Point p) {
		// TODO Auto-generated method stub

	}

}
