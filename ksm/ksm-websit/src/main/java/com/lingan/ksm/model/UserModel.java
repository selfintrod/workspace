package com.lingan.ksm.model;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.lingan.ksm.entity.ksmRole;
import com.lingan.ksm.entity.ksmUser;

public class UserModel extends ksmUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@TableField(exist=false)
	private List<ksmRole> UserRoles;
	
	public void setUserName(String UserName)
	{
		super.setUserName(UserName);;
	}
	
	public void setPassWord(String Password)
	{
		super.setPassWord(Password);;
	}
	
	public String getUserName()
	{
		return super.getUserName();
	}
	
	public String getPassWord()
	{
		return super.getPassWord();
	}
		
	public void setId(Integer id)
	{
		super.setId(id);;
	}
	
	public Integer getId()
	{
		return super.getId();
	}
	
	public List<ksmRole> getUserRoles()
	{
		return this.UserRoles;
	}
	
	public void setUserRoles(List<ksmRole> UserRoles)
	{
		this.UserRoles=UserRoles;
	}
}
