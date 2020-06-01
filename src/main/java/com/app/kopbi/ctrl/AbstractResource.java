package com.app.kopbi.ctrl;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.kopbi.model.UserPrincipal;

/**
 * 
 * @author Henda Prasetya
 * 
 * Base class for all REST API classes
 * 
 */
public abstract class AbstractResource {
	protected Optional<UserPrincipal> currentUser() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		
		if (principal instanceof String && ((String) principal).equalsIgnoreCase("anonymousUser")) {
			throw new UsernameNotFoundException("No user logged in.");
		}
		
		UserPrincipal up = null;
		if (principal instanceof UserPrincipal) {
			up = (UserPrincipal) principal;
		} else {
			throw new UsernameNotFoundException("No user logged in.");
		}
		
		return Optional.ofNullable(up);
	}
	
}
