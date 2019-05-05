package com.ruoyi.file.model;

public class FilePo  {
	private Long id;
	// 基本路径
	private String url;
	// 缩略图路径
	private String urlSm;
	// 文件类型
	private Byte fileType;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlSm() {
		return urlSm;
	}

	public void setUrlSm(String urlSm) {
		this.urlSm = urlSm;
	}

	public Byte getFileType() {
		return fileType;
	}

	public void setFileType(Byte fileType) {
		this.fileType = fileType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
