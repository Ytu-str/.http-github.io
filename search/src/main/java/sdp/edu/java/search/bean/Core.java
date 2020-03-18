package sdp.edu.java.search.bean;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName="Core")
public class Core{

    @Field("id")
    private int id;
    @Field("ti")
    private String ti;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTi(String ti) {
        this.ti = ti;
    }

    public String getTi() {
        return ti;
    }

    @Override
    public String toString() {
        return "Core{" +
                "id=" + id +
                ", ti='" + ti + '\'' +
                '}';
    }
}
