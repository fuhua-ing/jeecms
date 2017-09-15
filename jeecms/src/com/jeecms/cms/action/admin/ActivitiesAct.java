package com.jeecms.cms.action.admin;

import static com.jeecms.common.page.SimplePage.cpn;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.manager.assist.ManagerMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.JcCorp;
import com.jeecms.core.entity.JcCorpShareholder;

@Controller
public class ActivitiesAct
{
	@RequiresPermissions("activities:activities_main")
	@RequestMapping("/activities/activities_main.do")
	public String manager(HttpServletRequest request, ModelMap model)
	{
		return "activities/activities_main";
	}

	@RequiresPermissions("activities:activities_left")
	@RequestMapping("/activities/activities_left.do")
	public String managerLeft(HttpServletRequest request, ModelMap model)
	{
		return "activities/activities_left";
	}

//	@RequiresPermissions("manager:manager_membercheck_out")
//	@RequestMapping("/manager/manager_membercheck_out.do")
//	public String membercheckOuter(String applyUsername, String corpName, Integer audit, Date timeBegin, Date timeEnd,
//			Integer pageNo, HttpServletRequest request, ModelMap model)
//	{
//
//		Pagination pagination = manager.getPageOut(applyUsername, corpName, audit, timeBegin, timeEnd, cpn(pageNo),
//				CookieUtils.getPageSize(request));
//		model.addAttribute("pagination", pagination);
//		return "manager/manager_membercheck_out";
//	}
//
//	@RequiresPermissions("manager:manager_membercheck")
//	@RequestMapping("/manager/manager_membercheck.do")
//	public String membercheck(String applyUsername, String corpName, Integer audit, Date timeBegin, Date timeEnd,
//			Integer pageNo, HttpServletRequest request, ModelMap model)
//	{
//
//		Pagination pagination = manager.getPage(applyUsername, corpName, audit, timeBegin, timeEnd, cpn(pageNo),
//				CookieUtils.getPageSize(request));
//		model.addAttribute("pagination", pagination);
//		return "manager/manager_membercheck";
//	}
//
//	@RequiresPermissions("manager:manager_membercheck_search")
//	@RequestMapping(value = "/manager/manager_membercheck_search.do", method = RequestMethod.POST)
//	public String membercheckSearch(String applyUsername, String corpName, Integer audit, String timeBegin,
//			String timeEnd, HttpServletRequest request, ModelMap model)
//	{
//		Date parseDayStrToDate = DateUtils.parseDayStrToDate(timeBegin);
//		Date parseDayStrToDate2 = DateUtils.parseDayStrToDate(timeEnd);
//		Pagination pagination = manager.getPage(applyUsername, corpName, audit, parseDayStrToDate, parseDayStrToDate2,
//				cpn(null), CookieUtils.getPageSize(request));
//		model.addAttribute("pagination", pagination);
//		return "manager/manager_membercheck";
//	}
//
//	@RequiresPermissions("manager:manager_go_check")
//	@RequestMapping("/manager/manager_go_check.do")
//	public String goCheck(Integer ids, HttpServletRequest request, ModelMap model)
//	{
//
//		JcCorp jcCorp = manager.findById(ids);
//		List<JcCorpShareholder> jcCorpShareholders = manager.findShareById(ids);
//		model.addAttribute("jcCorp", jcCorp);
//		model.addAttribute("list", jcCorpShareholders);
//		return "manager/manager_go_check";
//	}
//
//	@RequiresPermissions("manager:manager_checkpass")
//	@RequestMapping(value = "/manager/manager_checkpass.do", method = RequestMethod.POST)
//	public @ResponseBody
//	String passCheck(Integer id, Boolean pass, HttpServletRequest request)
//	{
//		if (null == pass)
//		{
//			return "false";
//		}
//		try
//		{
//			HttpSession session = request.getSession();
//			SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session
//					.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
//
//			Object primaryPrincipal = simplePrincipalCollection.getPrimaryPrincipal();
//
//			String username = primaryPrincipal.toString();
//			manager.passCheck(username, id, pass);
//		}
//		catch (Exception e)
//		{
//			return "false";
//		}
//		return "ok";
//	}
//
//	@RequiresPermissions("manager:manager_go_view")
//	@RequestMapping("/manager/manager_go_view.do")
//	public String goView(Integer ids, HttpServletRequest request, ModelMap model)
//	{
//
//		JcCorp jcCorp = manager.findById(ids);
//		List<JcCorpShareholder> jcCorpShareholders = manager.findShareById(ids);
//		model.addAttribute("jcCorp", jcCorp);
//		model.addAttribute("list", jcCorpShareholders);
//		return "manager/manager_go_view";
//	}
//
//	@Autowired
//	private ManagerMng manager;
}
