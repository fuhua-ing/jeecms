package com.jeecms.cms.action.admin.main;



import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.manager.main.ActivitiesEnrollMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;

@Controller
public class CmsActivitiesEnrollAct {
	
	@Autowired
    private ActivitiesEnrollMng activitiesEnrollMng;
	
	@RequiresPermissions("enroll:v_list")
	@RequestMapping(value = "/enroll/v_list.do", method = RequestMethod.GET)
	public String activitiesPage(String queryTitle, Integer queryEnrollStatus,
			Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model){
		if (pageSize == null || pageSize == 0) {
			pageSize = CookieUtils.getPageSize(request);
		}
		if (queryTitle != null && queryTitle != "") {
			try {
				queryTitle = new String(queryTitle.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		Pagination pagination = activitiesEnrollMng.getPage(queryTitle,
				queryEnrollStatus, pageNo, pageSize);
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("queryTitle",queryTitle);
		model.addAttribute("queryEnrollStatus",queryEnrollStatus);
		return "enroll/list";
	}
	
	@RequiresPermissions("enroll:o_edit")
	@RequestMapping(value = "/enroll/o_enroll_detail.do",method = RequestMethod.GET)
	public String enrollDetail(Integer id, Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model){
		if (pageSize == null || pageSize == 0) {
			pageSize = CookieUtils.getPageSize(request);
		}
		Pagination pagination = activitiesEnrollMng.getPersonPage(id, pageNo, pageSize);
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("pageSize",pageSize);
		return "enroll/enroll_detail";
	}
	
//	@RequiresPermissions("project_release:o_edit")
//	@RequestMapping(value = "/project_release/o_edit_checkStatus.do")
//	public void editCheckStatus(Integer id,Integer checkStatus,String reason,
//			HttpServletRequest request, ModelMap model){
//		if (id >0 && checkStatus >0) {
//			projectReleaseMng.updateCheckStatus(id, checkStatus, reason);
//		}
//	}
//	
//	
//	@RequiresPermissions("project_release:o_edit")
//	@RequestMapping(value = "/project_release/o_edit_showStatus.do", method = RequestMethod.GET)
//	public String editShowStatus(Integer id, Integer showStatus,String queryUserName, Integer queryStage,
//			Integer queryIndustry,Integer queryCheckStatus,Date startTime,
//			Date endTime,Integer pageNo,Integer pageSize, 
//			HttpServletRequest request, ModelMap model){
//		if (id >0) {
//			projectReleaseMng.updateShowStatus(id, showStatus);
//		}
//		return projectReleasePage(queryUserName, 
//				queryStage, queryIndustry, queryCheckStatus, 
//				startTime, endTime, pageNo, pageSize, request, model);
//	}
//	
//	@RequiresPermissions("project_release:o_detail")
//	@RequestMapping(value = "/project_release/o_detail.do", method = RequestMethod.GET)
//	public String detail(Integer id, ModelMap model){
//		ProjectRelease item = projectReleaseMng.findById(id);
//		model.addAttribute("item", item);
//		return "project_release/detail";
//	}
//	
//	@RequiresPermissions("project_release:o_delete")
//	@RequestMapping(value = "/project_release/o_delete.do")
//	public String delete(Integer[] ids,String queryUserName, Integer queryStage,
//			Integer queryIndustry,Integer queryCheckStatus,Date startTime,
//			Date endTime,Integer pageNo,Integer pageSize, 
//			HttpServletRequest request, ModelMap model){
//		if (ids!= null && ids.length >0) {
//			projectReleaseMng.deleteByIds(ids);
//		}
//		return projectReleasePage(queryUserName, queryStage,
//				queryIndustry, queryCheckStatus, startTime, 
//				endTime, pageNo, pageSize, request, model);
//	}
//	
}
