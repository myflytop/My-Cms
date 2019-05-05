package com.ruoyi.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.cms.model.po.CmsCat;

public interface CmsCatMapper {
	 /**
     * 添加类目
    * @param CmsCat
    * @return
    */
	int insertCmsCat(CmsCat cmsCat);
	/**
    * 修改类目
   * @param CmsCat
   * @return
   */
	int updateCmsCat(CmsCat cmsCat);
	 
	/**
	 * 删除单个类目导航
	 * @param cmsCatId
	 * @return
	 */
	int deleteCmsCatById(Long catId);
	
	/**
	 * 获取类目列表 所有字段
	 * @param CmsCat
	 * @return
	 */
	List<CmsCat> listCmsCat(CmsCat cmsCat);	
	
	/**
	 * 获取一条类目 所有字段
	 * @param parentId
	 * @return
	 */	
	CmsCat getCmsCatById(Long catId);
	
	/**
	 * 获取类目子列表 所有字段
	 * @param parentId
	 * @return
	 */
	List<CmsCat> listCmsCatById(Long parentId);
	
	/**
	 * 获取类目名
	 * @param columnId
	 * @return
	 */
	String getCatNameById(Long catId);
	
	/**
	 * 判断是否为父节点
	 * @param columnId
	 * @return
	 */
	int isParentById(Long catId);
	
	/**
	 * 统计是否存在子节点的
	 * @param columnId
	 * @return
	 */
	int countCatById(Long catId);
	/**
	 * 验证同类目下类目名唯一
	 * @param parentId
	 * @param columnName
	 * @return
	 */
	int checkCatNameUnique(@Param("parentId")Long parentId,@Param("catName")String catName,@Param("catId")Long catId);
	
	/**
	 * 关联文章  类别
	 * @param articleId
	 * @param cats
	 * @return
	 */
	int insertCmsArticleCats(@Param("articleId")Long articleId,@Param("cats") int[] cats);
    /**
     * 获取文章关联catId
     * @param articleId
     * @return
     */
	int[] listCatIds(long articleId);
	/**
     * 获取文章关联catId_catName
     * @param articleId
     * @return
     */
	String[] listCats(long articleId);
	/**
	 * 批量删除 文章关联的类目
	 * @param articleIds
	 * @return
	 */
	int deleteArticleCatByIds(@Param("articleIds")Long[] articleIds);
	/**
	 * 删除一条文章关联的类目
	 * @param articleId
	 * @param catId
	 * @return
	 */
	int removeArticleCat(@Param("articleId")long articleId,@Param("catId") int catId);
	/**
	 * 添加一条文章关联的类目
	 * @param articleId
	 * @param catId
	 * @return
	 */
	int insertArticleCat(@Param("articleId")long articleId,@Param("catId") int catId);
}