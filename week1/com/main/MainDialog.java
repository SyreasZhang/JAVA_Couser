package com.main;

import java.awt.event.MouseAdapter;
import java.util.List;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.Document.Document;
import com.shape.Shape;

public class MainDialog {

	protected Shell shell;
	protected Document<Shape> doc;		// �����洢ͼ����Ϣ

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainDialog window = new MainDialog();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		UpdateProcess updateProcess = new UpdateProcess(shell, doc.Doc);	// ����ͼ���Զ�ˢ�½���
		updateProcess.start();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(1500, 800);
		shell.setText("Drawing");
		// �������������
		
		shell.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {	
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				CreateDialog creatreDialog = new CreateDialog();
				try {
					CreateDialog window = new CreateDialog();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// ��װ��������ʹ��
			}
		});
		
	}

	
	 public static class UpdateProcess extends Thread{
    	 Shell shell;
    	 List<Shape> shapeList;
    	 public UpdateProcess(Shell s,List<Shape> ls) {
    		 shell = s;
    		 shapeList = ls;
		}
    	 @Override
    	public void run() {
    		super.run();
    		while(true) {
	        	 for (Shape shape : shapeList) {
					shape.draw(shell);
				}
	             shell.update();
	             shell.open();
	             try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	         }
    	}
     }
}
