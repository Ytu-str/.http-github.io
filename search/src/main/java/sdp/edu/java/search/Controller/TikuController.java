package sdp.edu.java.search.Controller;


import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sdp.edu.java.search.Service.TikuService;
import sdp.edu.java.search.bean.Tiku;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TikuController {

	@Autowired
	private TikuService tikuservice;
/**
 * 管理端页面数据列表
 */
	@RequestMapping("/du")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(value="page",required=true)String page,
									@RequestParam(value="limit",required=true)String limit) throws Exception{
		int limits=Integer.parseInt(limit);
		int pages=Integer.parseInt(page);
		Map<String, Object> map2 = new HashMap<>();
		int num=tikuservice.findNum(limits,pages);
		List<Tiku> tiku=tikuservice.findlist(limits,pages);
			for (Tiku a :tiku) {
				JSONObject map= new JSONObject();
				map.put("it",a.getTi());
				map.put("id",a.getId());
			}
			map2.put("code",0);
			map2.put("msg","");
			map2.put("count",num);
			map2.put("data",new JSONArray().fromObject(tiku));
		return map2;
	}
	/**
	 * 导入题目数据
	 *
	 */
	@RequestMapping("/import2db")
	@ResponseBody
	public Integer import2db(MultipartFile file) throws Exception {
		int count= tikuservice.adduser(file);
		return count;
	}
	//返回测试页面
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
}

