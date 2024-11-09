package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import vn.viettuts.qlsv.entity.Order;
import vn.viettuts.qlsv.view.CafeView;
import vn.viettuts.qlsv.view.OrderView;


import vn.viettuts.qlsv.view.EmployeeView;
import vn.viettuts.qlsv.view.InventoryItemView;

public class CafeController {
    private CafeView cafeView;
    private OrderView orderView;
  
    private EmployeeView employeeView;
    private InventoryItemView inventoryItemView;
    private List<Order> orderList;

    public CafeController(CafeView cafeView, List<Order> orderList1) {
        this.cafeView = cafeView;
        this.orderList = orderList;
        // Initialize the required views
        this.orderView = new OrderView();
        this.employeeView = new EmployeeView();
        this.inventoryItemView = new InventoryItemView();

        // Add listeners for buttons in CafeView
        this.cafeView.addOrderListener(new OrderEntryListener());
        
        
        this.cafeView.addSearchListener(new SearchListener());
        this.cafeView.addEmployeeViewListener(new EmployeeViewListener());
        this.cafeView.addInventoryViewListener(new InventoryViewListener());
    }

    // Listener for handling the Search button
    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle the search action here
            System.out.println("Search action performed!");
        }
    }

    // Listener for opening OrderView
    private class OrderEntryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            orderView.setVisible(true);
            cafeView.setVisible(false);
        }
    }

 

   

 

    // Listener for opening EmployeeView
    private class EmployeeViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            employeeView.setVisible(true);
            cafeView.setVisible(false);
        }
    }

    // Listener for opening InventoryItemView
    private class InventoryViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inventoryItemView.setVisible(true);
            cafeView.setVisible(false);
        }
    }
     

    // Method to show the CafeView
    
    public void showCafeView() {
        cafeView.display();
    }
}
