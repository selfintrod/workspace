package com.lingan.ksm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingan.ksm.entity.ksmMenu;

public interface MenuMapper extends BaseMapper<ksmMenu> {

	@Select("SELECT  COUNT(*) FROM ksm_menu WHERE menu_pid=#{menuId}")
	int getChildrensCount(Integer menuId);
	
	@Insert("insert into ksm_rolemenu(role_id,menu_id) values(#{roleId},#{menuId})")
	int addMenu2Role(@Param("roleId")Integer roleId,@Param("menuId")Integer menuId);
}
