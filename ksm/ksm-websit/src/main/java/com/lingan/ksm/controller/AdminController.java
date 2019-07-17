package com.lingan.ksm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lingan.ksm.entity.ksmMenu;
import com.lingan.ksm.model.menuModel;
import com.lingan.ksm.model.menuTree;
import com.lingan.ksm.service.MenuService;
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

@GetMapping
public String visit()
{
	return "admin";
}

@ResponseBody
@GetMapping("/showMenus")
public String menus()
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
@GetMapping("/showMenuModels")
public String menusModels()
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


/*@ResponseBody
@GetMapping("/showUsers")
public String admin(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit)
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
}*/

@ResponseBody
@GetMapping("/saveMenu")
public Map<String, String> saveUser(@RequestParam("menuName") String menuName,
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

/*@ResponseBody
@GetMapping("/update")
public Map<String, String> updateUser(@RequestParam("userName") String userName,
		@RequestParam("passWord") String passWord,
		@RequestParam("userId") Long id)
{
	Map<String, String> map = new HashMap<>();
	ksmUser nUser=new ksmUser(userName,passWord,id);
	if(us.saveUser(nUser)==0)
	map.put("code", "400");
	else map.put("code","200");
	return map;
}*/

@ResponseBody
@GetMapping("/deleteMenu")
public Map<String, String> deleteUser(@RequestParam("menuId") Integer menuId)
{
	Map<String, String> map = new HashMap<>();
	int count=ms.hasChildren(menuId);
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

}
