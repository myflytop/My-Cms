package com.ruoyi.cms.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.cms.model.po.CmsTheme;


public class CmsUtils {
	/**
	 * 获取主题列表
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	 public static Map<String,CmsTheme> getThemes() throws JsonParseException, JsonMappingException, IOException {	 
		 final Map<String,CmsTheme> themeMap=new HashMap<>();
		 // 获取项目根路径
         final File basePath = new File(ResourceUtils.getURL("classpath:").getPath());
         // 获取主题路径
         final File themesPath = new File(basePath.getAbsolutePath(), "templates/themes");
         final File[] files = themesPath.listFiles();
         ObjectMapper mapper = new ObjectMapper();
         if (null != files) {
        	 for (File file : files) {
        		 if(file.isDirectory()) {
        			 File jsonFile=new File(file.getAbsolutePath(), "theme.json");
        			if( jsonFile.exists()) {
        				CmsTheme theme = mapper.readValue(
        						jsonFile,
        						CmsTheme.class);
        				theme.setThemeEnabled((byte) 0);
        				theme.setThemeName(file.getName());
        				if(CmsContest.CMSTHEME.equals(file.getName()))
        				{   
        					theme.setThemeEnabled((byte) 1);
        				}
        				themeMap.put(file.getName(), theme);
        			}
        			 
        		 }
        		 
        	 }     	 
         }	 
         CmsContest.CMSTHEMES=themeMap;
		 return themeMap;
	 }
	/**
	 * 切换主题
	 * @param themeName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String changeTheme(String themeName)  {
          final String oldThemeName=CmsContest.CMSTHEME;           
          final Map<String,CmsTheme> themeMap=CmsContest.CMSTHEMES;
          if(themeMap.get(themeName)!=null&&themeMap.get(oldThemeName)!=null)
          {   	  
        	  themeMap.get(oldThemeName).setThemeEnabled((byte)0);
        	  themeMap.get(themeName).setThemeEnabled((byte)1);
        	  CmsContest.CMSTHEME=themeName;
        	  return oldThemeName;
          }	
	return	null;
	}
	/**
	 * 删除主题
	 * @param themeName
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static boolean deleteThemeFile(String themeName) throws FileNotFoundException {
		 // 获取项目根路径
		
        final File basePath = new File(ResourceUtils.getURL("classpath:").getPath());
        // 获取主题路径
        final File themesPath = new File(basePath.getAbsolutePath(), "templates/themes/"+themeName);
        
        if(themesPath.exists())
        {
        CmsContest.CMSTHEMES.remove(themeName);
        return deleteDir(themesPath);
        }
        return false;
	}
	
	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
          //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
