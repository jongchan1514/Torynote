package kr.tory.note.controll;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.tory.note.beans.loginBean;
import kr.tory.note.beans.signBean;
import net.sf.json.JSONObject;


@Controller
public class LoginController {
	
	HttpSession session;
	List<kr.tory.note.beans.loginBean> User_Check = new ArrayList<kr.tory.note.beans.loginBean>();
	HashMap<String, Object> result = new HashMap<String, Object>();
	@Autowired
	SqlSession ss;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "Login";
	}
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public void login(loginBean lg, HttpServletResponse res, HttpServletRequest req) {
		session = req.getSession(true);
		result.put("User", lg.getUser());
		User_Check = ss.selectList("sql.login_User", result);
		if(User_Check.size() == 0) {
			result.put("result", "유저정보가 존재 하지 않습니다.");
		}else {
			if(ss.selectList("sql.login", lg).size() == 0) {
				result.put("result", "비밀번호를 확인해주세요.");
			}else {
				result.put("Check", "Ok");
				result.put("result", "환영합니다  "+lg.getUser()+"  님");
				session.setMaxInactiveInterval(60);
				session.setAttribute("val", lg.getUser().toString());
			}
		}
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(JSONObject.fromObject(result).toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/Main", method=RequestMethod.GET)
	public String main(HttpServletRequest req) {
		session = req.getSession(true);
		if(session.getAttribute("val") == null) {
			return "redirect:/";
		}else {
			System.out.println(session.getAttribute("val"));
			return "Home";	
		}
	}
	@RequestMapping(value="/Sign", method=RequestMethod.POST)
	public String sign(signBean sb) {
		result.put("User", sb.getUser());
		System.out.println(ss.selectList("sql.login_User", result).size());
		
		System.out.println(sb);
		
			return "redirect:/";	
	}
}
