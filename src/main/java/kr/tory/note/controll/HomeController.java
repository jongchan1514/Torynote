package kr.tory.note.controll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.tory.note.util.HttpUtil;
import net.sf.json.JSONObject;

@Controller
public class HomeController {
	
	@Autowired
	SqlSession ss;
	
	HashMap<String, Object> result = new HashMap<String, Object>();
	@RequestMapping(value = "/notice", method = RequestMethod.POST)
	public void notice1(HttpServletRequest req, HttpSession session, HttpServletResponse res) {
		result.put("data",(ss.selectList("sql.notice")));
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(JSONObject.fromObject(result).toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/Root", method=RequestMethod.POST)
	public void root(HttpServletRequest req, HttpSession hs, HttpServletResponse res) {
		String AdmCheck = hs.getAttribute("val").toString();
		HashMap<String, Boolean> result = new HashMap<String, Boolean>();
		if(AdmCheck.equals("관리자") || AdmCheck.equals("염종찬")) {
			result.put("result", true);
		}else {
			result.put("result", false);
		}
		JSONObject jobj = JSONObject.fromObject(result);
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jobj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@RequestMapping(value = "/edit_insert", method = RequestMethod.POST)
	public String edit_insert(HttpServletRequest req, HttpSession session) {
		System.out.println(req.getParameter("editor"));
//		HashMap<String, Object> insert_map = new HashMap<String, Object>();
//		String notice = req.getParameter("editor");
//		notice = notice.replaceAll("(<([^>]+)>)","");
//		insert_map.put("Title", req.getParameter("title"));
//		insert_map.put("Tags", req.getParameter("editor"));
//		insert_map.put("Notice", notice);
//		insert_map.put("Nickname", session.getAttribute("val"));
//		ss.insert("sql.notice_insert",insert_map);
	
//		세션의 전체 정보를 가져오기 위한 코드
//		Enumeration<?> test = session.getAttributeNames();
//		while (test.hasMoreElements()) {
//			String string = (String) test.nextElement();
//			System.out.println("key(" + string + ")" + session.getAttribute(string));
//		}  
		
		return null;
//		return "redirect:/Main#!/notice";
	}
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public void apply(HttpServletResponse res) {
		HashMap<String, Object> apply = new HashMap<String, Object>();
		apply.put("result", ss.selectList("sql.apply"));
		JSONObject jobj = JSONObject.fromObject(apply);
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jobj.toString());	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void list(HttpServletResponse res) {
		HashMap<String, Object> list = new HashMap<String, Object>();
		list.put("result", ss.selectList("sql.list"));
		JSONObject jobj = JSONObject.fromObject(list);
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jobj.toString());	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/imageUpload")
	public void imageUpload(HttpServletResponse res, MultipartHttpServletRequest req) {
		List<HashMap<String, Object>> files = HttpUtil.fileUpload(req, "upload");
		System.out.println(files.toString());
		HttpUtil.imgUpload(req, res, files);
	}
	@RequestMapping(value="/view", method = RequestMethod.POST)
	public void view(HttpServletRequest req, HttpServletResponse res){
		HashMap<String, Object> call = new HashMap<String, Object>();
		call.put("No", req.getParameter("No"));
		call.put("Nickname", req.getParameter("Nickname"));
		call.put("Title", req.getParameter("Title"));
		result.put("result",(ss.selectList("sql.view",call)));
		JSONObject jobj = JSONObject.fromObject(result);
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jobj.toString());	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/edit_alt", method = RequestMethod.POST)
	public String edit_alt(HttpServletRequest req, HttpSession session) {
		HashMap<String, Object> alt_map = new HashMap<String, Object>();
		String notice = req.getParameter("alt");
		notice = notice.replaceAll("(<([^>]+)>)","");
		alt_map.put("Title", req.getParameter("title"));
		alt_map.put("Tags", req.getParameter("alt"));
		alt_map.put("No", req.getParameter("no"));
		alt_map.put("Notice", notice);
		alt_map.put("Nickname", session.getAttribute("val"));
		System.out.println(alt_map.toString());
		ss.update("sql.notice_alt",alt_map);
//		세션의 전체 정보를 가져오기 위한 코드
//		Enumeration<?> test = session.getAttributeNames();
//		while (test.hasMoreElements()) {
//			String string = (String) test.nextElement();
//			System.out.println("key(" + string + ")" + session.getAttribute(string));
//		}  
		return "redirect:/Main#!/notice";
	}
}
