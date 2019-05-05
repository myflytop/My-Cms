package com.ruoyi.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.cms.model.po.CmsColumn;

public interface CmsColumnMapper {
    /**
      * 添加栏目
     * @param CmsColumn
     * @return
     */
	int insertCmsColumn(CmsColumn cmsColumn);
	
	/**
     * 修改栏目
    * @param CmsColumn
    * @return
    */
	int updateCmsColumn(CmsColumn cmsColumn); 
	
	/**
	 * 删除单个栏目导航
	 * @param cmsColumnId
	 * @return
	 */
	int deleteCmsColumnById(Long columnId);
	
	/**
	 * 获取栏目列表 所有字段
	 * @param CmsColumn
	 * @return
	 */
	List<CmsColumn> listCmsColumn(CmsColumn cmsColumn);
	
	/**
	 * 获取一条栏目 所有字段
	 * @param columnId
	 * @return
	 */
	CmsColumn getCmsColumnById(Long columnId);

	/**
	 * 通过 parentId获取子节点 所有字段
	 * @param parentId
	 * @return
	 */
	List<CmsColumn> listCmsColumnById(Long parentId);
		
	/**
	 * 验证同栏目下栏目名唯一
	 * @param parentId
	 * @param columnName
	 * @return
	 */
	int checkcolumnNameUnique(@Param("parentId")Long parentId,@Param("columnName")String columnName,@Param("columnId")Long columnId);
	
	/**
	 * 获取父类目名
	 * @param parentId
	 * @return
	 */
	String getCmsColumnNameById(Long parentId);
	
	/**
	 * 统计是否存在子节点的
	 * @param columnId
	 * @return
	 */
	int countCmsColumnById(Long columnId);
	
}
