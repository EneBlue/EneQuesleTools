package org.quesle.tool.fileupload;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Quesle
 * @Email  zrwuxian@126.com
 * @date   2015年8月19日 下午1:52:49 
 * @version 1.0 
 */
@Controller
public class IndexController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/index")
	public String index(){
		
		logger.debug("转跳到首页");
		return "index";
	}
	
}
