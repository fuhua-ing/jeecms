package com.jeecms.cms.action.member;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.ChangeEunm;
import com.jeecms.cms.Constants;
import com.jeecms.cms.MessageTypeEnum;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsConfig;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.JcEvent;
import com.jeecms.core.manager.CorpApplyService;
import com.jeecms.core.manager.SmsManagerPlatform;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class InfoResetAct
{

	private static final String NEW_EMAIL = "tpl.newemail";

	private static final String NEW_PHONE = "tpl.newphone";

	private static final String TIME_OUT = "tpl.timeout";

	private static final String REGEX = "^[1][0-9]{10}$";

	private static final String MESSAGEID = "^[0-9]{6}$";

	@Autowired
	private CorpApplyService corpApplyService;

	@Autowired
	private SmsManagerPlatform smsManagerPlatform;

	@Autowired
	private ImageCaptchaService imageCaptchaService;

	@Autowired
	private SessionProvider session;

	@Autowired
	private UnifiedUserMng unifiedUserMng;

	@RequestMapping("/inforeset/reset_info.jspx")
	public String reset(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		//		HttpSession session = request.getSession();
		//		SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session
		//				.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		//
		//		Object primaryPrincipal = simplePrincipalCollection.getPrimaryPrincipal();
		//
		//		String username = primaryPrincipal.toString();
		String reset = request.getParameter("reset");
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		Timestamp requestTime = new Timestamp(System.currentTimeMillis());
		JcEvent jcEvent = corpApplyService.resetInfo(reset, requestTime);
		if (null == jcEvent)
		{
			return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_PERSONAL, TIME_OUT);
		}
		Integer userId = jcEvent.getEventUserId();
		String eventUuid = jcEvent.getEventUuid();
		if (jcEvent.getEventType().equals(ChangeEunm.Email.getValue()))
		{
			model.addAttribute("userId", userId);
			model.addAttribute("eventUuid", eventUuid);
			return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_PERSONAL, NEW_EMAIL);
		}
		model.addAttribute("userId", userId);
		model.addAttribute("eventUuid", eventUuid);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_PERSONAL, NEW_PHONE);
	}

	@RequestMapping(value = "/inforeset/reset_email.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String resetNewEmail(String userId, String eventUuid, String email, HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
	{
		if (corpApplyService.resetNewEmail(userId, eventUuid, email))
		{
			return "ok";
		}
		return "false";
	}

	@RequestMapping(value = "/inforeset/getMessgeCode.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String getMessageReset(String username, String captcha, HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
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
		smsManagerPlatform.sendMessage(username, MessageTypeEnum.Change_Username);

		return "ok";
	}

	@RequestMapping(value = "/inforeset/newPhone_username.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String resetNewUsername(String username, String messageCode, String userId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
	{
		boolean flag = false;
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

		if (!messageCode.matches(MESSAGEID))
		{
			return "error.messageCode";
		}

		flag = smsManagerPlatform.validatorExsitor(username, messageCode, MessageTypeEnum.Change_Username);
		if (flag)
		{
			smsManagerPlatform.changeUsername(userId, username);
			return "ok";
		}
		return "false";
	}
}
