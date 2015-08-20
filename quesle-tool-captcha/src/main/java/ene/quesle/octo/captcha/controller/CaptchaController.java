package ene.quesle.octo.captcha.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import ene.quesle.octo.captcha.service.CaptchaService;

@RestController
public class CaptchaController {

	@Resource
	private CaptchaService captchaService;
	
	@RequestMapping(value="/image", method=RequestMethod.GET)
	public void captchaImage(HttpServletRequest	request, HttpServletResponse response){
		
		String captchaId = request.getSession().getId();
		ServletOutputStream stream = null;
		try {
			stream = response.getOutputStream();
			BufferedImage bufferedImage = captchaService.buildImage(captchaId, request.getLocale());
			
			ImageIO.write(bufferedImage, "jpg", stream);
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}
	
	
	@RequestMapping(value="/valid")
	public String captchaValid(HttpSession session, String code){
		String captchaId = session.getId();
		JSONObject obj = new JSONObject();
		try {
			boolean isValid = captchaService.isValid(captchaId, code);
			obj.put("success", isValid);
		} catch (Exception e) {
			obj.put("success", false);
		}
		return JSON.toJSONString(obj);
	}
}
