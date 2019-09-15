package matrix;

public class IntegerMatric extends GenericMatrix<Integer>{

	@Override
	protected Integer add(Integer e1, Integer e2) {
		return e1+e2;
	}

	@Override
	protected Integer multiply(Integer e1, Integer e2) {	
		return e1*e2;
	}

	@Override
	protected Integer zero() {
		return new Integer(0);
	}
}
