package com.lingan.ksm.mapper;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingan.ksm.entity.ksmMenu;

public interface MenuMapper extends BaseMapper<ksmMenu> {

	@Select("SELECT  COUNT(*) FROM ksm_menu WHERE menu_pid=#{menuId}")
	int getChildrensCount(Integer menuId);
}
