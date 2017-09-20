package cn.ssm.controller;
import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.ssm.exception.CustomException;
import cn.ssm.po.ItemsCustom;
import cn.ssm.po.ItemsQueryVo;
import cn.ssm.service.ItemsService;

/**
 * 
 * <p>Description:商品管理</p>
 * @author LXY
 * @time 2017年3月11日 下午9:14:35
 */
@Controller
//设置根路径
@RequestMapping("/items")
public class ItemsController {
	@Autowired
	private ItemsService itemsService ;
	
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(ItemsQueryVo itemsQueryVo) throws Exception{
		
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo) ;
		
		//返回ModelAndView
		ModelAndView modelAndView = new ModelAndView() ;
		//相当于request的setAttribut,在jsp页面中通过itemLists取数据
		modelAndView.addObject("itemsList",itemsList) ;
		//指定视图
		modelAndView.setViewName("/WEB-INF/jsp/items/itemLists.jsp");
		//在视图解析器中定义了jsp的前缀后缀，可以写成以下路径
		//modelAndView.setViewName("items/itemLists");
		return modelAndView;
	}
	
	//根据ID查询商品信息
	//@RequestMapping("/updateItems")
	//限定HTTP请求方式
	/*@RequestMapping(value="/updateItems",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updateItems() throws Exception{
		ItemsCustom itemsCustom = itemsService.findItemsById(1) ;
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.addObject("itemsCustom", itemsCustom) ;
		modelAndView.setViewName("/WEB-INF/jsp/items/updateItems.jsp");
		return modelAndView;
	}*/
	//@RequestParam指定request传入参数名称要和形参绑定
	//required指定参数是否必须要传入
	@RequestMapping(value="/updateItems",method={RequestMethod.POST,RequestMethod.GET})
	public String updateItems(Model model,@RequestParam(value="id",required=true) Integer itemsId) throws Exception{
		ItemsCustom itemsCustom = itemsService.findItemsById(itemsId) ;
		if(itemsCustom == null){
			throw new CustomException("商品信息不存在") ;
		}
		model.addAttribute("itemsCustom", itemsCustom) ;
		return "/WEB-INF/jsp/items/updateItems.jsp" ;
	}
	
	//查询商品信息——RESTful
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id) throws Exception{
		ItemsCustom itemsCustom = itemsService.findItemsById(id) ;
		return itemsCustom ;
	}
	
	//提交修改商品信息
	/*@RequestMapping("/updateItemsSubmit")
	public ModelAndView updateItemsSubmit() throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.setViewName("/WEB-INF/jsp/success.jsp");
		return modelAndView;
	}*/
	//在需要校验的pojo前添加@Validated，在需要校验的pojo后添加BindingResult bindingResult（接收错误校验信息），两者成对出现
	@RequestMapping("/updateItemsSubmit")
	public String updateItemsSubmit(Model model,Integer id,@Validated ItemsCustom itemsCustom,
			BindingResult bindingResult,MultipartFile items_pic) throws Exception{
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors = bindingResult.getAllErrors() ;
			for(ObjectError objectError:allErrors){
				System.out.println(objectError.getDefaultMessage());
			}
			model.addAttribute("allErrors", allErrors) ;
			//出错后跳转商品修改信息页面
			return "/WEB-INF/jsp/items/updateItems.jsp" ;
		}
		//上传图片
		if(items_pic!=null){
			//存储图片的物理路径
			String pic_path = "E:\\eclipse\\upload\\" ;
			//原始名称
			String originalFilename = items_pic.getOriginalFilename() ;
			//新图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf(".")) ;
			//新图片
			File newFile = new java.io.File(pic_path+newFileName) ;
			//将内存中的数据写入磁盘
			items_pic.transferTo(newFile);
			//将新的图片名称写入itemsCustom
			itemsCustom.setPic(newFileName);
		}
		itemsService.updateItems(id, itemsCustom);
		//return "redirect:queryItems.action" ;
		return "/WEB-INF/jsp/success.jsp" ;
	}
	
	//删除商品信息
	@RequestMapping("/deleteItem")
	/*public String deleteItem(Integer id){
		try {
			itemsService.deleteItem(id);
			return "/WEB-INF/jsp/success.jsp" ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}*/
	public ModelAndView deleteItem(Integer id){
		try {
			itemsService.deleteItem(id);
			ModelAndView modelAndView = new ModelAndView() ;
			modelAndView.setViewName("/WEB-INF/jsp/success.jsp");
			return modelAndView ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
	
	//批量删除商品信息
	@RequestMapping("/deleteItems")
	/*public String deleteAll(HttpServletRequest request,Integer[] items_id) throws Exception{
		itemsService.deleteItems(items_id);
		return "/WEB-INF/jsp/success.jsp" ;
		
	}*/
	public ModelAndView deleteAll(Integer[] items_id) throws Exception{
		itemsService.deleteItems(items_id);
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.setViewName("/WEB-INF/jsp/success.jsp");
		return modelAndView ;
	}
	//批量修改查询
	@RequestMapping("/updateItemsAll")
	public ModelAndView updateItemsAll(ItemsQueryVo itemsQueryVo) throws Exception{
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo) ;
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.addObject("itemsList",itemsList) ;
		modelAndView.setViewName("/WEB-INF/jsp/items/updateItemsAll.jsp");
		return modelAndView;
	}
	//批量修改提交
	@RequestMapping("/updateItemsAllSubmit")
	public String updateItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception{
		itemsService.updateItemsAll(itemsQueryVo);
		return "/WEB-INF/jsp/success.jsp" ;
	}
}
