package vn.viettuts.qlsv.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import vn.viettuts.qlsv.controller.ProductController;
import vn.viettuts.qlsv.entity.Order;
import vn.viettuts.qlsv.entity.Product;

public class OrderView extends JFrame {
    private JTable orderTable;
    private JButton btnDelete, btnAddProducts, btnStatistic, backButton;
    private JTextField txtOrderId, txtCustomerName;
    private List<Order> orders;
    private JTextField txtProductIds; // Trường nhập ID sản phẩm
    private JLabel lblTotalAmount; // Hiển thị tổng tiền
    private JButton btnCompleteOrder;  
    private JButton btnSearchProduct;
    private ProductController productController = new ProductController();

    public OrderView() {
        setTitle("Quản lý Đơn hàng");
        setSize(1728, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        orders = new ArrayList<>();
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        orderTable = new JTable(new OrderTableModel(orders));
        orderTable.setFillsViewportHeight(true);

        btnDelete = new JButton("Xóa");
        backButton = new JButton("Quay lại");
        btnCompleteOrder = new JButton("Hoàn Thành");
        btnStatistic = new JButton("Thống kê");
        // Đặt chiều rộng cho các nút sao cho chiều dài bằng nhau
        btnDelete.setPreferredSize(new Dimension(120, 30));
        btnAddProducts = new JButton("Thêm Món");
        JTextField txtSearchProductId = new JTextField(15);
       btnSearchProduct = new JButton("Tìm Món");
        btnAddProducts.setPreferredSize(new Dimension(120, 30)); // Đặt chiều rộng cho nút Thêm Món
        backButton.setPreferredSize(new Dimension(120, 30)); // Đặt chiều rộng cho nút Quay lại
        btnCompleteOrder.setPreferredSize(new Dimension(120, 30));  
        btnStatistic.setPreferredSize(new Dimension(120, 30));
        txtProductIds = new JTextField(15);
        txtOrderId = new JTextField(15);
        txtCustomerName = new JTextField(15);
        lblTotalAmount = new JLabel("Tổng Tiền: 0 VND");
    }

    private void layoutComponents() {
    // Panel để chứa các form nhập liệu
    JPanel panelForm = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    
    // Cấu hình các trường nhập liệu
    gbc.gridx = 0;
    gbc.gridy = 0;
    panelForm.add(new JLabel("Mã Đơn hàng:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    panelForm.add(txtOrderId, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panelForm.add(new JLabel("Tên Khách hàng:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    panelForm.add(txtCustomerName, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    panelForm.add(new JLabel("ID Món hàng:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    panelForm.add(txtProductIds, gbc);

    // Thêm hiển thị tổng tiền
    gbc.gridx = 0;
    gbc.gridy = 3;
    panelForm.add(lblTotalAmount, gbc);
  

    // Tạo panel chứa các nút điều khiển
    JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // Đặt chiều rộng cho các nút sao cho chiều dài bằng nhau
    btnDelete.setPreferredSize(new Dimension(120, 30));
    btnAddProducts.setPreferredSize(new Dimension(120, 30)); // Đặt chiều rộng cho nút Thêm Món
    backButton.setPreferredSize(new Dimension(120, 30)); // Đặt chiều rộng cho nút Quay lại
    btnCompleteOrder.setPreferredSize(new Dimension(120, 30));  

    // Thêm các nút vào panelButtons
    panelButtons.add(btnAddProducts);
    panelButtons.add(btnDelete);
    panelButtons.add(btnCompleteOrder);
    panelButtons.add(btnStatistic);
    panelButtons.add(backButton);
btnDelete.setBackground(Color.RED);
btnDelete.setForeground(Color.WHITE);

btnAddProducts.setBackground(Color.GREEN);
btnAddProducts.setForeground(Color.WHITE);

btnCompleteOrder.setBackground(Color.BLUE);
btnCompleteOrder.setForeground(Color.WHITE);

btnStatistic.setBackground(Color.YELLOW);
btnStatistic.setForeground(Color.BLACK);

backButton.setBackground(Color.GRAY);
backButton.setForeground(Color.WHITE);

    // Panel chính chứa các phần: form nhập liệu và các nút bấm
    setLayout(new BorderLayout(10, 10));
    add(panelForm, BorderLayout.NORTH);
    add(new JScrollPane(orderTable), BorderLayout.CENTER); // Bảng đơn hàng
    add(panelButtons, BorderLayout.SOUTH); // Nút bấm ở dưới cùng

    // Back Button ActionListener
    backButton.addActionListener(e -> {
        this.dispose();
        CafeView cafeView = CafeView.getInstance();
        if (!cafeView.isVisible()) {
            cafeView.setVisible(true);
        }
    });

    addAddProductsListener();
    addDeleteOrderListener();
    addCompleteOrderListener(); 
    addStatisticListener();
}
     private void addStatisticListener() {
        btnStatistic.addActionListener(e -> {
            double totalRevenue = orders.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();
            showMessage("Tổng Doanh Thu: " + totalRevenue + " VND");
        });
    }

 
    private void addCompleteOrderListener() {
    btnCompleteOrder.addActionListener(e -> {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow >= 0) {
            Order selectedOrder = orders.get(selectedRow);
            selectedOrder.setStatus("Hoàn Thành");  // Cập nhật trạng thái thành "Hoàn Thành"
            ((OrderTableModel) orderTable.getModel()).fireTableDataChanged();  // Cập nhật lại bảng
            showMessage("Đơn hàng đã được hoàn thành!");
        } else {
            showMessage("Vui lòng chọn đơn hàng để hoàn thành!");
        }
    });
}

   private void addAddProductsListener() {
    btnAddProducts.addActionListener(e -> {
        String input = txtProductIds.getText().trim();  // Loại bỏ khoảng trắng thừa ở đầu và cuối
        if (input.isEmpty()) {
            showMessage("Vui lòng nhập ID sản phẩm.");
            return;
        }

        String[] ids = input.split(",");
        double totalAmount = 0;
        List<Product> productsAdded = new ArrayList<>();

        for (String idStr : ids) {
            try {
                idStr = idStr.trim();  // Loại bỏ khoảng trắng giữa các ID
                if (!idStr.isEmpty()) {
                    int productId = Integer.parseInt(idStr);  // Chuyển chuỗi thành số
                    Product product = productController.getProductById(productId);
                    if (product != null) {
                        productsAdded.add(product);
                        totalAmount += product.getPrice();
                    } else {
                        showMessage("Không tìm thấy sản phẩm với ID: " + productId);
                    }
                }
            } catch (NumberFormatException ex) {
                showMessage("ID không hợp lệ: " + idStr);
            }
        }

        lblTotalAmount.setText("Tổng Tiền: " + totalAmount + " VND");

        // Cập nhật lại bảng
        Order newOrder = getOrderInfo();  // Lấy thông tin đơn hàng hiện tại
        if (newOrder != null) {
            newOrder.setTotalAmount((float) totalAmount);  // Cập nhật tổng tiền
            orders.add(newOrder);  // Thêm đơn hàng vào danh sách

            // Cập nhật lại dữ liệu bảng
            ((OrderTableModel) orderTable.getModel()).setOrders(orders);
        }

        txtProductIds.setText(""); // Xóa trường nhập ID sản phẩm
    });
}


    private void addDeleteOrderListener() {
        btnDelete.addActionListener(e -> {
            int selectedRow = orderTable.getSelectedRow();
            if (selectedRow >= 0) {
                orders.remove(selectedRow);
                ((OrderTableModel) orderTable.getModel()).fireTableDataChanged();
                showMessage("Đơn hàng đã được xóa!");
            } else {
                showMessage("Vui lòng chọn đơn hàng để xóa!");
            }
        });
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public Order getOrderInfo() {
        try {
            int orderId = Integer.parseInt(txtOrderId.getText());
            String customerName = txtCustomerName.getText();
            double totalAmount = Double.parseDouble(lblTotalAmount.getText().split(":")[1].replace(" VND", "").trim());
            return new Order(orderId, customerName, (float) totalAmount, java.time.LocalDateTime.now());
        } catch (NumberFormatException e) {
            showMessage("Vui lòng nhập thông tin hợp lệ!");
            return null;
        }
    }

    public void showOrder(Order order) {
        if (order != null) {
            txtOrderId.setText(String.valueOf(order.getOrderId()));
            txtCustomerName.setText(order.getCustomerName());
            lblTotalAmount.setText("Tổng Tiền: " + order.getTotalAmount() + " VND");
        }
    }

    public void showListOrders(List<Order> listOrders) {
        ((OrderTableModel) orderTable.getModel()).setOrders(listOrders);
    }

    public void clearOrderInfo() {
        txtOrderId.setText("");
        txtCustomerName.setText("");
        lblTotalAmount.setText("Tổng Tiền: 0 VND");
    }

    public void fillOrderFromSelectedRow() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow >= 0) {
            Order selectedOrder = orders.get(selectedRow);
            showOrder(selectedOrder);
        }
    }

    public void addListOrderSelectionListener(ListSelectionListener listOrderSelectionListener) {
        this.orderTable.getSelectionModel().addListSelectionListener(listOrderSelectionListener);
    }

    public Order getSelectedOrder() {
        int selectedRow = orderTable.getSelectedRow();
        return (selectedRow >= 0) ? orders.get(selectedRow) : null;
    }

    class OrderTableModel extends AbstractTableModel {
        private List<Order> orders;
        private final String[] columnNames = {"Mã Đơn", "Tên Khách Hàng", "Tổng Tiền", "Ngày Đặt", "Trạng Thái"};

        public OrderTableModel(List<Order> orders) {
            this.orders = orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return orders.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Order order = orders.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return order.getOrderId();
                case 1:
                    return order.getCustomerName();
                case 2:
                    return order.getTotalAmount();
                case 3:
                    return order.getOrderDate();
                case 4:
                    return order.getStatus();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

   
}