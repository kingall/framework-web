package com.lighting.framework.security.domain;

/**
 * 用户实体
 * 
 * @author Administrator
 *
 */
public interface AbstractAccountShiro {

	public String getId();

	public String getUsername();

	public String getPassword();

	public String getSalt();

}
