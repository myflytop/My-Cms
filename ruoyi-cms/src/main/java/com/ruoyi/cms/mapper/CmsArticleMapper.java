package com.ruoyi.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.cms.model.po.CmsArticle;
import com.ruoyi.cms.model.vo.ArticleVo;

public interface CmsArticleMapper {
	/**
	 *验证articleUrl是否唯一
	 * @param articleId 
	 * 
	 * @return
	 */
	int checkArticleUrl(@Param("articleUrl")String articleUrl,@Param("articleId") Long articleId);
	/**
	 *验证articleTitle是否唯一
	 * @param articleId 
	 * 
	 * @return
	 */
	int checkArticleTitle(@Param("articleTitle")String articleTitle,@Param("articleId") Long articleId);
	/**
	 * 文章插入
	 * @param cmsArticle
	 * @return
	 */
	int insertCmsArticle(CmsArticle cmsArticle);
	/**
	 * 关联分类 
	 * @param articleId
	 * @param catId
	 * @return
	 */
	int insertCmsArticleCat(@Param("articleId")long articleId,@Param("catId")long[] catIds);
	
	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
    int deleteCmsArticleById(Long id);
    /**
     * 删除一条文章关联
     * @param articleId
     * @param catId
     * @return
     */
    int deleteAcOne(@Param("articleId")long articleId,@Param("catId")long catId);
    /**
     * 批量删除文章
     * @param ids
     * @return
     */
    int deleteCmsArticleByIds(@Param("articleIds")Long[] articleIds);
    /**
     * 通过id获取文章
     * @param id
     * @return
     */
    CmsArticle getCmsArticleById(Long id);
    /**
     * 通过id更新文章
     * @param cmsArticle
     * @return
     */
    int updateCmsArticleById(CmsArticle cmsArticle);
    
    
    /**
     * 文章链接=分类路径/文章路径
     * 获取文章所有信息
     * @param cmsArticle
     * @return
     */
    List<CmsArticle> listCmsArticle(ArticleVo artVo);
    
    /**
     * 获取文章 基本信息
     * 
     * 通过文章表查询
     * 
     * 通过分类查询
     * 
     * 
     * @param cmsArticle
     * @return
     */
    List<CmsArticle> listCmsArticleBasic(CmsArticle cmsArticle);
    /**
     * 添加时无需文章id
     * 修改时需要文章id
     * @param articleUrl
     * @param articleTitle
     * @return
     */
    int checkUrlAndTitle(@Param("articleUrl")String articleUrl,@Param("articleTitle")String articleTitle,@Param("articleId")Long articleId);
    
}