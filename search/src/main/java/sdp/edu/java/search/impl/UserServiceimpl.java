package sdp.edu.java.search.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import sdp.edu.java.search.Dao.UserDao;
import sdp.edu.java.search.Service.UserService;
import sdp.edu.java.search.bean.User;

@Service("userService")
public class UserServiceimpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User findUserByName(String username, String password) {
		User user=userDao.findUserByName(username, password);
		return user;
	}
	
}
