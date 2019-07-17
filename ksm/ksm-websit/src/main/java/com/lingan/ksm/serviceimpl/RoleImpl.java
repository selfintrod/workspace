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
	public List<ksmRole> findRolesById(Integer id) {
		// TODO Auto-generated method stub
		return rm.findRolesById(id);
	}


	@Override
	public Page<ksmRole> findRoles(Page<ksmRole> page ) {
		// TODO Auto-generated method stub
		return page.setRecords(rm.selectList(null));
	}


	@Override
	public int deletRoleById(Integer id) {
		// TODO Auto-generated method stub
		return rm.deleteById(id);
	}

	@Override
	public int hasRole(Integer Id) {
		// TODO Auto-generated method stub
		if(rm.selectById(Id)==null)
		return 0;
		return 1;
	}
	
	@Override
	public List<ksmRole> findRoles()
	{
		return rm.selectList(null);
	}

	@Override
	public int addRole2User(Integer userId,Integer roleId) {
		// TODO Auto-generated method stub
		return rm.addRole2User(userId, roleId);
	}

	@Override
	public List<ksmRole> getRolesByUserName(String userName)
	{
		return rm.findRolesByUserName(userName);
	}
}
