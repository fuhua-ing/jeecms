package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.MemberConfig;
import com.jeecms.core.entity.ProjectRelease;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.manager.ProjectReleaseMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

@Controller
public class ProjectReleaseAct {

	private static final Logger log = LoggerFactory
			.getLogger(ProjectReleaseAct.class);
	
	private static final String TPL_PROJECTRELEASE = "tpl.projectRelease";
	private static final String FRONT_RELEASEAUTH_AMONG = "tpl.projectReleaseDetail";
	
	@RequestMapping(value = "/personal/project_release.jspx",method=RequestMethod.GET)
	public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model)
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
		FrontUtils.frontData(request, model, site);
		CmsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, TPL_PROJECTRELEASE);
	}
	
	@RequestMapping(value = "/personal/project_release.jspx",method=RequestMethod.POST)
	public String submit(ProjectRelease projectRelease,HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()){
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		
		FrontUtils.frontData(request, model, site);
		CmsUser user = (CmsUser)model.get("user");
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		if (projectRelease.getId() != null && projectRelease.getId() > 0) {
			projectRelease.setCheckStatus((byte)1);
			projectRelease.setCreateTime(new Date());
			projectRelease = projectReleaseMng.updateByUpdater(projectRelease);
		} else {
			projectRelease = projectReleaseMng.save(projectRelease,user.getUsername());
		}
		model.addAttribute("item", projectRelease);
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		return  FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_RELEASEAUTH_AMONG);
	}
	
	@Autowired
	private ProjectReleaseMng projectReleaseMng;
	@Autowired
	private CmsDictionaryMng cmsDictionaryMng;
}
