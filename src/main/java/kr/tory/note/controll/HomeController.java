package kr.tory.note.controll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="/apply", method=RequestMethod.GET)
	public String Users() {
		return "Admin_apply";
	}
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String test() {
		return "Home_notice";	
		
	}

}
