package com.lingan.ksm.model;

import java.util.List;

import com.lingan.ksm.entity.ksmMenu;

public class treeModel {
	
	private ksmMenu menu;
	List<treeModel> children;
	
	public ksmMenu getMenu()
	{
		return this.menu;
	}

	public void setMenu(ksmMenu menu)
	{
		this.menu=menu;
	}
	
	public List<treeModel> getChildren()
	{
		return this.children;
	}
	
	public void setChildren(List<treeModel> children)
	{
		this.children=children;
	}
	
	public treeModel()
	{
		
	}
	
	public treeModel(ksmMenu menu)
	{
		this.menu=menu;
	}
}
