package com.lingan.ksm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingan.ksm.entity.ksmRole;

public interface RoleMapper extends BaseMapper<ksmRole>{

	@Select("select DISTINCT kp.* from ksm_playing kp inner join  ksm_userroles kur on kp.role_name=kur.role_name where kur.user_name=#{username}")
	List<ksmRole> findRolesByName(String username);	
	
	@Insert("insert into ksm_userroles(user_name,role_name) values(#{userName},#{roleName})")
	int addRole2User(String userName,String roleName);
}
