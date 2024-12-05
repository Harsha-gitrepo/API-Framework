package eCommerceAPITest;

import java.util.List;

public class ordersPOJO {

    private List<orderDetailsPOJO> orders;

    public List<orderDetailsPOJO> getOrders() {
        return orders;
    }
    public void setOrders(List<orderDetailsPOJO> orders) {
        this.orders = orders;
    }
}
