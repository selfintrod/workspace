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
	
	@TableId("role_id")
	private Integer roleId;
	
	@TableField("role_name")	
	private String roleName;
		
	@TableField("description")
	private String Description;
	
	public ksmRole(Integer roleId, String roleName, String roleDes) {
		// TODO Auto-generated constructor stub
		this.roleId=roleId;
		this.roleName=roleName;
		this.Description=roleDes;
	}

	public ksmRole()
	{
		
	}
	
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
	
	public String getDescription()
	{
		return this.Description;
	}
	
	public void setDescription(String Description)
	{
		this.Description=Description;
	}
	
}
