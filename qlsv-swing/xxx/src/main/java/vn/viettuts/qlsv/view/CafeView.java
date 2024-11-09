package vn.viettuts.qlsv.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CafeView extends JFrame {
    private static CafeView instance;
    private JButton orderEntryButton;
    
    private JButton searchButton;
    private JButton employeeButton;
    private JButton inventoryButton;
    private JButton productViewButton;
    
    // Khai báo các biến cửa sổ cho các chức năng
    private OrderView orderView;
    
    private ProductSearchView productSearchView;
    private EmployeeView employeeView;
    private InventoryItemView inventoryItemView;
    private ProductView productView;
    private JLabel backgroundLabel;

    public CafeView() {
        setTitle("Quản Lý Quán Cà Phê");
        setSize(1728, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        String imagePath = "C:\\Users\\Admin\\Desktop\\De9-23010320-nguyenvanvu\\qlsv-swing\\xxx\\src\\main\\java\\vn\\viettuts\\qlsv\\img\\coffee-shop_24908-6688.jpg";
        
        // Tạo ImageIcon từ ảnh
        ImageIcon backgroundImage = new ImageIcon(imagePath);
        Image img = backgroundImage.getImage();
        Image scaledImg = img.getScaledInstance(1728, 1080, Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(scaledImg);
        
          // Tạo JLabel để chứa ảnh nền
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 1728, 1080);
        backgroundLabel.setLayout(null);
  
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        // Initialize buttons
      // Khởi tạo các nút với màu sắc tùy chỉnh
orderEntryButton = new JButton("Nhập Đơn Khách Hàng");
orderEntryButton.setBackground(new Color(46, 139, 87)); // Màu xanh lá cây đậm
orderEntryButton.setForeground(Color.WHITE); // Màu chữ trắng
orderEntryButton.setFont(new Font("Arial", Font.BOLD, 18)); // Font chữ in đậm, kích thước 18

searchButton = new JButton("Tìm Kiếm");
searchButton.setBackground(new Color(70, 130, 180)); // Màu xanh dương nhạt
searchButton.setForeground(Color.WHITE); // Màu chữ trắng
searchButton.setFont(new Font("Arial", Font.BOLD, 18));

employeeButton = new JButton("Quản Lý Nhân Viên");
employeeButton.setBackground(new Color(255, 165, 0)); // Màu cam
employeeButton.setForeground(Color.WHITE); // Màu chữ trắng
employeeButton.setFont(new Font("Arial", Font.BOLD, 18));

inventoryButton = new JButton("Quản Lý Kho");
inventoryButton.setBackground(new Color(220, 20, 60)); // Màu đỏ tươi
inventoryButton.setForeground(Color.WHITE); // Màu chữ trắng
inventoryButton.setFont(new Font("Arial", Font.BOLD, 18));

productViewButton = new JButton("Menu Xem Món");
productViewButton.setBackground(new Color(123, 104, 238)); // Màu tím nhạt
productViewButton.setForeground(Color.WHITE); // Màu chữ trắng
productViewButton.setFont(new Font("Arial", Font.BOLD, 18));

        // Đặt vị trí cho các nút
        orderEntryButton.setBounds(700, 200, 300, 50);
        searchButton.setBounds(700, 300, 300, 50);
        employeeButton.setBounds(700, 400, 300, 50);
        inventoryButton.setBounds(700, 500, 300, 50);
        productViewButton.setBounds(700, 600, 300, 50);
         backgroundLabel.add(orderEntryButton);
        backgroundLabel.add(searchButton);
        backgroundLabel.add(employeeButton);
        backgroundLabel.add(inventoryButton);
        backgroundLabel.add(productViewButton);

        // Thêm JLabel chứa ảnh nền vào JFrame
        add(backgroundLabel);


        // Add action listeners
        addActionListeners();
    }

    private void addActionListeners() {
        // Order Entry Button
        orderEntryButton.addActionListener(e -> toggleView(orderView, new OrderView()));

        // Statistic Button
      

        // Search Button
        searchButton.addActionListener(e -> toggleView(productSearchView, new ProductSearchView()));

        // Employee Button
        employeeButton.addActionListener(e -> toggleView(employeeView, new EmployeeView()));

        // Inventory Button
        inventoryButton.addActionListener(e -> toggleView(inventoryItemView, new InventoryItemView()));

        // Product View Button
        productViewButton.addActionListener(e -> toggleView(productView, new ProductView()));
    }

    // Generic method to toggle view visibility
    private <T extends JFrame> void toggleView(T currentView, T newView) {
        if (currentView == null) {
            currentView = newView;
            this.setVisible(false);
            currentView.setVisible(true);
        } else {
            currentView.setVisible(true);
        }
    }

    public static CafeView getInstance() {
        if (instance == null) {
            instance = new CafeView();
        }
        return instance;
    }

    public void display() {
        setVisible(true);
    }

   

    // Các phương thức thêm listener cho từng nút
    public void addOrderListener(ActionListener listener) {
        orderEntryButton.addActionListener(listener);
    }

   
    public void addEmployeeViewListener(ActionListener listener) {
        employeeButton.addActionListener(listener);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void addInventoryViewListener(ActionListener listener) {
        inventoryButton.addActionListener(listener);
    }
}
