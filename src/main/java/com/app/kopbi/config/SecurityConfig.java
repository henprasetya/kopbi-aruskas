package com.app.kopbi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity // This is the primary spring security annotation that is used to enable web security in a project.
@EnableGlobalMethodSecurity(
		securedEnabled = true, // It enables the @Secured annotation using which you can protect your controller/service methods 
		jsr250Enabled = true, // It enables the @RolesAllowed annotation 
		prePostEnabled = true) // It enables more complex expression based access control syntax with @PreAuthorize and @PostAuthorize annotations 

// WebSecurityConfigurerAdapter : It provides default security configurations and allows other classes to extend it and customize the security configurations by overriding its methods.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// The HttpSecurity configurations are used to configure security functionalities like csrf, sessionManagement, and add rules to protect resources based on various conditions.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
//				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/**").permitAll();

//		http.addFilter(consumerIdFilter());
		
	}
}
