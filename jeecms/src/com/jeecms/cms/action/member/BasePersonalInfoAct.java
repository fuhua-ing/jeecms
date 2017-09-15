package com.jeecms.cms.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.Constants;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.CmsUserExtMng;
import com.jeecms.core.manager.ConfigMng;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

public class BasePersonalInfoAct
{

	private static final String TPL_BASEINFO = "tpl_baseInfo";

	@Autowired
	private ConfigMng configMng;

	@Autowired
	private CmsUserExtMng cmsUserExtMng;

	@Autowired
	private UnifiedUserMng unifiedUserMng;

	@Autowired
	private SessionProvider session;

	@RequestMapping(value = "/baseinfo_get.jspx", method = RequestMethod.GET)
	public String getBasePersonInfoPage(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{

		CmsSite site = CmsUtils.getSite(request);
		String sol = site.getSolutionPath();
		Integer errorTimes = configMng.getConfigLogin().getErrorTimes();
		model.addAttribute("errorTimes", errorTimes);
		model.addAttribute("site", site);

		HttpSession session = request.getSession();
		SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session
				.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

		Object primaryPrincipal = simplePrincipalCollection.getPrimaryPrincipal();

		String username = primaryPrincipal.toString();

		UnifiedUser unifiedUser = unifiedUserMng.getByUsername(username);

		CmsUserExt cmsUserExt = cmsUserExtMng.findById(unifiedUser.getId());

		model.addAttribute("cmsuser", cmsUserExt);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, sol, Constants.TPLDIR_BASEINFO, TPL_BASEINFO);
	}

	@RequestMapping(value = "/baseinfo_modify_phone.jspx", method = RequestMethod.POST)
	public String confirmNewPhone(String username, String message, HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
	{

		unifiedUserMng.updateUsername(username);

		session.logout(request, response);

		return "ok";
	}

	@RequestMapping(value = "/baseinfo_submit.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String submitBaseInfo(CmsUserExt cmsUserExtDto, HttpServletRequest request, HttpServletResponse response,
			ModelMap model)
	{

		HttpSession session = request.getSession();
		SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session
				.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

		Object primaryPrincipal = simplePrincipalCollection.getPrimaryPrincipal();

		String username = primaryPrincipal.toString();

		UnifiedUser unifiedUser = unifiedUserMng.getByUsername(username);

		CmsUserExt cmsUserExt = cmsUserExtMng.findById(unifiedUser.getId());

		if (null != cmsUserExtDto.getGender() && !cmsUserExtDto.getGender().equals("")
				&& !cmsUserExt.getGender().equals(cmsUserExtDto.getGender()))
		{
			cmsUserExt.setGender(cmsUserExtDto.getGender());
		}
		if (null != cmsUserExtDto.getEducation() && !cmsUserExtDto.getEducation().equals("")
				&& !cmsUserExt.getEducation().equals(cmsUserExtDto.getEducation()))
		{
			cmsUserExt.setEducation(cmsUserExtDto.getEducation());
		}
		if (null != cmsUserExtDto.getGraduate() && !cmsUserExtDto.getGraduate().equals("")
				&& !cmsUserExt.getGraduate().equals(cmsUserExtDto.getGraduate()))
		{
			cmsUserExt.setGraduate(cmsUserExtDto.getGraduate());
		}
		if (null != cmsUserExtDto.getPhone() && !cmsUserExtDto.getPhone().equals("")
				&& !cmsUserExt.getPhone().equals(cmsUserExtDto.getPhone()))
		{
			cmsUserExt.setPhone(cmsUserExtDto.getPhone());
		}
		if (null != cmsUserExtDto.getMsn() && !cmsUserExtDto.getMsn().equals("")
				&& !cmsUserExt.getMsn().equals(cmsUserExtDto.getMsn()))
		{
			cmsUserExt.setMsn(cmsUserExtDto.getMsn());
		}
		if (null != cmsUserExtDto.getCompanyName() && !cmsUserExtDto.getCompanyName().equals("")
				&& !cmsUserExt.getCompanyName().equals(cmsUserExtDto.getCompanyName()))
		{
			cmsUserExt.setCompanyName(cmsUserExtDto.getCompanyName());
		}
		if (null != cmsUserExtDto.getCompanyType() && !cmsUserExtDto.getCompanyType().equals("")
				&& !cmsUserExt.getCompanyType().equals(cmsUserExtDto.getCompanyType()))
		{
			cmsUserExt.setCompanyType(cmsUserExtDto.getCompanyType());
		}
		if (null != cmsUserExtDto.getCompanyAddressProvince() && !cmsUserExtDto.getCompanyAddressProvince().equals("")
				&& !cmsUserExt.getCompanyAddressProvince().equals(cmsUserExtDto.getCompanyAddressProvince()))
		{
			cmsUserExt.setCompanyAddressProvince(cmsUserExtDto.getCompanyAddressProvince());
		}
		if (null != cmsUserExtDto.getCompanyAddressCity() && !cmsUserExtDto.getCompanyAddressCity().equals("")
				&& !cmsUserExt.getCompanyAddressCity().equals(cmsUserExtDto.getCompanyAddressCity()))
		{
			cmsUserExt.setCompanyAddressCity(cmsUserExtDto.getCompanyAddressCity());
		}
		if (null != cmsUserExtDto.getCompanyPosition() && !cmsUserExtDto.getCompanyPosition().equals("")
				&& !cmsUserExt.getCompanyPosition().equals(cmsUserExtDto.getCompanyPosition()))
		{
			cmsUserExt.setCompanyPosition(cmsUserExtDto.getCompanyPosition());
		}
		if (null != cmsUserExtDto.getCompanyIntro() && !cmsUserExtDto.getCompanyIntro().equals("")
				&& !cmsUserExt.getCompanyIntro().equals(cmsUserExtDto.getCompanyIntro()))
		{
			cmsUserExt.setCompanyIntro(cmsUserExtDto.getCompanyIntro());
		}
		if (null != cmsUserExtDto.getIntro() && !cmsUserExtDto.getIntro().equals("")
				&& !cmsUserExt.getIntro().equals(cmsUserExtDto.getIntro()))
		{
			cmsUserExt.setIntro(cmsUserExtDto.getIntro());
		}
		cmsUserExtMng.updateCmsUserExt(cmsUserExt);

		return "ok";
	}

}
