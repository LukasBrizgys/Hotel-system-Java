package lt.ku.hotel.configuration;

import lt.ku.hotel.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	ClientService clientService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
		auth
				.userDetailsService(this.clientService)
				.passwordEncoder(bc);
	}
	protected void configure(HttpSecurity http) throws Exception {
		
		http
				.authorizeRequests()
				.antMatchers("/**").permitAll()
				.antMatchers("/images").permitAll()
				.antMatchers("/login*").permitAll()
				.antMatchers("/register*").permitAll()
				.antMatchers("/rooms*").permitAll()
				.antMatchers("/reserve*").permitAll()
				.antMatchers("/reservations*").permitAll()
				.anyRequest().authenticated()

				.and()
				.formLogin()
				.loginPage("/login")
				.usernameParameter("email").permitAll()
				.defaultSuccessUrl("/")
				.failureUrl("/login-error")
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
