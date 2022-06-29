package com.lighting.framework.core;

import java.io.Serializable;

public class RowsResponse<T> extends BaseResponse<Serializable> {
	
	public RowsResponse(int code, String msg) {
		super(code, msg);
	}

	private long total;
	private long size;
	private long pageCount;
	private long current;
	private Iterable<T> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public Iterable<T> getRows() {
		return rows;
	}

	public void setRows(Iterable<T> rows) {
		this.rows = rows;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public static RowsResponse data(Iterable<?> rows){
		RowsResponse response  = new RowsResponse(200,"操作成功！");
		response.setRows(rows);
		return response;
	}

}
