package com.ruoyi.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cms.mapper.CmsCatMapper;
import com.ruoyi.cms.model.po.CmsCat;
import com.ruoyi.cms.service.ICmsCatService;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;

@Service
public class CmsCatServiceImpl implements ICmsCatService {

	@Autowired
	private CmsCatMapper catMapper;
    
	
	
	@Override
	public int insertCmsCat(CmsCat cmsCat) {
		// TODO Auto-generated method stub
		return catMapper.insertCmsCat(cmsCat);
	}

	@Override
	public int updateCmsCat(CmsCat cmsCat) {
		// TODO Auto-generated method stub
		return catMapper.updateCmsCat(cmsCat);
	}
	@Override
	public int deleteCmsCatById(Long catId) {
		// TODO Auto-generated method stub
		//验证是否存在子类目
		int count=catMapper.countCatById(catId);
		    if(count==0)
		    {		    	
		    	return catMapper.deleteCmsCatById(catId);
		    }
		return -1;
	}
	@Override
	public List<CmsCat> listCmsCat(CmsCat cmsCat) {
		// TODO Auto-generated method stub
		return catMapper.listCmsCat(cmsCat);
	}
	
	@Override
	public List<CmsCat> listCmsCatById(Long parentId) {
		// TODO Auto-generated method stub
		return catMapper.listCmsCatById(parentId);
	}

	@Override
	public CmsCat getCmsCatById(Long catId) {
		// TODO Auto-generated method stub
		return catMapper.getCmsCatById(catId);
	}

	@Override
	public List<Ztree> treeCatData(Byte b) {
		// TODO Auto-generated method stub
		CmsCat cmsCat=new CmsCat();
		cmsCat.setParent((b));
		List<CmsCat> catList = catMapper.listCmsCat(cmsCat);
		List<Ztree> ztrees = initZtree(catList);
		return ztrees;
	}

	/**
	 * 对象转菜单树
	 * 
	 * @param catList 菜单列表
	 * @return 树结构列表
	 */
	public List<Ztree> initZtree(List<CmsCat> catList) {
		List<Ztree> ztrees = new ArrayList<Ztree>();
		for (CmsCat cat : catList) {
			Ztree ztree = new Ztree();
			ztree.setId(cat.getCatId());
			ztree.setpId(cat.getParentId());
			ztree.setName(cat.getCatName());
			ztree.setTitle(cat.getCatName());

			ztrees.add(ztree);
		}
		return ztrees;
	}

	@Override
	public String checkCatNameUnique(CmsCat cmsCat) {
		// TODO Auto-generated method stub
		if (catMapper.checkCatNameUnique(cmsCat.getParentId(),cmsCat.getCatName(),cmsCat.getCatId()) == 0) {
			return UserConstants.MENU_NAME_UNIQUE;
		}
		return UserConstants.MENU_NAME_NOT_UNIQUE;
	}
    
	
	

	@Override
	public String getCatNameById(Long catId) {
		// TODO Auto-generated method stub
		return catMapper.getCatNameById(catId);
	}
	@Override
	public int removeArticleCat(long articleId, int catId) {
		// TODO Auto-generated method stub
		
		return catMapper.removeArticleCat(articleId, catId);
	}

	@Override
	public int insertArticleCat(long articleId, int catId) {
		// TODO Auto-generated method stub
		return catMapper.insertArticleCat(articleId, catId);
	}

	

}
