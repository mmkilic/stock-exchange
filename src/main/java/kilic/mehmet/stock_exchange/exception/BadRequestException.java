package kilic.mehmet.stock_exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
	private static final long serialVersionUID = -6700099419185504589L;
	
	public BadRequestException(String message) {
		super(message);
	}
}
