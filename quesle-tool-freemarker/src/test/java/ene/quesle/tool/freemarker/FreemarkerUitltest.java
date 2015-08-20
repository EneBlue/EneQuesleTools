package ene.quesle.tool.freemarker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.TemplateException;

/**
 * @author Quesle
 * @Email  zrwuxian@126.com
 * @date   2015年8月19日 下午6:23:36 
 * @version 1.0 
 */
public class FreemarkerUitltest {

	@Test
	public void test() throws TemplateException, IOException{
		String path1 = "file:/D:/dev/java/ene.work/quesle/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/quesle-tool-freemarker/WEB-INF/fm/message.ftl";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "Json");
		
		File file = new File(path1);
		FreemarkerUtil.execute(file, params);
	}
}
