package vn.viettuts.qlsv.view;

import vn.viettuts.qlsv.dao.ProductSearchDAO;
import vn.viettuts.qlsv.entity.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductSearchView extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JButton backButton;
    private JTable productTable;

    public ProductSearchView() {
        // Thiết lập giao diện
        setTitle("Tìm kiếm sản phẩm");
        setSize(1728,1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Khởi tạo các thành phần
        searchField = new JTextField(20);
        searchButton = new JButton("Tìm kiếm");
        backButton = new JButton("Quay lại");

        // Tạo bảng để hiển thị kết quả tìm kiếm
        productTable = new JTable();
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Bố cục giao diện
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());  // Sử dụng GridBagLayout để điều chỉnh các thành phần dễ dàng
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Đặt các thành phần lên giao diện
        gbc.insets = new Insets(10, 10, 10, 10);  // Khoảng cách giữa các thành phần
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Tìm sản phẩm:"), gbc);

        gbc.gridx = 1;
        panel.add(searchField, gbc);

        gbc.gridx = 2;
        panel.add(searchButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(backButton, gbc);

        // Tạo bảng kết quả
        JScrollPane scrollPane = new JScrollPane(productTable);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(scrollPane, gbc);

        add(panel, BorderLayout.CENTER);

        // Lắng nghe sự kiện tìm kiếm và quay lại
        searchButton.addActionListener(new SearchButtonListener());
        backButton.addActionListener(e -> backToMainMenu());
    }

    // Lắng nghe sự kiện nút tìm kiếm
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchQuery = searchField.getText().trim();
            if (!searchQuery.isEmpty()) {
                try {
                    // Tìm kiếm sản phẩm theo tên
                    List<Product> foundProducts = ProductSearchDAO.searchProductByName(searchQuery);
                    // Hiển thị kết quả vào bảng
                    displaySearchResults(foundProducts);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ProductSearchView.this, "Có lỗi xảy ra khi tìm kiếm.");
                }
            } else {
                JOptionPane.showMessageDialog(ProductSearchView.this, "Vui lòng nhập từ khóa tìm kiếm.");
            }
        }
    }

    // Hiển thị kết quả tìm kiếm lên bảng
    public void displaySearchResults(List<Product> products) {
        String[][] data = new String[products.size()][4];
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            data[i][0] = String.valueOf(product.getId());
            data[i][1] = product.getName();
            data[i][2] = String.valueOf(product.getPrice());
            data[i][3] = String.valueOf(product.getQuantity());
        }

        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng"};
        productTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        // Tự động điều chỉnh kích thước cột theo nội dung
        for (int i = 0; i < productTable.getColumnCount(); i++) {
            productTable.getColumnModel().getColumn(i).setPreferredWidth(150);
        }
    }

    // Hiển thị thông báo
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Thêm Listener cho nút Tìm kiếm
    public void addSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    // Thêm Listener cho nút Quay lại
    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    // Đóng cửa sổ hiện tại
    public void closeView() {
        this.dispose();
    }

    // Quay lại menu chính
    private void backToMainMenu() {
        // Thực hiện quay lại menu chính
        CafeView cafeView = new CafeView();
        cafeView.setVisible(true);
        this.dispose(); // Đóng cửa sổ hiện tại (nếu có)
    }

    // Phương thức để lấy từ khóa tìm kiếm
    public String getSearchQuery() {
        return searchField.getText().trim();
    }
    
}
