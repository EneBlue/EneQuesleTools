package ene.quesle.tool.freemarker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import freemarker.template.TemplateException;

/**
 * @author Quesle
 * @Email  zrwuxian@126.com
 * @date   2015年8月19日 下午5:24:19 
 * @version 1.0 
 */
@Controller
public class PageController {
	
	@RequestMapping("/")
	public String root(){
		return "index";
	}

	@RequestMapping("/**")
	public String roots(){
		return "index";
	}
	
	@RequestMapping("/r")
	public String r(){
		return "redirect:/r";
	}
	
	
	@RequestMapping("/index")
	public String index(Model model){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "Json");
		List<String> names = new ArrayList<String>();
		names.add("Air");
		names.add("Jone");
		names.add("User");
		
		params.put("names", names);
		
		try {
			String message = FreemarkerUtil.covert(Constants.PATH_FM, "message.ftl", params);
			model.addAttribute("message", message);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/index";
	}
}
