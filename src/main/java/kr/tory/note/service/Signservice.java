package kr.tory.note.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.tory.note.beans.UserBean;
import net.sf.json.JSONObject;

@Component
public class Signservice {
	HashMap<String, Object> result = new HashMap<String, Object>();
	
	@Autowired
	SqlSession ss;
	
	public void signservice(UserBean ub,HttpServletResponse res) {
		if(ss.selectList("sql.Check_User", ub).size() != 0) {
			result.put("msg", "중복된 계정이 있습니다.");
			result.put("Check", "fail");
		}else {
			if(ss.selectList("sql.Check_Nickname", ub).size() != 0) {
				System.out.println(1);
				result.put("msg", "중복된 닉네임이 있습니다.");
				result.put("Check", "fail");
			}else {
				System.out.println(2);
				ss.insert("sql.Create_User", ub);
				result.put("msg", "회원가입 요청이 완료 되었습니다.");
				result.put("Check", "Success");
			}
		}
		System.out.println(3);
		try {
			System.out.println(4);
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(JSONObject.fromObject(result).toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
		
