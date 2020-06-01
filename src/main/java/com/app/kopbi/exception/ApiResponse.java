package com.app.kopbi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ApiResponse {

	@JsonInclude(value = Include.ALWAYS)
	private Boolean success;
	@JsonInclude(value = Include.ALWAYS)
	private String message;
	@JsonInclude(value = Include.NON_NULL)
	private Object data;
	
	public ApiResponse(Boolean success, Object obj) {
		this.success = success;
		
		if(obj instanceof String) {
			this.message = (String)obj;
		}else {
			if(success!=null && success==true)
				this.message = "success";
			else
				this.message = "fail";
			this.setData(obj);
		}
	}
	
	public ApiResponse(Boolean success, String message, Object obj) {
		this.success = success;
		this.message = message;
		this.setData(obj);
	}
	
	public ApiResponse(boolean obj) {
		this.success = obj;
		if(obj) {
			this.message = "success";
		}else {
			this.message = "fail";
		}
	}
	
	public ApiResponse(Object obj) {
		this.success = true;
		this.message = "success";
		this.setData(obj);
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
