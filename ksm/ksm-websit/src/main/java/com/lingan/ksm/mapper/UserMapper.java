package com.lingan.ksm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingan.ksm.entity.ksmUser;

public interface UserMapper extends BaseMapper<ksmUser>{
			
	@Select("select * from ksm_User")
	public List<ksmUser> getUsers();
	
}
