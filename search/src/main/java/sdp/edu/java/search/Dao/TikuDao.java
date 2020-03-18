package sdp.edu.java.search.Dao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sdp.edu.java.search.bean.Tiku;

import java.util.List;

@Mapper
public interface TikuDao {
    //查询所有数据
    @Select("select * from tiku limit #{floats},#{limits}")
    List<Tiku> findtick(@Param("floats")int floats, @Param("limits")int limits);


    //查询数据数量
    @Select("select count(1) from tiku")
    int findNum(int limits, int floats);


    //将数据导入数据库
    @Insert("Insert into ceshi (ti) values (#{ti})")
    int insertSelective(Tiku ti);

}
