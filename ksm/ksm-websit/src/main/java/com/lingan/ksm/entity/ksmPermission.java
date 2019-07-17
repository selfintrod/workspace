package com.lingan.ksm.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("ksm_permission")
public class ksmPermission implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@TableField("permission_name")
	public String PermissionName;
	
	@TableId("permission_id")
	public Integer PermissionId;
	
	public String getPermissionName()
	{
		return PermissionName;
	}

	public void setPermissionName(String name)
	{
		this.PermissionName=name;
	}
	
	public void setPermissionId(Integer PermissionId)
	{
		this.PermissionId=PermissionId;
	}
	
	public Integer getPermissionId()
	{
		return this.PermissionId;
	}
}
