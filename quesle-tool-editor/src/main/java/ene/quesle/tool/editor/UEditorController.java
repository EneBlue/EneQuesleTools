package ene.quesle.tool.editor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.quesle.tool.fileupload.service.FileUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UEditorController {
	
	@Resource
	private FileUploadService fileUploadService;

	@RequestMapping("ueditorView")
	public String view(){
		return "ueditor";
	}
	
	@RequestMapping("edit")
	public String edit(String test){
		System.out.println(test);
		return "ueditor";
	}
	
	@RequestMapping("/upliad/image")
	public String edit(HttpServletRequest request){
		
		return "success";
	}
}
