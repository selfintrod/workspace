package com.lingan.ksm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lingan.ksm.entity.ksmPermission;

public interface PermissionService extends IService<ksmPermission>{

	public ksmPermission getPermById(Integer Id);
	public int deletPermById(Integer Id);
	public int savePerm(ksmPermission perm);
	public Page<ksmPermission> getPerms(Page<ksmPermission> page);
}
