package kilic.mehmet.stock_exchange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kilic.mehmet.stock_exchange.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	@Query("SELECT u from AppUser u where u.email=(:email)")
	Optional<AppUser> getByEmail(@Param("email") String email);
	
}
