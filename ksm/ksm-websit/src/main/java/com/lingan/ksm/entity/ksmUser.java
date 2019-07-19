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

	@TableField("user_name")
	private String userName;
	
	@TableField("pass_word")
	private String passWord;
	
	@TableId("user_id")
	private Integer id;
	
	public ksmUser()
	{
		
	}
	
	public ksmUser(String UserName,String PassWord,Integer id)
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
		
	public void setId(Integer id)
	{
		this.id=id;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	@Override
	public String toString()
	{
		return "考试系统用户：用户ID["+this.id+"] 用户名["+this.userName+"] 用户密码["+this.passWord+"]";
	}
}
