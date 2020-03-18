package sdp.edu.java.search.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import sdp.edu.java.search.bean.Core;

import java.util.List;

@Mapper
public interface SearchDao {
    @Select("select * from tiku")
    List<Core> findBySearchList();
}
