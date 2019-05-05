package com.ruoyi.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.file.model.FilePo;

import net.coobird.thumbnailator.Thumbnails;

public class FileUtils {

	/**
	 * 构建基本路径 包含时间
	 * 
	 * @param folder
	 * @param originalFileName
	 * @param uuId
	 * @return
	 */
	public static String getFileDir(String folder, String originalFileName, boolean uuId, boolean time) {
		String path = null;
		String ti = "";
		if (time) {
			ti = getDate();
		}
		if (uuId) {

			path = ti + UUID.randomUUID().toString().replaceAll("-", "") + "." + getSuffix(originalFileName);
		} else {

			path = ti + originalFileName;
		}
		return folder + path;
	}

	public static String getHurl() {

		return null;
	}

	/**
	 * 通过全文件名获取后缀
	 * 
	 * @param originalFileName
	 * @return
	 */
	public static String getSuffix(String originalFileName) {
		String suffName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		return suffName;
	}

	/**
	 * 前缀
	 * 
	 * @param originalFileName
	 * @return
	 * 
	 */
	public static String prefixName(String oname) {
		String prefixName = oname.substring(0, oname.lastIndexOf("."));
		return prefixName;
	}

	public static String rePath(String path, int index) {

		StringBuilder sb = new StringBuilder(path);
		if (sb.lastIndexOf(").") > 0) {
			sb.delete(sb.lastIndexOf("("), sb.lastIndexOf(")") + 1);
		}
		int last = sb.lastIndexOf(".");
		sb.replace(last, last + 1, "(" + index + ").");
		return sb.toString();
	}

	/**
	 * 判断后缀名与文件头后缀 是否一致
	 * 
	 * @param originalFileName
	 * @param inputStream
	 * @return
	 */
	public static boolean isFile(String originalFileName, InputStream inputStream) {
		String suffName = getSuffix(originalFileName);
		String heardType = CheckFileFormatUtil.getFileType(inputStream);
		// 后者主要用于判断 当头编码重复时选择
		return heardType.equals(suffName) || ("zipdocxlsxdocxlsx".indexOf(heardType) != -1);
	}

	/**
	 * 获取当前时间年月日
	 * 
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		return sdf.format(new Date());
	}

	/**
	 * 上传文件
	 * 
	 * @param originalFileName
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static FilePo uploadImg(String fileDir, String folder, String originalFileName, MultipartFile file,
			Boolean uuId, Boolean useSm, String thumbnailsDir, boolean useTimeDir) throws IOException {
		InputStream inputStream = file.getInputStream();
		FilePo fpo = new FilePo();
		// 判断是否可以上传
		if (isFile(originalFileName, inputStream)) {
			String path = null;
			File outFile;
			path = getFileDir(folder + "/", originalFileName, uuId, useTimeDir);
			// System.out.println(path);

			// 选择第一个盘符outFile = new File(File.listRoots()[0], path);
			outFile = new File(fileDir + path);
			// 主要用于判断原文件重名
			if (outFile.exists()) {
				int index = 0;
				while (outFile.exists()) {
					path = rePath(path, index);
					outFile = new File(fileDir + path);
					index++;
				}
			}
			try {
				if (!outFile.getParentFile().exists()) {
					outFile.getParentFile().mkdirs();
				}
				file.transferTo(outFile);
				fpo.setUrl(path);
				if (useSm) {
					int last = path.lastIndexOf("/");
					StringBuilder sb = new StringBuilder(path);
					String smPath = sb.replace(last, last + 1, thumbnailsDir).toString();
					fpo.setUrlSm(uploadThumbnail(fileDir, smPath, outFile));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fpo;

	}

	public static FilePo upload(String fileDir, String folder, String originalFileName, MultipartFile file,
			boolean uuId, boolean useTimeDir) throws IOException {

		InputStream inputStream = file.getInputStream();
		FilePo fpo = new FilePo();
		// 判断是否可以上传
		if (isFile(originalFileName, inputStream)) {
			String path = null;
			File outFile;
			path = getFileDir(folder + "/", originalFileName, uuId, useTimeDir);
			outFile = new File(fileDir + path);
			// 主要用于判断原文件重名
			if (outFile.exists()) {
				int index = 0;
				while (outFile.exists()) {
					path = rePath(path, index);
					outFile = new File(fileDir + path);
					index++;
				}
			}
			try {
				if (!outFile.getParentFile().exists()) {
					outFile.getParentFile().mkdirs();
				}
				file.transferTo(outFile);
				fpo.setUrl(path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fpo;
	}

	/**
	 * 上传缩略图
	 * 
	 * @param fileDir
	 * @param path
	 * @param outFile
	 * @param useSm
	 */
	public static String uploadThumbnail(String fileDir, String smPath, File outFile) {
		// 获取文件类型
		String contentType = null;
		try {
			// contentType = Files.probeContentType(Paths.get(outFile.getName()));
			contentType = new Tika().detect(outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (contentType != null && contentType.startsWith("image/")) {
			File smImg = new File(fileDir + smPath);
			if (!smImg.getParentFile().exists()) {
				smImg.getParentFile().mkdirs();
			}
			try {
				Thumbnails.of(outFile).scale(1f).outputQuality(0.25f).toFile(fileDir + smImg);
				return smPath;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	// 输出文件流
	public static void outputFile(String file, HttpServletResponse response) {
		// 判断文件是否存在
		File inFile = new File(file);
		if (!inFile.exists()) {
			PrintWriter writer = null;
			try {
				response.setContentType("text/html;charset=UTF-8");
				writer = response.getWriter();
				writer.write(
						"<!doctype html><title>404 Not Found</title><h1 style=\"text-align: center\">404 Not Found</h1><hr/><p style=\"text-align: center\">Easy File Server</p>");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		// 获取文件类型
		String contentType = null;
		try {
			// Path path = Paths.get(inFile.getName());
			// contentType = Files.probeContentType(path);
			contentType = new Tika().detect(inFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (contentType != null) {
			response.setContentType(contentType);
		} else {
			response.setContentType("application/force-download");
			String newName;
			try {
				newName = URLEncoder.encode(inFile.getName(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				newName = inFile.getName();
			}
			response.setHeader("Content-Disposition", "attachment;fileName=" + newName);
		}
		// 输出文件流
		OutputStream os = null;
		FileInputStream is = null;
		try {
			is = new FileInputStream(inFile);
			os = response.getOutputStream();
			byte[] bytes = new byte[1024];
			int len;
			while ((len = is.read(bytes)) != -1) {
				os.write(bytes, 0, len);
			}
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除 
	 * 
	 * @param file
	 * @param fileDir
	 * @return
	 */
	public static int delFile(String fileDir) {
		if (fileDir != null && !fileDir.isEmpty()) {
			File f = new File(fileDir);
			if (f.delete()) {
				return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {

		File f = new File("C:\\Users\\Administrator\\Desktop\\花照顶 - 副本.docx");

		System.out.println(isFile("花照顶 - 副本.docx", new FileInputStream(f)));
		;
		/*
		 * uploadThumbnail( "av9.png", f, true); System.out.println(new
		 * Tika().detect(f)); System.out.println(getSuffix("a.ngpng"));
		 * System.out.println(getDate());
		 * System.out.println(System.getProperties().getProperty("user.dir"));
		 */
	}

}
