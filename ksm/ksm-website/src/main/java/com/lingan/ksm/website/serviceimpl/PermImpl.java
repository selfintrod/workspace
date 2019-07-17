package com.lingan.ksm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lingan.ksm.entity.ksmPermission;
import com.lingan.ksm.mapper.PermissionMapper;
import com.lingan.ksm.service.PermissionService;
@Service
public class PermImpl extends BaseServiceImpl<PermissionMapper,ksmPermission> implements PermissionService{

	@Autowired
	private PermissionMapper pm;
	
	//public ksmPermission getPerm(String role)
	//{
	//	return pm.findPermissionByRole(role);
	//}

	@Override
	public ksmPermission getPermByName(String permName) 
	{		
		return null;
	}

	@Override
	public int deletPermByName(String permName) 
	{		
		return pm.deleteById(permName);
	}

	@Override
	public int savePerm(ksmPermission perm) 
	{
		if(this.saveOrUpdate(perm))
			return 1;
		return 0;
	}

	@Override
	public Page<ksmPermission> getPerms(Page<ksmPermission> page) {
		// TODO Auto-generated method stub
		return page.setRecords(pm.selectList(null));
	}
	
}
