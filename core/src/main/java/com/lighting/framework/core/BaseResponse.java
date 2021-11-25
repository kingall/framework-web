package com.lighting.framework.core;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 *
 */
public class BaseResponse<I extends Serializable> {
	public int code;
	public String msg;
	public String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BaseResponse() {

	}

	public BaseResponse(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
