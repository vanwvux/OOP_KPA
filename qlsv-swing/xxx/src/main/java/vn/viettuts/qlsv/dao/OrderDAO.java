package vn.viettuts.qlsv.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.viettuts.qlsv.entity.Order;
import vn.viettuts.qlsv.entity.OrderXML;
import vn.viettuts.qlsv.utils.FileUtils;

/**
 * OrderDao class
 * 
 * @author viettuts.vn
 */
public class OrderDAO {
    private static final String ORDER_FILE_NAME = "orde.xml";
    private List<Order> listOrders;

    public OrderDAO() {
        this.listOrders = readListOrders();
        if (listOrders == null) {
            listOrders = new ArrayList<Order>();
        }
    }

    /**
     * Lưu các đối tượng order vào file order.xml
     * 
     * @param orders
     */
    public void writeListOrders(List<Order> orders) {
        OrderXML orderXML = new OrderXML();
        orderXML.setOrder(orders);
        FileUtils.writeXMLtoFile(ORDER_FILE_NAME, orderXML);
    }

    /**
     * Đọc các đối tượng order từ file order.xml
     * 
     * @return list order
     */
    public List<Order> readListOrders() {
        List<Order> list = new ArrayList<Order>();
        OrderXML orderXML = (OrderXML) FileUtils.readXMLFile(
                ORDER_FILE_NAME, OrderXML.class);
        if (orderXML != null) {
            list = orderXML.getOrder();
        }
        return list;
    }

    /**
     * Thêm order vào listOrders và lưu listOrders vào file
     * 
     * @param order
     */
    public void add(Order order) {
        int id = 1;
        if (listOrders != null && listOrders.size() > 0) {
            id = listOrders.size() + 1;
        }
        order.setOrderId(id);
        listOrders.add(order);
        writeListOrders(listOrders);
    }

    /**
     * Cập nhật order vào listOrders và lưu listOrders vào file
     * 
     * @param order
     */
    public void edit(Order order) {
        int size = listOrders.size();
        for (int i = 0; i < size; i++) {
            if (listOrders.get(i).getOrderId() == order.getOrderId()) {
                listOrders.get(i).setCustomerName(order.getCustomerName());
                listOrders.get(i).setOrderDate(order.getOrderDate());
                listOrders.get(i).setTotalAmount(order.getTotalAmount());
                writeListOrders(listOrders);
                break;
            }
        }
    }

    /**
     * Xóa order từ listOrders và lưu listOrders vào file
     * 
     * @param order
     */
    public boolean delete(Order order) {
        boolean isFound = false;
        int size = listOrders.size();
        for (int i = 0; i < size; i++) {
            if (listOrders.get(i).getOrderId() == order.getOrderId()) {
                order = listOrders.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listOrders.remove(order);
            writeListOrders(listOrders);
            return true;
        }
        return false;
    }

    

    /**
     * Lấy danh sách đơn hàng
     * 
     * @return list of orders
     */
    public List<Order> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Order> listOrders) {
        this.listOrders = listOrders;
    }





 public void update(Order order) {
    for (int i = 0; i < listOrders.size(); i++) {
        if (listOrders.get(i).getOrderId() == order.getOrderId()) {
            listOrders.set(i, order); // Replace with updated order
            writeListOrders(listOrders);
            return;
        }
    }
    throw new IllegalArgumentException("Order not found"); // Handle case where order doesn't exist
}

public Order getOrderByIndex(int index) {
    if (index >= 0 && index < listOrders.size()) {
        return listOrders.get(index);
    }
    return null; // or throw an exception
}


}
