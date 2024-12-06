package spring.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic(Customizer.withDefaults());

		http.authorizeHttpRequests(authorize -> {
			authorize.requestMatchers("/api/utilisateur/**").hasRole("ADMIN");
			authorize.requestMatchers("/api/**").authenticated();
			authorize.requestMatchers("/h2-console/**").permitAll();
			authorize.requestMatchers("/**").permitAll();
		});

		http.csrf(c -> c.ignoringRequestMatchers("/api/**", "/h2-console/**"));

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public UserDetailsService inMemory(PasswordEncoder passwordEncoder) {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(
//				User.withUsername("patient01").password(passwordEncoder.encode("123456")).roles("PATIENT").build());
//		manager.createUser(
//				User.withUsername("praticien01").password(passwordEncoder.encode("123456")).roles("PRATICIEN").build());
//		manager.createUser(
//				User.withUsername("admin").password(passwordEncoder.encode("123456")).roles("ADMIN").build());
//		manager.createUser(User.withUsername("secretaire01").password(passwordEncoder.encode("123456"))
//				.roles("SECRETAIRE").build());
//		return manager;
//	}
}
