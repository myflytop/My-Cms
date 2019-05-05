package com.ruoyi.cms.model.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.cms.model.base.BaseModel;

public class CmsArticle extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4796285731272251639L;

	private Long articleId;


	private String articleTitle;

	// 转载url
	private String reprintUrl;

	// 固定链接
	private String articleUrl;

	private Byte articleType;

	private Byte articleBuild;

	private Byte articleTop;

	private String articleImg;

	private String articleSummary;

	private Byte visible;

	private Boolean allowComment;

	private String keyword;

	private String articleContent;

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public Byte getArticleType() {
		return articleType;
	}

	public void setArticleType(Byte articleType) {
		this.articleType = articleType;
	}

	public Byte getArticleBuild() {
		return articleBuild;
	}

	public void setArticleBuild(Byte articleBuild) {
		this.articleBuild = articleBuild;
	}

	public Byte getArticleTop() {
		return articleTop;
	}

	public void setArticleTop(Byte articleTop) {
		this.articleTop = articleTop;
	}

	public String getArticleImg() {
		return articleImg;
	}

	public void setArticleImg(String articleImg) {
		this.articleImg = articleImg;
	}

	public String getArticleSummary() {
		return articleSummary;
	}

	public void setArticleSummary(String articleSummary) {
		this.articleSummary = articleSummary;
	}

	public Byte getVisible() {
		return visible;
	}

	public void setVisible(Byte visible) {
		this.visible = visible;
	}

	public Boolean getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(Boolean allowComment) {
		this.allowComment = allowComment;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getReprintUrl() {
		return reprintUrl;
	}

	public void setReprintUrl(String reprintUrl) {
		this.reprintUrl = reprintUrl;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	
	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CmsArticle [articleId=");
		builder.append(articleId);
		builder.append(", createBy=");
		builder.append(getCreateBy());
		builder.append(", updateBy=");
		builder.append(getUpdateBy());
		builder.append(", articleTitle=");
		builder.append(articleTitle);
		builder.append(", reprintUrl=");
		builder.append(reprintUrl);
		builder.append(", articleUrl=");
		builder.append(articleUrl);
		builder.append(", articleType=");
		builder.append(articleType);
		builder.append(", articleBuild=");
		builder.append(articleBuild);
		builder.append(", articleTop=");
		builder.append(articleTop);
		builder.append(", articleImg=");
		builder.append(articleImg);
		builder.append(", articleSummary=");
		builder.append(articleSummary);
		builder.append(", visible=");
		builder.append(visible);
		builder.append(", allowComment=");
		builder.append(allowComment);
		builder.append(", keyword=");
		builder.append(keyword);
		builder.append(", articleContent=");
		builder.append(articleContent);
		builder.append(", createTime=");
		builder.append(getCreateTime());
		builder.append(", params=");
		builder.append(getParams());
		builder.append(", updateTime=");
		builder.append(getUpdateTime());
		builder.append("]");
		return builder.toString();
	}

}