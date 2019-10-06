package com.avk.practice.springsecurityjdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication()
//			.dataSource(datasource)	// spring security is smart enough to auto configure h2 in memory db which is in the classpath with this minimal configuration
//			.withDefaultSchema()
//			.withUser(				// creating test users to go initially with default schema.. hmm works fine.
//						User.withUsername("user")
//							.password("passw")
//							.roles("USER")
//					)
//			.withUser(
//						User.withUsername("admin")
//							.password("pass")
//							.roles("ADMIN")
//					);
			
//		auth.jdbcAuthentication()
//			.dataSource(datasource);
		
		auth.jdbcAuthentication()
			.dataSource(datasource)
			.usersByUsernameQuery("select username, password, enabled from myusers where username = ?")	// if we want to change table names, then we can make our functionality work with these queries.
			.authoritiesByUsernameQuery("select username, authority from myauthorities where username = ?");	// the schema will be fetched from schema.sql and data from data.sql and these two sql files get executed at spring boot start up.
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("USER", "ADMIN")
			.antMatchers("/").permitAll()
			.and().formLogin();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
