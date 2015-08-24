package org.quesle.tool.fileupload;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.quesle.tool.fileupload.entity.UploadFile;
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
		// 为获取需要导入的文件，把 HttpServletRequest 转型为 MultipartHttpRequest
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		logger.debug("获取到上传图片的请求");
		UploadFile file = null;
		try {
			file = fileUploadService.saveFile(multipartRequest, "image");
		} catch (FileUploadException e) {
			logger.error("文件上传失败：" + e.getMessage());
		} catch (IOException e) {
			logger.error("保存文件失败：" + e.getMessage());
		}
		
		return file;
	}
}
