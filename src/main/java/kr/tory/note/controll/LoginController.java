package kr.tory.note.controll;
import java.io.IOException;
import java.lang.reflect.Member;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.tory.note.beans.UserBean;
import kr.tory.note.service.Signservice;
import kr.tory.note.util.HttpUtil;
import net.sf.json.JSONObject;


@Controller
public class LoginController {
	
	HttpSession session;
	List<kr.tory.note.beans.UserBean> User_Check = new ArrayList<kr.tory.note.beans.UserBean>();
	HashMap<String, Object> result = new HashMap<String, Object>();
	@Autowired
	Signservice sign;
	@Autowired
	SqlSession ss;
	

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public void login(UserBean ub, HttpServletResponse res, HttpServletRequest req) {
		session = req.getSession(true);
		if(ss.selectList("sql.Check_User", ub).size() == 0) {
			result.put("msg", "유저정보가 존재 하지 않습니다.");
		}else {
			if((User_Check = ss.selectList("sql.login", ub)).size() == 0) {
				result.put("msg", "비밀번호를 확인해주세요.");
			}else {
				if((User_Check.get(0).getNickname()).equals("관리자") || (User_Check.get(0).getNickname()).equals("염종찬")) {
					result.put("Adm", "Yes");
				}
				result.put("Check", "Ok");
				result.put("Adm", "no");
				result.put("msg", "환영합니다  "+User_Check.get(0).getNickname()+"  님");
				session.setMaxInactiveInterval(6000);
				session.setAttribute("val", User_Check.get(0).getNickname());				
			}
		}
		msgs(res);
	}
	
	@RequestMapping(value="/Sign", method=RequestMethod.POST)
	public void sign(HttpServletResponse res, @Valid UserBean ub, BindingResult err) {
		List<ObjectError> errors = err.getAllErrors();
		List<Object> msg = new ArrayList<Object>();
		if(errors.size() != 0) {
			for(ObjectError error : errors) {		
				msg.add(error.getDefaultMessage()+"\n\n");
			}
			result.put("msg", msg);
			msgs(res);
		}else {
			sign.signservice(ub, res);
		}
	}

	public void msgs(HttpServletResponse res) {
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(JSONObject.fromObject(result).toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	

}
