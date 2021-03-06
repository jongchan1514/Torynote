package kr.tory.note.controll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
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
		String AdmCheck;
		if(hs.getAttribute("val") != null) {
			AdmCheck = hs.getAttribute("val").toString();
		}else {
			AdmCheck = "잘못된접근방식";
		}
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
	public void imageUpload(HttpServletResponse res, MultipartHttpServletRequest req, HttpSession session) {
		List<HashMap<String, Object>> files = HttpUtil.fileUpload(req, "upload","imageUpload",session);
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
	@RequestMapping(value = "/shift", method = RequestMethod.POST)	
	public String shift(MultipartHttpServletRequest req, HttpSession hs, HttpServletResponse res) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, Object>> files = HttpUtil.fileUpload(req, "upload", "shift", hs);
		map.put("Nickname", hs.getAttribute("val"));
		map.put("img", hs.getAttribute("img"));
		System.out.println(map);
		ss.update("sql.img_updata", map);
		return "redirect:/Main";
	}
	
	@RequestMapping(value = "/open_table", method = RequestMethod.POST)	
	public void open_table(HttpServletRequest req, HttpSession hs, HttpServletResponse res) {	
		HashMap<String, Object> table_list = new HashMap<String, Object>();
		table_list.put("result", ss.selectList("sql.open_table"));
		JSONObject jobj = JSONObject.fromObject(table_list);
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jobj.toString());	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/table_insert", method = RequestMethod.POST)
	public String table_insert(HttpServletRequest req, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String quarter =  req.getParameter("quarter");
		map.put("Title", req.getParameter("Title"));
		map.put("Tags", req.getParameter("editor"));
		map.put("Nickname", session.getAttribute("val"));
		switch (quarter) {
		case "insert":
			ss.insert("sql.table_insert",map);
			break;
		case "alt":
			map.put("No", req.getParameter("No"));
			if(ss.selectList("sql.table_check",map).size() != 0) {
			ss.update("sql.table_alt",map);
			}
			break;
		default:
			break;
		}
		return "/open_table";
	}
	@RequestMapping(value="/table_view", method = RequestMethod.POST)
	public void table_view(HttpServletRequest req, HttpServletResponse res){
		HashMap<String, Object> call = new HashMap<String, Object>();
		call.put("No", req.getParameter("No"));
		call.put("Nickname", req.getParameter("Nickname"));
		call.put("Title", req.getParameter("Title"));
		call.put("col", req.getParameter("col"));
		ss.update("sql.table_col",call);
		result.put("result",(ss.selectList("sql.table_view",call)));
		JSONObject jobj = JSONObject.fromObject(result);
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jobj.toString());	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/table_delete", method = RequestMethod.POST)
	public String table_delete(HttpServletRequest req, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Title", req.getParameter("Title"));
		map.put("Nickname", session.getAttribute("val"));
		map.put("No", req.getParameter("No"));
		if(ss.selectList("sql.table_check",map).size() != 0) {
			ss.update("sql.table_delete",map);
		}
		return "/open_table";
	}
	@RequestMapping(value = "/table_search", method = RequestMethod.POST)
	public void table_search(HttpServletRequest req, HttpSession session, HttpServletResponse res) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("search", req.getParameter("search"));
		map.put("result", ss.selectList("sql.table_search",map));
		JSONObject jobj = JSONObject.fromObject(map);
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jobj.toString());	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/notice_open", method = RequestMethod.POST)
	public void notice_open(HttpServletRequest req, HttpSession session, HttpServletResponse res) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String Open = "";
		if((req.getParameter("Open")).equals("N")) {
			Open = "Y";
		}else {
			Open = "N";
		}
		System.out.println(Open);
		map.put("No", req.getParameter("No"));
		map.put("Title", req.getParameter("Title"));
		map.put("Open", Open);
		map.put("Nickname", session.getAttribute("val"));
		ss.update("sql.notice_open",map);
	}
}
