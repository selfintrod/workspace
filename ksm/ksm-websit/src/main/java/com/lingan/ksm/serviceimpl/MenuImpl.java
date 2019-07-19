package com.lingan.ksm.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingan.ksm.entity.ksmMenu;
import com.lingan.ksm.entity.ksmRole;
import com.lingan.ksm.entity.ksmUser;
import com.lingan.ksm.mapper.MenuMapper;
import com.lingan.ksm.model.menuModel;
import com.lingan.ksm.model.menuTree;
import com.lingan.ksm.service.MenuService;
import com.lingan.ksm.service.RoleService;
import com.lingan.ksm.service.UserService;

@Service
public class MenuImpl extends BaseServiceImpl<MenuMapper,ksmMenu> implements MenuService{

	@Autowired
	MenuMapper mp;
	
	@Autowired
	UserService us;
	
	@Autowired
	RoleService rs;
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

	@Override
	public int addMenu2Role(Integer roleId, Integer MenuId) {
		// TODO Auto-generated method stub
		return mp.addMenu2Role(roleId, MenuId);
		
	}

	public List<ksmMenu> getMenusByRole(Integer roleId)
	{
		return mp.getMenusByRole(roleId);
	}
	
	@Override
	public List<menuTree> getMenuTree() {
		
		//获得所有菜单
		List<ksmMenu> menus=this.list();	
		
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
	public List<menuTree> getMenuTreeByUser(String userName) {
		// TODO Auto-generated method stub
		ksmUser user=us.getUserByName(userName);
		List<ksmRole> roles=rs.getRolesByUserName(user.getUserName());
		List<ksmMenu> menus=new ArrayList<>();
		for(ksmRole role:roles)
		{
			//System.out.print("roleId为"+role.getRoleId());
			List<ksmMenu> temp=getMenusByRole(role.getRoleId());		
			//System.out.print(temp);
			if(temp!=null)
			{
				menus.addAll(temp);
			
			}
		}
		List<menuTree> models=new ArrayList<>();	
		//System.out.print(menus);
		//循环找出菜单根节点		
		for(ksmMenu menu:menus)
	    {
			if(menu.getParentId()==0)
	    	{	
				//System.out.print("菜单数量为"+menu.getMenuName());
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
