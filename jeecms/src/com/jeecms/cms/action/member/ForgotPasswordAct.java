package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.MessageTypeEnum;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsConfig;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.manager.SmsManagerPlatform;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 找回密码Action
 * 
 * 用户忘记密码后点击找回密码链接，输入用户名、邮箱和验证码<li>
 * 如果信息正确，返回一个提示页面，并发送一封找回密码的邮件，邮件包含一个链接及新密码，点击链接新密码即生效<li>
 * 如果输入错误或服务器邮箱等信息设置不完整，则给出提示信息<li>
 */
@Controller
public class ForgotPasswordAct
{
	private static Logger log = LoggerFactory.getLogger(ForgotPasswordAct.class);

	public static final String FORGOT_PASSWORD_INPUT = "tpl.forgotPasswordInput";
	public static final String FORGOT_PASSWORD_RESULT = "tpl.forgotPasswordResult";
	public static final String PASSWORD_RESET = "tpl.passwordReset";

	private static final String REGEX = "^[1][0-9]{10}$";

	private static final String MESSAGEID = "^[0-9]{6}$";

	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

	/**
	 * 找回密码输入页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/forgot_password.jspx", method = RequestMethod.GET)
	public String forgotPasswordInput(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FORGOT_PASSWORD_INPUT);
	}

	/**
	 * 
	 * <一句话功能简述>
	 * <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author gzhiwei
	 */
	@RequestMapping(value = "/member/forgot_sendMessage.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String send(String username, String captcha, HttpServletRequest request, HttpServletResponse response,
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
		if (!unifiedUserMng.usernameExist(username))
		{
			return "error.phone.not.exsit";
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

		smsManagerPlatform.sendMessage(username, MessageTypeEnum.ForgetPassword);
		return "ok";

	}

	/**
	 * 找回密码提交页
	 * 
	 * @param username
	 * @param email
	 * @param captcha
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/member/forgot_password_sendCaptcha.jspx", method = RequestMethod.POST)
	public String forgotPasswordSubmit(String username, String message, HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
	{
		if (StringUtils.isBlank(username))
		{
			model.addAttribute("errMsg", "手机号码格式错误");
			return FrontUtils.newShowError(request, response, model);
		}
		CmsSite site = CmsUtils.getSite(request);
		CmsConfig config = site.getConfig();
		// 保留字检查不通过，返回false。
		if (!config.getMemberConfig().checkUsernameReserved(username))
		{
		}
		// 用户名存在，返回false。
		if (!unifiedUserMng.usernameExist(username))
		{
			model.addAttribute("errMsg", "用户名或者验证码错误");
			return FrontUtils.newShowError(request, response, model);
		}

		if (!username.matches(REGEX))
		{
			model.addAttribute("errMsg", "手机号码格式错误");
			return FrontUtils.newShowError(request, response, model);
		}

		if (!smsManagerPlatform.validatorExsitor(username, message, MessageTypeEnum.ForgetPassword))
		{
			model.addAttribute("errMsg", "短信验证码错误");
			return FrontUtils.newShowError(request, response, model);
		}
		FrontUtils.frontData(request, model, site);
		model.addAttribute("username", username);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, PASSWORD_RESET);

	}

	@RequestMapping(value = "/member/password_confirm.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String confirm(String username, String passwd, HttpServletRequest request, HttpServletResponse response,
			ModelMap model)
	{
		unifiedUserMng.updatePassword(username, passwd);
		return "ok";
	}

	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private SmsManagerPlatform smsManagerPlatform;
}
