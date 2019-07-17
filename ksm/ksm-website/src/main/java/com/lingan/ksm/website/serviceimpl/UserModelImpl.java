package com.lingan.ksm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lingan.ksm.entity.ksmRole;
import com.lingan.ksm.mapper.UserModelMapper;
import com.lingan.ksm.model.UserModel;
import com.lingan.ksm.service.RoleService;
import com.lingan.ksm.service.UserModelService;

@Service
public class UserModelImpl extends BaseServiceImpl<UserModelMapper,UserModel> implements UserModelService{

	@Autowired
	private UserModelMapper ump;
	
	@Autowired
	private RoleService rs;
	@Override
	public Page<UserModel> getUsers(Page<UserModel> page) {
		// TODO Auto-generated method stub
		return page.setRecords(ump.getUserModels());
	}
    @Override
	public int hasRole(String roleName)
	{
		return rs.hasRole(roleName);
	}
	@Override
	public int addRole2User(String userName, String roleName) {
		// TODO Auto-generated method stub
		if(rs.addRole2User(userName, roleName)!=1)
		return 0;
		return 1;
	}
	@Override
	public List<ksmRole> findeRoles() {
		// TODO Auto-generated method stub
		return rs.findRoles();
	}
}
