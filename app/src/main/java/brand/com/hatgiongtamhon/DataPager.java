package brand.com.hatgiongtamhon;

import java.util.List;

public class DataPager {
    private List<DataRecycler> dataRecyclerList;
    private int orderPager;

    public DataPager(List<DataRecycler> dataRecyclerList, int orderPager) {
        this.dataRecyclerList = dataRecyclerList;
        this.orderPager = orderPager;
    }

    public List<DataRecycler> getDataRecyclerList() {
        return dataRecyclerList;
    }

    public void setDataRecyclerList(List<DataRecycler> dataRecyclerList) {
        this.dataRecyclerList = dataRecyclerList;
    }

    public int getOrderPager() {
        return orderPager;
    }

    public void setOrderPager(int orderPager) {
        this.orderPager = orderPager;
    }
}
