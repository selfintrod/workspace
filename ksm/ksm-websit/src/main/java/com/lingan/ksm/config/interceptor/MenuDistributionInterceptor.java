package com.lingan.ksm.config.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class MenuDistributionInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
		/*
		 *  HttpSession session = request.getSession();
      if (session.getAttribute("moduleMenus") == null) {
          session.setAttribute("moduleMenus", moduleService.getModuleMenus());
      }
      User user = (User) session.getAttribute("loginUser");
      if (user != null) {
          RoleUser roleUser = roleUserService.getUserByUserId(user.getId());
          List<RoleMenu> roleMenus = roleMenuService.getRoleMenuByRoleId(roleUser.getRoleId());
          List<Menu> menus=new ArrayList<>();
          for (RoleMenu roleMenu : roleMenus) {
              Menu menu = menuService.getMenuById(roleMenu.getMenuId());
              menus.add(menu);
          }
          if (session.getAttribute("indexMenus") == null) {
              session.setAttribute("indexMenus", menus);
          }
          return true;
      } else {
          //response.sendRedirect(request.getContextPath());
          redirect(request, response);
          return false;
      }
         */
			return true;
		}

	 public void redirect(HttpServletRequest request, HttpServletResponse response) {
         // 获取当前请求的路径
         String basePath = request.getContextPath();
         // 如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理
         // 否则直接重定向就可以了
         if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
             // 告诉ajax我是重定向
           //  response.setHeader("REDIRECT", "REDIRECT");
             // 告诉ajax我重定向的路径
            // response.setHeader("CONTENTPATH", basePath + "/");
            // response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        	 response.setHeader("sessionstatus", "timeout");
         } else {
             try {
                 response.sendRedirect(basePath + "/");
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
	
}
