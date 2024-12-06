package spring.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import spring.formation.config.jwt.JwtHeaderAuthorizationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderAuthorizationFilter jwtFilter) throws Exception {
		http.httpBasic(Customizer.withDefaults());

		http.authorizeHttpRequests(authorize -> {
			authorize.requestMatchers("/api/utilisateur/connexion").anonymous(); // Autorisé à tout le monde
			authorize.requestMatchers("/api/utilisateur/**").hasRole("ADMIN");
			authorize.requestMatchers("/api/**").authenticated();
			authorize.requestMatchers("/h2-console/**").permitAll();
			authorize.requestMatchers("/**").permitAll();
		});

		http.csrf(c -> c.ignoringRequestMatchers("/api/**", "/h2-console/**"));

		// Positionner le filtre JWT AVANT le filter
		// UsernamePasswordAuthenticationFilter
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Grace à ce Bean, on pourra injecter un AuthenticationManager directement
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
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
