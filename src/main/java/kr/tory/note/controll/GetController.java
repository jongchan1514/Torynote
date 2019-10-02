package kr.tory.note.controll;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class GetController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "Login";
	}
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
	@RequestMapping(value = "/notice1", method = RequestMethod.GET)
	public String notice1() {
		return "notice_list1";		
	}
	@RequestMapping(value="/Logout", method=RequestMethod.GET)
	public String Logout(HttpServletRequest req ,HttpSession session) {
		session.invalidate();
		return "redirect:/";	
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

}
