package ene.quesle.tool.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author Quesle
 * @Email  zrwuxian@126.com
 * @date   2015年8月19日 下午5:57:55 
 * @version 1.0 
 */
public class FreemarkerUtil {

	public static String covert(String path, String name, Map<String, Object> params) throws TemplateException, IOException{
		File file = new File(path, name);
		System.out.println(file.getPath());
		System.out.println(file.getName());
		return execute(file, params);
	}
	
	public static String execute(File ftlFile, Map<String, Object> data)
			throws TemplateException, IOException {
		StringWriter sw = new StringWriter();
		Template template = createTemplate(ftlFile);
		template.process(data, sw);
		return sw.toString();
	}
	
	public static Template createTemplate(File ftlFile) throws IOException {
		Configuration cfg = new Configuration();
		File parentFile = ftlFile.getParentFile();
		System.out.println(parentFile.getPath());
		System.out.println(parentFile.getName());
		// 加载freemarker模板文件
		cfg.setDirectoryForTemplateLoading(ftlFile.getParentFile());
		cfg.setClassicCompatible(true);
		cfg.setDateFormat("yyyy-MM-dd");
		cfg.setNumberFormat("");
		cfg.setDefaultEncoding("utf-8");
		// 设置对象包装器
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		// 设计异常处理器
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

		// 获取指定模板文件
		Template template = cfg.getTemplate(ftlFile.getName());

		return template;
	}
}
