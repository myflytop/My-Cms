package com.ruoyi.cms.service;

import java.util.List;

import com.ruoyi.cms.model.po.CmsCat;
import com.ruoyi.common.core.domain.Ztree;

public interface ICmsCatService {
	/**
	 * 添加类目
	 * 
	 * @param cmsCat
	 * @return
	 */
	int insertCmsCat(CmsCat cmsCat);

	/**
	 * 修改类目
	 * 
	 * @param CmsCat
	 * @return
	 */
	int updateCmsCat(CmsCat cmsCat);

	/**
	 * 获取类目列表
	 * 
	 * @param CmsCat
	 * @return
	 */
	List<CmsCat> listCmsCat(CmsCat cmsCat);

	/**
	 * 根据id获取类目
	 * 
	 * @param CmsCat
	 * @return
	 */
	public CmsCat getCmsCatById(Long catId);

	/**
	 * Ztree 封装
	 * 
	 * @return
	 */
	public List<Ztree> treeCatData(Byte b);

	/**
	 * 验证当前类目 类目名是否唯一
	 * 
	 * @param cms
	 * @return
	 */
	public String checkCatNameUnique(CmsCat cmsCat);

	

	/**
	 * 获取类目名
	 * 
	 * @param CatId
	 * @return
	 */
	String getCatNameById(Long catId);

	/**
	 * 删除单个类目
	 * 
	 * @param CatId
	 * @return
	 */
	int deleteCmsCatById(Long catId);
    
	/**
	 * 根据父节点 获取子节点列表
	 * @param catId
	 * @return
	 */
	List<CmsCat> listCmsCatById(Long catId);

	int removeArticleCat(long articleId, int catId);
	
	int insertArticleCat(long articleId, int catId);

}
