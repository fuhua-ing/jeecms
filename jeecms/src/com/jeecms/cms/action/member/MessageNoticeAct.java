package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.MemberConfig;
import com.jeecms.core.manager.JcUserNoticeMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

@Controller
public class MessageNoticeAct {
	private static final String FRONT_MESSAGENOTICE = "tpl.messageNotice";
	
	@Autowired
	private JcUserNoticeMng jcUserNoticeMng;
	
	@RequestMapping(value = "/messageNotice/messagePage.jspx", method = RequestMethod.GET)
	public String investorInfo(Integer pageNo,Integer pageSize,HttpServletRequest request, ModelMap model){
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
		if (pageSize == null) {
			pageSize = 5;
		}
		Pagination pagination = jcUserNoticeMng.getUserNoticePage(user.getId(),pageNo, pageSize);
		model.addAttribute("pagination", pagination);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_MESSAGENOTICE);
	}
	
	@RequestMapping(value = "/messageNotice/markMessageNoticeRead.jspx")
	public String markMessageNoticeRead(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model,Integer[] ids,Integer pageNo,Integer pageSize){
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
		
		jcUserNoticeMng.readUserNotice(ids);
		
		if (pageSize == null) {
			pageSize = 5;
		}
		Pagination pagination = jcUserNoticeMng.getUserNoticePage(user.getId(), pageNo, pageSize);
		model.addAttribute("pagination", pagination);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_MESSAGENOTICE);
	}
	
	@RequestMapping(value = "/messageNotice/deleteMessageNotice.jspx")
	public String deleteMessage(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model, Integer[] ids,Integer pageNo,Integer pageSize){
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
		
		jcUserNoticeMng.deleteUserNotice(ids);
		
		if (pageSize == null) {
			pageSize = 5;
		}
		Pagination pagination = jcUserNoticeMng.getUserNoticePage(user.getId(), pageNo, pageSize);
		model.addAttribute("pagination", pagination);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_MESSAGENOTICE);
	}
}
