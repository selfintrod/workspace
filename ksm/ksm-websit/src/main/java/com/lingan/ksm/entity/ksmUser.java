package com.lingan.ksm.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;



@TableName("ksm_user")
public class ksmUser implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId("user_name")
	private String userName;
	
	@TableField("pass_word")
	private String passWord;
	
	@TableField("user_id")
	private Long id;
		
	public ksmUser()
	{
		
	}
	
	public ksmUser(String UserName,String PassWord,Long id)
	{
		this.userName=UserName;
		this.passWord=PassWord;
		this.id=id;
	}
	
	public ksmUser(String UserName,String PassWord)
	{
		this(UserName,PassWord,null);
	}
	

	public void setUserName(String UserName)
	{
		this.userName=UserName;
	}
	
	public void setPassWord(String Password)
	{
		this.passWord=Password;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getPassWord()
	{
		return passWord;
	}
		
	public void setId(Long id)
	{
		this.id=id;
	}
	
	public Long getId()
	{
		return id;
	}
}
