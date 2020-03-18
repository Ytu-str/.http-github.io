package sdp.edu.java.search.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.edu.java.search.Dao.SearchDao;
import sdp.edu.java.search.Service.SearchService;
import sdp.edu.java.search.bean.Core;

import java.util.List;

@Service("searchService")
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;
    @Override
    public List<Core> selecGoods() {
        return searchDao.findBySearchList();
    }
}
