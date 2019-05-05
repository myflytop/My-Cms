package com.ruoyi.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.cms.model.po.CmsTag;

public interface CmsTagMapper {
	
    int deleteCmsTagById(Integer tagId);

    int insertCmsTag(CmsTag cmsTag);
    
    int insertCmsArticleTags(@Param("articleId")long articleId,@Param("tags") int[] tags);

    int insertCmsTagSelective(CmsTag cmsTag);

    CmsTag getCmsTagById(Integer tagId);

    List<CmsTag> listCmsTag(CmsTag cmsTag);
    
    int countTagName(String tagName);

    int updateCmsTagById(CmsTag cmsTag);

	int checkTagNameUnique(@Param("tagName")String tagName,@Param("tagId") Integer tagId);
	
	int[] listTagIds(long articleId);
	
	String[] listTags(long articleId);

	int deleteArticleTagByIds(@Param("articleIds")Long[] articleIds);
	
	/**
	 * 删除一条文章关联的标签
	 * @param articleId
	 * @param catId
	 * @return
	 */
	int removeArticleTag(@Param("articleId")long articleId,@Param("tagId") int tagId);
	/**
	 * 添加一条文章关联的标签
	 * @param articleId
	 * @param catId
	 * @return
	 */
	int insertArticleTag(@Param("articleId")long articleId,@Param("tagId") int tagId);
}