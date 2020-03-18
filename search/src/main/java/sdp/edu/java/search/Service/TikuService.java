package sdp.edu.java.search.Service;

import org.springframework.web.multipart.MultipartFile;
import sdp.edu.java.search.bean.Tiku;

import java.io.IOException;
import java.util.List;

public interface TikuService {

    List<Tiku> findlist(int limits, int pages);

    int findNum(int limits, int pages);

    int adduser(MultipartFile file) throws IOException;
}
