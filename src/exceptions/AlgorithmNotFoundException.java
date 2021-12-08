package exceptions;

public class AlgorithmNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4245441700666820085L;
	
	private String algortihm;
	
	public AlgorithmNotFoundException(String algortihm) {
		this.algortihm = algortihm;
	}
	
	@Override
	public String getMessage() {
		return "Algorithm \"" + algortihm + "\" is not available.";
	}
}
