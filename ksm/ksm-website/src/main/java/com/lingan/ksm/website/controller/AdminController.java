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

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lingan.ksm.entity.ksmRole;
import com.lingan.ksm.entity.ksmUser;
import com.lingan.ksm.model.UserModel;
import com.lingan.ksm.service.UserModelService;
import com.lingan.ksm.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

@Autowired
private UserService us;

@Autowired
private UserModelService ums;

@GetMapping
public String visit()
{
	return "admin";
}

@ResponseBody
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
}

@ResponseBody
@GetMapping("/save")
public Map<String, String> saveUser(@RequestParam("userName") String userName,
		@RequestParam("passWord") String passWord,
		@RequestParam("userId") Long id,@RequestParam("roleName") String roleName)
{
	Map<String, String> map = new HashMap<>();
	ksmUser nUser=new ksmUser(userName,passWord,id);
	if(ums.hasRole(roleName)==1)
		ums.addRole2User(userName, roleName);
	if(us.saveUser(nUser)==0)
	map.put("code", "400");
	else map.put("code","200");
	return map;
}

@ResponseBody
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
}

@ResponseBody
@GetMapping("/delete")
public Map<String, String> deleteUser(@RequestParam("userName") String userNames)
{
	Map<String, String> map = new HashMap<>();
	String[] names=userNames.split(",");
	for(String name:names)
	{
		if(us.deleteUserByName(name)==0)
		map.put("code","400");
		else map.put("code","200");
	}				
	return map;
}

}
