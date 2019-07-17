package com.lingan.ksm.website.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("ksm_perms")
public class ksmPerms {
	
	@TableId
	private Integer permId;
	
	@TableField
	private String permName;
	
	public Integer getPermId()
	{
		return this.permId;
	}

	public String getPermName()
	{
		return this.permName;
	}
	
	public void setPermId(Integer permId)
	{
		this.permId=permId;
	}
	
	public void setPermName(String permName)
	{
		this.permName=permName;
	}
}
