package com.lingan.ksm.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("ksm_playing")
public class ksmRole implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TableId("role_name")
	private String roleName;
	
	@TableField("role_id")
	private Integer roleId;
	
	
	public String getRoleName()
	{
		return roleName;
	}
	
	public void setRoleName(String rolename)
	{
		this.roleName=rolename;
	}
	
	public Integer getRoleId()
	{
		return this.roleId;
	}
	
	public void setRoleId(Integer roleId)
	{
		this.roleId=roleId;
	}
	
}
