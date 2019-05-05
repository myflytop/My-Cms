package com.ruoyi.cms.model.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CmsTheme implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer themeId;

    private String themeName;

    private String themeAuthor;
    
    private String themeUpdate;

    private Date createTime;

    private String themeInfo;
    
    private String themeVersion;

    private Byte themeEnabled;

    private String themeTouch;

    private Long createBy;

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName == null ? null : themeName.trim();
    }

    public String getThemeAuthor() {
        return themeAuthor;
    }

    public void setThemeAuthor(String themeAuthor) {
        this.themeAuthor = themeAuthor == null ? null : themeAuthor.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getThemeInfo() {
        return themeInfo;
    }

    public void setThemeInfo(String themeInfo) {
        this.themeInfo = themeInfo == null ? null : themeInfo.trim();
    }

    public Byte getThemeEnabled() {
        return themeEnabled;
    }

    public void setThemeEnabled(Byte themeEnabled) {
        this.themeEnabled = themeEnabled;
    }

    public String getThemeTouch() {
        return themeTouch;
    }

    public void setThemeTouch(String themeTouch) {
        this.themeTouch = themeTouch == null ? null : themeTouch.trim();
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

	public String getThemeUpdate() {
		return themeUpdate;
	}

	public void setThemeUpdate(String themeUpdate) {
		this.themeUpdate = themeUpdate;
	}

	public String getThemeVersion() {
		return themeVersion;
	}

	public void setThemeVersion(String themeVersion) {
		this.themeVersion = themeVersion;
	}
}