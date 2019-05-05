package com.ruoyi.file.config;
/**
 * 文件上传路径
 * uuid文件名开关
 * 图片缩略图开关
 * 上传类型
 * 上传最大
 * 图片严重
 * @author bobey
 */

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ruoyi.file.enums.FileEnum;
@Component
@ConfigurationProperties
public class FileConfig {
	@Value("${fs.basicDir}")
	private String basicDir;
	@Value("${fs.thumbnailsDir}")
	private String thumbnailsDir;
	@Value("${fs.useuuId}")
	private boolean useuuId;
	@Value("${fs.useuuIdo}")
	private boolean useuuIdo;
	@Value("${fs.useTimeDir}")
	private boolean useTimeDir;
	public boolean isUseuuIdo() {
		return useuuIdo;
	}

	public void setUseuuIdo(boolean useuuIdo) {
		this.useuuIdo = useuuIdo;
	}

	public boolean isUseTimeDir() {
		return useTimeDir;
	}

	public void setUseTimeDir(boolean useTimeDir) {
		this.useTimeDir = useTimeDir;
	}

	public boolean isUseTimeDiro() {
		return useTimeDiro;
	}

	public void setUseTimeDiro(boolean useTimeDiro) {
		this.useTimeDiro = useTimeDiro;
	}

	@Value("${fs.useTimeDiro}")
	private boolean useTimeDiro;
	@Value("${fs.useSm}")
	private boolean useSm;
	//自定义保存路径
	@Value("${fs.useLocation}")
	private boolean useLocation;
	/*@Value("${fs.allowType}")
	private Map<String,String> allowType= new HashMap<String, String>();*/
	
	public String getBasicDir() {
		return basicDir;
	}

	public void setBasicDir(String basicDir) {
		this.basicDir = basicDir;
	}

	public String getThumbnailsDir() {
		return thumbnailsDir;
	}

	public void setThumbnailsDir(String thumbnailsDir) {
		this.thumbnailsDir = thumbnailsDir;
	}

	public boolean isUseuuId() {
		return useuuId;
	}

	public void setUseuuId(boolean useuuId) {
		this.useuuId = useuuId;
	}

	public boolean isUseSm() {
		return useSm;
	}

	public void setUseSm(boolean useSm) {
		this.useSm = useSm;
	}

	/*public Map<String, String> getAllowType() {
		return allowType;
	}

	public void setAllowType(Map<String, String> allowType) {
		this.allowType = allowType;
	}
*/
	public FileEnum getFileDir() {
		return fileDir;
	}

	public void setFileDir(FileEnum fileDir) {
		this.fileDir = fileDir;
	}

	public boolean isUseLocation() {
		return useLocation;
	}

	public void setUseLocation(boolean useLocation) {
		this.useLocation = useLocation;
	}

	private FileEnum fileDir;
	
	

}
