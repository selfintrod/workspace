package com.lingan.ksm.shiro.Service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingan.ksm.entity.ksmRole;
import com.lingan.ksm.entity.ksmUser;
import com.lingan.ksm.model.menuTree;
import com.lingan.ksm.service.MenuService;
import com.lingan.ksm.service.RoleService;
import com.lingan.ksm.service.UserService;


@Service
public class shiroService 
{
	
@Autowired
private UserService us;

@Autowired
private RoleService rs;

@Autowired
private MenuService ms;

//@Autowired
//private PermissionImpl pi;

//登陆时获取当前用户和菜单存入session
public void login(String userName,String passWord)
{
	UsernamePasswordToken token=new UsernamePasswordToken(userName,passWord);		
	Subject subject=SecurityUtils.getSubject();	
	subject.login(token);
	Session session=subject.getSession();
	ksmUser user=us.getUserByName(userName);
	//System.out.print(user.getUserName());
	List<menuTree> menus=ms.getMenuTreeByUser(userName);
	System.out.print("菜单数量"+menus.size());
	session.setAttribute("CurrentUser",user);
	session.setAttribute("userMenus", menus);
	//System.out.print(session.getAttribute("CurrentUser"));
	//System.out.print(session.getAttribute("userMenus"));
}

public void logout()
{
	SecurityUtils.getSubject().logout();
}


public void saveCurrentUser2Session(ksmUser user)
{
	Session session = SecurityUtils.getSubject().getSession();
	session.setAttribute("CurrentUser",user);
}

public ksmUser getCurrentUser()
{	
	return getSessionObject("CurrentUser",ksmUser.class);
}

@SuppressWarnings("unchecked")
public <T> T getSessionObject(String sessionkey, Class<T> classz) {
	Session session = SecurityUtils.getSubject().getSession();
	Object obj = session.getAttribute(sessionkey);
	if (obj == null) {
		return null;
	}
	return (T) obj;
}

public void setAttr(String attr, Object obj) {
	Session session = SecurityUtils.getSubject().getSession();
	session.setAttribute(attr, obj);

}

public ksmUser getUserByName(String userName)
{
	return us.getUserByName(userName);
}

public List<ksmRole> getRolesByUserName(String userName)
{
	return rs.getRolesByUserName(userName);
}

}
