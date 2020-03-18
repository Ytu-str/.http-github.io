package sdp.edu.java.search.Service;

import sdp.edu.java.search.bean.User;

public interface UserService {
    User findUserByName(String username, String password);
}
