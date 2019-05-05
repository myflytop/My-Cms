package com.ruoyi.cms.model.vo;

import java.util.Arrays;

import com.ruoyi.cms.model.po.CmsArticle;

public class ArticleVo extends CmsArticle {
	
	private static final long serialVersionUID = 1L;
	//标签
    private int[] tags;
    //分类
    private int[] cats;
	public int[] getTags() {
		return tags;
	}
	public void setTags(int[] tags) {
		this.tags = tags;
	}
	public int[] getCats() {
		return cats;
	}
	public void setCats(int[] cats) {
		this.cats = cats;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleVo [tags=");
		builder.append(Arrays.toString(tags));
		builder.append(", cats=");
		builder.append(Arrays.toString(cats));
		builder.append(", getArticleTitle()=");
		builder.append(getArticleTitle());
		builder.append(", getArticleType()=");
		builder.append(getArticleType());
		builder.append(", getArticleBuild()=");
		builder.append(getArticleBuild());
		builder.append(", getArticleTop()=");
		builder.append(getArticleTop());
		builder.append(", getArticleImg()=");
		builder.append(getArticleImg());
		builder.append(", getArticleSummary()=");
		builder.append(getArticleSummary());
		builder.append(", getVisible()=");
		builder.append(getVisible());
		builder.append(", getAllowComment()=");
		builder.append(getAllowComment());
		builder.append(", getKeyword()=");
		builder.append(getKeyword());
		builder.append(", getArticleContent()=");
		builder.append(getArticleContent());
		builder.append(", getReprintUrl()=");
		builder.append(getReprintUrl());
		builder.append(", getArticleId()=");
		builder.append(getArticleId());
		builder.append(", getArticleUrl()=");
		builder.append(getArticleUrl());
		builder.append(", getCreateBy()=");
		builder.append(getCreateBy());
		builder.append(", getCreateTime()=");
		builder.append(getCreateTime());
		builder.append(", getUpdateBy()=");
		builder.append(getUpdateBy());
		builder.append(", getUpdateTime()=");
		builder.append(getUpdateTime());
		builder.append(", getParams()=");
		builder.append(getParams());
		builder.append("]");
		return builder.toString();
	}
    
   

}
