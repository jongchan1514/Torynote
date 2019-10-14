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
	@RequestMapping(value="/{path}", method=RequestMethod.GET)
	public String main(@PathVariable("path") String path, HttpServletRequest req, HttpSession session) {
		int flag = 0;
		session = req.getSession(true);
		String[] url = {"Main","apply","list","edit","notice","open_notice","open_table","news"};
		if(("Main").equals(path)) {
			if(session.getAttribute("val") == null) {
				return "redirect:/";
			}else {
				return "Home";	
			}
		}else {
			for(int i = 0; i < url.length; i++) {
				if(path.equals(url[i])) {
					flag++;
				}
			}
			if(flag != 1 && session.getAttribute("val") == null) {
				return "redirect:/";
			}else {
				return path;	
			}
		}

	}
}
