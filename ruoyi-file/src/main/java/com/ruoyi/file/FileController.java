package com.ruoyi.file;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.file.model.FilePo;
import com.ruoyi.file.service.FileService;
import com.ruoyi.file.util.CheckFileFormatUtil;
import com.ruoyi.file.util.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件服务器 Created by wangfan on 2018-12-24 下午 4:10.
 */
@CrossOrigin
@Controller
public class FileController {
	@Value("${fs.dir}")
	private String fileDir;
	@Value("${fs.uuidName}")
	private Boolean uuidName;
	@Value("${fs.useSm}")
	private Boolean useSm;
	@Value("${fs.useNginx}")
	private Boolean useNginx;
	@Value("${fs.nginxUrl}")
	private String nginxUrl;
	@Autowired
	private FileService fs;

	// 首页
	@RequestMapping({ "/", "/index" })
	public String index() {
		/*
		 * System.out.println(Paths.get("abc") .toAbsolutePath().normalize());
		 */
		return "index.html";
	}

	@ResponseBody
	@PostMapping("/file/upload")
	public Map upload(@RequestParam MultipartFile file) throws IOException {
		FilePo po = fs.uploadFile(file.getOriginalFilename(), file);
		Map rs = getRS(200, "上传成功", po.getUrl());
		rs.put("smUrl", po.getUrlSm());
		return rs;

	}

	/**
	 * 查看原文件
	 * 
	 * @throws IOException
	 */
	@GetMapping("/file/**")
	public String fileDownload(HttpServletResponse response, HttpServletRequest request) throws IOException {
		StringBuffer url = request.getRequestURL();
		url.replace(0, url.indexOf("/file/") + 6, "");
		String suff = FileUtils.getSuffix(url.toString());
		String bold = CheckFileFormatUtil.folder.get(suff);
		if (bold != null && url.toString().startsWith(bold)) {
			fs.downloadFile(java.net.URLDecoder.decode(url.toString(), "UTF-8"), response);
		} else {
			PrintWriter writer = null;
			response.setContentType("text/html;charset=UTF-8");
			writer = response.getWriter();
			writer.write(
					"<!doctype html><title>4r4 Not Found</title><h1 style=\"text-align: center\">404 Not Found</h1><hr/><p style=\"text-align: center\">Easy File Server</p>");
			writer.flush();
		}
		return null;
	}

	/**
	 * 获取全部文件
	 */
	@ResponseBody
	@RequestMapping("/api/list")
	public Map lists(String dir, String accept, String exts) {
		
		return null;
	}
	/**
	 * 获取全部文件
	 */
	@ResponseBody
	@RequestMapping("/api/list")
	public Map list(String dir, String accept, String exts) {
		String[] mExts = null;
		if (exts != null && !exts.trim().isEmpty()) {
			mExts = exts.split(",");
		}
		if (fileDir == null) {
			fileDir = "/";
		}
		if (!fileDir.endsWith("/")) {
			fileDir += "/";
		}
		Map<String, Object> rs = new HashMap<>();
		if (dir == null || "/".equals(dir)) {
			dir = "";
		} else if (dir.startsWith("/")) {
			dir = dir.substring(1);
		}
		File file = new File(fileDir + dir);
		File[] listFiles = file.listFiles();
		List<Map> dataList = new ArrayList<>();
		if (listFiles != null) {
			for (File f : listFiles) {
				if ("sm".equals(f.getName())) {
					continue;
				}
				Map<String, Object> m = new HashMap<>();
				m.put("name", f.getName()); // 文件名称
				m.put("updateTime", f.lastModified()); // 修改时间
				m.put("isDir", f.isDirectory()); // 是否是目录
				if (f.isDirectory()) {
					m.put("type", "dir"); // 文件类型
				} else {
					String type;
					m.put("url", (dir.isEmpty() ? dir : (dir + "/")) + f.getName()); // 文件地址
					// 获取文件类型
					String contentType = null;
					String suffix = f.getName().substring(f.getName().lastIndexOf(".") + 1);
					try {
						// Path path = Paths.get(f.getName());
						// contentType = Files.probeContentType(path);
						contentType = new Tika().detect(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
					// 筛选文件类型
					if (accept != null && !accept.trim().isEmpty() && !accept.equals("file")) {
						if (contentType == null || !contentType.startsWith(accept + "/")) {
							continue;
						}
						if (mExts != null) {
							for (String ext : mExts) {
								if (!f.getName().endsWith("." + ext)) {
									continue;
								}
							}
						}
					}
					// 获取文件图标
					if ("ppt".equalsIgnoreCase(suffix) || "pptx".equalsIgnoreCase(suffix)) {
						type = "ppt";
					} else if ("doc".equalsIgnoreCase(suffix) || "docx".equalsIgnoreCase(suffix)) {
						type = "doc";
					} else if ("xls".equalsIgnoreCase(suffix) || "xlsx".equalsIgnoreCase(suffix)) {
						type = "xls";
					} else if ("pdf".equalsIgnoreCase(suffix)) {
						type = "pdf";
					} else if ("html".equalsIgnoreCase(suffix) || "htm".equalsIgnoreCase(suffix)) {
						type = "htm";
					} else if ("txt".equalsIgnoreCase(suffix)) {
						type = "txt";
					} else if ("swf".equalsIgnoreCase(suffix)) {
						type = "flash";
					} else if ("zip".equalsIgnoreCase(suffix) || "rar".equalsIgnoreCase(suffix)
							|| "7z".equalsIgnoreCase(suffix)) {
						type = "zip";
					} else if ("zip".equalsIgnoreCase(suffix) || "rar".equalsIgnoreCase(suffix)
							|| "7z".equalsIgnoreCase(suffix)) {
						type = "zip";
					} else if (contentType != null && contentType.startsWith("audio/")) {
						type = "mp3";
					} else if (contentType != null && contentType.startsWith("video/")) {
						type = "mp4";
					} /*
						 * else if (contentType != null && contentType.startsWith("image/")) { type =
						 * "file"; m.put("hasSm", true); m.put("smUrl", m.get("url")); // 缩略图地址 }
						 */ else {
						type = "file";
					}
					m.put("type", type);
					// 是否有缩略图
					String smUrl = "sm/" + (dir.isEmpty() ? dir : (dir + "/")) + f.getName();
					if (new File(File.listRoots()[0], fileDir + smUrl).exists()) {
						m.put("hasSm", true);
						m.put("smUrl", smUrl); // 缩略图地址
					}
				}
				dataList.add(m);
			}
		}
		// 根据上传时间排序
		Collections.sort(dataList, new Comparator<Map>() {
			@Override
			public int compare(Map o1, Map o2) {
				Long l1 = (long) o1.get("updateTime");
				Long l2 = (long) o2.get("updateTime");
				return l1.compareTo(l2);
			}
		});
		// 把文件夹排在前面
		Collections.sort(dataList, new Comparator<Map>() {
			@Override
			public int compare(Map o1, Map o2) {
				Boolean l1 = (boolean) o1.get("isDir");
				Boolean l2 = (boolean) o2.get("isDir");
				return l2.compareTo(l1);
			}
		});
		rs.put("code", 200);
		rs.put("msg", "查询成功");
		rs.put("data", dataList);
		return rs;
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/api/del")
	public Map del(String file) {
		if (fs.delFile(file) == 1) {
			return getRS(200, "删除成功");
		}
		return getRS(500, "删除失败");
	}

	// 获取当前日期
	private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		return sdf.format(new Date());
	}

	// 封装返回结果
	private Map getRS(int code, String msg, String url) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("msg", msg);
		if (url != null) {
			map.put("url", url);
		}
		return map;
	}

	// 封装返回结果
	private Map getRS(int code, String msg) {
		return getRS(code, msg, null);
	}
}
