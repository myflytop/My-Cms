package com.ruoyi.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruoyi.cms.util.CmsContest;

@Controller
@RequestMapping("/test")
public class WebController {
	
	 public static String THEME =CmsContest.CMSTHEME;
	 @GetMapping("/index")
	 public String index() {
		 
		 return "themes/"+THEME+"/index";
	 }
	
	

}
