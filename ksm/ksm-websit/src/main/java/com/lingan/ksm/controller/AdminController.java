package com.lingan.ksm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lingan.ksm.entity.ksmMenu;
import com.lingan.ksm.entity.ksmRole;
import com.lingan.ksm.entity.ksmUser;
import com.lingan.ksm.model.UserModel;
import com.lingan.ksm.model.menuModel;
import com.lingan.ksm.model.menuTree;
import com.lingan.ksm.service.MenuService;
import com.lingan.ksm.service.RoleService;
import com.lingan.ksm.service.UserModelService;
import com.lingan.ksm.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

@Autowired
private UserService us;

@Autowired
private MenuService ms;

@Autowired
private UserModelService ums;

@Autowired
private RoleService rs;

@GetMapping
public String visit()
{
	return "admin";
}

@ResponseBody
@GetMapping("/showUsers")
public String showUsers(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit)
{	
	JSONObject obj=new JSONObject();
	int pagenum,size;
	if(page!=null&&limit!=null)
	{
		pagenum=page;
		size=limit;
	}
	else
	{
		pagenum=1;
		size=30;
	}
	//Page<ksmUser> Page=new Page<>(pagenum,size);
	//Page<ksmUser> userPage=us.getUsers(Page);  
	Page<UserModel> Page=new Page<>(pagenum,size);
	Page<UserModel> userPage=ums.getUsers(Page);
	List<ksmRole> roles=new ArrayList<>(ums.findeRoles());
	if(userPage!=null)
		obj.put("code","200");
	else obj.put("code","400");
	obj.put("msg","");
	obj.put("count","1000");
	obj.put("data",userPage);
	obj.put("roles",roles);
	return obj.toJSONString();	
}

@ResponseBody
@GetMapping("/saveUser")
public Map<String, String> saveUser(@RequestParam("userName") String userName,
		@RequestParam("passWord") String passWord,
		@RequestParam("userId") Integer id,
		@RequestParam("roleId") String roleId)
{
	Map<String, String> map = new HashMap<>();
	Integer userId=id;
	ksmUser nUser=new ksmUser(userName,passWord,id);
	List<Integer> roleIds=JSON.parseArray(roleId, Integer.class);
	if(us.saveUser(nUser)==0)
		map.put("code", "400");
	else map.put("code","200");
	for(Integer roleid:roleIds)
	{
		if(ums.hasRole(roleid)==1)	
		ums.addRole2User(userId, roleid);
		else 
		{ 
			map.put("code", "400");
		    return map;
		}
	}	
	return map;
}

@ResponseBody
@GetMapping("/updateUser")
public Map<String, String> updateUser(@RequestParam("userName") String userName,
		@RequestParam("passWord") String passWord,
		@RequestParam("userId") Integer id)
{
	Map<String, String> map = new HashMap<>();
	ksmUser nUser=new ksmUser(userName,passWord,id);
	if(us.saveUser(nUser)==0)
	map.put("code", "400");
	else map.put("code","200");
	return map;
}

@ResponseBody
@GetMapping("/deleteUser")
public Map<String, String> deleteUser(@RequestParam("userId") Integer userId)
{
	Map<String, String> map = new HashMap<>();
	//String[] names=userNames.split(",");
	//for(String name:names)
	//{
	
		if(us.deleteUserById(userId)==0)
		map.put("code","400");
		else map.put("code","200");
	//}				
	return map;
}

@ResponseBody
@GetMapping("/getRoles")
public List<ksmRole> getRoles()
{
	List<ksmRole> roles=rs.findRoles();
	return roles;
}

@ResponseBody
@GetMapping("/getRoles2")
public String getRoles2(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit)
{
	JSONObject obj=new JSONObject();
	int pagenum,size;
	if(page!=null&&limit!=null)
	{
		pagenum=page;
		size=limit;
	}
	else
	{
		pagenum=1;
		size=30;
	} 
	Page<ksmRole> Page=new Page<>(pagenum,size);
	Page<ksmRole> roles=rs.findRoles(Page);	
	if(roles!=null)
		obj.put("code","200");
	else obj.put("code","400");
	obj.put("msg","");
	obj.put("count","1000");
	obj.put("data",roles);
	return obj.toJSONString();	
}

@ResponseBody
@GetMapping("/showMenus")
public String showMenus()
{
	JSONObject obj=new JSONObject();
 List<ksmMenu> menus=ms.getMenus();
 if(menus!=null)
		obj.put("code","200");
	else obj.put("code","400");
	obj.put("msg","");
	obj.put("count","1000");
	obj.put("data",menus);
	return obj.toJSONString();
	
}

@ResponseBody
@GetMapping("/showMenuTree")
public String menusTrees()
{
	JSONObject obj=new JSONObject();
 List<menuTree> models=ms.getMenuTree();
 if(models.size()!=0)
		obj.put("code","200");
	else obj.put("code","400");
	obj.put("msg","");
	obj.put("count","1000");
	obj.put("data",models);
	return obj.toJSONString();
	
}

@ResponseBody
@GetMapping("/showMenuModels")
public String showMenusModels()
{
	JSONObject obj=new JSONObject();
 List<menuModel> models=ms.getMenuModels();
 if(models.size()!=0)
		obj.put("code","200");
	else obj.put("code","400");
	obj.put("msg","");
	obj.put("count","1000");
	obj.put("data",models);
	return obj.toJSONString();
	
}

@ResponseBody
@GetMapping("/saveMenu")
public Map<String, String> saveMenu(@RequestParam("menuName") String menuName,
		@RequestParam("menuDes") String menuDes,
		@RequestParam("parentId") Integer parentId,
		@RequestParam("menuId")Integer menuId,@RequestParam("menuUrl") String menuUrl)
{
	Map<String, String> map = new HashMap<>();
	ksmMenu menu=new ksmMenu(menuId,parentId,menuName,menuDes,menuUrl);
	if(ms.saveMenu(menu)==0)
	map.put("code", "400");
	else map.put("code","200");
	return map;
}

@ResponseBody
@GetMapping("/deleteMenu")
public Map<String, String> deleteMenu(@RequestParam("menuId") Integer menuId)
{
	Map<String, String> map = new HashMap<>();
	int count=ms.hasChildren(menuId);
	System.out.print(count);
	if(count==0)
	{	if(ms.deleteMenu(menuId)==1)
			map.put("code","200");
		else map.put("code","400");
	}
	else 
		{
		map.put("code","400");
		map.put("msg", "还有"+count+"个子菜单未删除");
	}				
	return map;
}

@ResponseBody
@GetMapping("/saveRole")
public Map<String, String> saveRole(@RequestParam("roleName") String roleName,
		@RequestParam("roleDes") String roleDes,
		@RequestParam("roleId") Integer roleId,
		@RequestParam("menus") String Smenus)
{
	
	List<Integer> menus=JSON.parseArray(Smenus, Integer.class);
	//System.out.print(menus);
	Map<String, String> map = new HashMap<>();
	ksmRole role=new ksmRole(roleId,roleName,roleDes);
	System.out.print(roleId+roleName+roleDes);
	if(rs.saveRole(role)==0)
	map.put("code", "400");
	else {
		
		for(Integer menuId:menus)
		{
			ms.addMenu2Role(roleId, menuId);
		}		
		map.put("code","200");
	}
	
	return map;
}

@ResponseBody
@GetMapping("/deleteRole")
public Map<String, String> deleteRole(@RequestParam("roleId") Integer roleId)
{
	Map<String, String> map = new HashMap<>();
	if(rs.deleteRoleById(roleId)==1)
			map.put("code","200");
		else map.put("code","400");

				
	return map;
}


@ResponseBody
@GetMapping("/showMenuTrees")
public String showMenusTrees()
{
	JSONObject obj=new JSONObject();
 List<menuTree> models=ms.getMenuTree();
 if(models.size()!=0)
		obj.put("code","200");
	else obj.put("code","400");
	obj.put("msg","");
	obj.put("count","1000");
	obj.put("data",models);
	return obj.toJSONString();
	
}

}
