package sdp.edu.java.search.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import sdp.edu.java.search.bean.User;

@Mapper
public interface UserDao {
    @Select( "select * from user where username=#{username} and password=#{password}" )
    User findUserByName(String username, String password);
}
