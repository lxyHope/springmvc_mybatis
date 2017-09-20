package cn.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.ssm.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService ;
	//登录
	/*@RequestMapping("/login")
	public String login(HttpSession session,String username,String address) throws Exception{
		User user = loginService.login(username, address) ;
		session.setAttribute("user", user);
		return "redirect:/items/queryItems.aciton" ;
	}*/
	@RequestMapping("/login")
	public String login(HttpSession session,String username,String address) throws Exception{
		boolean flag = loginService.userLogin(username, address) ;
		if(flag){
			session.setAttribute("username", username);
			return "redirect:/items/queryItems.aciton" ;
		}
		return "redirect:/items/queryItems.aciton" ;
	}
	
	//退出登录
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/items/queryItems.aciton" ;
	}
}
