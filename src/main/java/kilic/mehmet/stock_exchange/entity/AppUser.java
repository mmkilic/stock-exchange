package kilic.mehmet.stock_exchange.entity;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kilic.mehmet.stock_exchange.enums.Authorities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements UserDetails{
	private static final long serialVersionUID = -6209670748128028671L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//Credential
	@Column(unique=true, nullable = false)
	private String email;
	private String name;
	@JsonIgnore
	private String password;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	@Enumerated(EnumType.ORDINAL)
	private Set<Authorities> authorities;
	
	@JsonIgnore
	@Override
	public String getUsername() {
		return email;
	}
}
