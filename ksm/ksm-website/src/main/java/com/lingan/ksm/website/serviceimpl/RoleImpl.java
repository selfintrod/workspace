package com.lingan.ksm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lingan.ksm.entity.ksmRole;
import com.lingan.ksm.mapper.RoleMapper;
import com.lingan.ksm.service.RoleService;
@Service
public class RoleImpl extends BaseServiceImpl<RoleMapper,ksmRole> implements RoleService{

	@Autowired
	private RoleMapper rm;
	
    @Override
	public int saveRole(ksmRole role) {
		if(this.saveOrUpdate(role))
			return 1;
		return 0;
	}
	
	public void Userinit(String username)
	{
		
	}


	@Override
	public List<ksmRole> findRolesByName(String username) {
		// TODO Auto-generated method stub
		return rm.findRolesByName(username);
	}


	@Override
	public Page<ksmRole> findRoles(Page<ksmRole> page ) {
		// TODO Auto-generated method stub
		return page.setRecords(rm.selectList(null));
	}


	@Override
	public int deletRoleByName(String roleName) {
		// TODO Auto-generated method stub
		return rm.deleteById(roleName);
	}

	@Override
	public int hasRole(String roleName) {
		// TODO Auto-generated method stub
		if(rm.selectById(roleName)==null)
		return 0;
		return 1;
	}
	
	@Override
	public List<ksmRole> findRoles()
	{
		return rm.selectList(null);
	}

	@Override
	public int addRole2User(String userName, String roleName) {
		// TODO Auto-generated method stub
		return rm.addRole2User(userName, roleName);
	}

}
