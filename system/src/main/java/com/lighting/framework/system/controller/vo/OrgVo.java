package com.lighting.framework.system.controller.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrgVo {

	private String id;

	/**
	 * 组织机构名称
	 */
	private String name;

	/**
	 * 联系电话
	 */
	private String tel;

	/**
	 * 描述
	 */
	private String desc;

	private String pcode;

	private String code;

	private String creater;

	private LocalDateTime createTime;

	private String updater;

	private LocalDateTime updateTime;

	private String isdel;

	private List<OrgVo> children = new ArrayList<OrgVo>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public List<OrgVo> getChildren() {
		return children;
	}

	public void setChildren(List<OrgVo> children) {
		this.children = children;
	}

	public void addChildren(OrgVo orgVo) {
		children.add(orgVo);
	}

}
