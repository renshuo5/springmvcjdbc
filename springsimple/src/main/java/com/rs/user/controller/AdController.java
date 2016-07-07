package com.rs.user.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ad")
public class AdController {

	@RequestMapping("/adBanner")
	@ResponseBody
	public String adBanner(@RequestParam(value = "req", required = false) String reqString,Model model,
			HttpServletRequest req) {
		
		System.out.println("Begin");
		
		try {
			if(req.getParameter("ref")!=null && !"".equals(req.getParameter("ref"))){
				System.out.println(URLDecoder.decode(req.getParameter("ref"), "UTF-8"));
			}
			
			if(req.getParameter("name")!=null && !"".equals(req.getParameter("name"))){
				System.out.println(URLDecoder.decode(req.getParameter("name"), "UTF-8"));
			}
			
			if(req.getParameter("page")!=null && !"".equals(req.getParameter("page"))){
				System.out.println(URLDecoder.decode(req.getParameter("page"), "UTF-8"));
			}
			System.out.println("bannerW"+req.getParameter("bannerW"));
			System.out.println("bannerH"+req.getParameter("bannerH"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String plattype=req.getParameter("plattype");
		System.out.println(plattype);
		String divid=req.getParameter("divid");
		
		StringBuffer sb = new StringBuffer();
		sb.append("jsonp_callback({'pid':'mm_106985768_8466335_29078274',");
		sb.append("'acookie':'1',");
		sb.append("'width':'250',");
		sb.append("'height':'250',");
		sb.append("'stdwidth':'250',");
		sb.append("'stdheight':'250',");
		sb.append("'distype':'5',");
		sb.append("'adboardtype':'98','title':'','clickurl':''");

//		sb.append("data':'<iframe frameborder=0 scrolling='no' marginheight=0 marginwidth=0 width=250 height=250 
		//src='http:\\a.emarbox.com\\a.do?info=19187_592183_100105_5056_0A625C4A000055B6F80BBA8105AAC9DF__20150728113331_5020_cpc_1236938_1236.938000_0.001819_0.100000____800book.net__1236.938000&guid=1l8KcaNV2-M%3D&sign=8438BF232E41D4F776B0B1ADEE0DF6E3&apid=250X250X0Xmm_106985768_8466335_29078274X0500X41101&ctype=image&cwidth=250&cheight=250&csrc=http%3A%2F%2Fimg.emarbox.com%2Fdsp%2F20150720%2F144960%2F250x250%2F1437370645468.jpg&url=http%3A%2F%2Fa1417.oadz.com%2Flink%2FC%2F1417%2F605507%2FCuxQwhkpUA3ir9luWRV0rTWynEc_%2Fp03a%2F0%2Fhttp%3A%2F%2Factivity.aoyou.com%2Fhd%2Fshujiaziyouxing%2Findex_bj.html&rt=0503050505&rt2=0&td=0&c=AQpiXEoAAFW2%2BAu6gQWqyd8d65MmZ9LtnA%3D%3D&tanxclick=http%3a%2f%2fclick.tanx.com%2fct%3ftanx_k%3d173%26tanx_e%3dutRp2qk6sl0EmF1G3wMh0jIR5nYONXrQ%252b%252b6HeB3SWPqIb3sTntB7TUtRpcS7FyGiu9YUj6WnBzKyW%252fiPOCg6V6X3H9h77tFFTQrExE3Y10H5SqNVoFL37gvXT4eM3XFjBaF0W8CcwsxNcsxlk%252bj7svrNiUHAMWjpt4gYS73KK6I%253d%26tanx_u%3d&dmpid=1427343242012571238377\'>");
//		sb.append("</iframe>");
//		sb.append("<img src='http://cms.tanx.com//t.gif?tanx_nid=29600513&tanx_cm' width='0' height='0' alt=''>','icon':'',
		//'feedback':'http://df.tanx.com/spf?e=kA6BrbqnB24Z8VO5DUg65rQjJKxTiSUJVRNqtJ5yw2PhRUuwoOEBJwlXXi81wORv3FPKOsDJA8b4vAwBA%2ByCDJGYsFljCfE%2BpGJujbq4oUO%2Blv3xeFPXvO3k7akk8bQddNpcOY%2FCLK00W6hPUiWOJI97%2FBQQGQYu&u=&k=161','unregist':'','tproduct':'2','isx':'0','mid':'','crid':'','tsid':'0a625c4a000055b6f80bba8105aac9df'");
		
		sb.append("});");
		
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		String str="";
		if("msat-adwrap1".equals(divid)){
			str = "jsonp_callback({'divid':'"+divid+"','containertype':'banner/suspe','admtype':'swf',"
					+ "'seatbid':[{"
					+ "'bid':[{'adm':'http://c4.py0.cc/2015/203/0602/2644.swf',"
						+ "'width':300,'height':250,"
						+ "'impid':'',"
						+ "'pm':['http://miaozhen.com/1','http://miaozhen.com/2'],"
						+ "'cm':['http://miaozhen.com/1','http://miaozhen.com/2'],"
					+ "'clickurl':'http://t.paiyue.com/miaozhent.gif?pr=MgsFLupx_legnosXlYKF_kpUiWQV0oGMrFmaoxEkYlOQ7MD_o5UYaKjG39D8yfsFg7mizcZmQlIXpVAcPiQQcnuinl8vcMDfLYbCNuOeYv_1LIDFoXW_syaPVyKOaYtEqmxmlyaEiIjD6jX8c3hEGuewiLQIBheHLYKdoBpGmHSZvmAtSgElYS_xFlXDvNLgMZbNiiDYBSmP31sXdiAHzLefr7QocCpnnFFnpUE_uI7ORfXPHzrLLkOru2eRbdikpeBlOcHh3wd8kMeXlFQ&km=RSPPDa'"
					+ "}]}]})";
			
		}else if("msat-adwrap2".equals(divid)){
			
			str = "jsonp_callback({'divid':'"+divid+"','containertype':'banner/suspe','admtype':'img',"
					+ "'seatbid':[{"
					+ "'bid':[{'adm':'http://cdnweb.b5m.com/web/cmsphp/article/201403/1659dd5a605add0fb922368620bf7bc8.jpg',"
						+ "'width':300,'height':250,"
						+ "'impid':'',"
						+ "'pm':['http://miaozhen.com/1','http://miaozhen.com/2'],"
						+ "'cm':['http://miaozhen.com/1','http://miaozhen.com/2'],"
					+ "'clickurl':'http://t.paiyue.com/miaozhent.gif?pr=MgsFLupx_legnosXlYKF_kpUiWQV0oGMrFmaoxEkYlOQ7MD_o5UYaKjG39D8yfsFg7mizcZmQlIXpVAcPiQQcnuinl8vcMDfLYbCNuOeYv_1LIDFoXW_syaPVyKOaYtEqmxmlyaEiIjD6jX8c3hEGuewiLQIBheHLYKdoBpGmHSZvmAtSgElYS_xFlXDvNLgMZbNiiDYBSmP31sXdiAHzLefr7QocCpnnFFnpUE_uI7ORfXPHzrLLkOru2eRbdikpeBlOcHh3wd8kMeXlFQ&km=RSPPDa'"
					+ "}]}]})";
			
		}else if("msat-adwrap3".equals(divid)){
			str = "jsonp_callback({'divid':'"+divid+"','plattype':'"+plattype+"','containertype':'banner/suspe','admtype':'img',"
					+ "'seatbid':[{"
					+ "'bid':[{'adm':'http://img2.3lian.com/2014/f5/17/18.jpg',"
						+ "'width':300,'height':250,"
						+ "'impid':'',"
						+ "'pm':['http://miaozhen.com/1','http://miaozhen.com/2'],"
						+ "'cm':['http://miaozhen.com/1','http://miaozhen.com/2'],"
					+ "'clickurl':'http://t.paiyue.com/miaozhent.gif?pr=MgsFLupx_legnosXlYKF_kpUiWQV0oGMrFmaoxEkYlOQ7MD_o5UYaKjG39D8yfsFg7mizcZmQlIXpVAcPiQQcnuinl8vcMDfLYbCNuOeYv_1LIDFoXW_syaPVyKOaYtEqmxmlyaEiIjD6jX8c3hEGuewiLQIBheHLYKdoBpGmHSZvmAtSgElYS_xFlXDvNLgMZbNiiDYBSmP31sXdiAHzLefr7QocCpnnFFnpUE_uI7ORfXPHzrLLkOru2eRbdikpeBlOcHh3wd8kMeXlFQ&km=RSPPDa'"
					+ "}]}]})";
		}else if("miaov_float_layer_1".equals(divid)){
			str = "jsonp_callback({'divid':'"+divid+"','containertype':'suspe','admtype':'swf',"
					+ "'seatbid':[{"
					+ "'bid':[{'adm':'http://img2.3lian.com/2014/f5/17/18.jpg',"
						+"'data':'<iframe frameborder=0 scrolling=\"no\" marginheight=0 marginwidth=0 width=250 height=250 src=\"http://192.168.1.111:8080/SpringMvcPage/html/iframe.html?clickurl=http://t.paiyue.com/miaozhent.gif&adm=http://c4.py0.cc/2015/203/0602/2644.swf&admtype=swf&bannerW=300&bannerH=250\"></iframe>',"
						+ "'width':300,'height':250,"
						+ "'impid':'',"
						+ "'pm':['http://miaozhen.com/1','http://miaozhen.com/2'],"
						+ "'cm':['http://miaozhen.com/1','http://miaozhen.com/2'],"
					+ "'clickurl':'http://t.paiyue.com/miaozhent.gif?pr=MgsFLupx_legnosXlYKF_kpUiWQV0oGMrFmaoxEkYlOQ7MD_o5UYaKjG39D8yfsFg7mizcZmQlIXpVAcPiQQcnuinl8vcMDfLYbCNuOeYv_1LIDFoXW_syaPVyKOaYtEqmxmlyaEiIjD6jX8c3hEGuewiLQIBheHLYKdoBpGmHSZvmAtSgElYS_xFlXDvNLgMZbNiiDYBSmP31sXdiAHzLefr7QocCpnnFFnpUE_uI7ORfXPHzrLLkOru2eRbdikpeBlOcHh3wd8kMeXlFQ&km=RSPPDa'"
					+ "}]}]})";
		}
		System.out.println("End");
		return str;
	}
	
	@RequestMapping("/outPage")
	@ResponseBody
	public String outPage(@RequestParam(value = "py_div", required = false) String div,
			@RequestParam(value = "adId", required = false) String adId,
			@RequestParam(value = "ref", required = false) String ref,
			@RequestParam(value = "page", required = false) String page,Model model,
			HttpServletRequest req,HttpServletResponse res) {
		System.out.println("Begin");
		System.out.println(req.getParameter("req"));
		System.out.println(div);
		System.out.println(adId);
		System.out.println(ref);
		System.out.println(page);
		//模拟阻塞
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		try {
			OutputStream out = res.getOutputStream();
			PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
			
			if("paiyue_suspe".equals(div)){
				String floatHtmlStr="<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0' width='300' height='250' id='Main' align='middle'><param name='allowScriptAccess' value='true' /><param name='allowFullScreen' value='false' /><param name='movie' value='http://c4.py0.cc/2015/203/0602/2644.swf' /><param name='wmode' value='transparent'/><param name='quality' value='high' /><embed src='http://c4.py0.cc/2015/203/0602/2644.swf' quality='high' wmode='transparent' width='300' height='250' name='Main' id='Main' align='middle' allowScriptAccess='true' allowFullScreen='false' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer'/></object>";
				String fun = "paiyuei.float('"+div+"',\""+floatHtmlStr+"\",'http://www.baidu.com')";
				pw.print(fun);
				
			}else{
				//模拟单个广告位超时
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				String fixHtmlStr="<img id=\"ad_img_"+div+"\" src=\"http://cdnweb.b5m.com/web/cmsphp/article/201403/1659dd5a605add0fb922368620bf7bc8.jpg\">";
				String fun = "paiyuei.fix('"+div+"','"+fixHtmlStr+"','http://www.baidu.com')";
				pw.print(fun);
				
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("End");
		return "";
	}
	
	
	@RequestMapping("/timeOutAd")
	@ResponseBody
	public String timeOutAd(@RequestParam(value = "py_div", required = false) String div,
			@RequestParam(value = "adId", required = false) String adId,
			@RequestParam(value = "ref", required = false) String ref,
			@RequestParam(value = "page", required = false) String page,Model model,
			HttpServletRequest req,HttpServletResponse res) {
		System.out.println("Begin");
		
		System.out.println(div);
		try {
			OutputStream out = res.getOutputStream();
			PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
			String fixHtmlStr="<img id=\"ad_img_"+div+"\" src=\"http://www.baidu.com/img/baidu_sylogo1.gif\">";
			String fun = "paiyuei.fix('"+div+"','"+fixHtmlStr+"','http://www.baidu.com')";
			pw.print(fun);
			
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("End");
		return "";
	}

	public String loadSuspeAD(String div, String suspeHtml) {
		StringBuffer sb=new StringBuffer();
		sb.append("");
		sb.append("function loadSuspe_"+div+"(){");
		
		sb.append("var url = 'http://192.168.1.144:8080/SpringMvcPage/js/py.js';");
		sb.append("var scr = document.createElement('script');");
		sb.append("scr.type = 'text/javascript';");
		sb.append("scr.async = true;");
		sb.append("scr.src = url;");
		
		sb.append("var obj = document.getElementById('"+div+"');");
		sb.append(" obj.insertBefore(scr, obj.childNodes[0]);");
		
		sb.append("var pyscr = document.createElement('script');");
		sb.append("pyscr.type = 'text/javascript';");
		sb.append("pyscr.text = \"function py_ready(f){(typeof(py)!='object')?setTimeout('py_ready('+f+')',9):f();}py_ready(function(){ var html=\\\""+suspeHtml+"\\\"; py.load.init('paiyue_suspe',html);});\";");
		sb.append(" obj.insertBefore(pyscr, obj.childNodes[1]);");
		sb.append("};loadSuspe_"+div+"();");
		String fun=sb.toString();
		return fun;
	}
	
	public static void main(String [] args){
//		US-ASCII
		try {
			String t=URLEncoder.encode("<HTML><HEAD><TITLE>输出HTML标签</HEAD></TITLE><BODY>", "US-ASCII");
			System.out.println(t);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
