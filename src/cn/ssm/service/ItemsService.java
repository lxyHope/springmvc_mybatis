package cn.ssm.service;

import java.util.List;

import cn.ssm.po.ItemsCustom;
import cn.ssm.po.ItemsQueryVo;

/**
 * <p>Description:商品新管理</p>
 * @author lxy
 * @time 2017年3月10日 上午11:12:45
 */
public interface ItemsService {
	//商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception ;
	/**
	 * 根据ID查询商品信息
	 * @param id:查询的商品信息的ID
	 * @return
	 * @throws Exception
	 */
	public ItemsCustom findItemsById(Integer id) throws Exception ;
	/**
	 * 修改商品信息
	 * @param id:修改商品的ID
	 * @param itemsCustom:修改商品的信息
	 * @throws Exception
	 */
	public void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception ;
	/**
	 * 根据ID删除商品信息
	 * @param id:要删除的商品ID
	 * @throws Exception
	 */
	public void deleteItem(Integer id) throws Exception ;
	/**
	 * 批量删除
	 * @param id:批量删除的商品ID的数组
	 * @throws Exception
	 */
	public void deleteItems(Integer[] id) throws Exception ;
	/**
	 * 批量修改商品信息
	 * @param itemsCustom:传入的商品信息
	 * @throws Exception
	 */
	public void updateItemsAll(ItemsQueryVo itemsQueryVo) throws Exception ;
}

