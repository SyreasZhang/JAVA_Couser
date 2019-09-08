package com.shape;

import java.awt.Point;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

public class Circle extends Shape {

	private static final long serialVersionUID = 12L;
	private int radius;
	
	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}


	public  Circle() {
		this.shapeType = ShapeType.CIRCLE;
	}
	
	public Circle(int radius,Point p,
			BorderType bt,Color bc,
			BrushType ft,Color fc){
		super(p, bt, bc, ft, fc);
		setType(ShapeType.SQUARE);
		setRadius(radius);
	}
	
	@Override
	public void draw(Shell shell) {
		if(radius == 0) {
			radius = 100;
		}
		final  Canvas canvas = new Canvas(shell,SWT.NO_REDRAW_RESIZE);
        canvas.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setBackground(new Color(null, 255,255,0));
				e.gc.fillOval(0,0,radius,radius);	
			}
		});
	}

	@Override
	public void isMatched(Point p) {
		// TODO Auto-generated method stub

	}

}
