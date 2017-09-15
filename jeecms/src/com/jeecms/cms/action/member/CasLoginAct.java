package com.jeecms.cms.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.Constants;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.Authentication;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.manager.CmsUserExtMng;
import com.jeecms.core.manager.ConfigMng;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

@Controller
public class CasLoginAct
{
	private static final String TPL_INDEX = "tpl.index";
	private static final String TPL_LOGIN = "tpl.login_html";

	@RequestMapping(value = "/login.jspx", method = RequestMethod.GET)
	public String input(String returnUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);
		String sol = site.getSolutionPath();
		Integer errorTimes = configMng.getConfigLogin().getErrorTimes();
		model.addAttribute("errorTimes", errorTimes);
		model.addAttribute("site", site);
		if (StringUtils.isBlank(returnUrl))
		{
			session.setAttribute(request, response, "loginSource", null);
		}
		Object source = session.getAttribute(request, "loginSource");
		if (source != null)
		{
			String loginSource = (String) source;
			model.addAttribute("loginSource", loginSource);
		}
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, sol, Constants.TPLDIR_MEMBER, TPL_LOGIN);
	}

	@RequestMapping(value = "/login.jspx", method = RequestMethod.POST)
	public String submit(String username, String password, HttpServletRequest request, HttpServletResponse response,
			ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);
		String sol = site.getSolutionPath();
		String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (error != null)
		{
			model.addAttribute("error", "用户名或者密码错误");
			//			model.addAttribute("errorRemaining", unifiedUserMng.errorRemaining(username));
			model.addAttribute("username", username);
			model.addAttribute("password", password);
			FrontUtils.frontData(request, model, site);
			return FrontUtils.getTplPath(request, sol, Constants.TPLDIR_MEMBER, TPL_LOGIN);
		}

		Authentication auth = authMng.login(username, password, RequestUtils.getIpAddr(request), request, response,
				session);

		CmsUserExt cmsUserExt = cmsUserExtMng.findById(auth.getUid());
		model.addAttribute("auth", auth);
		model.addAttribute("user", cmsUserExt);
		session.setAttribute(request, response, "loginSource", null);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, sol, Constants.TPLDIR_INDEX, TPL_INDEX);
	}

	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private ConfigMng configMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private AuthenticationMng authMng;
	@Autowired
	private CmsUserExtMng cmsUserExtMng;
}
