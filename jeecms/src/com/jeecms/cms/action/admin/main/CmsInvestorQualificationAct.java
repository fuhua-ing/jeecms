package com.jeecms.cms.action.admin.main;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.IdentityEnum;
import com.jeecms.cms.manager.assist.ManagerMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.InvestorQualification;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.manager.InvestorQualificationMng;

@Controller
public class CmsInvestorQualificationAct {
	
	@Autowired
	private InvestorQualificationMng personalCenterMng;
	
	@Autowired
	private CmsDictionaryMng cmsDictionaryMng;
	
	@Autowired
	private ManagerMng managerMng;
	
	@RequiresPermissions("investor:v_list")
	@RequestMapping(value = "/investor/v_list.do")
	public String list(String queryUserName, String queryRealName,
			Date startTime,Date endTime,Integer pageNo, Integer checkStatus,
			HttpServletRequest request, ModelMap model){
		Pagination pagination = personalCenterMng.getPage(
				queryUserName, queryRealName, startTime, endTime, 
				checkStatus, pageNo, CookieUtils.getPageSize(request));
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("queryUserName",queryUserName);
		model.addAttribute("queryRealName",queryRealName);
		model.addAttribute("checkStatus",checkStatus);
		model.addAttribute("startTime",startTime);
		model.addAttribute("endTime",endTime);
		return "investor/list";
	}
	
	@RequiresPermissions("investor:o_edit")
	@RequestMapping(value = "/investor/o_edit.do")
	public void checkInvestorQualification(Integer id,Integer status,String reason,
			HttpServletRequest request, ModelMap model){
		if (id >0 && status >0){
			InvestorQualification entity = personalCenterMng.checkInvestorQualification(id, status, reason);
			if (status !=null && status == 2) {
				managerMng.addIdentityById(entity.getCmsUser().getId(),IdentityEnum.Investor);
			}
		}
	}
	
	@RequiresPermissions("investor:o_detail")
	@RequestMapping(value = "/investor/o_detail.do")
	public String investorQualificationDetail(Integer id, ModelMap model){
		InvestorQualification item = personalCenterMng.findById(id);
		model.addAttribute("item", item);
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		return "investor/detail";
	}
	
	@RequiresPermissions("investor:o_delete")
	@RequestMapping(value = "/investor/o_delete.do")
	public String delete(Integer[] ids, String queryUserName, String queryRealName,
			Date startTime,Date endTime,Integer pageNo, Integer checkStatus,
			HttpServletRequest request, ModelMap model){
		if (ids!= null && ids.length >0) {
			personalCenterMng.deleteByIds(ids);
		}
		return list(queryUserName, queryRealName, startTime,
				endTime, pageNo, checkStatus, request, model);
	}
}
