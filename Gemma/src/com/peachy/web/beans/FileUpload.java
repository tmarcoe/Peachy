package com.peachy.web.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	private MultipartFile file;

	private String filePath;
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}
	
	@Value("${filePath}")
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


}
