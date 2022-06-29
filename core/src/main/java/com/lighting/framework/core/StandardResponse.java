package com.lighting.framework.core;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;

public class StandardResponse<T> extends BaseResponse<Serializable> {
	
	public StandardResponse(int code, String msg) {
		super(code, msg);
	}

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static StandardResponse data(Object data){
		StandardResponse response = new StandardResponse(200, "操作成功！");
		response.setData(data);
		return response;
	}

	public static StandardResponse data(IPage page){
		StandardResponse response = new StandardResponse(200, "操作成功！");
		response.setData(page);
		return response;
	}
}
