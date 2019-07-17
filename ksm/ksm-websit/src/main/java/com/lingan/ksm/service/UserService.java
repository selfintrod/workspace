package com.lingan.ksm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lingan.ksm.entity.ksmUser;

public interface UserService extends IService<ksmUser>{

	public ksmUser getUserById(Integer Id);
	public int saveUser(ksmUser user);
	public int deleteUserById(Integer Id);
	public Page<ksmUser> getUsers(Page<ksmUser> page);
	public ksmUser getUserByName(String userName);
}

