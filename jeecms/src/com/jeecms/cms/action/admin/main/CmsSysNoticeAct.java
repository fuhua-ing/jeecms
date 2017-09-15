package com.jeecms.cms.action.admin.main;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcSysNotice;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.manager.JcSysNoticeMng;
import com.jeecms.core.manager.JcUserNoticeMng;
import com.jeecms.core.web.util.CmsUtils;

@Controller
public class CmsSysNoticeAct {
	
	@Autowired
	private JcSysNoticeMng jcSysNoticeMng;
	
	@Autowired
	private CmsDictionaryMng cmsDictionaryMng;
	
	@Autowired
	private JcUserNoticeMng jcUserNoticeMng;
	
	@RequiresPermissions("sys_notice:v_list")
	@RequestMapping(value = "/sys_notice/v_list.do")
	public String sysNoticePage(String queryTitle,Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model){
		if (pageSize == null || pageSize == 0) {
			pageSize = CookieUtils.getPageSize(request);
		}
		Pagination pagination = jcSysNoticeMng.getPage(queryTitle,pageNo, pageSize);
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("pageNo",pageSize);
		model.addAttribute("queryTitle",queryTitle);
		model.addAttribute("userTypeList",cmsDictionaryMng.getList("recvNoticeUserType"));
		return "sys_notice/list";
	}
	
	@RequiresPermissions("sys_notice:o_edit")
	@RequestMapping(value = "/sys_notice/o_edit.do")
	public String edit(JcSysNotice entity,Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model){
		if (entity != null && entity.getId() != null) {
			jcSysNoticeMng.updateByUpdater(entity);
		}
		return sysNoticePage(null, pageNo, pageSize, request, model);
	}
	
	@RequiresPermissions("sys_notice:o_detail")
	@RequestMapping(value = "/sys_notice/o_detail.do")
	public String detail(Integer id, ModelMap model){
		if(id != null && id>0) {
			JcSysNotice item = jcSysNoticeMng.findById(id);
			model.addAttribute("item", item);
		}
		model.addAttribute("userTypeList",cmsDictionaryMng.getList("recvNoticeUserType"));
		return "sys_notice/detail";
	}
	
	@RequiresPermissions("sys_notice:o_delete")
	@RequestMapping(value = "/sys_notice/o_delete.do")
	public String delete(Integer[] ids,String title, Integer pageNo,Integer pageSize, 
			HttpServletRequest request, ModelMap model){
		if (ids!= null && ids.length >0) {
			jcSysNoticeMng.deleteByIds(ids);
		}
		return sysNoticePage(title, pageNo, pageSize, request, model);
	}
	
	@RequiresPermissions("sys_notice:o_send")
	@RequestMapping(value = "/sys_notice/o_send.do")
	public String sendMessage(Integer id,String[] userType,String title, String content, 
			HttpServletRequest request, ModelMap model){
		CmsUser user = CmsUtils.getUser(request);
		jcSysNoticeMng.save(id,userType, title, content,user);
		jcUserNoticeMng.recvSysNotice(userType, title, content);
		return sysNoticePage(null, 1, null, request, model);
	}
	
}
