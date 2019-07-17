package com.lingan.ksm.model;

import java.util.List;

import com.lingan.ksm.entity.ksmMenu;

public class menuModel {
	
	private String title;
	private Integer id;
	private List<menuModel> children;
	
	public menuModel(ksmMenu menu) 
	{
		this.id=menu.getId();
		this.title=menu.getMenuName();
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String titel)
	{
		this.title=titel;
	}

	public Integer getId()
	{
		return this.id;
	}
	
	public void setId(Integer id)
	{
		this.id=id;
	}
	
	public List<menuModel> getChildren()
	{
		return this.children;
	}
	
	public void setChildren(List<menuModel> children)
	{
		this.children=children;	
	}	
}

