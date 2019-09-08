package com.shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

public class Square extends Shape {

	private static final long serialVersionUID = 11L;
	private int sideLength = 100;		// ±ß³¤
	
	public int getSideLength() {
		return sideLength;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}
	
	public void draw(Shell shell) {
		if(sideLength == 0) {
			sideLength = 100;
		}
		final  Canvas canvas = new Canvas(shell,SWT.NO_REDRAW_RESIZE);
        canvas.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setBackground(new Color(null, 255,255,0));
				e.gc.fillOval(0,0,sideLength,sideLength);	
			}
		});
	}

	@Override
	public void isMatched(Point p) {
		// TODO Auto-generated method stub
		
	}
	
	public Square(){
		setType(ShapeType.SQUARE);
	}
	
	public Square(int sideLength,Point p,
			BorderType bt,Color bc,
			BrushType ft,Color fc){
		super(p, bt, bc, ft, fc);
		setType(ShapeType.SQUARE);
		setSideLength(sideLength);
	}

}
