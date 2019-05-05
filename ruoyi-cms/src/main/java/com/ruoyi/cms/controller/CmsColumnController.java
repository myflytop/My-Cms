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
import com.ruoyi.cms.model.po.CmsColumn;
import com.ruoyi.cms.service.impl.CmsColunmServiceImpl;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.AjaxResult.Type;
import com.ruoyi.framework.util.ShiroUtils;

/**
 * 页面 控制层
 * 
 * @author bobey
 * 
 *         需要权限 只能控制授权栏目
 */
@Controller
@RequestMapping("cms/column")
public class CmsColumnController extends BaseController {
	@Autowired
	private CmsColunmServiceImpl columnService;
	private String prefix = "cms/column";

	@GetMapping
	public String column() {

		return prefix + "/column";
	}

	@GetMapping("/list")
	@ResponseBody
	public List<CmsColumn> list(CmsColumn cmsColumn) {

		return columnService.listCmsColumn(cmsColumn);
	}

	/**
	 * @param columnId
	 * @param mmap
	 * @return
	 */
	@GetMapping("add/{parentId}")
	public String addView(@PathVariable("parentId") Long parentId, ModelMap mmap) {
		String parentName = "根栏目";

		if (0L != parentId) {
			parentName = columnService.getCmsColumnNameById(parentId);
		}
		else {
			parentId=0L;
		}
		mmap.put("parentId", parentId);
		mmap.put("parentName", parentName);

		return prefix + "/add";
	}

	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(CmsColumn cc) {
		cc.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(columnService.insertCmsColumn(cc));
	}

	/**
	 * @param columnId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/edit/{columnId}")
	public String editView(@PathVariable("columnId") Long columnId, ModelMap mmap) {
		CmsColumn cmsColumn = columnService.getCmsColumnById(columnId);
		String parentName = null;
		if (cmsColumn.getParentId() == 0L) {
			parentName = "根栏目";
		} else {
			parentName = columnService.getCmsColumnNameById(cmsColumn.getParentId());
		}
		mmap.put("cmsColumn", cmsColumn);
		mmap.put("parentName", parentName);

		return prefix + "/edit";
	}

	@GetMapping("/detail/{columnId}")
	public String detail(@PathVariable("columnId") Long columnId, ModelMap mmp) {
		mmp.put("column", columnService.getCmsColumnById(columnId));
		return prefix + "/detail";
	}

	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editView(CmsColumn cm) {
		cm.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(columnService.updateCmsColumn(cm));
	}

	@GetMapping("/remove/{columnId}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("columnId") Long columnId) {
		int code = 0;
		code = columnService.deleteCmsColumnById(columnId);
		if (code == -1) {
			return error(Type.WARN, "存在子菜单,不允许删除");
		} else {
			return toAjax(1);
		}

	}

	@GetMapping("/selectColumnTree/{columnId}")
	public String selectColumnTree(@PathVariable("columnId") Long columnId, ModelMap mmp) {
		CmsColumn cmsColumn = columnService.getCmsColumnById(columnId);
		
		mmp.put("column", cmsColumn);
		return prefix + "/tree";
	}

	/**
	 * 获取目录
	 * 
	 * @return
	 */
	@GetMapping("/columnTreeData")
	@ResponseBody
	public List<Ztree> columnTreeCatData() {
		List<Ztree> ztrees = columnService.columnTreeCatData();
		return ztrees;
	}

	/**
	 * 校验菜单名称
	 */
	@PostMapping("/checkColumnNameUnique")
	@ResponseBody
	public String checkcolumnNameUnique(CmsColumn cms) {
		return columnService.checkcolumnNameUnique(cms);
	}

}
