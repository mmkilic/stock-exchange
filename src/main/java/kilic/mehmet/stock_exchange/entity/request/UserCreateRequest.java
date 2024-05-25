package kilic.mehmet.stock_exchange.entity.request;

import java.util.Set;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kilic.mehmet.stock_exchange.enums.Authorities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
	private String email;
	private String name;
	private String password;
	@Enumerated(EnumType.ORDINAL)
	private Set<Authorities> authorities;
}
