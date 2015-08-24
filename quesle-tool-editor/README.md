
此项目是基于UEditor和kindEditor编辑器来写的工具类；
在版本v0.0.1中实现功能如下：
	使用UEditor编辑器上传单张图片，包括后台的使用；
	其中包括对UEditor的修改如下：


对UEditor的修改:
1.修改配置文件/resources/ueditor/jsp/config.json中的配置
	- 在配置文件中添加属性basePath，作为上传文件的根目录，UEditor默认使用服务器的根目录，在很多需求中，上传文件的根目录可能在服务器的某个固定的目录，此配置为这个固定目录；
	
	- 修改com.baidu.ueditor.ConfigManager中的方法
		public Map<String, Object> getConfig ( int type ){}
		再次方法中添加如下类容：
		conf.put("basePath", this.jsonConfig.getString("basePath"));
	
	- 修改com.baidu.ueditor.upload.BinaryUploader的【71行】如下：
		String physicalPath = (String) conf.get("rootPath") + savePath;
		修改为：
		String physicalPath = (String) conf.get("basePath") + savePath;
	
	
	
	
	
