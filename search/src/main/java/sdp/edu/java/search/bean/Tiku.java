package sdp.edu.java.search.bean;

public class Tiku {
    private String ti;
    private int id;

    public Tiku(String ti,int id) {
        this.id=id;
        this.ti=ti;
    }

    public Tiku() {

    }

    public String getTi() {
        return ti;
    }
    public void setTi(String ti) {
        this.ti = ti;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Tiku [ti=" + ti + ", id=" + id + "]";
    }


}
