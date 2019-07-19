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
	
	@Select("select DISTINCT kp.* from ksm_playing kp " + 
			"INNER  join ksm_userroles kur on (kp.role_id=kur.role_id) "+ 
			"INNER join ksm_user ku on (ku.user_id=kur.user_id) where ku.user_name=#{userName}")
	List<ksmRole> findRolesByUserName(String userName);
	
	//@Insert("insert into ksm_playing(role_id,role_name,description) values(#{roleId},#{roleName},#{roleDes})")
	//int saveRole(@Param("roleId")Integer roleId,@Param("roleDes")String roleDes,@Param("roleName")String roleName);
	
	//@Update("UPDATE ksm_playing SET role_name=#{roleName},description=#{roleDes} WHERE role_id=#{roleId}")
	//int updateRole(@Param("roleId")Integer roleId,@Param("roleDes")String roleDes,@Param("roleName")String roleName);
	
}
