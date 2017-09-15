package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.MemberConfig;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;


@Controller
public class AccountSecurityAct {
	
	private static final Logger log = LoggerFactory
			.getLogger(AccountSecurityAct.class);
	
	private static final String TPL_ACCOUNTSECURITY = "tpl.accountSecurity";
	
	@RequestMapping(value = "/personal/account_security.jspx",method=RequestMethod.GET)
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
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, TPL_ACCOUNTSECURITY);
		
	}
	
	@RequestMapping(value = "/personal/account_security.jspx",method=RequestMethod.POST)
	public String submit(String oldPassword,String newPassword,HttpServletRequest request, HttpServletResponse response, ModelMap model)
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
		//后台管理User
		CmsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		//客户端User
		UnifiedUser unifiedUser = unifiedUserMng.getByUsername(user.getUsername());
		if (unifiedUser != null) {
			if(unifiedUserMng.isPasswordValid(unifiedUser.getId(), oldPassword)) {
				unifiedUserMng.updatePassword(unifiedUser.getUsername(), newPassword);
				return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, TPL_ACCOUNTSECURITY);
			}else {
				return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, TPL_ACCOUNTSECURITY);
			}
		}

		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, TPL_ACCOUNTSECURITY);
	}
	
	@RequestMapping(value = "/personal/check_old_password.jspx",method=RequestMethod.POST)
	public void checkOldPassword(String oldPassword, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn())
		{
			 FrontUtils.showMessage(request, model, "member.memberClose");
		}
		// 没有开启会员注册
		if (!mcfg.isRegisterOn())
		{
			 FrontUtils.showMessage(request, model, "member.registerClose");
		}
		FrontUtils.frontData(request, model, site);
		//后台管理User
		CmsUser user = CmsUtils.getUser(request);
		if (user == null) {
			 FrontUtils.showLogin(request, model, site);
		}
		//客户端User
		UnifiedUser unifiedUser = unifiedUserMng.getByUsername(user.getUsername());
				
		//返回response数据
		PrintWriter out = null;
		try {
			 out = response.getWriter();
			 if (unifiedUser != null) {
				if(unifiedUserMng.isPasswordValid(unifiedUser.getId(), oldPassword)) {
					 out.write(Boolean.toString(true)); 
				}else {
					 out.write(Boolean.toString(false)); 
				}
			}
		} catch (IOException e) {
			log.error("{}",e);
		}finally{
			out.flush();
			out.close();
		}
	}
	
	@Autowired
	private UnifiedUserMng unifiedUserMng;
}
