package kilic.mehmet.stock_exchange.entity.response;

import kilic.mehmet.stock_exchange.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	private String token;
	private AppUser user;
}
