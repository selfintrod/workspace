package com.lingan.ksm.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("ksm_menu")
public class ksmMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId("menu_id")
	private Integer id;
	
	@TableField("menu_pid")
	private Integer parentId;
	
	@TableField("menu_name")
	private String menuName;
	
	@TableField("menu_description")
	private String menuDes;
	
	@TableField("menu_url")
	private String Url;
		
	//private Timestamp creatTime;
	//private Timestamp modifTime;
	//private Integer createrId;
	//private Integer modifierId;
	public ksmMenu()
	{
		
	}
	public ksmMenu(Integer id,Integer parentId,String menuName,String menuDes,String Url)
	{
		this.id=id;
		this.parentId=parentId;
		this.menuName=menuName;
		this.menuDes=menuDes;
		this.Url=Url;		
	}
	
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId(Integer id)
	{
		this.id=id;
	}
	
	public Integer getParentId()
	{
		return this.parentId;
	}
	
	public void setParentId(Integer parentId)
	{
		this.parentId=parentId;
	}
	
	public String getMenuName()
	{
		return this.menuName;
	}
	
	public void setMenuName(String menuName)
	{
		this.menuName=menuName;
	}
	
	public String getMenuDes()
	{
		return this.menuDes;
	}
	
	public void setMenuDes(String menuDes)
	{
		this.menuDes=menuDes;
	}
	
	public String getUrl()
	{
		return this.Url;
	}
	
	public void setUrl(String Url)
	{
		this.Url=Url;
	}

	//@Override
	//public String toString()
	//{
//		return "[菜单id"+this.id+"菜单名"+this.menuName+"菜单描述"+this.menuDes+"菜单url"+this.Url+"父菜单id"+this.parentId+"]";
//	}
	
}

