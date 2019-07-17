package com.lingan.ksm.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingan.ksm.entity.ksmMenu;
import com.lingan.ksm.mapper.MenuMapper;
import com.lingan.ksm.model.menuModel;
import com.lingan.ksm.model.menuTree;
import com.lingan.ksm.model.treeModel;
import com.lingan.ksm.service.MenuService;

@Service
public class MenuImpl extends BaseServiceImpl<MenuMapper,ksmMenu> implements MenuService{

	@Autowired
	MenuMapper mp;
	
	@Override
	public List<ksmMenu> getMenus() {
		// TODO Auto-generated method stub
		return this.list();
	}

	@Override
	public int saveMenu(ksmMenu menu) {
		// TODO Auto-generated method stub
		if(this.saveOrUpdate(menu))
			return 1;
		else return 0;
	}

	@Override
	public int deleteMenu(Integer id) {
		// TODO Auto-generated method stub
		if(this.removeById(id))
			return 1;
		return 0;
	}

	@Override
	public ksmMenu getMenuById(Integer id) {
		// TODO Auto-generated method stub
		return this.getById(id);
	}

	@Override
	public int hasChildren(Integer menuId) {
		// TODO Auto-generated method stub
		int count=mp.getChildrensCount(menuId);
		if(count==0)
		{			
		    return 0;
		}
	    return count;		
	}

	@Override
	public List<menuModel> getMenuModels() {
		// TODO Auto-generated method stub
		List<ksmMenu> menus=this.list();	
		//System.out.print("全部菜单"+menus);
		List<menuModel> models=new ArrayList<>();	   
		//循环找出菜单根节点		
		for(ksmMenu menu:menus)
	    {
			if(menu.getParentId()==0)
	    	{	
	    		models.add(new menuModel(menu));
	    	}
	    }
		
		//System.out.print("根节点"+models);
		
	    for(menuModel model:models)
	    {
	        List<menuModel> child=getChild(model.getId(),menus);
	    	model.setChildren(child);	      
	    }
	   // System.out.print("树已完成"+models);
		return models;
	}
	
	public List<menuModel> getChild(Integer Pid,List<ksmMenu> menus)
	{
		List<menuModel> children=new ArrayList<>();			
			
		for(ksmMenu menu:menus)
			{
				if(menu.getParentId().equals(Pid))				
				{				
					children.add(new menuModel(menu));								
				}	
			}
						
			for(menuModel model:children)			 
			  {							
			        model.setChildren(getChild(model.getId(), menus));
			  }			 
		
		if(children.size()==0)
			return new ArrayList<menuModel>();
		return children;
	}
	
	public List<menuTree> getMenuChildren(Integer Pid,List<ksmMenu> menus)
	{
		List<menuTree> children=new ArrayList<>();			
			
		for(ksmMenu menu:menus)
			{
				if(menu.getParentId().equals(Pid))				
				{				
					children.add(new menuTree(menu));								
				}	
			}
						
			for(menuTree model:children)			 
			  {							
			        model.setChildren(getMenuChildren(model.getId(), menus));
			  }			 
		
		if(children.size()==0)
			return new ArrayList<menuTree>();
		return children;
	}

	@Override
	public List<menuTree> getMenuTree() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				List<ksmMenu> menus=this.list();	
				//System.out.print("全部菜单"+menus);
				List<menuTree> models=new ArrayList<>();	   
				//循环找出菜单根节点		
				for(ksmMenu menu:menus)
			    {
					if(menu.getParentId()==0)
			    	{	
			    		models.add(new menuTree(menu));
			    	}
			    }
				
				//System.out.print("根节点"+models);
				
			    for(menuTree model:models)
			    {
			        List<menuTree> child=getMenuChildren(model.getId(),menus);
			    	model.setChildren(child);	      
			    }
			   // System.out.print("树已完成"+models);
				return models;
	}

}
