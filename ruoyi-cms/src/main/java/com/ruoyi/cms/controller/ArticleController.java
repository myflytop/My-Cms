package com.ruoyi.cms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.cms.model.po.CmsArticle;
import com.ruoyi.cms.model.po.CmsCat;
import com.ruoyi.cms.model.vo.ArticleVo;
import com.ruoyi.cms.service.impl.CmsArticleServiceImpl;
import com.ruoyi.cms.service.impl.CmsCatServiceImpl;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.util.ShiroUtils;

import cn.hutool.core.util.StrUtil;

/**
 * 文章 控制层
 * 
 * @author bobey
 * 
 */
@Controller
@RequestMapping("cms/article")
public class ArticleController extends BaseController {
	@Autowired
	private CmsCatServiceImpl catService;
	@Autowired
	private CmsArticleServiceImpl articleService;
	
	private String prefix = "cms/article";

	@GetMapping
	public String view() {

		return prefix + "/article";
	}

	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ArticleVo artVo) {
		        startPage();
		        List<CmsArticle> list = articleService.listCmsArticle(artVo);
		        return getDataTable(list);
	}

	/**
	 * @param catId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/add")
	public String addView(ModelMap mmap) {
		mmap.put("catId",0L );
		mmap.put("catName", "根类目");		
		return prefix + "/add";
	}
	
	/**
	 * @param articleId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/edit/{articleId}")
	public String editArticleView(@PathVariable("articleId") Long articleId,ModelMap mmap) {
		mmap.put("catId",0L );
		mmap.put("catName", "根类目");
		mmap.put("article", articleService.getArticleVo(articleId));	
		return prefix + "/edit";
	}
	
	
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(CmsArticle art) {
		art.setUpdateBy(ShiroUtils.getUserId());
		if(articleService.updateCmsArticleById(art)==1)
		{
			return AjaxResult.success("修改成功！");
		}
		else
		{
		return AjaxResult.error("验证未通过，请检查！");
		}
	}
	
	
	
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(ArticleVo artVo,ModelMap mmap) {
		artVo.setUpdateBy(ShiroUtils.getUserId());
		if(articleService.insertCmsArticle(artVo)==1)
		{
			return AjaxResult.success("添加成功！");
		}
		else
		{
		return AjaxResult.error("验证未通过，请检查！");
		}
	}
	
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		Long[] articleIds = Convert.toLongArray(ids);
		return toAjax(articleService.deleteCmsArticleByIds(articleIds));
	}
	

	@GetMapping("/selectCatTree/{catId}")
	public String selectCatTree(@PathVariable("catId") Long catId, ModelMap mmp) {
		CmsCat cmsCat = catService.getCmsCatById(catId);
		mmp.put("cmsCat", cmsCat);
		return prefix + "/tree";
	}

	/**
	 * 获取目录
	 * 
	 * @return
	 */
	@GetMapping("/catTreeData")
	@ResponseBody
	public List<Ztree> catTreeData() {
		Byte b=null;
		List<Ztree> ztrees = catService.treeCatData(b);
		return ztrees;
	}

	/**
	 * 校验url名称
	 * 不允许出现空格
	 */
	@PostMapping("/checkArticleUrl")
	@ResponseBody
	public String checkArticleUrl(@RequestParam("articleUrl") String articleUrl,Long articleId) {
		String url=StrUtil.replaceChars(articleUrl," ","");
		
		if(articleService.checkArticleUrl(url,articleId)==0)
		{	
			return UserConstants.MENU_NAME_UNIQUE;
		}
		return UserConstants.MENU_NAME_NOT_UNIQUE;
	}
	
	/**
	 * 校验Title名称
	 * 不允许出现空格
	 */
	@PostMapping("/checkArticleTitle")
	@ResponseBody
	public String checkArticleTitle(@RequestParam("articleTitle") String articleTitle,Long articleId) {
		
		if(articleService.checkArticleTitle(articleTitle,articleId)==0)
		{
			
			return UserConstants.MENU_NAME_UNIQUE;
		}
		return UserConstants.MENU_NAME_NOT_UNIQUE;
	}
	
	@PostMapping("/articleImg")
	@ResponseBody
	public String articleSummary(@RequestParam("upload") MultipartFile file) throws IOException {
		String avatar="";
		Map mp=new HashMap();
		if(!file.isEmpty())
		{
			 avatar = FileUploadUtils.upload(Global.getAvatarPath(), file);
		}
		mp.put("uploaded", file.getSize());
		mp.put("url", "/profile/avatar/"+avatar);
		ObjectMapper mo=new ObjectMapper();
		
		return mo.writeValueAsString(mp);
	}
	
	
}
