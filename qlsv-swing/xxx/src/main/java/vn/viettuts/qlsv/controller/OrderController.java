package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vn.viettuts.qlsv.dao.OrderDAO;
import vn.viettuts.qlsv.entity.Order;
import vn.viettuts.qlsv.view.CafeView;
import vn.viettuts.qlsv.view.OrderView;

public class OrderController {
    private OrderDAO orderDAO;
    private OrderView orderView;
    private CafeView cafeView;
    public OrderController(OrderView view) {
        this.orderView = view;
        orderDAO = new OrderDAO();
        this.cafeView = cafeView;
        // Gán các sự kiện nút bấm
     
       
       
      
        view.addListOrderSelectionListener(new ListOrderSelectionListener());
     
    }

    // Hiển thị giao diện đơn hàng
    public void showOrderView() {
        List<Order> orderList = orderDAO.getListOrders();
        orderView.setVisible(true);
        orderView.showListOrders(orderList);
    }

    /**
     * Lớp AddOrderListener 
     * Xử lý sự kiện thêm đơn hàng
     */
    
    class AddOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Order order = orderView.getOrderInfo();
            if (order != null) {
                orderDAO.add(order);
                orderView.showOrder(order);
                orderView.showListOrders(orderDAO.getListOrders());
                orderView.showMessage("Thêm đơn hàng thành công!");
            }
        }
    }

    /**
     * Lớp EditOrderListener 
     * Xử lý sự kiện chỉnh sửa đơn hàng
     */
    class EditOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Order order = orderView.getOrderInfo();
            if (order != null) {
                orderDAO.edit(order);
                orderView.showOrder(order);
                orderView.showListOrders(orderDAO.getListOrders());
                orderView.showMessage("Cập nhật đơn hàng thành công!");
            }
        }
    }

    /**
     * Lớp DeleteOrderListener 
     * Xử lý sự kiện xóa đơn hàng
     */
    class DeleteOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Order order = orderView.getOrderInfo();
            if (order != null) {
                orderDAO.delete(order);
                orderView.clearOrderInfo();
                orderView.showListOrders(orderDAO.getListOrders());
                orderView.showMessage("Xóa đơn hàng thành công!");
            }
        }
    }

    /**
     * Lớp ClearOrderListener 
     * Xử lý sự kiện xóa thông tin nhập liệu
     */
    class ClearOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            orderView.clearOrderInfo();
        }
    }

   

 

    /**
     * Lớp ListOrderSelectionListener 
     * Xử lý sự kiện khi chọn đơn hàng trong bảng
     */
    class ListOrderSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            orderView.fillOrderFromSelectedRow();
        }
    }

    /**
     * Lớp BackListener 
     * Xử lý sự kiện nhấn nút "Quay lại"
     */
    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            orderView.setVisible(false);
            // Thực hiện hành động quay lại menu chính
            cafeView.setVisible(true);
            
        }
    }
    
}