package kr.tory.note.controll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.json.JSONObject;

@Controller
public class HomeController {
	
	@Autowired
	SqlSession ss;
	
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public String Users() {
		return "Admin_apply";
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String test() {
		return "Home_list";	
		
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit() {
		return "edit";	
		
	}
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String test(HttpServletRequest req) {		
		System.out.println(req.getParameter("title"));
		System.out.println(req.getParameter("editor"));
		return "redirect:/Main";
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

}
