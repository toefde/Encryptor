package Exceptions;

public class WrongInputException extends RuntimeException {

	private static final long serialVersionUID = -1380777193157706882L;
	
	private String msg;
	
	public WrongInputException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}

}
