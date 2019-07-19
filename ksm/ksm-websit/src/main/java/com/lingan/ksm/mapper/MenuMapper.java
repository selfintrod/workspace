package com.lingan.ksm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingan.ksm.entity.ksmMenu;

public interface MenuMapper extends BaseMapper<ksmMenu> {

	@Select("SELECT  COUNT(*) FROM ksm_menu WHERE menu_pid=#{menuId}")
	int getChildrensCount(Integer menuId);
	
	@Select("SELECT  km.* FROM ksm_menu km INNER join ksm_rolemenu krm "+
			 "on (km.menu_id=krm.menu_id) WHERE krm.role_id=#{roleId}")
	@Results({ @Result(column = "menu_id", property = "id"),
		@Result(column = "menu_name", property = "menuName"),
		@Result(column = "menu_description", property = "menuDes"),
		@Result(column = "menu_pid", property = "parentId"),
		@Result(column = "menu_url", property = "Url")})
	List<ksmMenu> getMenusByRole(Integer roleId);
	
	@Insert("insert into ksm_rolemenu(role_id,menu_id) values(#{roleId},#{menuId})")
	int addMenu2Role(@Param("roleId")Integer roleId,@Param("menuId")Integer menuId);
}
