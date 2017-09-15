package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcReleaseTransfer;
import com.jeecms.core.entity.MemberConfig;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.manager.JcFollowProjectMng;
import com.jeecms.core.manager.JcReleaseTransferMng;
import com.jeecms.core.manager.ProjectReleaseMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

@Controller
public class ProjectManagerAct {
	
	public static final String FRONT_PROJECT_RELEASE_PAGE = "tpl.projectReleasePage";
	public static final String FRONT_PROJECT_TRANSFER_PAGE = "tpl.projectTransferPage";
	public static final String FRONT_PROJECT_FOLLOW_PAGE = "tpl.projectFollowPage";
	public static final String FRONT_PROJECT_TRANSFERDETAIL = "tpl.projectTransferDetail";
	
	@Autowired
	private JcReleaseTransferMng jcReleaseTransferMng;
	
	@Autowired
	private JcFollowProjectMng jcFollowProjectMng;
	
	@Autowired
	private CmsDictionaryMng cmsDictionaryMng;
	
	@Autowired
    private ProjectReleaseMng projectReleaseMng;
	
	/**
	 * 项目管理tag项目发布转让
	 */
	@RequestMapping(value = "/projectManager/page.jspx")
	public String projectManagerPage(HttpServletRequest request, ModelMap model, 
			Integer pageNo, Integer pageSize,Integer tab){
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
		if (pageSize == null){
			pageSize = 3;
		}
		Pagination pagination = null;
		String frontPage = null;
		
		if (tab != null && tab == 2) {
			//项目发布转让
			pagination = jcReleaseTransferMng.getPage(user.getUsername(), null, null, 
					null, null, null, pageNo, pageSize);
			frontPage = FRONT_PROJECT_TRANSFER_PAGE;
		} else if (tab != null && tab == 3) {
			//我的关注
			pagination = jcFollowProjectMng.getPage(pageNo, pageSize, user.getId());
			frontPage = FRONT_PROJECT_FOLLOW_PAGE;
		} else {
			pagination = projectReleaseMng.getPage(user.getUsername(), null, null, 
					null, null, null, pageNo, pageSize);
			frontPage = FRONT_PROJECT_RELEASE_PAGE;
		}
		
		model.addAttribute("pagination",pagination);
		
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, frontPage);
	}
	
	/**
	 * 项目管理tag项目发布转让详细
	 */
	@RequestMapping(value = "/projectManager/managerTransferDetail.jspx", method = RequestMethod.GET)
	public String managerTransferDetail(HttpServletRequest request, ModelMap model, 
			Integer id){
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
		
		JcReleaseTransfer item = jcReleaseTransferMng.findById(id);
		model.addAttribute("item",item);
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_PROJECT_TRANSFERDETAIL);
	}
	
	/**
	 * 删除项目管理tab页项目发布信息
	 */
	@RequestMapping(value = "/projectManager/deleteReleaseInfo.jspx")
	public String deleteReleaseInfo(HttpServletRequest request, ModelMap model, 
			Integer[] ids,Integer pageNo,Integer pageSize){
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
		
		projectReleaseMng.deleteByIds(ids);
		
		if (pageSize == null){
			pageSize = 3;
		}
		Pagination pagination = projectReleaseMng.getPage(user.getUsername(), null, null, 
				null, null, null, pageNo, pageSize);
		model.addAttribute("pagination",pagination);
		
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_PROJECT_RELEASE_PAGE);
	}
	
	/**
	 * 删除项目管理tab页项目发布转让
	 */
	@RequestMapping(value = "/projectManager/deleteTransferInfo.jspx")
	public String deleteTransferInfo(HttpServletRequest request, ModelMap model, 
			Integer[] ids,Integer pageNo,Integer pageSize){
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
		
		jcReleaseTransferMng.deleteByIds(ids);
		
		if (pageSize == null){
			pageSize = 3;
		}
		Pagination pagination = jcReleaseTransferMng.getPage(user.getUsername(), null, null, 
				null, null, null, pageNo, pageSize);
		model.addAttribute("pagination",pagination);
		
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_PROJECT_TRANSFER_PAGE);
	}
	
	/**
	 * 删除项目管理tab页关注列表
	 */
	@RequestMapping(value = "/projectManager/deleteFollowProject.jspx")
	public String deleteFollowProject(HttpServletRequest request, ModelMap model, 
			Integer[] ids,Integer pageNo,Integer pageSize){
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
		
		jcFollowProjectMng.deleteByIds(ids);
		
		if (pageSize == null){
			pageSize = 3;
		}
		Pagination pagination = jcFollowProjectMng.getPage(pageNo, pageSize, user.getId());
		model.addAttribute("pagination",pagination);
		
		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_MEMBER, FRONT_PROJECT_FOLLOW_PAGE);
	}
}
