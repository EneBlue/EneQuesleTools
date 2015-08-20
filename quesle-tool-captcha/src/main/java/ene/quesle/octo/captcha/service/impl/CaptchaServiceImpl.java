/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package ene.quesle.octo.captcha.service.impl;

import java.awt.image.BufferedImage;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ene.quesle.octo.captcha.service.CaptchaService;

/**
 * Service - 验证码
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("captchaServiceImpl")
public class CaptchaServiceImpl implements CaptchaService {

	@Resource(name = "imageCaptchaService")
	private com.octo.captcha.service.CaptchaService imageCaptchaService;

	public BufferedImage buildImage(String captchaId, Locale locale) {
		return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId, locale);
	}

	public boolean isValid(String captchaId, String captcha) {
		return imageCaptchaService.validateResponseForID(captchaId, captcha.toUpperCase());
	}

}