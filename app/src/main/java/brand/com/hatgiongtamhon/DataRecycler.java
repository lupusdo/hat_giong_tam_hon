package brand.com.hatgiongtamhon;

import java.util.ArrayList;
import java.util.List;

public class DataRecycler {

    private int img_recycler, order,ordertab;
    private String txt_recycler;

    public DataRecycler(int img_recycler, int order, String txt_recycler,int ordertab) {
        this.img_recycler = img_recycler;
        this.order = order;
        this.txt_recycler = txt_recycler;
        this.ordertab= ordertab;
    }


    public int getImg_recycler() {
        return img_recycler;
    }
    public void setImg_recycler(int img_recycler) {
        this.img_recycler = img_recycler;
    }

    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }

    public String getTxt_recycler() {
        return txt_recycler;
    }
    public void setTxt_recycler(String txt_recycler) {
        this.txt_recycler = txt_recycler;
    }
    public int getOrdertab() {
        return ordertab;
    }
    public void setOrdertab(int ordertab) {
        this.ordertab = ordertab;
    }

}
