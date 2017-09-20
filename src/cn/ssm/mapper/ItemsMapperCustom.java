package cn.ssm.mapper;

import cn.ssm.po.ItemsCustom;
import cn.ssm.po.ItemsQueryVo;

import java.util.List;


public interface ItemsMapperCustom {
	//商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception ;
	//批量修改
	public void itemsUpdate(List<ItemsCustom> itemsList) throws Exception ;
}