package com.lingan.ksm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingan.ksm.entity.ksmRole;

public interface RoleMapper extends BaseMapper<ksmRole>{

	@Select("select DISTINCT kp.* from ksm_playing kp inner join  ksm_userroles kur on kp.role_id=kur.role_id where kur.user_id=#{userId}")
	List<ksmRole> findRolesById(Integer userId);	
	
	@Insert("insert into ksm_userroles(user_id,role_id) values(#{userId},#{roleId})")
	int addRole2User(@Param("userId")Integer userId,@Param("roleId")Integer roleId);
	
	@Select("select DISTINCT kp.* from ksm_playing kp inner join  ksm_userroles kur on kp.role_id=kur.role_id where kur.user_name=#{userName}")
	List<ksmRole> findRolesByUserName(String userName);
	
}
