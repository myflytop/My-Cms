package com.ruoyi.cms.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.cms.mapper.CmsThemeMapper;
import com.ruoyi.cms.model.po.CmsTheme;
import com.ruoyi.cms.service.ICmsThemeService;
import com.ruoyi.cms.util.CmsContest;
import com.ruoyi.cms.util.CmsUtils;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;

@Service
public class CmsThemeServiceImpl implements ICmsThemeService {

	@Autowired
	private CmsThemeMapper themeMapper;

	@Override
	public int deleteByName(String themeName) throws FileNotFoundException {
		// TODO Auto-generated method stub		
			if(CmsUtils.deleteThemeFile(themeName))
			{
				themeMapper.deleteByName(themeName);
				return 1;
			}
			return 0;	
	}

	@Override
	public int insert(CmsTheme theme) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(CmsTheme theme) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CmsTheme selectById(Integer themeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(CmsTheme theme) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String uploadTheme(MultipartFile file, File themePath) throws Throwable, IOException {
		// TODO Auto-generated method stub
		// 上传
		file.transferTo(themePath);
		// 解压到当前目录
		ZipUtil.unzip(themePath, new File(themePath.getParent()));
		// 删除压缩文件
		FileUtil.del(themePath);
		// 读取配置主题文件
		String fileName = file.getOriginalFilename() ;
		String filePrefix=fileName.substring(0, fileName.lastIndexOf("."));
		ObjectMapper mapper = new ObjectMapper();
		// 读取主题说明
		CmsTheme theme = mapper.readValue(
				new File(themePath.getParent() + "/" + filePrefix+ "/theme.json"),
				CmsTheme.class);
		theme.setThemeEnabled((byte)0);
		themeMapper.insertSelective(theme);
		theme.setThemeName(filePrefix);
		CmsContest.CMSTHEMES.put(filePrefix, theme);
		return "uploadSuccess";
	}

	@Override
	public Map<String, CmsTheme> listThemes() throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		if(CmsContest.CMSTHEMES.isEmpty())
		{
			return CmsUtils.getThemes();
		}
		return CmsContest.CMSTHEMES;
	}
    @Transactional
    @Override
	public int setTheme(String themeName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//改变主题	
		String ma=CmsUtils.changeTheme(themeName);
		if(ma==null)
		{
			return 0;
		}	
		themeMapper.updateThemeEnable(ma,themeName);
		return 1;
	}

	public int settingTheme() {
		// TODO Auto-generated method stub
		
		return 0;
	}

}
