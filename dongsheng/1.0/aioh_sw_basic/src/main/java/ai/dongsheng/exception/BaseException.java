package ai.dongsheng.exception;


public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private int code;

	public BaseException(int code) {
		this.code = code;
	}

	public BaseException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public BaseException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
