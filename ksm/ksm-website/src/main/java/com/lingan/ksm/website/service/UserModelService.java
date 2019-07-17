package com.lingan.ksm.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lingan.ksm.entity.ksmRole;
import com.lingan.ksm.model.UserModel;

public interface UserModelService extends IService<UserModel>{
	
	public Page<UserModel> getUsers(Page<UserModel> page);
    public int hasRole(String roleName);
    public List<ksmRole> findeRoles();
    public int addRole2User(String userName,String roleName);
}
