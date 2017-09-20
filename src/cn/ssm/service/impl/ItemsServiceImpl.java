package cn.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ssm.mapper.ItemsMapper;
import cn.ssm.mapper.ItemsMapperCustom;
import cn.ssm.po.Items;
import cn.ssm.po.ItemsCustom;
import cn.ssm.po.ItemsQueryVo;
import cn.ssm.service.ItemsService;

/**
 * 商品管理
 * @author Administrator
 *
 */
public class ItemsServiceImpl implements ItemsService {

	@Autowired 
	private ItemsMapperCustom itemsMapperCustom ;
	@Autowired
	private ItemsMapper itemsMapper ;
	
	//商品查询列表
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		//通过ItemsMapperCustom查询数据库
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}
	
	//根据ID查询商品信息
	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		//使用的是mybatis逆向工程生成的方法，所以需要Items调用
		Items items = itemsMapper.selectByPrimaryKey(id) ;
		ItemsCustom itemsCustom = null ;
		
		if(items!=null){
			itemsCustom = new ItemsCustom() ;
			//通过BeanUtils.copyProperties()方法，将items的值拷贝到itemsCustom中
			BeanUtils.copyProperties(items, itemsCustom);
		}
		
		return itemsCustom ;
	}
	
	//修改商品信息
	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		//使用updateByPrimaryKeyWithBLOBs()方法可以修改itemsCustom中的所有属性，包括属性中含义text类型的字段
		//使用updateByPrimaryKeyWithBLOBs()方法，必须先注入ID
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom) ;
		
	}
	//根据ID删除商品信息
	@Override
	public void deleteItem(Integer id) throws Exception {
		itemsMapper.deleteByPrimaryKey(id) ;
	}
	//批量删除商品信息
	@Override
	public void deleteItems(Integer[] ids) throws Exception {
		for(int id : ids){
			itemsMapper.deleteByPrimaryKey(id) ;
		}
	}
	//批量修改商品信息
	@Override
	public void updateItemsAll(ItemsQueryVo itemsQueryVo) throws Exception {
		List<ItemsCustom> itemsCustom = itemsQueryVo.getItemsList() ;
		itemsMapperCustom.itemsUpdate(itemsCustom);
	}

}
