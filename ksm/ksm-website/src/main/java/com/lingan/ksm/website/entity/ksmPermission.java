package com.lingan.ksm.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("ksm_permission")
public class ksmPermission implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@TableId("permission_name")
	public String PermissionName;
	
	public String getPermissionName()
	{
		return PermissionName;
	}

	public void setPermissionName(String name)
	{
		this.PermissionName=name;
	}
}
