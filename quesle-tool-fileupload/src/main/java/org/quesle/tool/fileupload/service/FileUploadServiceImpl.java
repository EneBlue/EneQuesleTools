package org.quesle.tool.fileupload.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.commons.fileupload.FileUploadException;
import org.quesle.tool.fileupload.entity.UploadFile;
import org.quesle.tool.fileupload.utils.TimeUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUploadServiceImpl implements FileUploadService {
	
	public static final int FILE_MAX_SIZE = 10 * 1024 * 1024;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/** 项目上传的文件的路径 */
	private String uploadPath;
	
	

	@Override
	public UploadFile saveFile(MultipartHttpServletRequest request, String name) throws FileUploadException, IOException {
		
		logger.debug("文件上传名称为input name ：" + name);
		
		// 获得 request 中上传的 excel 文件
		MultipartFile localUrl = request.getFile(name);
		
		if (localUrl == null) {
			logger.debug("文件上传：保存的文件出错");
			throw new FileUploadException("文件上传：保存的文件出错");
		}

		if (localUrl.isEmpty()) {
			logger.debug("文件上传：保存的文件是空的");
			throw new FileUploadException("文件上传：保存的文件是空的");
		}

		if (localUrl.getSize() > FILE_MAX_SIZE) {
			logger.debug("文件上传：上传的文件超出最大限度");
			throw new FileUploadException("文件上传：上传的文件超出最大限度");
		}
		
		//将文件上传到本地
		return this.saveFileToPath(localUrl);
	}

	private UploadFile saveFileToPath(MultipartFile localUrl) throws IOException {
		
		UploadFile upladFile = new UploadFile();
		
		//获取上传文件的名称
		String originalFilename = localUrl.getOriginalFilename();
		upladFile.setFileInitName(originalFilename);
		
		logger.debug("文件的名称：" + localUrl.getName());
		
		//获取上传文件的文件扩展名
		String postfix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		
		//获取上传文件的名称
		String prefix = originalFilename.substring(0, originalFilename.lastIndexOf(".") );

		//生成保存的文件名， 如： 科贸办公网络及打印机设置指南_20150818110756.pdf
		String name = prefix + "_" + TimeUtils.getCurrentDateTime("yyyyMMddHHmmss") + "." + postfix;
		upladFile.setFileFinalName(name);
		
		
		//保存到相应的目录中
		logger.debug("文件保存的文件夹为 :  " + this.uploadPath);
		File diskFile = new File(this.uploadPath, name);
		
		upladFile.setFilePath(diskFile.getPath());
		
		OutputStream fos = new FileOutputStream(diskFile);
		
		//将上传的文件流保存到数据库中
		IOUtils.copy(localUrl.getInputStream(), fos);
		
		//关闭流
		IOUtils.closeQuietly(fos);
		return upladFile;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

}
