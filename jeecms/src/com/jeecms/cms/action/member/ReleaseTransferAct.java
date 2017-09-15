package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcReleaseTransfer;
import com.jeecms.core.entity.MemberConfig;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.manager.JcReleaseTransferMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

@Controller
public class ReleaseTransferAct {
	
	private static final String FRONT_PROJECT_TRANSFER = "tpl.projectTransfer";
	private static final String FRONT_PROJECT_TRANSFERDETAIL = "tpl.projectTransferDetail";
	
	@Autowired
	private JcReleaseTransferMng jcReleaseTransferMng;
	
	@Autowired
	private CmsDictionaryMng cmsDictionaryMng;
	
	/**
	 * 项目发布转让页面
	 */
	@RequestMapping(value = "/releaseTransfer/transferInfo.jspx", method = RequestMethod.GET)
	public String transferInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model){
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
		
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_PROJECT_TRANSFER);
	}
	
	/**
	 * 项目发布转让保存信息
	 */
	@RequestMapping(value = "/releaseTransfer/transferInfo.jspx", method = RequestMethod.POST)
	public String saveTransferInfo(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model,JcReleaseTransfer info){
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
			info.setCheckStatus((byte)1);
			info.setApplyTime(new Date());
			info = jcReleaseTransferMng.updateByUpdater(info);
		} else {
			info = jcReleaseTransferMng.save(info,user.getUsername());
		}
		model.addAttribute("item", info);
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_PROJECT_TRANSFERDETAIL);
	}
}
