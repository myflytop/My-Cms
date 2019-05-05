package com.ruoyi.cms.service;

import java.util.List;

import com.ruoyi.cms.model.po.CmsArticle;
import com.ruoyi.cms.model.vo.ArticleVo;

public interface ICmsArticleService {
	
    int insertCmsArticle(ArticleVo articleVo);
	
    int deleteCmsArticleById(Long id);
    
    int deleteCmsArticleByIds(Long[] articleIds);

    CmsArticle getCmsArticleById(Long id);

    int updateCmsArticleById(CmsArticle cmsArticle);
    
    List<CmsArticle> listCmsArticle(ArticleVo artVo);
    
    int checkArticleUrl(String articleUrl,Long articleId);
    
    int checkArticleTitle(String articleTitle,Long articleId);
    
    ArticleVo getArticleVo(Long articleId);


}
