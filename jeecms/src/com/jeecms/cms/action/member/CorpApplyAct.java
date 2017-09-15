package com.jeecms.cms.action.member;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.cms.Constants;
import com.jeecms.common.image.ImageUtils;
import com.jeecms.common.upload.FileRepository;
import com.jeecms.core.entity.CmsConfigItem;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.JcCorp;
import com.jeecms.core.entity.JcCorpShareholder;
import com.jeecms.core.entity.MemberConfig;
import com.jeecms.core.manager.CmsConfigItemMng;
import com.jeecms.core.manager.CorpApplyService;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

@Controller
public class CorpApplyAct
{
	private static final String CROPAPPLAY = "tpl.applyPage";

	private static final String USER_IMG_PATH = "/company/images";

	private static final String COOPERATION = "tpl.cooperation";

	@Autowired
	private CmsConfigItemMng cmsConfigItemMng;

	@Autowired
	private CorpApplyService corpApplyService;

	@Autowired
	private FileRepository fileRepository;

	@RequestMapping(value = "/company/upload_file.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String uploadCertification(@RequestParam(value = "uploadfile") MultipartFile[] files,
			HttpServletRequest request, ModelMap model)
	{
		try
		{
			StringBuffer builder = new StringBuffer();
			CmsSite site = CmsUtils.getSite(request);
			site.getConfig().getMemberConfig();
			for (MultipartFile multipartFile : files)
			{
				String ext = validatoer(multipartFile);
				if (ext.contains("error"))
				{
					return ext;
				}
				String fileUrl = fileRepository.storeByExt(USER_IMG_PATH, ext, getTempFile(multipartFile));
				builder.append(fileUrl).append(",");
			}
			String string = builder.toString();
			if (string.endsWith(","))
			{
				return string;
			}
			return "error";
		}
		catch (IllegalStateException e)
		{
			return "error1";
		}
		catch (IOException e)
		{
			return "error2";
		}
	}

	private File getTempFile(MultipartFile multipartFile) throws IllegalStateException, IOException
	{
		String path = System.getProperty("java.io.tmpdir");
		File tempFile = new File(path, String.valueOf(System.currentTimeMillis()));
		multipartFile.transferTo(tempFile);
		return tempFile;
	}

	private String validatoer(MultipartFile multipartFile)
	{
		String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		if (!ImageUtils.isValidImageExt(ext))
		{
			return "error";
		}
		return ext;
	}

	@RequestMapping(value = "/company/corp_apply.jspx", method = RequestMethod.GET)
	public String corpApplyPage(HttpServletRequest request, HttpServletResponse response, ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);

		HttpSession session = request.getSession();
		SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session
				.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

		Object primaryPrincipal = simplePrincipalCollection.getPrimaryPrincipal();

		String username = primaryPrincipal.toString();

		JcCorp queryCorpInnerByUsername = corpApplyService.queryCorpInnerByUsername(username);
		if (null != queryCorpInnerByUsername)
		{
			model.addAttribute("errMsg", "您已经申请过入驻企业，请耐心等待审核");
			return FrontUtils.newShowError(request, response, model);
		}

		List<CmsConfigItem> items = cmsConfigItemMng.getList(site.getConfig().getId(), CmsConfigItem.CATEGORY_REGISTER);
		FrontUtils.frontData(request, model, site);
		model.addAttribute("items", items);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_COMPANY, CROPAPPLAY);
	}

	@RequestMapping(value = "/company/corp_apply.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String corpApplyData(JcCorp corpInfo, String jcCorpShareholderDto, HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
	{
		try
		{
			List<JcCorpShareholder> list = parseList(jcCorpShareholderDto);

			HttpSession session = request.getSession();
			SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session
					.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

			Object primaryPrincipal = simplePrincipalCollection.getPrimaryPrincipal();

			String username = primaryPrincipal.toString();

			corpApplyService.saveInfo(username, corpInfo, list);
		}
		catch (Exception e)
		{
			return "false";
		}

		return "ok";
	}

	@RequestMapping(value = "/company/apply_cooperation.jspx", method = RequestMethod.GET)
	public String applyCooperation(JcCorp corpInfo, HttpServletRequest request, HttpServletResponse response,
			ModelMap model)
	{
		CmsSite site = CmsUtils.getSite(request);
		MemberConfig mcfg = site.getConfig().getMemberConfig();

		HttpSession session = request.getSession();
		SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session
				.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

		Object primaryPrincipal = simplePrincipalCollection.getPrimaryPrincipal();

		String username = primaryPrincipal.toString();

		JcCorp queryCorpInnerByUsername = corpApplyService.queryCorpOutByUsername(username);
		if (null != queryCorpInnerByUsername)
		{
			model.addAttribute("errMsg", "您已经申请过外部合作机构，请耐心等待审核");
			return FrontUtils.newShowError(request, response, model);
		}

		List<CmsConfigItem> items = cmsConfigItemMng.getList(site.getConfig().getId(), CmsConfigItem.CATEGORY_REGISTER);
		FrontUtils.frontData(request, model, site);
		model.addAttribute("mcfg", mcfg);
		model.addAttribute("items", items);
		return FrontUtils.getTplPath(request, site.getSolutionPath(), Constants.TPLDIR_COMPANY, COOPERATION);
	}

	@RequestMapping(value = "/company/apply_cooperation.jspx", method = RequestMethod.POST)
	@ResponseBody
	public String applyCooperationPost(JcCorp corpInfo, HttpServletRequest request, HttpServletResponse response,
			ModelMap model)
	{
		try
		{
			HttpSession session = request.getSession();
			SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session
					.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

			Object primaryPrincipal = simplePrincipalCollection.getPrimaryPrincipal();

			String username = primaryPrincipal.toString();

			corpApplyService.saveCooperation(username, corpInfo);
		}
		catch (Exception e)
		{
			return "false";
		}

		return "ok";
	}

	private List<JcCorpShareholder> parseList(String jcCorpShareholderDto)
	{
		List<JcCorpShareholder> jcList = new ArrayList<>();

		String str = jcCorpShareholderDto.substring(jcCorpShareholderDto.indexOf("[") + 1,
				jcCorpShareholderDto.indexOf("]"));
		String replaceAll = str.replaceAll("\\{", "\\|").replaceAll("\\}", "\\|");
		String[] split = replaceAll.split("\\|");
		for (String string : split)
		{
			if (!string.equals(",") && !string.equals(""))
			{
				JcCorpShareholder jcCorpShareholder = new JcCorpShareholder();

				String[] split2 = string.split(",");
				for (String string2 : split2)
				{
					String prefixBean = string2.substring(0, string2.indexOf(":"));

					switch (prefixBean.substring(1, prefixBean.length() - 1))
					{

						case "shareholderName":
							System.out.println("shareholderName: "
									+ string2.substring(string2.indexOf(":") + 2, string2.length() - 1));
							String shareholderName = string2.substring(string2.indexOf(":") + 2, string2.length() - 1);
							jcCorpShareholder.setShareholderName(shareholderName);
							break;
						case "shareholderDonate":
							System.out.println("shareholderDonate: "
									+ string2.substring(string2.indexOf(":") + 2, string2.length() - 1));
							String shareholderDonate = string2
									.substring(string2.indexOf(":") + 2, string2.length() - 1);
							jcCorpShareholder.setShareholderDonate(Double.parseDouble(shareholderDonate));
							break;
						case "shareholderPercent":
							System.out.println("shareholderPercent: "
									+ string2.substring(string2.indexOf(":") + 2, string2.length() - 1));
							String shareholderPercent = string2.substring(string2.indexOf(":") + 2,
									string2.length() - 1);
							jcCorpShareholder.setShareholderPercent(shareholderPercent);
							break;
						case "shareholderPaytype":
							System.out.println("shareholderPaytype: "
									+ string2.substring(string2.indexOf(":") + 2, string2.length() - 1));
							String shareholderPaytype = string2.substring(string2.indexOf(":") + 2,
									string2.length() - 1);
							jcCorpShareholder.setShareholderPaytype(shareholderPaytype);
							break;

						default:
							break;
					}
				}
				jcList.add(jcCorpShareholder);
			}
		}
		return jcList;
	}
}
