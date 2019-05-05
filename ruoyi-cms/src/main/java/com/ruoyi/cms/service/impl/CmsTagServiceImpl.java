package com.ruoyi.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.cms.mapper.CmsTagMapper;
import com.ruoyi.cms.model.po.CmsTag;
import com.ruoyi.cms.service.ICmsTagService;
import com.ruoyi.common.constant.UserConstants;
@Service
public class CmsTagServiceImpl implements ICmsTagService {
	
	@Autowired
	private CmsTagMapper tagMapper;

	@Override
	public int deleteCmsTagById(Integer tagId) {
		// TODO Auto-generated method stub
		return tagMapper.deleteCmsTagById(tagId);
	}

	@Override
	public int insertCmsTag(CmsTag cmsTag) {
		// TODO Auto-generated method stub
		return tagMapper.insertCmsTag(cmsTag);
	}

	@Override
	public int insertCmsTagSelective(CmsTag cmsTag) {
		// TODO Auto-generated method stub
		return tagMapper.insertCmsTagSelective(cmsTag);
	}

	@Override
	public CmsTag getCmsTagById(Integer tagId) {
		// TODO Auto-generated method stub
		return tagMapper.getCmsTagById(tagId);
	}

	

	@Override
	public int countTagName(String tagName) {
		// TODO Auto-generated method stub
		return tagMapper.countTagName(tagName);
	}

	@Override
	public List<CmsTag> listCmsTag(CmsTag cmsTag) {
		// TODO Auto-generated method stub
		return tagMapper.listCmsTag(cmsTag);
	}

	@Override
	public int updateCmsTagById(CmsTag cmsTag) {
		// TODO Auto-generated method stub
		return tagMapper.updateCmsTagById(cmsTag);
	}
    
	@Override
	public String checkTagNameUnique(String tagName,Integer tagId) {
		// TODO Auto-generated method stub
		if(tagMapper.checkTagNameUnique(tagName,tagId)==0)
		{
			return UserConstants.MENU_NAME_UNIQUE; 
		}
		return UserConstants.MENU_NAME_NOT_UNIQUE;
	}

	@Override
	public int removeArticleTag(long articleId, int tagId) {
		// TODO Auto-generated method stub
		return tagMapper.removeArticleTag(articleId, tagId);
	}

	@Override
	public int insertArticleTag(long articleId, int tagId) {
		// TODO Auto-generated method stub
		return tagMapper.insertArticleTag(articleId, tagId);
	}

}
