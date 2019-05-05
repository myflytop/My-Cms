package com.ruoyi.cms.model.po;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.ruoyi.common.core.domain.BaseEntity;

public class CmsCat extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long catId;

    private String catName;

    private String catIcon;
    
    private String catUrl;

    private Byte visible;

    private Byte orderNo;

    private Long parentId;
    
 // 是否父元素
 	private Byte parent;


    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public String getCatIcon() {
        return catIcon;
    }

    public void setCatIcon(String catIcon) {
        this.catIcon = catIcon == null ? null : catIcon.trim();
    }

    public Byte getVisible() {
        return visible;
    }

    public void setVisible(Byte visible) {
        this.visible = visible;
    }

    public Byte getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Byte orderNo) {
        this.orderNo = orderNo;
    }

    public Long getParentId() {
        return parentId;
    }

    @Override
	public String toString() {
		ToStringBuilder bu = new ToStringBuilder(this);
		bu.append("catId", catId);
		bu.append("catName", catName);
		bu.append("catIcon", catIcon);
		bu.append("visible", visible);
		bu.append("orderNo", orderNo);
		bu.append("parentId", parentId);
		bu.append("getCreateBy()", getCreateBy());
		bu.append("getCreateTime()", getCreateTime());
		bu.append("getUpdateBy()", getUpdateBy());
		bu.append("getUpdateTime()", getUpdateTime());
		bu.append("getRemark()", getRemark());
		bu.append("catUrl", catUrl);
		bu.append("parent", parent);
		return bu.toString();
	}

	public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

	public String getCatUrl() {
		return catUrl;
	}

	public void setCatUrl(String catUrl) {
		this.catUrl = catUrl;
	}

	public Byte getParent() {
		return parent;
	}

	public void setParent(Byte parent) {
		this.parent = parent;
	}

    

}