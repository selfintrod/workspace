package com.lingan.ksm.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lingan.ksm.entity.ksmMenu;
import com.lingan.ksm.model.menuModel;
import com.lingan.ksm.model.menuTree;

public interface MenuService extends IService<ksmMenu> {

	List<ksmMenu> getMenus();
	int saveMenu(ksmMenu menu);
	int deleteMenu(Integer id);
	ksmMenu getMenuById(Integer id);
	int hasChildren(Integer menuId);
	List<menuModel> getMenuModels();
	int addMenu2Role(Integer roleId,Integer MenuId);
	List<menuTree> getMenuTree();
}
