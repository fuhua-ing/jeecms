package com.jeecms.cms.action.front;

import static com.jeecms.cms.Constants.TPLDIR_SPECIAL;

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
import com.jeecms.core.entity.InvestorQualification;
import com.jeecms.core.entity.JcFollowProject;
import com.jeecms.core.entity.JcReleaseTransfer;
import com.jeecms.core.entity.ProjectRelease;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.manager.CmsUserExtMng;
import com.jeecms.core.manager.InvestorQualificationMng;
import com.jeecms.core.manager.JcFollowProjectMng;
import com.jeecms.core.manager.JcReleaseTransferMng;
import com.jeecms.core.manager.ProjectReleaseMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

@Controller
public class InteractionAct {
    public static final String INTERACTION_INDEX = "tpl.interactionIndex";
    public static final String INTERACTION_FRAME1 = "tpl.interactionFrame1";
    public static final String INTERACTION_FRAME2 = "tpl.interactionFrame2";
    public static final String INTERACTION_FRAME3 = "tpl.interactionFrame3";
    public static final String INTERACTION_FRAME1_DETAIL = "tpl.interactionFrame1Detail";
    public static final String INTERACTION_FRAME2_DETAIL = "tpl.interactionFrame2Detail";
    public static final String INTERACTION_FRAME3_DETAIL = "tpl.interactionFrame3Detail";
    
    @Autowired
	private JcReleaseTransferMng jcReleaseTransferMng;
    
    @Autowired
    private InvestorQualificationMng investorQualificationMng;
    
    @Autowired
    private CmsUserExtMng cmsUserExtMng;
    
    @Autowired
	private CmsDictionaryMng cmsDictionaryMng;
    
    @Autowired
    private JcFollowProjectMng jcFollowProjectMng;
    
    @Autowired
    private ProjectReleaseMng projectReleaseMng;
    
    /** 
     * 融资信息互动平台首页
     */
    @RequestMapping(value = "/interaction.jspx", method = RequestMethod.GET)
    public String interactionIndex(HttpServletRequest request,
            HttpServletResponse response, ModelMap model, String searchMoney, 
            String searchTerm, String searchPosition) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        return FrontUtils.getTplPath(request, site.getSolutionPath(),
                TPLDIR_SPECIAL, INTERACTION_INDEX);
    }
    
    /** 
     * 融资信息互动平台首页
     * frame1发布项目
     * frame2投资人
     * frame3退出渠道(项目发布转让)
     */
    @RequestMapping(value = "/interactionFrame1.jspx", method = RequestMethod.GET)
    public String interactionFrame(HttpServletRequest request,
            HttpServletResponse response, ModelMap model, String searchMoney, 
            String searchTerm, String searchPosition,String searchName, Integer pageNo) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        
        Pagination pagination = projectReleaseMng.getInteractionPage(searchMoney, searchTerm,
				searchPosition, searchName, pageNo, 3);
		model.addAttribute("pagination",pagination);
		model.addAttribute("searchMoney", searchMoney);
		model.addAttribute("searchTerm", searchTerm);
		model.addAttribute("searchPosition", searchPosition);
		model.addAttribute("searchName", searchName);
   
        return FrontUtils.getTplPath(request, site.getSolutionPath(),
                TPLDIR_SPECIAL, INTERACTION_FRAME1);
    }
    
    @RequestMapping(value = "/interactionFrame1/detail.jspx", method = RequestMethod.GET)
    public String interactionFrame1Detail(HttpServletRequest request,
            HttpServletResponse response, ModelMap model,Integer id) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        
        CmsUser user = (CmsUser)model.get("user");
		if (user != null) {
			if (jcFollowProjectMng.findById(user.getId(), id, 1) != null) {
				model.addAttribute("hasFollow", 1);
			}
		}
        
		ProjectRelease projectRelease = projectReleaseMng.findById(id);
        
		model.addAttribute("item",projectRelease);
		if (projectRelease != null && projectRelease.getCmsUser()!=null) {
			model.addAttribute("userInfo",cmsUserExtMng.findById(projectRelease.getCmsUser().getId()));
		}
		
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
   
        return FrontUtils.getTplPath(request, site.getSolutionPath(),
                TPLDIR_SPECIAL, INTERACTION_FRAME1_DETAIL);
    }
    
    /**
     * frame2投资人
     */
    @RequestMapping(value = "/interactionFrame2.jspx", method = RequestMethod.GET)
    public String interactionFrame2(HttpServletRequest request,
            ModelMap model, String searchName, String searchIndustry,Integer pageNo) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        
        Pagination pagination = investorQualificationMng.getInteractionPage(searchName,searchIndustry,(byte)2, pageNo, 3);
		model.addAttribute("pagination",pagination);
		model.addAttribute("searchIndustry", searchIndustry);
		model.addAttribute("searchName", searchName);
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
   
        return FrontUtils.getTplPath(request, site.getSolutionPath(),
                TPLDIR_SPECIAL, INTERACTION_FRAME2);
    }
    
    @RequestMapping(value = "/interactionFrame2/detail.jspx", method = RequestMethod.GET)
    public String interactionFrame2Detail(HttpServletRequest request,
            HttpServletResponse response, ModelMap model,Integer id) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        
        InvestorQualification investorQualification = investorQualificationMng.findById(id);
        
		model.addAttribute("item",investorQualification);
		if (investorQualification != null && investorQualification.getCmsUser()!=null){
			model.addAttribute("userInfo",cmsUserExtMng.findById(investorQualification.getCmsUser().getId()));	
		}
		
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
   
        return FrontUtils.getTplPath(request, site.getSolutionPath(),
                TPLDIR_SPECIAL, INTERACTION_FRAME2_DETAIL);
    }
    
    
    /**
     * frame3退出渠道(项目发布转让)
     */
    @RequestMapping(value = "/interactionFrame3.jspx", method = RequestMethod.GET)
    public String interactionFrame3(HttpServletRequest request,
            HttpServletResponse response, ModelMap model, String searchMoney, 
            String searchRadio, String searchPosition, String searchName, Integer pageNo) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        
        Pagination pagination = jcReleaseTransferMng.getInteractionPage(searchMoney, searchRadio,
				searchPosition, searchName, pageNo, 3);
		model.addAttribute("pagination",pagination);
		model.addAttribute("searchMoney", searchMoney);
		model.addAttribute("searchRadio", searchRadio);
		model.addAttribute("searchPosition", searchPosition);
		model.addAttribute("searchName", searchName);
   
        return FrontUtils.getTplPath(request, site.getSolutionPath(),
                TPLDIR_SPECIAL, INTERACTION_FRAME3);
    }
    
    @RequestMapping(value = "/interactionFrame3/detail.jspx", method = RequestMethod.GET)
    public String interactionFrame3Detail(HttpServletRequest request,
            HttpServletResponse response, ModelMap model,Integer id) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        
        CmsUser user = (CmsUser)model.get("user");
		if (user != null) {
			if (jcFollowProjectMng.findById(user.getId(), id, 2) != null) {
				model.addAttribute("hasFollow", 1);
			}
		}
        
        JcReleaseTransfer jcReleaseTransfer = jcReleaseTransferMng.findById(id);
        
		model.addAttribute("item",jcReleaseTransfer);
		if (jcReleaseTransfer != null && jcReleaseTransfer.getCmsUser()!=null) {
			model.addAttribute("userInfo",cmsUserExtMng.findById(jcReleaseTransfer.getCmsUser().getId()));
		}
		
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
   
        return FrontUtils.getTplPath(request, site.getSolutionPath(),
                TPLDIR_SPECIAL, INTERACTION_FRAME3_DETAIL);
    }
    
    @RequestMapping(value = "/interactionFrame/follow.jspx", method = RequestMethod.GET)
    public String follow(HttpServletRequest request, ModelMap model,Integer id,Integer tag,Integer follow) {
        CmsSite site = CmsUtils.getSite(request);
        
        FrontUtils.frontData(request, model, site);
        CmsUser user = (CmsUser)model.get("user");
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
        FrontUtils.frontPageData(request, model);
        
        if (id != null && id>0 && tag != null) {
        	model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
    		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
        	if (tag == 1) {
        		ProjectRelease projectRelease = projectReleaseMng.findById(id);
        		if (projectRelease != null) {
        			JcFollowProject jcFollowProject = jcFollowProjectMng.findById(user.getId(), id, tag);
            		if (jcFollowProject == null && follow == 1) {
            			jcFollowProjectMng.save(user, id, tag);
            			model.addAttribute("hasFollow", 1);
            		} else if (jcFollowProject !=null && follow == 2) {
            			jcFollowProjectMng.delete(jcFollowProject);
            		}
            		model.addAttribute("item",projectRelease);
            		model.addAttribute("userInfo",cmsUserExtMng.findById(projectRelease.getCmsUser().getId()));
        		}
        		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_SPECIAL, INTERACTION_FRAME3_DETAIL);
        	}
        	if (tag == 2) {
        		JcReleaseTransfer jcReleaseTransfer = jcReleaseTransferMng.findById(id);
        		if (jcReleaseTransfer != null) {
        			JcFollowProject jcFollowProject = jcFollowProjectMng.findById(user.getId(), id, tag);
            		if (jcFollowProject == null && follow == 1) {
            			jcFollowProjectMng.save(user, id, tag);
            			model.addAttribute("hasFollow", 1);
            		} else if (jcFollowProject !=null && follow == 2) {
            			jcFollowProjectMng.delete(jcFollowProject);
            		}
            		model.addAttribute("item",jcReleaseTransfer);
            		model.addAttribute("userInfo",cmsUserExtMng.findById(jcReleaseTransfer.getCmsUser().getId()));
        		}
        		return FrontUtils.getTplPath(request, site.getSolutionPath(), TPLDIR_SPECIAL, INTERACTION_FRAME3_DETAIL);
        	}
        }
		
        return "";
    }
}
