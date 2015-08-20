package org.quesle.tool.fileupload;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.quesle.tool.fileupload.service.FileUploadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@RestController
public class FileUpladController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private FileUploadService fileUploadService;
	
	@RequestMapping("/upload")
	public Object upload(HttpServletRequest request){
		System.out.println("==============");
		// 为获取需要导入的文件，把 HttpServletRequest 转型为 MultipartHttpRequest
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		
		try {
			logger.error("文件上传失败=============");
			fileUploadService.saveFile(multipartRequest, "localUrl");
		} catch (FileUploadException e) {
			logger.error("文件上传失败：" + e.getMessage());
		} catch (IOException e) {
			logger.error("保存文件失败：" + e.getMessage());
		}
		
		return "index";
	}
}
