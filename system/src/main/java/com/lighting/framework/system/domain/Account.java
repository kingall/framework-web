package com.lighting.framework.system.domain;

import com.lighting.framework.security.domain.AbstractAccountShiro;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
@TableName("sys_account")
public class Account extends Model<Account> implements AbstractAccountShiro {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_UUID)
	private String id;
	private String username;
	@JsonIgnore
	private String password;
	private String salt;
	private String name;
	private String gender;
	private Byte[] portrait;
	private String telephone;
	private String email;
	private String state;
	private String creater;

	private String userId;

	@TableField("create_time")
	private Date createTime;

	private String updater;

	@TableField("update_time")
	private Date updateTime;
	/**
	 * 默认角色
	 */
	@TableField("default_role")
	private String defaultRole;

	@TableField("department_code")
	private String departmentCode;

	@TableField("org_code")
	private String orgCode;

	@TableLogic
	private String isdel;

	private String roleId;


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getIsdel() {
		return isdel;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Byte[] getPortrait() {
		return portrait;
	}

	public void setPortrait(Byte[] portrait) {
		this.portrait = portrait;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(String defaultRole) {
		this.defaultRole = defaultRole;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentId) {
		this.departmentCode = departmentCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", name='" + name + '\'' +
				", gender='" + gender + '\'' +
				", portrait=" + Arrays.toString(portrait) +
				", telephone='" + telephone + '\'' +
				", email='" + email + '\'' +
				", state='" + state + '\'' +
				", creater='" + creater + '\'' +
				", createTime=" + createTime +
				", updater='" + updater + '\'' +
				", updateTime=" + updateTime +
				", defaultRole='" + defaultRole + '\'' +
				", departmentCode='" + departmentCode + '\'' +
				", orgCode='" + orgCode + '\'' +
				", isdel='" + isdel + '\'' +
				", roleId='" + roleId + '\'' +
				'}';
	}
}
