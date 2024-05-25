package kilic.mehmet.stock_exchange.component;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kilic.mehmet.stock_exchange.service.JwtService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private static final String[] WHITE_LIST_URL = {"/auth/",
			//"/stock",
			//"/stock-exchange",
			"/h2-console"};
	
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private final HandlerExceptionResolver handlerExceptionResolver;
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, 
									@NonNull HttpServletResponse response, 
									@NonNull FilterChain filterChain) throws ServletException, IOException {
		
		try {
			String url = request.getServletPath();
			for (String whiteUrl : WHITE_LIST_URL) {
				if(url.contains(whiteUrl)) {
					filterChain.doFilter(request, response);
					return;
				}
			}
			
			final String authHeader = request.getHeader("Authorization");
			final String token;
			final String userEmail;
			
			if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
				throw new MalformedJwtException("Token Failed");
			}
			
			token = authHeader.substring(7);
			userEmail = jwtService.extractUsername(token);
	    
			if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
				if (jwtService.isTokenValid(token, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				} else throw new MalformedJwtException("Token Failed");
			} else throw new MalformedJwtException("Token Failed");
			
			filterChain.doFilter(request, response);
			
		} catch (AccessDeniedException ex) {
			handlerExceptionResolver.resolveException(request, response, null, ex);
		} catch (ExpiredJwtException ex) {
			handlerExceptionResolver.resolveException(request, response, null, ex);
		} catch (MalformedJwtException ex) {
			handlerExceptionResolver.resolveException(request, response, null, ex);
		}
	}
}