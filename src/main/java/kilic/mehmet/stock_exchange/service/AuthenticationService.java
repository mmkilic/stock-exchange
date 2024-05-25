package kilic.mehmet.stock_exchange.service;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import kilic.mehmet.stock_exchange.entity.AppUser;
import kilic.mehmet.stock_exchange.entity.request.LoginRequest;
import kilic.mehmet.stock_exchange.entity.response.LoginResponse;
import kilic.mehmet.stock_exchange.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final AppUserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AppUser validation(HttpServletRequest request) {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;
		
		if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
			throw new BadCredentialsException("Token is incorrect."); 
		}
		refreshToken = authHeader.substring(7);
		userEmail = jwtService.extractUsername(refreshToken);
		var user = this.userRepo.getByEmail(userEmail).orElseThrow(() -> new BadCredentialsException("Token - User conflict occurred."));
		
		if(!jwtService.isTokenValid(refreshToken, user))
			throw new BadCredentialsException("Token is not valid.");
		
		return user;
	}
	
	public LoginResponse login(LoginRequest request) {
		var user = userRepo.getByEmail(request.getEmail())
				.orElseThrow(() -> new BadCredentialsException("User name or password is incorrect."));
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
			throw new BadCredentialsException("User name or password is incorrect.");
		
		if(!user.isAccountNonLocked())
			throw new BadCredentialsException("User has been locked. Please change your password.");
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					request.getEmail(),
					request.getPassword()
			));
		} catch (Exception e) {
			throw new BadCredentialsException("User name or password is incorrect.");
		}
		
		return LoginResponse.builder()
							.token(jwtService.generateToken(user))
							.user(user)
							.build();
	}

}
