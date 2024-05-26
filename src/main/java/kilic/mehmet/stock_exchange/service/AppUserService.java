package kilic.mehmet.stock_exchange.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kilic.mehmet.stock_exchange.entity.AppUser;
import kilic.mehmet.stock_exchange.entity.request.UserCreateRequest;
import kilic.mehmet.stock_exchange.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService{
	
	private final AppUserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	
	public List<AppUser> getAll() {
		return userRepo.findAll();
	}
	
	@Transactional
	public AppUser create(UserCreateRequest userRequest) {
		var user = AppUser.builder()
				.email(userRequest.getEmail())
				.name(userRequest.getName())
				.password(passwordEncoder.encode(userRequest.getPassword()))
				.authorities(userRequest.getAuthorities())
				.enabled(true)
				.accountNonExpired(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.build();
		return userRepo.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.getByEmail(username).orElseThrow(EntityNotFoundException::new);
	}
}
