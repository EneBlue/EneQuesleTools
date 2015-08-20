package org.quesle.tool.fileupload.entity;

/**
 * 创建保存文件的对象
 * @author Quesle
 *
 */
public class UploadFile {

	//获取得到的文件的名称
	private String fileInitName;
	
	//保存后的文件名称
	private String fileFinalName;
	
	//文件保存的地址
	private String filePath;
	
	public String getFileInitName() {
		return fileInitName;
	}
	public void setFileInitName(String fileInitName) {
		this.fileInitName = fileInitName;
	}
	public String getFileFinalName() {
		return fileFinalName;
	}
	public void setFileFinalName(String fileFinalName) {
		this.fileFinalName = fileFinalName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
