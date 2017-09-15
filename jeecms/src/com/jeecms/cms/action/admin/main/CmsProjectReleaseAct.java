package com.jeecms.cms.action.admin.main;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.ProjectRelease;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.manager.ProjectReleaseMng;

@Controller
public class CmsProjectReleaseAct {
	
	@Autowired
	private ProjectReleaseMng projectReleaseMng;
	@Autowired
	private CmsDictionaryMng cmsDictionaryMng;
	
	@RequiresPermissions("project_release:v_list")
	@RequestMapping(value = "/project_release/v_list.do", method = RequestMethod.GET)
	public String projectReleasePage(String queryUserName, Integer queryStage,
			Integer queryIndustry,Integer queryCheckStatus,Date startTime,
			Date endTime,Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model){
		if (pageSize == null || pageSize == 0) {
			pageSize = CookieUtils.getPageSize(request);
		}
		Pagination pagination = projectReleaseMng.getPage(queryUserName,
				queryStage, queryIndustry, queryCheckStatus, startTime,
				endTime, pageNo, pageSize);
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("queryUserName",queryUserName);
		model.addAttribute("queryStage",queryStage);
		model.addAttribute("queryIndustry",queryIndustry);
		model.addAttribute("queryCheckStatus",queryCheckStatus);
		model.addAttribute("startTime",startTime);
		model.addAttribute("endTime",endTime);
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		return "project_release/list";
	}
	
	@RequiresPermissions("project_release:o_edit")
	@RequestMapping(value = "/project_release/o_edit_checkStatus.do")
	public void editCheckStatus(Integer id,Integer checkStatus,String reason,
			HttpServletRequest request, ModelMap model){
		if (id >0 && checkStatus >0) {
			projectReleaseMng.updateCheckStatus(id, checkStatus, reason);
		}
	}
	
	
	@RequiresPermissions("project_release:o_edit")
	@RequestMapping(value = "/project_release/o_edit_showStatus.do", method = RequestMethod.GET)
	public String editShowStatus(Integer id, Integer showStatus,String queryUserName, Integer queryStage,
			Integer queryIndustry,Integer queryCheckStatus,Date startTime,
			Date endTime,Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model){
		if (id >0) {
			projectReleaseMng.updateShowStatus(id, showStatus);
		}
		return projectReleasePage(queryUserName, 
				queryStage, queryIndustry, queryCheckStatus, 
				startTime, endTime, pageNo, pageSize, request, model);
	}
	
	@RequiresPermissions("project_release:o_detail")
	@RequestMapping(value = "/project_release/o_detail.do", method = RequestMethod.GET)
	public String detail(Integer id, ModelMap model){
		ProjectRelease item = projectReleaseMng.findById(id);
		model.addAttribute("item", item);
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		model.addAttribute("investAccountExitList",cmsDictionaryMng.getList("investAccountExit"));
		return "project_release/detail";
	}
	
	@RequiresPermissions("project_release:o_delete")
	@RequestMapping(value = "/project_release/o_delete.do")
	public String delete(Integer[] ids,String queryUserName, Integer queryStage,
			Integer queryIndustry,Integer queryCheckStatus,Date startTime,
			Date endTime,Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model){
		if (ids!= null && ids.length >0) {
			projectReleaseMng.deleteByIds(ids);
		}
		return projectReleasePage(queryUserName, queryStage,
				queryIndustry, queryCheckStatus, startTime, 
				endTime, pageNo, pageSize, request, model);
	}
	
}
