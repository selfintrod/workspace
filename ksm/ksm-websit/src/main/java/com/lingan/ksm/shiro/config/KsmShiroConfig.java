package com.lingan.ksm.shiro.config;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lingan.ksm.shiro.Realm.SimpleRealm;

@Configuration
public class KsmShiroConfig 
{
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securitymanager)
	{
		
		//shiro过滤器工厂bean
		ShiroFilterFactoryBean sffb=new ShiroFilterFactoryBean();
		//注册安全管理器
		sffb.setSecurityManager(getsecurityManager(getshiroRealm(gethashedCredentialsMatcher())));
		//据说过滤规则过多需要使用linkedhashmap
		Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
		//设置登陆url，非验证url，验证成功后转到的url
		sffb.setLoginUrl("/login");
		sffb.setUnauthorizedUrl("/regist");
		sffb.setSuccessUrl("/user");
		//
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/regist", "anon");
		filterChainDefinitionMap.put("/user","authc");
		filterChainDefinitionMap.put("/admin","authc");
		sffb.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return sffb;		
	}
	

	@Bean(name="shiroRealm")
	public SimpleRealm getshiroRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
		SimpleRealm shiroRealm=new SimpleRealm();
		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
		return shiroRealm;
	}



	@Bean(name="hashedCredentialsMatcher")
	 public HashedCredentialsMatcher gethashedCredentialsMatcher() 
	{
	        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
	        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME); // 散列算法
	        hashedCredentialsMatcher.setHashIterations(PasswordHelper.INT_ITERATIONS); // 散列次数
	        return hashedCredentialsMatcher;
	        //return null;
	    }
	
    @Bean(name="securitymanager")
    public SecurityManager getsecurityManager(SimpleRealm simpleRealm)
    {
    	DefaultWebSecurityManager SecurityManager=new DefaultWebSecurityManager();
    	SecurityManager.setRealm(simpleRealm);
    	SecurityUtils.setSecurityManager(SecurityManager);
    	return SecurityManager;
    }
	
  
    
    @Bean
    public PasswordHelper passwordHelper()
    {
        return new PasswordHelper();
    }
    
}
