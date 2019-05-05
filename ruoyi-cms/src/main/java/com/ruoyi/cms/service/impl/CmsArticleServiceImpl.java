package com.ruoyi.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.cms.mapper.CmsArticleMapper;
import com.ruoyi.cms.mapper.CmsCatMapper;
import com.ruoyi.cms.mapper.CmsTagMapper;
import com.ruoyi.cms.model.po.CmsArticle;
import com.ruoyi.cms.model.vo.ArticleVo;
import com.ruoyi.cms.service.ICmsArticleService;
import com.ruoyi.common.core.text.Convert;

@Service
public class CmsArticleServiceImpl implements ICmsArticleService {
	@Autowired
	private CmsArticleMapper articleMapple;

	@Autowired
	private CmsTagMapper tagMapper;

	@Autowired
	private CmsCatMapper catMapper;

	@Transactional
	@Override
	public int insertCmsArticle(ArticleVo arVo) {
		// TODO Auto-generated method stub
		int uqin = articleMapple.checkUrlAndTitle(arVo.getArticleUrl(), arVo.getArticleTitle(), null);
		if (uqin == 0) {
			CmsArticle art = new CmsArticle();
			art.setAllowComment(arVo.getAllowComment());
			art.setArticleBuild(arVo.getArticleBuild());
			art.setArticleContent(arVo.getArticleContent());
			art.setArticleImg(arVo.getArticleImg());
			art.setArticleType(arVo.getArticleType());
			art.setArticleSummary(arVo.getArticleSummary());
			art.setArticleTitle(arVo.getArticleTitle());
			art.setCreateBy(arVo.getCreateBy());
			art.setReprintUrl(arVo.getReprintUrl());
			art.setArticleUrl(arVo.getArticleUrl());
			art.setKeyword(arVo.getKeyword());
			art.setArticleTop((byte) 0);
			art.setVisible((byte) 0);

			articleMapple.insertCmsArticle(art);
			if (arVo.getTags().length > 0) {
				tagMapper.insertCmsArticleTags(art.getArticleId(), arVo.getTags());
			}
			if (arVo.getCats().length > 0) {
				catMapper.insertCmsArticleCats(art.getArticleId(), arVo.getCats());
			}
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteCmsArticleById(Long id) {
		// TODO Auto-generated method stub
		return articleMapple.deleteCmsArticleById(id);
	}

	/**
	 * 删除关联cat 删除关联tag
	 */
	@Override
	public int deleteCmsArticleByIds(Long[] articleIds) {
		// TODO Auto-generated method stub

		tagMapper.deleteArticleTagByIds(articleIds);
		catMapper.deleteArticleCatByIds(articleIds);
		return articleMapple.deleteCmsArticleByIds(articleIds);
	}

	@Override
	public CmsArticle getCmsArticleById(Long id) {
		// TODO Auto-generated method stub
		return articleMapple.getCmsArticleById(id);
	}

	@Override
	public int updateCmsArticleById(CmsArticle cmsArticle) {
		// TODO Auto-generated method stub

		if (articleMapple.checkUrlAndTitle(cmsArticle.getArticleUrl(), cmsArticle.getArticleTitle(),
				cmsArticle.getArticleId()) == 0) {
			return articleMapple.updateCmsArticleById(cmsArticle);
		}
		return -1;
	}

	@Override
	public List<CmsArticle> listCmsArticle(ArticleVo artVo) {
		// TODO Auto-generated method stub
		return articleMapple.listCmsArticle(artVo);
	}

	@Override
	public int checkArticleUrl(String articleUrl,Long articleId) {
		// TODO Auto-generated method stub
		return articleMapple.checkArticleUrl(articleUrl,articleId);
	}

	@Override
	public int checkArticleTitle(String articleTitle,Long articleId) {
		// TODO Auto-generated method stub
		return articleMapple.checkArticleTitle(articleTitle,articleId);
	}

	@Override
	public ArticleVo getArticleVo(Long articleId) {
		ArticleVo arVo = new ArticleVo();
		CmsArticle cmArt = articleMapple.getCmsArticleById(articleId);

		arVo.setArticleId(cmArt.getArticleId());
		arVo.setAllowComment(cmArt.getAllowComment());
		arVo.setArticleContent(cmArt.getArticleContent());
		arVo.setArticleImg(cmArt.getArticleImg());
		arVo.setArticleTop(cmArt.getArticleTop());
		arVo.setArticleTitle(cmArt.getArticleTitle());
		arVo.setArticleType(cmArt.getArticleType());
		arVo.setVisible(cmArt.getVisible());
		arVo.setArticleSummary(cmArt.getArticleSummary());
		arVo.setReprintUrl(cmArt.getReprintUrl());
		arVo.setArticleUrl(cmArt.getArticleUrl());
		arVo.setArticleBuild(cmArt.getArticleBuild());
		arVo.setKeyword(cmArt.getKeyword());
		/*
		 * arVo.setCats(catMapper.listCatIds(articleId));
		 * arVo.setTags(tagMapper.listTagIds(articleId));
		 */

		HashMap<String, Object> pm = new HashMap<>();
		pm.put("cats", catMapper.listCats(articleId));
		pm.put("tags", tagMapper.listTags(articleId));
		arVo.setParams(pm);
		return arVo;
	}

}
