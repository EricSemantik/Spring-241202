package spring.formation.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "")
public class MonRdvApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MonRdvApiException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MonRdvApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public MonRdvApiException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MonRdvApiException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MonRdvApiException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
