package com.app.kopbi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails {
	
	private String id;

	private String name;
	
	private String nip;

	private String type;
	
	@JsonIgnore
	private LocalDateTime lastSuccessLogin;
	
	@JsonIgnore
	private LocalDateTime lastFailLogin;
	
	@JsonIgnore
	private LocalDateTime lastChangedPassword;
	
	@JsonIgnore
	private LocalDateTime lastSubmitForm;
	
	@JsonIgnore
	private int passFail;
	
	@JsonIgnore
	private int scFail;
	
	@JsonIgnore
	private LocalDateTime lastAccessAt;
	
	@JsonIgnore
	private String sessionId;//session untuk relate user dan login, gak dipake buat authentikasi
	
	@JsonIgnore
	private Collection<? extends GrantedAuthority> authorities;
	
	@JsonIgnore
	private String password;
	
	public UserPrincipal() {
		
	}
	

	public void resetFailCounter() {
		this.setPassFail(0);
		this.setScFail(0);
	}
	
	
	/**
	 * Informasi yang perlu disimpen utk user yang sedang login, bisa dipopulate disini
	 * @param user
	 * @return
	 */
	public static UserPrincipal create(Map<String, Object> data, String sessionId) {


		UserPrincipal up= new UserPrincipal();
		up.setName(data.get("nama").toString());
		up.setId(data.get("nomorAnggota").toString());
		up.setNip(data.get("nomorNik").toString());
		up.setSessionId(sessionId);
		up.setType(data.get("kodeJabatan").toString());
		return up;
	}


	public String getId() {
		return id;
	}



	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}

	public LocalDateTime getLastAccessAt() {
		return lastAccessAt;
	}

	public void setLastAccessAt(LocalDateTime lastAccessAt) {
		this.lastAccessAt = lastAccessAt;
	}

	public int getPassFail() {
		return passFail;
	}

	public void setPassFail(int passFail) {
		this.passFail = passFail;
	}

	public int getScFail() {
		return scFail;
	}

	public void setScFail(int scFail) {
		this.scFail = scFail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setId(String id) {
		this.id = id;
	}


	public LocalDateTime getLastSuccessLogin() {
		return lastSuccessLogin;
	}

	public void setLastSuccessLogin(LocalDateTime lastSuccessLogin) {
		this.lastSuccessLogin = lastSuccessLogin;
	}

	public LocalDateTime getLastFailLogin() {
		return lastFailLogin;
	}

	public void setLastFailLogin(LocalDateTime lastFailLogin) {
		this.lastFailLogin = lastFailLogin;
	}

	public LocalDateTime getLastChangedPassword() {
		return lastChangedPassword;
	}

	public void setLastChangedPassword(LocalDateTime lastChangedPassword) {
		this.lastChangedPassword = lastChangedPassword;
	}

	@JsonIgnore
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public LocalDateTime getLastSubmitForm() {
		return lastSubmitForm;
	}

	public void setLastSubmitForm(LocalDateTime lastSubmitForm) {
		this.lastSubmitForm = lastSubmitForm;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.id;
	}

}
