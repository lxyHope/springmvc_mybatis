package cn.ssm.mapper;

import cn.ssm.po.User;
import cn.ssm.po.UserVo;

public interface UserMapperCustom {
	//登录
	public User login(User user) throws Exception ;
	
	//用户登录
	public boolean userLogin(UserVo userVo) throws Exception ;
}
