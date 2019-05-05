package com.ruoyi.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.cms.mapper.CmsColumnMapper;
import com.ruoyi.cms.model.po.CmsColumn;
import com.ruoyi.cms.service.ICmsColumnService;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.StringUtils;

@Service
public class CmsColunmServiceImpl implements ICmsColumnService {
	@Autowired
	private CmsColumnMapper columnMapper;

	@Override
	public int insertCmsColumn(CmsColumn cmsColumn) {
		// TODO Auto-generated method stub
		return columnMapper.insertCmsColumn(cmsColumn);
	}

	@Override
	public int updateCmsColumn(CmsColumn cmsColumn) {
		// TODO Auto-generated method stub
		return columnMapper.updateCmsColumn(cmsColumn);
	}

	@Override
	public int deleteCmsColumn(Long columnId) {
		int count=columnMapper.countCmsColumnById(columnId);
		// TODO Auto-generated method stub
		if(count==0)
		{
			return columnMapper.deleteCmsColumnById(columnId);
		}
		return -1;
	}

	@Override
	public List<CmsColumn> listCmsColumnById(Long parentId) {
		// TODO Auto-generated method stub
		return columnMapper.listCmsColumnById(parentId);
	}

	@Override
	public List<CmsColumn> listCmsColumn(CmsColumn cmsColumn) {
		// TODO Auto-generated method stub
		return columnMapper.listCmsColumn(cmsColumn);
	}

	@Override
	public List<Ztree> columnTreeCatData() {
		// TODO Auto-generated method stub
		CmsColumn cc=new CmsColumn();
		cc.setParent((byte) 1);
		List<CmsColumn> colunmList = columnMapper.listCmsColumn(cc);
		List<Ztree> ztrees = initZtree(colunmList);
		return ztrees;
	}

	private List<Ztree> initZtree(List<CmsColumn> colunmList) {
		// TODO Auto-generated method stub
		return initZtree(colunmList, null, false);
	}

	/**
	 * 对象转菜单树
	 * 
	 * @param colunmList     菜单列表
	 * @param rolecolunmList 角色已存在菜单列表
	 * @param permsFlag      是否需要显示权限标识
	 * @return 树结构列表
	 */
	public List<Ztree> initZtree(List<CmsColumn> colunmList, List<String> rolecolunmList, boolean permsFlag) {
		List<Ztree> ztrees = new ArrayList<Ztree>();
		boolean isCheck = StringUtils.isNotNull(rolecolunmList);
		for (CmsColumn colunm : colunmList) {
			Ztree ztree = new Ztree();
			ztree.setId(colunm.getColumnId());
			ztree.setpId(colunm.getParentId());
			ztree.setName(transcolunmName(colunm, rolecolunmList, permsFlag));
			ztree.setTitle(colunm.getColumnName());
			if (isCheck) {
				ztree.setChecked(rolecolunmList.contains(colunm.getColumnId() + colunm.getPerms()));
			}
			ztrees.add(ztree);
		}
		return ztrees;
	}

	public String transcolunmName(CmsColumn colunm, List<String> rolecolunmList, boolean permsFlag) {
		StringBuffer sb = new StringBuffer();
		sb.append(colunm.getColumnName());
		if (permsFlag) {
			sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + colunm.getPerms() + "</font>");
		}
		return sb.toString();
	}

	@Override
	public String checkcolumnNameUnique(CmsColumn cms) {
		// TODO Auto-generated method stub
		if (columnMapper.checkcolumnNameUnique(cms.getParentId(), cms.getColumnName(),cms.getColumnId()) == 0) {
			return UserConstants.MENU_NAME_UNIQUE;
		}
		return UserConstants.MENU_NAME_NOT_UNIQUE;
	}

	@Override
	public CmsColumn getCmsColumnById(Long columnId) {
		// TODO Auto-generated method stub
		return columnMapper.getCmsColumnById(columnId);
	}

	@Override
	public String getCmsColumnNameById(Long columnId) {
		// TODO Auto-generated method stub
		return columnMapper.getCmsColumnNameById(columnId);
	}
   
	/**
    * 0表示成功 1表示还有子菜单
    */
	@Override
	public int deleteCmsColumnById(Long columnId) {
		// TODO Auto-generated method stub
		//验证是否存在子类目
	int count=columnMapper.countCmsColumnById(columnId);
	    if(count==0)
	    {
	    	
	    	return columnMapper.deleteCmsColumnById(columnId);
	    }
		return -1;
	}

}
