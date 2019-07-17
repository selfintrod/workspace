package com.lingan.ksm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lingan.ksm.entity.ksmUser;
import com.lingan.ksm.mapper.UserMapper;
import com.lingan.ksm.service.UserService;
//import com.lingan.ksm.shiro.config.PasswordHelper;

@Service
public class UserImpl extends BaseServiceImpl<UserMapper,ksmUser> implements UserService

{

	@Autowired
	private UserMapper um;
	//@Autowired
	//private PasswordHelper ph;
	
	@Override
	public ksmUser getUserByName(String name)
	{					
		return um.selectById(name);
	}
		


	@Override
	public int saveUser(ksmUser user) {
		// TODO Auto-generated method stub		
		//ph.entryPassword(user);
		if( this.saveOrUpdate(user))
			return 1;
		return 0;
		 
	}



	@Override
	public int deleteUserByName(String userName) {
		// TODO Auto-generated method stub
		return um.deleteById(userName);
	}



	@Override
	public Page<ksmUser> getUsers(Page<ksmUser> page) {
		// TODO Auto-generated method stub
		page.setRecords(um.selectList(null));
		return page;
	}

}