package kilic.mehmet.stock_exchange.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import kilic.mehmet.stock_exchange.entity.AppUser;
import kilic.mehmet.stock_exchange.entity.request.LoginRequest;
import kilic.mehmet.stock_exchange.entity.response.LoginResponse;
import kilic.mehmet.stock_exchange.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authService;
	

	@GetMapping("/validation")
	public ResponseEntity<AppUser> validation(HttpServletRequest request) {
	  return ResponseEntity.ok(authService.validation(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
	  return ResponseEntity.ok(authService.login(request));
	}
	
}
