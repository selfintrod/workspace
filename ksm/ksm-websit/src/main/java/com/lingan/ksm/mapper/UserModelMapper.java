package com.lingan.ksm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingan.ksm.model.UserModel;

public interface UserModelMapper extends BaseMapper<UserModel>{
	
	@Select("select * from ksm_user")
	@Results({@Result(property="userName",column="user_name"),
		@Result(property="passWord",column="pass_word"),
		@Result(property="id",column="user_id"),
		@Result(property="UserRoles",javaType=List.class,column="user_id",many=@Many(select="com.lingan.ksm.mapper.RoleMapper.findRolesById"))})
	List<UserModel> getUserModels();

}
