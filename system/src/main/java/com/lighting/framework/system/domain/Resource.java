package com.lighting.framework.system.domain;

import com.lighting.framework.security.domain.AbstractResourceShiro;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenwei
 * @since 2019-12-31
 */
@TableName("sys_resource")
public class Resource extends Model<Resource> implements AbstractResourceShiro {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_UUID)
	private String id;
	private String name;
	private String type;
	private String pid;
	private Integer level;
	@TableField("URL")
	private String url;
	@TableField("icon_type")
	private String iconType;
	@TableField("icon_text")
	private String iconText;
	@TableField("icon_image")
	private Byte[] iconImage;
	private String description;
	private Integer sort;
	private String creater;
	@TableField("create_time")
	private Date createTime;
	private String updater;
	@TableField("update_time")
	private Date updateTime;

	private String isdel;

	public String getIsdel() {
		return isdel;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconType() {
		return iconType;
	}

	public void setIconType(String iconType) {
		this.iconType = iconType;
	}

	public String getIconText() {
		return iconText;
	}

	public void setIconText(String iconText) {
		this.iconText = iconText;
	}

	public Byte[] getIconImage() {
		return iconImage;
	}

	public void setIconImage(Byte[] iconImage) {
		this.iconImage = iconImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Resource{" + "id=" + id + ", name=" + name + ", type=" + type + ", pid=" + pid + ", level=" + level
				+ ", url=" + url + ", iconType=" + iconType + ", iconText=" + iconText + ", iconImage=" + iconImage
				+ ", description=" + description + ", sort=" + sort + ", creater=" + creater + ", createTime="
				+ createTime + ", updater=" + updater + ", updateTime=" + updateTime + "}";
	}
}
