package com.lingan.ksm.website.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lingan.ksm.website.entity.ksmUser;
import com.lingan.ksm.website.service.mapper.userMapper;
import com.lingan.ksm.website.service.service.userService;

@Service
public class userServiceImpl extends ServiceImpl<userMapper,ksmUser> implements userService
{
	@Autowired
	private userMapper um;

	public ksmUser findUserByName(String userName)
	{
		return um.selectById(userName);
	}

	public void saveOrUpdateOne(ksmUser user)
	{
	   um.insert(user);	
	}

	public void updateOne(ksmUser user)
	{
      this.saveOrUpdate(user);
	}
}
