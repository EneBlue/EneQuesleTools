package org.quesle.tool.fileupload.service;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.quesle.tool.fileupload.entity.UploadFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public interface FileUploadService {
	
	/**
	 * 上传文件的保存
	 * @param request
	 * @param name
	 * @throws FileUploadException
	 */
	public UploadFile saveFile(MultipartHttpServletRequest request, String name) throws FileUploadException, IOException ;
	
}
