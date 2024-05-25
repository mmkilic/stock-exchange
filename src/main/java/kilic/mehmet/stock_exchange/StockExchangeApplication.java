package kilic.mehmet.stock_exchange;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import kilic.mehmet.stock_exchange.entity.AppUser;
import kilic.mehmet.stock_exchange.enums.Authorities;
import kilic.mehmet.stock_exchange.repository.AppUserRepository;

@SpringBootApplication
public class StockExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockExchangeApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(AppUserRepository userRepo, PasswordEncoder passwordEncoder) {
		return args -> {
			userRepo.save(AppUser.builder()
								.email("admin@asd.com")
								.name("admin")
								.password(passwordEncoder.encode("123"))
								.authorities(Set.of(Authorities.ADM, Authorities.USR))
								.enabled(true)
								.accountNonExpired(true)
								.accountNonLocked(true)
								.credentialsNonExpired(true)
								.build());
			
			userRepo.save(AppUser.builder()
								.email("user@asd.com")
								.name("user")
								.password(passwordEncoder.encode("123"))
								.authorities(Set.of(Authorities.USR))
								.enabled(true)
								.accountNonExpired(true)
								.accountNonLocked(true)
								.credentialsNonExpired(true)
								.build());
		};
	}
	
}
