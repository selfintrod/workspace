package com.lingan.ksm.shiro.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingan.ksm.entity.ksmRole;
import com.lingan.ksm.entity.ksmUser;
import com.lingan.ksm.service.RoleService;
import com.lingan.ksm.service.UserService;


@Service
public class shiroService 
{
	
@Autowired
private UserService us;

@Autowired
private RoleService rs;

//@Autowired
//private PermissionImpl pi;


public ksmUser getUserByName(String name)
{
	return us.getUserByName(name);
}

public List<ksmRole> getRolesByUser(String username)
{
	return rs.findRolesByName(username);
}

}
