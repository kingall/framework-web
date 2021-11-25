package com.lighting.framework.core;

import java.io.Serializable;

public class StandardResponse<T> extends BaseResponse<Serializable> {
	
	public StandardResponse(int code, String msg) {
		super(code, msg);
	}

	private T domain;

	public T getDomain() {
		return domain;
	}

	public void setDomain(T domain) {
		this.domain = domain;
	}
	
}
