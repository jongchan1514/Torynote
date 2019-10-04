package kr.tory.note.controll;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class GetController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "Login";
	}
	@RequestMapping(value = "/{Url}", method = RequestMethod.GET)
	public String Users(@PathVariable("Url")String url, HttpServletRequest req) {
		String geturl = url;
		System.out.println(url);
		return geturl;
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
