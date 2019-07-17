package com.lingan.ksm.shiro.config;

import org.apache.shiro.crypto.hash.SimpleHash;

import com.lingan.ksm.entity.ksmUser;


public class PasswordHelper 
{
public static final String ALGORITHM_NAME="md5";
public static final int INT_ITERATIONS=1;

public void entryPassword(ksmUser user)
{
	//String salt=new SimpleHash(ALGORITHM_NAME,user.getUserName(),1).toHex();
    //user.setsalt(salt);
    String NewPassword=new SimpleHash(ALGORITHM_NAME,
    		user.getPassWord(),
    		INT_ITERATIONS).toHex();
	user.setPassWord(NewPassword);
}
	
}
