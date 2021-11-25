package com.lighting.framework.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Captainxero
 * @since 2020-05-21
 */
@TableName("sys_org")
public class Org extends Model<Org> {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_UUID)
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
	@TableField("`desc`")
	private String desc;

	private String pcode;

	private String code;

	private String creater;

	private LocalDateTime createTime;

	private String updater;

	private LocalDateTime updateTime;

	@TableLogic
	private String isdel;
	
	

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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Org{" + "id=" + id + ", name=" + name + ", tel=" + tel + ", desc=" + desc + ", creater=" + creater
				+ ", createTime=" + createTime + ", updater=" + updater + ", updateTime=" + updateTime + ", isdel="
				+ isdel + "}";
	}
}
