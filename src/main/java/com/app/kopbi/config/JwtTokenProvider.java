package com.app.kopbi.config;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.app.kopbi.model.JwtUserDetail;
import com.app.kopbi.model.UserPrincipal;
import com.app.kopbi.util.UtilService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;

	@Autowired
	UtilService utilService;

	@Autowired
	ObjectMapper mapper;

	public Map generateToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		return generateToken(userPrincipal);
	}

	public Map generateToken(UserPrincipal userPrincipal) {
		Map map = new HashMap();
		LocalDateTime expiryDate = null;

		expiryDate = LocalDateTime.now().plus(Duration.ofMillis(jwtExpirationInMs));

		logger.info("expiryDate:" + expiryDate);

		String jwt = "";
		JwtBuilder jwtBuilder = null;
		try {
			JwtUserDetail jwtUser = new JwtUserDetail();
			jwtUser.setId(userPrincipal.getId());
			jwtUser.setType(userPrincipal.getType());
			jwtUser.setSessionId(userPrincipal.getSessionId());
			
			jwtBuilder = Jwts.builder().setSubject(mapper.writeValueAsString(jwtUser))
					.claim("type", userPrincipal.getType()).setIssuedAt(new Date())
					.setExpiration(utilService.convertToDate(expiryDate)).signWith(SignatureAlgorithm.HS512, jwtSecret);
			jwt = jwtBuilder.compact();
		} catch (JsonProcessingException e) {
			logger.error("Failed generate TOKEN");
		}

		map.put("jwt", jwt);
		map.put("expiryDate", expiryDate);
//		map.put("sessionId", userPrincipal.getSessionId());
		return map;
	}


	public String getSessionIdFromJwtToken(String jwtToken) {
		return jwtToken.substring(jwtToken.length() - 32, jwtToken.length());
	}

	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		logger.info("claims:" + claims.entrySet());
		return Long.parseLong(claims.getSubject());
	}

	public UserPrincipal getJwtUserDetailFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		logger.info("claims:" + claims.entrySet());
		UserPrincipal jwtud = getUserDetails(claims.getSubject());
		return jwtud;
	}


	private UserPrincipal getUserDetails(String token) {
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		UserPrincipal jud = new UserPrincipal();
		try {
			Map map = mapper.readValue(token, Map.class);
			jud = mapper.convertValue(map, UserPrincipal.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		UserPrincipal up = UserPrincipal.create(user);
		return jud;
	}

	public String getUserTypeFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		logger.info("claims:" + claims.entrySet());
		return (String) claims.get("type");
	}

	public boolean validateToken(String authToken) {
		try {
			Claims claim = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody();
			logger.info("validateToken id:" + claim.getSubject() + ", created:" + claim.getIssuedAt() + ", exp:"
					+ claim.getExpiration());
			return true;
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		}
		return false;
	}
}
