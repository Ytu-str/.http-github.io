package sdp.edu.java.search.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sdp.edu.java.search.CustomAnnotation.Journal;
import sdp.edu.java.search.Service.SearchService;
import sdp.edu.java.search.bean.Core;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@RestController
public class SearchController {

    @Value(value = "${Core.form.question1}")
    private String QUESTION1;
    @Value(value = "${Core.form.question2}")
    private String QUESTION2;
    @Autowired
    private SolrClient solrClient;
    @Autowired
    private SearchService searchService;


    /**
     * 分词查询
     *
     * @return
     */
    @Journal
    @GetMapping("/search")
    @ResponseBody
    public String Search(@RequestParam(name = "ti", required = false) String ti
                        ) throws IOException, SolrServerException{
        //调用符号转义
        String TransferredMeaning = escapeQueryChars(ti);
        List<Core> CoreList = new ArrayList<>();
        SolrQuery solrQuery = new SolrQuery();
        if (ti.equals(null) || ti == "") {
            return "没有搜到答案呀";
        } else {
            solrQuery.setQuery(TransferredMeaning);
            solrQuery.set("q", "ti:" + TransferredMeaning);
            try {
                QueryResponse response = solrClient.query(solrQuery);
                if (response != null) {
                    SolrDocumentList results = response.getResults();
                    for (SolrDocument document : results) {
                        Core core;
                        String jsonString = JSON.toJSONString(document);
                        core = JSONObject.parseObject(jsonString, Core.class);
                        CoreList.add(core);
                    }
                } else {
                    return "没有搜到答案呀";
                }
            } catch (IOException io) {
                io.printStackTrace();
                return "没有搜到答案呀";
            } catch (SolrServerException solre) {
                solre.printStackTrace();
                return "没有搜到答案呀";
            }
        }
        return CoreList.get(0).getTi();
    }


    /**
     * 定时更新索引
     */
    //cron代表的是时间  如下代表（23点59分59秒）
    @Scheduled(cron = "59 59 23 * * ?")
    public void timer() throws IOException, SolrServerException, InterruptedException {
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        //输出当前时间
        System.out.println("当前时间为:" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //调用删除索引的方法
        deleteDocument();
        Thread.sleep(120000);
        //调用数据新增到索影库的方法
        selectCores();

    }

    /**
     * 更新添加数据
     */
    private void selectCores() throws IOException, SolrServerException {
        List<Core> list = searchService.selecGoods();
        for (Core Searchlist : list) {
            //创建文档对象
            SolrInputDocument document = new SolrInputDocument();
            document.addField("ti", Searchlist.getTi());
            document.addField("id", Searchlist.getId());
            //写入solr
            solrClient.add(document);
        }
        //提交
        solrClient.commit();
        System.out.println("写入成功");
    }

    /**
     * 更新删除索引
     *
     * @throws IOException
     * @throws SolrServerException
     */
    private void deleteDocument() throws IOException, SolrServerException {
        //删除所有的索引
        solrClient.deleteByQuery("*:*");
        //提交修改
        solrClient.commit();
        System.out.println("删除成功");
    }

    /**
     * 符号转义
     * @param s
     * @return
     */
    public String escapeQueryChars(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\\' || c == '+' || c == '-' || c == '!'  || c == '(' || c == ')' || c == ':'
                    || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
                    || c == '*' || c == '?' || c == '|' || c == '&'  || c == ';' || c == '/'
                    || Character.isWhitespace(c)) {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

}