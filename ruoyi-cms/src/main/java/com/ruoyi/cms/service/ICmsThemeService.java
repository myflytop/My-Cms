package com.ruoyi.cms.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ruoyi.cms.model.po.CmsTheme;

public interface ICmsThemeService {
    /**
     * 删除主题 通过主题Id
     * @param themeId
     * @return
     * @throws FileNotFoundException 
     */
	int deleteByName(String themeName) throws FileNotFoundException;
    /**
     * 添加主题
     * @param theme
     * @return
     */
    int insert(CmsTheme theme);
    /**
     * 有选择添加
     * @param theme
     * @return
     */
    int insertSelective(CmsTheme theme);
    /**
     * 查询主题
     * @param themeId
     * @return
     */
    CmsTheme selectById(Integer themeId);
    /**
     * 更新主题
     * @param theme
     * @return
     */
    int updateById(CmsTheme theme);
    /**
     * 
     * @param file 文件
     * @param themePath 主题路径
     * @return
     * @throws IOException 
     * @throws Throwable 
     */
    String uploadTheme(MultipartFile file,File themePath) throws Throwable, IOException;
	int setTheme(String themeName) throws FileNotFoundException;
	Map<String, CmsTheme> listThemes() throws JsonParseException, JsonMappingException, IOException;
   
}
