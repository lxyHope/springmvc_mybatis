package cn.ssm.service;

import cn.ssm.po.User;

public interface LoginService {
	/**
	 * 登录
	 * @param username：前台传入的用户帐号
	 * @param password：前台传入的用户密码
	 * @return
	 * @throws Exception
	 */
	public User login(String username,String address) throws Exception ;
	/**
	 * 登录——创建单独的pojo
	 * @param userVo:只包含username和address两个字段
	 * @return
	 * @throws Exception
	 */
	public boolean userLogin(String username,String address) throws Exception ;
}
