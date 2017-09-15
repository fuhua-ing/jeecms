package com.jeecms.cms.action.member;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.dao.main.ContentDao;
import com.jeecms.cms.entity.main.ActivitiesEnroll;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.manager.main.ActivitiesEnrollMng;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.manager.CmsDictionaryMng;
import com.jeecms.core.web.util.CmsUtils;

import net.sf.json.JSONObject;

@Controller
public class ActivitiesEnrollAct {

	private static final Logger log = LoggerFactory
			.getLogger(ActivitiesEnrollAct.class);
	
	private static final String TPL_PROJECTRELEASE = "tpl.projectRelease";
	private static final String FRONT_RELEASEAUTH_AMONG = "tpl.project_release_among";
	private static final String FRONT_RELEASEAUTH_SUCCESS = "tpl.project_release_success";
	private static final String FRONT_RELEASEAUTH_FAIL = "tpl.project_release_fail";
	
	@RequestMapping(value = "/activities/enroll.jspx",method=RequestMethod.POST)
	public void input(String contentId,String username,String userphone,HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);
	    PrintWriter out = null;  
	    try {
			out = response.getWriter();
		} catch (IOException e1) {
			log.error("System Error:{}",e1);
		}  
		if (contentId == null || contentId == "") {
			 out.write(Boolean.toString(false)); 
	        return;
		}
		Integer content_id = null;
		try {
			contentId = contentId.replace("/", "");
			content_id = Integer.valueOf(contentId);
		} catch (Exception e) {
			log.error("System Error:{}",e);
		}
		Content con = contentDao.findById(content_id);
		ActivitiesEnroll activitiesEnroll = null;
		if (con != null) {
			activitiesEnroll = new ActivitiesEnroll();
			activitiesEnroll.setEnrollName(username);
			activitiesEnroll.setEnrollPhone(userphone);
			activitiesEnroll.setContent(con);
			activitiesEnroll.setEnrollTime(new Date());
		}else{
			 out.write(Boolean.toString(false)); 
	        return;
		}
		activitiesEnrollMng.save(activitiesEnroll, content_id);
		out.write(Boolean.toString(true)); 
		out.flush();
		out.close();
        return;
	}
	
	@RequestMapping(value = "/activities/check_user_phone.jspx",method=RequestMethod.POST)
	public boolean submit(String userphone,String contentId,HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);
		if (contentId == null || contentId == "") {
			return false;
		}
		if (userphone == null || userphone == "") {
			return false;
		}
		Integer content_id = null;
		try {
			contentId = contentId.replace("/", "");
			content_id = Integer.valueOf(contentId);
		} catch (Exception e) {
			log.error("System Error:{}",e);
		}
		ActivitiesEnroll activitiesEnroll = activitiesEnrollMng.findByUserPhone(content_id, userphone);
		PrintWriter out = null;
		try {
			 out = response.getWriter();
			 if (activitiesEnroll == null) {
				 out.write(Boolean.toString(true)); 
				 return true;
			}else {
				out.write(Boolean.toString(false)); 
				return false;
			}
		} catch (IOException e) {
			log.error("{}",e);
		}finally{
			out.flush();
			out.close();
		}
		return false;
	}
	
	@Autowired
	private ActivitiesEnrollMng activitiesEnrollMng;
	@Autowired
	private ContentDao contentDao;
}
