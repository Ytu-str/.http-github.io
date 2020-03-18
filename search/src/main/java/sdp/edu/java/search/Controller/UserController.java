package sdp.edu.java.search.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sdp.edu.java.search.Service.UserService;
import sdp.edu.java.search.bean.User;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 管理端页面登录
	 * @param username
	 * @param password
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("login")
	public String login(String username ,String password ,HttpSession session ,Model model) {
		User user=userService.findUserByName(username , password);
		if(user!=null) {
			session.setAttribute("USER_SESSION",user);
			return "background";
		}
		//model.addAttribute("error","输入有误");
		return "login";
	}
	@GetMapping("du")
public String b(){
		return "background";
}
}
