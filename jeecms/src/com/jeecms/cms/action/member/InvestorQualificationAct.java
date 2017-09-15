package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.InvestorQualification;
import com.jeecms.core.entity.MemberConfig;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.manager.InvestorQualificationMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

@Controller
public class InvestorQualificationAct {
	
	private static final String FRONT_INVESTORINFO = "tpl.investorInfo";
	private static final String FRONT_INVESTORINFO_HANDING = "tpl.investorInfoHanding";
	private static final String FRONT_INVESTORINFO_SUCCESS = "tpl.investorInfoSuccess";
	private static final String FRONT_INVESTORINFO_FAILURE = "tpl.investorInfoFailure";
	
	@Autowired
	private InvestorQualificationMng investorQualificationMng;
	
	@Autowired
	private CmsDictionaryMng cmsDictionaryMng;
	
	@RequestMapping(value = "/investor/investorQualificationInfo.jspx", method = RequestMethod.GET)
	public String investorInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()){
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		CmsUser user = (CmsUser)model.get("user");
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		InvestorQualification info = investorQualificationMng.findByUserName(user.getUsername());
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		
		if(info != null) {
			model.addAttribute("item", info);
			if (info.getCheckStatus()!=null){
				if (info.getCheckStatus() == 1) {
					return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_INVESTORINFO_HANDING);
				}
				if (info.getCheckStatus() == 2) {
					return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_INVESTORINFO_SUCCESS);
				}
				if (info.getCheckStatus() == 3) {
					return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_INVESTORINFO_FAILURE);
				}
			}
		}
		
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_INVESTORINFO);
	}
	
	@RequestMapping(value = "/investor/modifyInvestorQualificationInfo.jspx", method = RequestMethod.GET)
	public String modifyInvestorInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model){
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
		InvestorQualification info = investorQualificationMng.findByUserName(user.getUsername());
		if(info != null) {
			model.addAttribute("item", info);
		}
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_INVESTORINFO);
	}
	
	@RequestMapping(value = "/investor/investorQualificationInfo.jspx", method = RequestMethod.POST)
	public String saveInvestorInfo(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model,InvestorQualification info){
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
		
		if (info.getId() != null && info.getId() > 0) {
			info.setCmsUser(user);
			info = investorQualificationMng.modifyInvestorQualificationMax(info);
		} else {
			info = investorQualificationMng.saveInvestorQualification(info,user.getUsername());
		}
		model.addAttribute("item", info);
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_INVESTORINFO_HANDING);
	}
}
