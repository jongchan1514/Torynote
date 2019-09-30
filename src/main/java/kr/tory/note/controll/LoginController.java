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

import kr.tory.note.beans.UserBean;
import net.sf.json.JSONObject;


@Controller
public class LoginController {
	
	HttpSession session;
	List<kr.tory.note.beans.UserBean> User_Check = new ArrayList<kr.tory.note.beans.UserBean>();
	HashMap<String, Object> result = new HashMap<String, Object>();
	@Autowired
	SqlSession ss;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "Login";
	}
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public void login(UserBean ub, HttpServletResponse res, HttpServletRequest req) {
		session = req.getSession(true);
		User_Check = ss.selectList("sql.Check_User", ub);
		if(ss.selectList("sql.Check_User", ub).size() == 0) {
			result.put("result", "유저정보가 존재 하지 않습니다.");
		}else {
			if(ss.selectList("sql.login", ub).size() == 0) {
				result.put("result", "비밀번호를 확인해주세요.");
			}else {
				result.put("Check", "Ok");
				result.put("result", "환영합니다  "+User_Check.get(0).getNickname()+"  님");
				session.setMaxInactiveInterval(60);
				session.setAttribute("val", ub.getUser().toString());
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
//		session = req.getSession(true);
//		if(session.getAttribute("val") == null) {
//			return "redirect:/";
//		}else {
//			System.out.println(session.getAttribute("val"));
			return "Home";	
//		}
	}
	@RequestMapping(value="/Sign", method=RequestMethod.POST)
	public void sign(UserBean ub, HttpServletResponse res) {
		result.put("User", ub.getUser());
		if(ss.selectList("sql.Check_User", result).size() != 0) {
			result.put("result", "중복된 계정이 있습니다.");
			result.put("Check", "fail");
		}else {
			if(ss.selectList("sql.Check_Nickname", ub).size() != 0) {
				result.put("result", "중복된 닉네임이 있습니다.");
				result.put("Check", "fail");
			}else {
				ss.insert("sql.Create_User", ub);
				result.put("result", "회원가입 요청이 완료 되었습니다.");
				result.put("Check", "Success");
			}
		}
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(JSONObject.fromObject(result).toString());
		} catch(IOException e) {
			e.printStackTrace();
		}	
	}
}
