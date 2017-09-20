package cn.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ssm.po.ItemsCustom;

/**
 * Json测试
 * <p>Description:1）请求json，返回json；2）请求key/value,返回json</p>
 * @author LXY
 * @time 2017年3月14日 下午3:40:42
 */
@Controller
public class JsonTest {
	//请求json（商品信息），输出json（商品信息）
	//@RequestBody将请求的商品信息的json串转成itemsCustom对象
	//@ResponseBody将itemsCustom转成json输出
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom){
		//@ResponseBody将itemsCustom转成json输出
		return itemsCustom ;
	}
	@RequestMapping("/responseJson")
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom){
		//@ResponseBody将itemsCustom转成json输出
		return itemsCustom ;
	}
}
