package com.jeecms.cms.action.admin.main;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.JcReleaseTransfer;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.manager.JcReleaseTransferMng;

@Controller
public class CmsReleaseTransferAct {
	
	@Autowired
	private JcReleaseTransferMng jcReleaseTransferMng;
	
	@Autowired
	private CmsDictionaryMng cmsDictionaryMng;
	
	@RequiresPermissions("release_transfer:v_list")
	@RequestMapping(value = "/release_transfer/v_list.do")
	public String releaseTransferPage(String queryUserName, Integer queryStage,
			Integer queryIndustry,Integer queryCheckStatus,Date startTime,
			Date endTime,Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model){
		if (pageSize == null || pageSize == 0) {
			pageSize = CookieUtils.getPageSize(request);
		}
		Pagination pagination = jcReleaseTransferMng.getPage(queryUserName,
				queryStage, queryIndustry, queryCheckStatus, startTime,
				endTime, pageNo, pageSize);
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("pageNo",pageSize);
		model.addAttribute("queryUserName",queryUserName);
		model.addAttribute("queryStage",queryStage);
		model.addAttribute("queryIndustry",queryIndustry);
		model.addAttribute("queryCheckStatus",queryCheckStatus);
		model.addAttribute("startTime",startTime);
		model.addAttribute("endTime",endTime);
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		return "release_transfer/list";
	}
	
	@RequiresPermissions("release_transfer:o_edit")
	@RequestMapping(value = "/release_transfer/o_edit_checkStatus.do")
	public void editCheckStatus(Integer id,Integer checkStatus,String reason,
			String title,HttpServletRequest request, ModelMap model){
		if (id >0 && checkStatus >0) {
			jcReleaseTransferMng.updateCheckStatus(id, checkStatus, reason ,title);
		}
	}
	
	@RequiresPermissions("release_transfer:o_edit")
	@RequestMapping(value = "/release_transfer/o_edit_showStatus.do")
	public String editShowStatus(Integer id, Integer showStatus,String queryUserName, Integer queryStage,
			Integer queryIndustry,Integer queryCheckStatus,Date startTime,
			Date endTime,Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model){
		if (id >0) {
			jcReleaseTransferMng.updateShowStatus(id, showStatus);
		}
		return releaseTransferPage(queryUserName, 
				queryStage, queryIndustry, queryCheckStatus, 
				startTime, endTime, pageNo, pageSize, request, model);
	}
	
	@RequiresPermissions("release_transfer:o_detail")
	@RequestMapping(value = "/release_transfer/o_detail.do")
	public String detail(Integer id, ModelMap model){
		JcReleaseTransfer item = jcReleaseTransferMng.findById(id);
		model.addAttribute("item", item);
		model.addAttribute("investFiledList",cmsDictionaryMng.getList("investFiled"));
		model.addAttribute("investStageList",cmsDictionaryMng.getList("investStage"));
		return "release_transfer/detail";
	}
	
	@RequiresPermissions("release_transfer:o_delete")
	@RequestMapping(value = "/release_transfer/o_delete.do")
	public String delete(Integer[] ids,String queryUserName, Integer queryStage,
			Integer queryIndustry,Integer queryCheckStatus,Date startTime,
			Date endTime,Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model){
		if (ids!= null && ids.length >0) {
			jcReleaseTransferMng.deleteByIds(ids);
		}
		return releaseTransferPage(queryUserName, queryStage,
				queryIndustry, queryCheckStatus, startTime, 
				endTime, pageNo, pageSize, request, model);
	}
	
}
