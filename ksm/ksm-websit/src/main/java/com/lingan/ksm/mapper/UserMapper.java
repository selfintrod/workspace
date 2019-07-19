package com.lingan.ksm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingan.ksm.entity.ksmUser;

public interface UserMapper extends BaseMapper<ksmUser>{
			
	@Select("select * from ksm_User")
	public List<ksmUser> getUsers();
	
	@Select("select user_id,user_name,pass_word from ksm_User where ksm_User.user_name=#{userName}")
	@Results({ @Result(column = "user_id", property = "id"),
		@Result(column = "user_name", property = "userName"),
		@Result(column = "pass_word", property = "passWord")})
	public ksmUser getUserByName(String userName);
	
	@Delete("delete from ksm_User ku where ku.user_id=#{userId}")
	public int deleteUserById(Integer userId);
	
}
