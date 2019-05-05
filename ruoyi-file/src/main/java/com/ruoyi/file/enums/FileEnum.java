package com.ruoyi.file.enums;

public enum FileEnum {
	IMG("0", "img"), DOC("1", "doc"), ZIP("2", "zip"),VIDEO("3", "video"),AUDIO("4", "audio"),IMGSM("5", "imgSm");
	private final String code;
	private final String fileName;
	private FileEnum(String code, String fileName) {
		this.code = code;
		this.fileName = fileName;
	}

	public String getCode() {
		return code;
	}

	public String getFileName() {
		return fileName;
	}

}
