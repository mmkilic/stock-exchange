package kilic.mehmet.stock_exchange.exception;


import java.security.SignatureException;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

	//400
	@ExceptionHandler
	public ResponseEntity<BadRequestException> handleException(BadRequestException ex) {
		return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<MethodArgumentNotValidException> handleException(MethodArgumentNotValidException ex) {
		return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<IllegalStateException> handleException(IllegalStateException ex) {
		return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
	}
	
	//401
	@ExceptionHandler
	public ResponseEntity<BadCredentialsException> handleException(BadCredentialsException ex) {
		return new ResponseEntity<>(ex, HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler
	public ResponseEntity<UsernameNotFoundException> handleException(UsernameNotFoundException ex) {
		return new ResponseEntity<>(ex, HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler
	public ResponseEntity<InsufficientAuthenticationException> handleException(InsufficientAuthenticationException ex) {
		return new ResponseEntity<>(ex, HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler
	public ResponseEntity<AccountStatusException> handleException(AccountStatusException ex) {
		return new ResponseEntity<>(ex, HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler
	public ResponseEntity<InvalidCsrfTokenException> handleException(InvalidCsrfTokenException ex) {
		return new ResponseEntity<>(ex, HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler
	public ResponseEntity<MalformedJwtException> handleException(MalformedJwtException ex) {
		return new ResponseEntity<>(ex, HttpStatus.UNAUTHORIZED);
	}
	
	//404
	@ExceptionHandler
	public ResponseEntity<ObjectNotFoundException> handleException(ObjectNotFoundException ex) {
		return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<NoHandlerFoundException> handleException(NoHandlerFoundException ex) {
		return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<SignatureException> handleException(SignatureException ex) {
		return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
	}
	
	//406
	@ExceptionHandler
	public ResponseEntity<AccessDeniedException> handleException(AccessDeniedException ex) {
		return new ResponseEntity<>(ex, HttpStatus.NOT_ACCEPTABLE);
	}
	
	//500
	@ExceptionHandler
	public ResponseEntity<JdbcSQLIntegrityConstraintViolationException> handleException(JdbcSQLIntegrityConstraintViolationException ex) {
		return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//502
	@ExceptionHandler
	public ResponseEntity<HttpRequestMethodNotSupportedException> handleException(HttpRequestMethodNotSupportedException ex) {
		return new ResponseEntity<>(ex, HttpStatus.BAD_GATEWAY);
	}
	
	
}

