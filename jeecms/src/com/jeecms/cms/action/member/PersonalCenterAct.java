package com.jeecms.cms.action.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.ChangeEunm;
import com.jeecms.cms.Constants;
import com.jeecms.cms.MessageTypeEnum;
import com.jeecms.cms.SexEnum;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsConfig;
import com.jeecms.core.entity.CmsDictionary;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.manager.CmsUserExtMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.manager.SmsManagerPlatform;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 
 * <个人中心页面>
 * <功能详细描述>
 * 
 * @author gzhiwei
 * @version [V100R001C00, 2017-9-5]
 */
@Controller
public class PersonalCenterAct
{

	private static final String PERSON_CENTER = "tpl.personalcenter";

	private static final String PERSON_CHOOSE = "tpl.personchoose";

	private static final String PHONE_VALIDATOR = "tpl.phonevalidator";

	private static final String PERSON_SEND_EMAIL_SECCUSS = "tpl.sendemailsuccess";

	private static final String MESSAGE_TO_NEW_EMAIL = "tpl.messageNewMail";

	private static final String MESSAGE_TO_NEW_PHONE = "tpl.messageNewPhone";

	private static final String ERROR = "tpl.errorPage";

	private static final String REGEX = "^[1][0-9]{10}$";

	private static final String MESSAGEID = "^[0-9]{6}$";

	@Autowired
	private CmsUserExtMng cmsUserExtMng;

	@Autowired
	private CmsUserMng cmsUserMng;

	@Autowired
	private CmsDictionaryMng cmsDictionaryMng;

	@Autowired
	private SmsManagerPlatform smsManagerPlatform;

	@Autowired
	private ImageCaptchaService imageCaptchaService;

	@Autowired
	private SessionProvider session;

	@Autowired
	private UnifiedUserMng unifiedUserMng;

	/**
	 * 
	 * <进入个人中心主页面>
	 * <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author gzhiwei
	 */
	@RequestMapping(value = "/personal/arrive_center.jspx", method = RequestMethod.GET)
	public String personCenter(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);
		String username = getUsername(request);
		CmsUserExt cmsUserExt = cmsUserExtMng.findByUsername(username);
		CmsUser user = cmsUserMng.findByUsername(username);

		Integer sex = cmsUserExt.getGender() ? SexEnum.Male.getValue() : SexEnum.Female.getValue();
		String email = user.getEmail();
		Integer memberType = user.getMemberType();
		int type = memberType == null ? 0 : memberType;
		List<CmsDictionary> list = cmsDictionaryMng.getList("investFiled");
		String cmsDictionary = list.get(Integer.parseInt(cmsUserExt.getCompanyType()) - 1).getName();

		model.addAttribute("cmsUserExt", cmsUserExt);
		model.addAttribute("type", type);
		model.addAttribute("email", email);
		model.addAttribute("sex", sex);
		model.addAttribute("investFiledList", list);
		model.addAttribute("companyType", cmsDictionary);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_PERSONAL, PERSON_CENTER);
	}

	/**
	 * 
	 * <新金融个人中心保存按钮>
	 * <功能详细描述>
	 * 
	 * @param userExt
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author gzhiwei
	 */
	@RequestMapping(value = "/personal/update_baseinfo.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String saveBaseInfo(CmsUserExt userExt, HttpServletRequest request, HttpServletResponse response,
			ModelMap model)
	{
		try
		{
			String username = getUsername(request);
			CmsUserExt cmsUserExt = cmsUserExtMng.findByUsername(username);
			userExt.setId(cmsUserExt.getId());
			if (null == userExt.getUserImg() || userExt.getUserImg().equals(""))
			{
				userExt.setUserImg(cmsUserExt.getUserImg());
			}
			cmsUserExtMng.updateCmsUserExt(userExt);
			return "o";
		}
		catch (Exception e)
		{
			return "f";
		}
	}

	/**
	 * 
	 * <个人中心页面点击更改邮箱或者手机号码选择验证方式>
	 * <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author gzhiwei
	 */
	@RequestMapping("/personal/personal_choose.jspx")
	public String stepPersonalChoose(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		String wantChange = request.getParameter("wantChange");
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("wantChange", wantChange);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_PERSONAL, PERSON_CHOOSE);
	}

	/**
	 * 
	 * <选择手机验证跳转的页面>
	 * <功能详细描述>
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author gzhiwei
	 */
	@RequestMapping("/personal/personal_phoneValidator.jspx")
	public String phoneValidator(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		String wantChange = request.getParameter("wantChange");
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("wantChange", wantChange);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_PERSONAL, PHONE_VALIDATOR);
	}

	/**
	 * 
	 * <发送邮件验证>
	 * <功能详细描述>
	 * 
	 * @param changeTpye
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author gzhiwei
	 */
	@RequestMapping(value = "/personal/send_email.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmail(String wantChange, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		boolean flag = false;
		String username = getUsername(request);

		String emailEumn = ChangeEunm.Email.getValue();
		String phoneEumn = ChangeEunm.Phone.getValue();
		if (emailEumn.equals(wantChange))
		{
			flag = cmsUserMng.sendEmail(username, emailEumn);
		}
		else
		{
			flag = cmsUserMng.sendEmail(username, phoneEumn);

		}
		if (flag)
		{
			return wantChange;
		}
		return "false";
	}

	@RequestMapping("/personal/personal_sendEmailSuccess.jspx")
	public String sendEmailSuccess(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{

		String wantChange = request.getParameter("wantChange");
		CmsSite site = CmsUtils.getSite(request);

		String username = getUsername(request);

		CmsUser findByUsername = cmsUserMng.findByUsername(username);
		String email = findByUsername.getEmail();

		model.addAttribute("wantChange", wantChange);
		model.addAttribute("email", email);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_PERSONAL,
				PERSON_SEND_EMAIL_SECCUSS);
	}

	@RequestMapping(value = "/personal/getMessage.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String getMessageInner(String captcha, HttpServletRequest request, HttpServletResponse response,
			ModelMap model)
	{
		String username = getUsername(request);
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
		smsManagerPlatform.sendMessage(username, MessageTypeEnum.Validator);

		return "ok";
	}

	@RequestMapping(value = "/personal/getMessage_newPhone.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String getMessageNewPhone(String username, String captcha, HttpServletRequest request,
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

	@RequestMapping(value = "/personal/step_next.jspx", method = RequestMethod.POST)
	public String validatorByMessageThenStepNext(String wantChange, String messageCode, HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
	{
		String username = getUsername(request);
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("wantChange", wantChange);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		if (smsManagerPlatform.validatorExsitor(username, messageCode, MessageTypeEnum.Validator))
		{
			if (ChangeEunm.Email.getValue().equals(wantChange))
			{
				FrontUtils.frontData(request, model, site);
				FrontUtils.frontPageData(request, model);
				return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_PERSONAL,
						MESSAGE_TO_NEW_EMAIL);
			}

			return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_PERSONAL,
					MESSAGE_TO_NEW_PHONE);
		}

		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_COMMON, ERROR);
	}

	@RequestMapping(value = "/personal/reset_email.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String updateEmail(String email, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		String username = getUsername(request);
		smsManagerPlatform.updateEmailBymessageValidator(username, email);
		return "ok";
	}

	@RequestMapping(value = "/personal/newPhone_username.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String resetNewUsername(String username, String messageCode, String userId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
	{

		String oldUsername = getUsername(request);

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
			smsManagerPlatform.changeUsernameByOldUsername(oldUsername, username);
			session.logout(request, response);
			return "ok";
		}

		return "false";
	}

	/**
	 * 
	 * <从session获取username>
	 * <功能详细描述>
	 * 
	 * @param request
	 * @return
	 * @author gzhiwei
	 */
	private String getUsername(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session
				.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

		Object primaryPrincipal = simplePrincipalCollection.getPrimaryPrincipal();

		String username = primaryPrincipal.toString();
		return username;
	}
}
