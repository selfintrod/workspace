package com.lingan.ksm.model;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.lingan.ksm.entity.ksmMenu;

public class menuTree extends ksmMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public menuTree(ksmMenu menu)
	{
		this.setId(menu.getId());
		this.setMenuDes(menu.getMenuDes());
		this.setMenuName(menu.getMenuName());
		this.setUrl(menu.getUrl());
		this.setParentId(menu.getParentId());		
	}
	
	@TableField(exist=false)
	private List<menuTree> children;
	
	
	public List<menuTree> getChildren()
	{
		return this.children;
	}
	
	public void setChildren(List<menuTree> children)
	{
		this.children=children;
	}
	
	
	public Integer getId()
	{
		return super.getId();
	}
	
	public void setId(Integer id)
	{
		super.setId(id);;
	}
	
	public Integer getParentId()
	{
		return super.getParentId();
	}
	
	public void setParentId(Integer parentId)
	{
		super.setParentId(parentId);;
	}
	
	public String getMenuName()
	{
		return super.getMenuName();
	}
	
	public void setMenuName(String menuName)
	{
		super.setMenuName(menuName);;
	}
	
	public String getMenuDes()
	{
		return super.getMenuDes();
	}
	
	public void setMenuDes(String menuDes)
	{
		super.setMenuDes(menuDes);;
	}
	
	public String getUrl()
	{
		return super.getUrl();
	}
	
	public void setUrl(String Url)
	{
		super.setUrl(Url);;
	}

}
