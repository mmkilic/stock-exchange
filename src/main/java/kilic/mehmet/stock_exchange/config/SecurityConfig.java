package kilic.mehmet.stock_exchange.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kilic.mehmet.stock_exchange.component.JwtAuthenticationFilter;
import kilic.mehmet.stock_exchange.enums.Authorities;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private static final String[] WHITE_LIST_URL = {"/auth/**",
			//"/stock/**",
			//"/stock-exchange/**",
			"/h2-console/**"};
	
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
			.csrf(AbstractHttpConfigurer::disable)
         	.authorizeHttpRequests(auth -> auth
         			.requestMatchers(WHITE_LIST_URL).permitAll()
         			.requestMatchers("/stock-exchange").hasAnyAuthority(Authorities.USR.name())
         			.requestMatchers("/stock").hasAnyAuthority(Authorities.USR.name())
         			.requestMatchers("/user").hasAnyAuthority(Authorities.ADM.name())
         			.anyRequest().authenticated())
         	.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
         	.headers(headers -> headers.frameOptions(fo -> fo.sameOrigin()))
         	.authenticationProvider(authenticationProvider)
         	.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
         return http.build();
    }
    
}
