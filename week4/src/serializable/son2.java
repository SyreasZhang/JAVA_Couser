package serializable;

import java.io.Serializable;

public class son2 extends father2 implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String StudentID;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.Name+StudentID;
	}
	
}
