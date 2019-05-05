package com.ruoyi.file.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.file.config.FileConfig;
import com.ruoyi.file.model.FilePo;
import com.ruoyi.file.util.CheckFileFormatUtil;
import com.ruoyi.file.util.FileUtils;

/**
 * 文件按照类型存储
 * 
 * 图片，(图片有缩略图)，文档，压缩文件，音频
 * 
 * @author bobey
 *
 */
@Service
public class FileService {

	private FileConfig fileConfig;

	/**
	 * 初始化路径
	 * 
	 * @param fileConfig
	 */
	@Autowired
	public FileService(FileConfig fileConfig) {
		String basicDir = fileConfig.getBasicDir();
		String thumbnailsDir = fileConfig.getThumbnailsDir();
		if (basicDir == null) {
			basicDir = "/";
		}
		if (!basicDir.endsWith("/")) {
			basicDir += "/";
		}
		if (thumbnailsDir == null) {
			thumbnailsDir = "";
		}
		if (!thumbnailsDir.endsWith("/")) {
			thumbnailsDir += "/";
		}
		if (fileConfig.isUseLocation()) {
			basicDir = System.getProperties().getProperty("user.dir").replaceAll("\\\\", "/") + basicDir;
		}
		fileConfig.setBasicDir(basicDir);
		fileConfig.setThumbnailsDir(thumbnailsDir);
		this.fileConfig = fileConfig;
	}

	/**
	 * 上传文件
	 * 
	 * @param originalFileName
	 * @param file
	 * @param uuId
	 * @param useSm
	 * @return
	 * @throws IOException
	 */
	public FilePo uploadFile(String originalFileName, MultipartFile file) throws IOException {
		FilePo fpo = null;
		String suf = FileUtils.getSuffix(originalFileName);
		String folder = CheckFileFormatUtil.folder.get(suf);
		// 获取文件类型
		if (folder != null) {
			if ("img".equals(folder)) {
				fpo = FileUtils.uploadImg(fileConfig.getBasicDir(),folder, originalFileName, file,
						fileConfig.isUseuuId(), fileConfig.isUseSm(), fileConfig.getThumbnailsDir(),fileConfig.isUseTimeDir());
			} else {
				fpo = FileUtils.upload(fileConfig.getBasicDir(),folder, originalFileName, file,fileConfig.isUseuuIdo(),fileConfig.isUseTimeDiro());
			}

		}
		return fpo;
	}

	public void downloadFile(String url,HttpServletResponse response) {
		
		FileUtils.outputFile(fileConfig.getBasicDir()+url, response);
		
		
	}
	public	int delFile(String url) {
		
		return FileUtils.delFile(fileConfig.getBasicDir()+url);
	}
	
	void listFile() {
		
		
		
	}

	void logoFile() {
	}

}
