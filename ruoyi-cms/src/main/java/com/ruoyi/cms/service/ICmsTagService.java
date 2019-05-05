package com.ruoyi.cms.service;

import java.util.List;

import com.ruoyi.cms.model.po.CmsTag;

public interface ICmsTagService  {

	    int deleteCmsTagById(Integer tagId);

	    int insertCmsTag(CmsTag cmsTag);

	    int insertCmsTagSelective(CmsTag cmsTag);

	    CmsTag getCmsTagById(Integer tagId);
	    
	    List<CmsTag> listCmsTag(CmsTag cmsTag);

	    int updateCmsTagById(CmsTag cmsTag);
	    
	    int countTagName(String tagName);

		String checkTagNameUnique(String tagName,Integer tagId);
		
		int removeArticleTag(long articleId, int tagId);
		
		int insertArticleTag(long articleId, int tagId);
	
}
