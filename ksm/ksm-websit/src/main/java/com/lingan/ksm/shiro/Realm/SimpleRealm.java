package com.lingan.ksm.shiro.Realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lingan.ksm.entity.ksmRole;
import com.lingan.ksm.entity.ksmUser;
import com.lingan.ksm.shiro.Service.shiroService;


public class SimpleRealm extends AuthorizingRealm
{
@Autowired
private shiroService service;

    //此处配置角色与权限认证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo authorizationinfo=new SimpleAuthorizationInfo();
		String username=(String)principals.getPrimaryPrincipal();
		List<ksmRole> roles=service.getRolesByUserName(username);//应当再装入permission，此处简写		
		for(ksmRole role:roles)
        authorizationinfo.addRole(role.getRoleName());
		return null;
	}

	
	//此处配置身份验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {		
		//usernamepasswordtoken与remember me
		UsernamePasswordToken token2=(UsernamePasswordToken)token;
		String username=token2.getUsername();// token有principal 账户名，credential 密码两个属性可以获取	
		ksmUser user=service.getUserByName(username);
		if(user==null)
		{
			throw new UnknownAccountException();
		}
		
		SimpleAuthenticationInfo sa=new SimpleAuthenticationInfo(user.getUserName(),user.getPassWord(),getName());		
		return sa;
	}

	
}
