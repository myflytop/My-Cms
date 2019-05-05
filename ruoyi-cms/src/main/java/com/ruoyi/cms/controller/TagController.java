package com.ruoyi.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.cms.model.po.CmsTag;
import com.ruoyi.cms.service.impl.CmsTagServiceImpl;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResult.Type;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 类别 控制层
 * 
 * @author bobey
 * 
 *         需要权限 只能控制授权栏目
 */
@Controller
@RequestMapping("cms/tag")
public class TagController extends BaseController {
	@Autowired
	private CmsTagServiceImpl tagService;

	private String prefix = "cms/tag";

	@GetMapping
	public String tagView(CmsTag tag, ModelMap mmp) {

		mmp.put("tags", tagService.listCmsTag(tag));
		return prefix + "/tag";
	}

	@GetMapping("/edit/{tagId}")
	public String editView(@PathVariable("tagId") int tagId, ModelMap mmp) {
		mmp.put("tag", tagService.getCmsTagById(tagId));
		return prefix + "/edit";
	}

	@GetMapping("/detail/{tagId}")
	public String detailView(@PathVariable("tagId") int tagId, ModelMap mmp) {
		mmp.put("tag", tagService.getCmsTagById(tagId));
		return prefix + "/detail";
	}

	@PostMapping("/list")
	@ResponseBody
	public AjaxResult listTag(CmsTag tag) {
		Type t = Type.SUCCESS;
		String msg = "success";
		List<CmsTag> tags = tagService.listCmsTag(tag);
		if (tags == null) {
			t = Type.ERROR;
			msg = "NotFind";
		}
		return new AjaxResult(t, msg, tags);
	}

	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addTag(CmsTag tag) {
		Type t = Type.SUCCESS;
		String msg = "success";
		tag.setCreateBy(ShiroUtils.getUserId());
		if (tagService.checkTagNameUnique(tag.getTagName(),tag.getTagId()).equals(UserConstants.MENU_NAME_NOT_UNIQUE)
				|| tagService.insertCmsTag(tag) != 1) {
			t = Type.ERROR;
			msg = "AddFile";
		}
		return new AjaxResult(t, msg, tag);
	}

	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult updateTag(CmsTag tag) {
		Type t = Type.SUCCESS;
		tag.setUpdateBy(ShiroUtils.getUserId());
		String msg = "success";
		if (tagService.checkTagNameUnique(tag.getTagName(),tag.getTagId()).equals(UserConstants.MENU_NAME_NOT_UNIQUE)
				|| tagService.updateCmsTagById(tag) != 1) {
			t = Type.ERROR;
			msg = "EditFile";
		}
		return new AjaxResult(t, msg, null);
	}

	@GetMapping("/remove/{tagId}")
	@ResponseBody
	public AjaxResult delTag(@PathVariable("tagId")int tagId) {
		
		
		tagService.deleteCmsTagById(tagId);
		return success();
	}

	@PostMapping("/checkTagNameUnique")
	@ResponseBody
	public String checkTagNameUnique(String tagName,Integer tagId) {
		return tagService.checkTagNameUnique(tagName,tagId);
	}

	/**
	 * 移除文章与标签关联
	 * @param cms
	 * @return
	 */
	@PostMapping("/removeTagArticle")
	@ResponseBody
	public AjaxResult removeArticleTag(long articleId,int tagId) {
		  tagService.removeArticleTag(articleId, tagId);
		return success();
	}
	
	/**
	 * 添加文章与标签关联
	 * @param cms
	 * @return
	 */
	@PostMapping("/addTagArticle")
	@ResponseBody
	public AjaxResult addArticleTag(long articleId,int tagId) {
		tagService.insertArticleTag(articleId, tagId);
		return success();
	}

	
}
