package kr.tory.note.controll;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class GetController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "Login";
	}
	@RequestMapping(value="/Logout", method=RequestMethod.GET)
	public String Logout(HttpServletRequest req ,HttpSession session) {
		session.invalidate();
		return "redirect:/";	
	}
	@RequestMapping(value="/Main", method=RequestMethod.GET)
	public String main(HttpServletRequest req, HttpSession session) {
		session = req.getSession(true);
		if(session.getAttribute("val") == null) {
			return "redirect:/";
		}else {
			return "Home";	
		}
	}
	@RequestMapping(value = "/apply", method = RequestMethod.GET)	
	public String Users() {		
		return "apply";			
	}			
	@RequestMapping(value = "/list", method = RequestMethod.GET)		
	public String list() {	
		return "list";		
	}	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)	
	public String edit() {	
		return "edit";			
	}	
	@RequestMapping(value = "/notice", method = RequestMethod.GET)	
	public String notice() {	
		return "notice";			
	}
	@RequestMapping(value = "/open_notice", method = RequestMethod.GET)	
	public String open_notice() {	
		return "notice";			
	}

}
