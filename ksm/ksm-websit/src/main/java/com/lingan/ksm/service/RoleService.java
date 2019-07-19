package com.lingan.ksm.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lingan.ksm.entity.ksmRole;

public interface RoleService extends IService<ksmRole>{

	public List<ksmRole> findRolesById(Integer id);
	public Page<ksmRole> findRoles(Page<ksmRole> page);
	int deletRoleById(Integer Id);
	public int saveRole(ksmRole role);
	public int hasRole(Integer roleName);
	public List<ksmRole> findRoles();
	public int addRole2User(Integer userId,Integer roleId);
	public List<ksmRole> getRolesByUserName(String userName);
	public int deleteRoleById(Integer roleId);

}
