package kilic.mehmet.stock_exchange.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authorities implements GrantedAuthority{

	ADM("ADMINISTRATOR"),
	USR("USER");

    private String value;

    Authorities(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String getAuthority() {
        return name();
    }

}
