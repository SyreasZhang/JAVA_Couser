package matrix;

public class RationalMatrix extends GenericMatrix<Integer>  {

	@Override
	protected Integer add(Integer e1, Integer e2) {
		if(e1.equals(0)&&e2.equals(0))
			return new Integer(0);
		return new Integer(1);
	}

	@Override
	protected Integer multiply(Integer e1, Integer e2) {
		if(e1.equals(0)||e2.equals(0))
			return new Integer(0);
		return new Integer(1);
	}

	@Override
	protected Integer zero() {
		return new Integer(0);
	}

}
