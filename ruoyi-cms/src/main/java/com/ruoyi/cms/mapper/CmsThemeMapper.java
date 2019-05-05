package com.ruoyi.cms.mapper;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.cms.model.po.CmsTheme;

public interface CmsThemeMapper {
    int deleteByName(String themeName);

    int insert(CmsTheme theme);

    int insertSelective(CmsTheme theme);

    CmsTheme selectById(Integer themeId);

    int updateById(CmsTheme theme);
    
    int updateThemeEnable(@Param("oldName")String oldName,@Param("newName")String newName);
}