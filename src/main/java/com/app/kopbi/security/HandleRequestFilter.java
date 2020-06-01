package com.app.kopbi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.kopbi.config.JwtTokenProvider;
import com.app.kopbi.exception.DefaultException;
import com.app.kopbi.model.UserPrincipal;

@Component
public class HandleRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String path = request.getServletPath();
		return path.startsWith("/getobject");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = getJwtTokenFromRequest(request);
		if (!jwtTokenProvider.validateToken(jwt)) {
			throw new DefaultException("JWT is Failed");
		}
		try {
			UserPrincipal up = jwtTokenProvider.getJwtUserDetailFromJWT(jwt);
			request.setAttribute("userPrincipal", up);
		}catch(Exception e) {
			throw new DefaultException("Unauthorized");
		}
		filterChain.doFilter(request, response);
	}

	private String getJwtTokenFromRequest(HttpServletRequest request) {
		String jwtToken = request.getHeader("token");
		if (StringUtils.hasText(jwtToken)) {
			return jwtToken;
		}
		return null;
	}

}
