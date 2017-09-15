package com.jeecms.cms.action.member;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.Constants;
import com.jeecms.cms.MessageTypeEnum;
import com.jeecms.cms.entity.assist.CmsWebservice;
import com.jeecms.cms.manager.assist.CmsWebserviceMng;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsConfig;
import com.jeecms.core.entity.CmsConfigItem;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.entity.MemberConfig;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.CmsConfigItemMng;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.manager.SmsManagerPlatform;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 前台会员注册Action
 */
@Controller
public class RegisterAct
{
	private static final Logger log = LoggerFactory.getLogger(RegisterAct.class);

	public static final String REGISTER = "tpl.register";
	public static final String REGISTER_RESULT = "tpl.registerResult";
	public static final String REGISTER_ACTIVE_SUCCESS = "tpl.registerActiveSuccess";
	public static final String LOGIN_INPUT = "tpl.loginInput";
	public static final String REGISTER_NEXT = "tpl.registerNext";

	private static final String REGEX = "^[1][0-9]{10}$";

	private static final String MESSAGEID = "^[0-9]{6}$";

	@RequestMapping(value = "/register.jspx", method = RequestMethod.GET)
	public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);
		List<CmsConfigItem> items = cmsConfigItemMng.getList(site.getConfig().getId(), CmsConfigItem.CATEGORY_REGISTER);
		FrontUtils.frontData(request, model, site);
		model.addAttribute("items", items);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_MEMBER, REGISTER);
	}

	@RequestMapping(value = "/register_success.jspx", method = RequestMethod.GET)
	public String registerSuccess(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn())
		{
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		// 没有开启会员注册
		if (!mcfg.isRegisterOn())
		{
			return FrontUtils.showMessage(request, model, "member.registerClose");
		}
		List<CmsConfigItem> items = cmsConfigItemMng.getList(site.getConfig().getId(), CmsConfigItem.CATEGORY_REGISTER);
		FrontUtils.frontData(request, model, site);
		model.addAttribute("mcfg", mcfg);
		model.addAttribute("items", items);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_MEMBER, REGISTER_RESULT);
	}

	@RequestMapping(value = "/register_getmessage.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String getMessage(String username, String captcha, HttpServletRequest request, HttpServletResponse response,
			ModelMap model)
	{
		if (StringUtils.isBlank(username))
		{
			return "error.phone.nulls";
		}
		CmsSite site = CmsUtils.getSite(request);
		CmsConfig config = site.getConfig();
		// 保留字检查不通过，返回false。
		if (!config.getMemberConfig().checkUsernameReserved(username))
		{
		}
		// 用户名存在，返回false。
		if (unifiedUserMng.usernameExist(username))
		{
			return "error.phone.exsit";
		}

		if (!username.matches(REGEX))
		{
			return "error.phone.wrong";
		}
		try
		{
			if (!imageCaptchaService.validateResponseForID(session.getSessionId(request, response), captcha))
			{
				return "error.captcha";
			}
		}
		catch (CaptchaServiceException e)
		{
			return "error.captcha";
		}

		smsManagerPlatform.sendMessage(username, MessageTypeEnum.Register);
		return "ok";

	}

	@RequestMapping(value = "/register_userAccount.jspx", method = RequestMethod.POST)
	public String account(String username, String passwd, String passwdAgain, String message, String captcha,
			HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);

		if (!message.matches(MESSAGEID)
				|| !smsManagerPlatform.validatorExsitor(username, message, MessageTypeEnum.Register))
		{
			model.addAttribute("errMsg", "短信验证码错误");
			return FrontUtils.newShowError(request, response, model);
		}

		if (!passwd.equals(passwdAgain))
		{
			model.addAttribute("errMsg", "两次密码不一致");
			return FrontUtils.newShowError(request, response, model);
		}

		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		session.setAttribute("password", passwd);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		List<CmsConfigItem> items = cmsConfigItemMng.getList(site.getConfig().getId(), CmsConfigItem.CATEGORY_REGISTER);
		FrontUtils.frontData(request, model, site);
		model.addAttribute("mcfg", mcfg);
		model.addAttribute("items", items);
		model.addAttribute("investFiledList", cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("username", username);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_MEMBER, REGISTER_NEXT);
	}

	@RequestMapping(value = "/register.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String submit(CmsUserExt userExt, String email, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException
	{
		boolean disabled = false;
		String ip = RequestUtils.getIpAddr(request);
		Map<String, String> attrs = RequestUtils.getRequestMap(request, "attr_");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String loginPassword = (String) session.getAttribute("password");
		try
		{
			cmsUserMng.registerMember(username, email, loginPassword, ip, null, disabled, userExt, attrs, false, null,
					null);
			cmsWebserviceMng.callWebService("false", username, loginPassword, userExt.getMobile(), userExt,
					CmsWebservice.SERVICE_TYPE_ADD_USER);
			model.addAttribute("status", 0);
		}
		catch (UnsupportedEncodingException e)
		{
		}
		catch (MessagingException e)
		{
		}
		log.info("member register success. username={}", username);
		return "ok";
	}

	@RequestMapping(value = "/registerNext.jspx", method = RequestMethod.GET)
	public String registerNext(String username, String passwd, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException
	{
		CmsSite site = CmsUtils.getSite(request);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		List<CmsConfigItem> items = cmsConfigItemMng.getList(site.getConfig().getId(), CmsConfigItem.CATEGORY_REGISTER);
		FrontUtils.frontData(request, model, site);
		model.addAttribute("mcfg", mcfg);
		model.addAttribute("items", items);
		model.addAttribute("username", username);
		model.addAttribute("passwd", passwd);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_MEMBER, REGISTER_NEXT);
	}

	@RequestMapping(value = "/active.jspx", method = RequestMethod.GET)
	public String active(String username, String key, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException
	{
		CmsSite site = CmsUtils.getSite(request);
		username = new String(request.getParameter("username").getBytes("iso8859-1"), "GBK");
		WebErrors errors = validateActive(username, key, request, response);
		if (errors.hasErrors())
		{
			return FrontUtils.showError(request, response, model, errors);
		}
		unifiedUserMng.active(username, key);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_MEMBER, REGISTER_ACTIVE_SUCCESS);
	}

	@RequestMapping(value = "/username_unique.jspx")
	public void usernameUnique(HttpServletRequest request, HttpServletResponse response)
	{
		String username = RequestUtils.getQueryParam(request, "username");
		// 用户名为空，返回false。
		if (StringUtils.isBlank(username))
		{
			ResponseUtils.renderJson(response, "false");
			return;
		}
		CmsSite site = CmsUtils.getSite(request);
		CmsConfig config = site.getConfig();
		// 保留字检查不通过，返回false。
		if (!config.getMemberConfig().checkUsernameReserved(username))
		{
			ResponseUtils.renderJson(response, "false");
			return;
		}
		// 用户名存在，返回false。
		if (unifiedUserMng.usernameExist(username))
		{
			ResponseUtils.renderJson(response, "false");
			return;
		}
		ResponseUtils.renderJson(response, "true");
	}

	@RequestMapping(value = "/email_unique.jspx")
	public void emailUnique(HttpServletRequest request, HttpServletResponse response)
	{
		String email = RequestUtils.getQueryParam(request, "email");
		// email为空，返回false。
		if (StringUtils.isBlank(email))
		{
			ResponseUtils.renderJson(response, "false");
			return;
		}
		// email存在，返回false。
		if (unifiedUserMng.emailExist(email))
		{
			ResponseUtils.renderJson(response, "false");
			return;
		}
		ResponseUtils.renderJson(response, "true");
	}

	//	private WebErrors validateSubmit(String username, String email, String password, String captcha, CmsSite site,
	//			HttpServletRequest request, HttpServletResponse response)
	//	{
	//		MemberConfig mcfg = site.getConfig().getMemberConfig();
	//		WebErrors errors = WebErrors.create(request);
	//		try
	//		{
	//			//			if (!imageCaptchaService.validateResponseForID(session.getSessionId(request, response), captcha))
	//			//			{
	//			//				errors.addErrorCode("error.invalidCaptcha");
	//			//				return errors;
	//			//			}
	//		}
	//		catch (CaptchaServiceException e)
	//		{
	//			errors.addErrorCode("error.exceptionCaptcha");
	//			log.warn("", e);
	//			return errors;
	//		}
	//		if (errors.ifOutOfLength(username, MessageResolver.getMessage(request, "field.username"),
	//				mcfg.getUsernameMinLen(), 100))
	//		{
	//			return errors;
	//		}
	//		if (errors.ifNotUsername(username, MessageResolver.getMessage(request, "field.username"),
	//				mcfg.getUsernameMinLen(), 100))
	//		{
	//			return errors;
	//		}
	//		if (errors.ifOutOfLength(password, MessageResolver.getMessage(request, "field.password"),
	//				mcfg.getPasswordMinLen(), 100))
	//		{
	//			return errors;
	//		}
	//		if (errors.ifNotEmail(email, MessageResolver.getMessage(request, "field.email"), 100))
	//		{
	//			return errors;
	//		}
	//		// 保留字检查不通过，返回false。
	//		if (!mcfg.checkUsernameReserved(username))
	//		{
	//			errors.addErrorCode("error.usernameReserved");
	//			return errors;
	//		}
	//		// 用户名存在，返回false。
	//		if (unifiedUserMng.usernameExist(username))
	//		{
	//			errors.addErrorCode("error.usernameExist");
	//			return errors;
	//		}
	//		return errors;
	//	}

	private WebErrors validateActive(String username, String activationCode, HttpServletRequest request,
			HttpServletResponse response)
	{
		WebErrors errors = WebErrors.create(request);
		if (StringUtils.isBlank(username) || StringUtils.isBlank(activationCode))
		{
			errors.addErrorCode("error.exceptionParams");
			return errors;
		}
		UnifiedUser user = unifiedUserMng.getByUsername(username);
		if (user == null)
		{
			errors.addErrorCode("error.usernameNotExist");
			return errors;
		}
		/*
		 * firefox访问链接二次访问，现简单不验证
		 * if (user.getActivation()
		 * || StringUtils.isBlank(user.getActivationCode())) {
		 * errors.addErrorCode("error.usernameActivated");
		 * return errors;
		 * }
		 * if (!user.getActivationCode().equals(activationCode)) {
		 * errors.addErrorCode("error.exceptionActivationCode");
		 * return errors;
		 * }
		 */
		if (StringUtils.isNotBlank(user.getActivationCode()) && !user.getActivationCode().equals(activationCode))
		{
			errors.addErrorCode("error.exceptionActivationCode");
			return errors;
		}
		return errors;
	}

	@Autowired
	private CmsWebserviceMng cmsWebserviceMng;
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private CmsConfigItemMng cmsConfigItemMng;
	@Autowired
	private CmsDictionaryMng cmsDictionaryMng;
	@Autowired
	private SmsManagerPlatform smsManagerPlatform;
}
