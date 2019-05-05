package com.ruoyi.cms.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ruoyi.cms.service.impl.CmsThemeServiceImpl;
import com.ruoyi.cms.util.CmsContest;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResult.Type;

@Controller
@RequestMapping("cms/theme")
public class ThemeController {	
	private String prefix = "cms/theme";
	@Autowired
	private CmsThemeServiceImpl themeService;
    
	/**
	 * 转向列表页设置页面
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@GetMapping
	public String themeView(ModelMap mp) throws JsonParseException, JsonMappingException, IOException {
		mp.put("themes", themeService.listThemes());    
		return prefix + "/theme";
	}
	  
	/**
	 * 上传主题
	 * @param file
	 * @param covery
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@PostMapping("/uploadTheme")
	@ResponseBody
	public AjaxResult themeUpload(@RequestParam("themeFile") MultipartFile file, @RequestParam(value ="covery", defaultValue = "false", required = false) boolean covery,HttpServletRequest request
			)
			throws Throwable {
		try {
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename() ;
				String filePrefix=fileName.substring(0, fileName.lastIndexOf("."));
				// 获取项目根路径
				final File basePath = new File(ResourceUtils.getURL("classpath:").getPath());  
				// 构建路径
				final File themePath = new File(basePath.getAbsolutePath(),
						new StringBuffer("templates/themes/").append(fileName).toString());
				final File par = new File(themePath.getParent());	
				// 判断是否存在(多模块.jar的内容不向外暴露)
				if (!par.exists()) {
					par.mkdirs();
				}
				System.err.println(covery);
				// 判断主题是否已经上传 是否可以覆盖上传
				if (new File(themePath.getParent(),"/"+filePrefix).exists()) {
					if (covery) {
						themeService.uploadTheme(file, themePath);
					}
					else {
						return new AjaxResult(Type.ERROR, "文件已经存在！", null);
					}
				} else {
					//file.transferTo(themePath);
					themeService.uploadTheme(file, themePath);
				}
				
				// 日志
				// log.info("Upload topic success, path is " + themePath.getAbsolutePath());
				// 保存日志
				// logsService.save(LogsRecord.UPLOAD_THEME, file.getOriginalFilename(),
				// request);
				// 解压
				//ZipUtil.unzip(themePath, new File(basePath.getAbsolutePath(), "templates/themes/"));
				// 删除压缩包
				//FileUtil.del(themePath);

				// 清空主题列表
				// THEMES.clear();
				// 填充主题列表
				// THEMES = HaloUtils.getThemes();
			} else {
				return new AjaxResult(Type.ERROR, "文件不能为空！", null);
				/*
				 * log.error("Upload theme failed, no file selected"); return new
				 * JsonResult(ResultCodeEnum.FAIL.getCode(),
				 * localeMessageUtil.getMessage("code.admin.theme.upload-no-file"));
				 */
			}
		} catch (Exception e) {
			return new AjaxResult(Type.ERROR, "服务异常！", e.getMessage());
			/*
			 * log.error("Upload theme failed: {}", e.getMessage()); return new
			 * JsonResult(ResultCodeEnum.FAIL.getCode(),
			 * localeMessageUtil.getMessage("code.admin.theme.upload-failed"));
			 */
		}
		/*
		 * return new JsonResult(ResultCodeEnum.SUCCESS.getCode(),
		 * localeMessageUtil.getMessage("code.admin.theme.upload-success"));
		 */

		return AjaxResult.success("上传成功！");
	}
	
	/**
	 * 启用主题
	 * @return
	 * @throws FileNotFoundException 
	 */
	@PostMapping("/setTheme")
	@ResponseBody
	public AjaxResult setTheme(String themeName) throws FileNotFoundException {	
		
		if(CmsContest.CMSTHEMES.get(themeName).getThemeEnabled()==1) {
			return AjaxResult.error("Theme used");
		}
		
		if(themeService.setTheme(themeName)==0)
		{   
			return AjaxResult.error("set fial");
		}
		WebController.THEME=themeName;
		return AjaxResult.success("set Success");
	}
	@PostMapping("/deleteTheme")
	@ResponseBody
	public AjaxResult deleteTheme(String themeName) throws FileNotFoundException {
		if("theme".equals(themeName))
		{   
			return AjaxResult.error("系统主题不允许删除");
		}
		if(themeService.deleteByName(themeName)==0)
		{   
			return AjaxResult.error("delset fial");
		}
		WebController.THEME=themeName;
		return AjaxResult.success("delset Success");
	}
	
	/**
	 * 设置主题属性
	 * @return
	 */
	public AjaxResult settingTheme() {
		
		
		return AjaxResult.success("settingTheme Success",themeService.settingTheme());
	}

}
