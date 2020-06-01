package com.app.kopbi.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.kopbi.exception.DefaultException;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

//	@Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        String path = request.getServletPath();
//        return !path.startsWith("/api/");
//    }
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = getJwtTokenFromRequest(request);
		logger.info("jwt:"+jwt);
		if(jwt==null || !StringUtils.hasText(jwt)) {
			logger.info("jwt null or not valid! throw exception!");
			throw new DefaultException();
		}
		
	}
	
	private String getJwtTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("token");
		if (StringUtils.hasText(bearerToken)) {
			bearerToken = bearerToken.replace("\"","");
			return bearerToken;
		}
		return null;
	}

}
