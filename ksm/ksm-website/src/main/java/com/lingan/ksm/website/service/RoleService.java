package com.lingan.ksm.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lingan.ksm.entity.ksmRole;

public interface RoleService extends IService<ksmRole>{

	public List<ksmRole> findRolesByName(String username);
	public Page<ksmRole> findRoles(Page<ksmRole> page);
	int deletRoleByName(String roleName);
	public int saveRole(ksmRole role);
	public int hasRole(String roleName);
	public List<ksmRole> findRoles();
	public int addRole2User(String userName,String roleName);
}
