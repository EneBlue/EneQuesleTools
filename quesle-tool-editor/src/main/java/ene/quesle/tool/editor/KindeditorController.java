package ene.quesle.tool.editor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KindeditorController{

	@RequestMapping("kinder")
	public String kindeditorView(){
		return "kindeditor";
	}
}
