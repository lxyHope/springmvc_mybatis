package cn.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ssm.mapper.UserMapperCustom;
import cn.ssm.po.User;
import cn.ssm.po.UserVo;
import cn.ssm.service.LoginService;

public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserMapperCustom userMapperCustom ;
	//登录
	@Override
	public User login(String username, String address) throws Exception {
		User tmpUser = new User();
		tmpUser.setUsername(username);
		tmpUser.setAddress(address);
		tmpUser.setUser(tmpUser);
		User user = userMapperCustom.login(tmpUser) ;
		return user;
	}
	@Override
	public boolean userLogin(String username,String address) throws Exception {
		UserVo userVo = new UserVo() ;
		userVo.setUsername(username);
		userVo.setAddress(address);
		userMapperCustom.userLogin(userVo) ;
		if(username != null){
			return true ;
		}
		return false;
	}

}
