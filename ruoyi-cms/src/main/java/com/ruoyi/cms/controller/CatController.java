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

import com.ruoyi.cms.model.po.CmsCat;
import com.ruoyi.cms.service.impl.CmsCatServiceImpl;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
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
@RequestMapping("cms/cat")
public class CatController extends BaseController {
	@Autowired
	private CmsCatServiceImpl catService;
	private String prefix = "cms/cat";

	@GetMapping
	public String cat() {

		return prefix + "/cat";
	}

	@GetMapping("/list")
	@ResponseBody
	public List<CmsCat> list(CmsCat CmsCat) {

		return catService.listCmsCat(CmsCat);
	}

	/**
	 * @param catId
	 * @param mmap
	 * @return
	 */
	@GetMapping("add/{parentId}")
	public String addView(@PathVariable("parentId") Long parentId, ModelMap mmap) {
		String parentName = "根类目";
        
		if (0L != parentId) {
			parentName = catService.getCatNameById(parentId);
		}
		else {
			parentId=0L;
		}
		mmap.put("parentId", parentId);
		mmap.put("parentName", parentName);

		return prefix + "/add";
	}
    /**
     * 添加分类
     * 一.验证父节点是否为目录，验证分类名
     * 二.验证分类路径
     * 三.插入
     *
     * @param cc
     * @return
     */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(CmsCat cc) {
		cc.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(catService.insertCmsCat(cc));
	}
	
	
	/**
	 * @param catId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/edit/{catId}")
	public String editView(@PathVariable("catId") Long catId, ModelMap mmap) {
		CmsCat cmsCat = catService.getCmsCatById(catId);
		String parentName = null;
		if (cmsCat.getParentId() == 0L) {
			parentName = "根栏目";
		} else {
			parentName = catService.getCatNameById(cmsCat.getParentId());
		}
		mmap.put("cmsCat", cmsCat);
		mmap.put("parentName", parentName);

		return prefix + "/edit";
	}

	@GetMapping("/detail/{catId}")
	public String detail(@PathVariable("catId") Long catId, ModelMap mmp) {
		mmp.put("cat", catService.getCmsCatById(catId));
		return prefix + "/detail";
	}

	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editView(CmsCat cm) {
		cm.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(catService.updateCmsCat(cm));
	}
    
	/**
	 * 从分类表里删除
	 * @param catId
	 * @return
	 */
	@GetMapping("/remove/{catId}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("catId") Long catId) {
		int code = 0;
		code = catService.deleteCmsCatById(catId);
		if (code == -1) {
			return error(Type.WARN, "存在子类目,不允许删除");
		} else {
			return toAjax(1);
		}

	}
	/**
	 * 移除文章与类目关联
	 * @param cms
	 * @return
	 */
	@PostMapping("/removeCatArticle")
	@ResponseBody
	public AjaxResult removeArticleCat(long articleId,int catId) {
		catService.removeArticleCat(articleId, catId);
		return success();
	}
	
	/**
	 * 添加文章与类目关联
	 * @param cms
	 * @return
	 */
	@PostMapping("/addCatArticle")
	@ResponseBody
	public AjaxResult addArticleCat(long articleId,int catId) {
		catService.insertArticleCat(articleId, catId);
		return success();
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
		Byte b=1;
		List<Ztree> ztrees = catService.treeCatData(b);
		return ztrees;
	}
	


	/**
	 * 校验菜单名称
	 */
	@PostMapping("/checkCatNameUnique")
	@ResponseBody
	public String checkCatNameUnique(CmsCat cms) {
		return catService.checkCatNameUnique(cms);
	}
	
	

}
