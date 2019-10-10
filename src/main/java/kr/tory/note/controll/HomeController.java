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
	public void notice(HttpServletRequest req, HttpSession session, HttpServletResponse res) {
		HashMap<String, Object> useview = new HashMap<String, Object>();
		useview.put("Nickname", session.getAttribute("val"));
		result.put("data",(ss.selectList("sql.notice", useview)));
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(JSONObject.fromObject(result).toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/open_notice", method = RequestMethod.POST)
	public void open_notice(HttpServletRequest req, HttpSession session, HttpServletResponse res) {
		HashMap<String, Object> useview = new HashMap<String, Object>();
		useview.put("Nickname", session.getAttribute("val"));
		result.put("data",(ss.selectList("sql.notice", useview)));
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
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("Nickname" , AdmCheck);
		result.put("data",ss.selectList("sql.data" , result));
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
		HashMap<String, Object> insert_map = new HashMap<String, Object>();
		String notice = req.getParameter("editor");
		notice = notice.replaceAll("(<([^>]+)>)","");
		insert_map.put("Title", req.getParameter("Title"));
		insert_map.put("Tags", req.getParameter("editor"));
		insert_map.put("Notice", notice);
		insert_map.put("Nickname", session.getAttribute("val"));
		ss.insert("sql.notice_insert",insert_map);
		return "/notice";
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
//		System.out.println(files.toString());
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
		String notice = req.getParameter("editor");
		notice = notice.replaceAll("(<([^>]+)>)","");
		alt_map.put("Title", req.getParameter("Title"));
		alt_map.put("Tags", req.getParameter("editor"));
		alt_map.put("No", req.getParameter("No"));
		alt_map.put("Notice", notice);
		alt_map.put("Nickname", session.getAttribute("val"));
		ss.update("sql.notice_alt",alt_map);
		return "/notice";
	}
	@RequestMapping(value = "/edit_delete", method = RequestMethod.POST)
	public String edit_delete(HttpServletRequest req, HttpSession session) {
		HashMap<String, Object> delete_map = new HashMap<String, Object>();
		delete_map.put("Title", req.getParameter("title"));
		delete_map.put("No", req.getParameter("No"));
		delete_map.put("Nickname", session.getAttribute("val"));
		ss.update("sql.notice_delete",delete_map);
		return "/notice";
	}
	@RequestMapping(value = "/user_apply", method = RequestMethod.POST)
	public void user_apply(HttpServletRequest req, HttpSession session, HttpServletResponse res) {
		String AdmCheck = session.getAttribute("val").toString();
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(AdmCheck.equals("관리자") || AdmCheck.equals("염종찬")) {
			result.put("Nickname", req.getParameter("Nickname").toString());
			ss.update("sql.user_apply", result);
			result.put("msg", "승인 완료");
		}else {
			result.put("msg", "권한 없음");
		}
		JSONObject jobj = JSONObject.fromObject(result);
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jobj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
