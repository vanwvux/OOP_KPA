package vn.viettuts.qlsv.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class InventoryItemView extends JFrame {
    private JTable inventoryTable;
    private JButton btnAdd, btnEdit, btnDelete, backButton;
    private JTextField txtItemName, txtItemQuantity, txtItemPrice;
    private DefaultTableModel tableModel;  // Mô hình bảng

    public InventoryItemView() {
        setTitle("Quản lý hàng hóa");
        setSize(1728, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        inventoryTable = new JTable();  // Bảng cần thiết lập mô hình cho JTable
        btnAdd = new JButton("Thêm hàng hóa");
        btnEdit = new JButton("Chỉnh sửa");
        btnDelete = new JButton("Xóa");
        backButton = new JButton("Quay lại");

        txtItemName = new JTextField(15);
        txtItemQuantity = new JTextField(5);
        txtItemPrice = new JTextField(10);

        // Tạo mô hình cho bảng (chứa các cột và dữ liệu)
        String[] columnNames = {"Tên hàng hóa", "Số lượng(kg)", "Giá"};
        Object[][] data = {};  // Khởi tạo bảng rỗng
        tableModel = new DefaultTableModel(data, columnNames);
        inventoryTable.setModel(tableModel);
        inventoryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = inventoryTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Điền thông tin vào các trường nhập liệu khi chọn dòng
                    String itemName = (String) tableModel.getValueAt(selectedRow, 0);
                    String itemQuantity = (String) tableModel.getValueAt(selectedRow, 1);
                    String itemPrice = (String) tableModel.getValueAt(selectedRow, 2);

                    txtItemName.setText(itemName);
                    txtItemQuantity.setText(itemQuantity);
                    txtItemPrice.setText(itemPrice);
                }
            }
        });
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Thêm tiêu đề
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Quản lý hàng hóa"), gbc);

        // Thêm các trường nhập thông tin hàng hóa
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Tên hàng hóa:"), gbc);
        gbc.gridx = 1;
        add(txtItemName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Số lượng(kg):"), gbc);
        gbc.gridx = 1;
        add(txtItemQuantity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Giá:"), gbc);
        gbc.gridx = 1;
        add(txtItemPrice, gbc);

        // Thêm các nút chức năng với màu sắc và kiểu dáng đẹp hơn
        styleButton(btnAdd);
        styleButton(btnEdit);
        styleButton(btnDelete);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(btnAdd, gbc);

        gbc.gridx = 1;
        add(btnEdit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(btnDelete, gbc);

        // Thêm bảng hàng hóa
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(inventoryTable), gbc);

        // Thêm nút quay lại
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(backButton, gbc);

        // Thêm hành động cho nút quay lại
        backButton.addActionListener(e -> {
            this.dispose();
            CafeView cafeView = new CafeView();
            cafeView.setVisible(true);
        });

        // Thêm hành động cho nút "Thêm hàng hóa"
        btnAdd.addActionListener(e -> addItemToTable());
    }

    // Phương thức để thêm màu sắc và kiểu dáng cho nút
    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180)); // Blue color
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 40));
    }

    // Phương thức để thêm hàng hóa vào bảng
   private void addItemToTable() {
    String itemName = getItemName();
    String itemQuantity = getItemQuantity();
    String itemPrice = getItemPrice();

    // Kiểm tra nếu các trường dữ liệu không rỗng
    if (!itemName.isEmpty() && !itemQuantity.isEmpty() && !itemPrice.isEmpty()) {
        // Định dạng giá trị itemPrice (giá)
        try {
            double price = Double.parseDouble(itemPrice);
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault()); // Sử dụng định dạng của ngôn ngữ hệ thống (có thể thay bằng Locale cụ thể như Locale("vi", "VN"))
            String formattedPrice = numberFormat.format(price);

            // Thêm dữ liệu vào bảng
            Object[] row = {itemName, itemQuantity, formattedPrice};
            tableModel.addRow(row);

            // Xóa thông tin nhập liệu sau khi thêm
            clearInputs();

            showMessage("Hàng hóa đã được thêm!");
        } catch (NumberFormatException e) {
            showMessage("Giá không hợp lệ. Vui lòng nhập giá trị hợp lệ.");
        }
    } else {
        showMessage("Vui lòng điền đầy đủ thông tin!");
    }
}

    // Lắng nghe hành động cho các nút
    public void addAddInventoryListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

public void addEditInventoryListener(ActionListener listener) {
    // Lắng nghe sự kiện chọn dòng trong bảng
    inventoryTable.getSelectionModel().addListSelectionListener(e -> {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy dữ liệu từ dòng đã chọn
            String itemName = (String) tableModel.getValueAt(selectedRow, 0);
            String itemQuantity = (String) tableModel.getValueAt(selectedRow, 1);
            String itemPrice = (String) tableModel.getValueAt(selectedRow, 2);

            // Điền vào các ô nhập liệu
            txtItemName.setText(itemName);
            txtItemQuantity.setText(itemQuantity);
            txtItemPrice.setText(itemPrice.replace(",", "")); // Loại bỏ dấu phân cách nghìn nếu có
        }
    });

    // Lắng nghe hành động cho nút "Chỉnh sửa"
    btnEdit.addActionListener(e -> {
    int selectedRow = inventoryTable.getSelectedRow();
    if (selectedRow != -1) {
        String itemName = getItemName();
        String itemQuantity = getItemQuantity();
        String itemPrice = getItemPrice();

        // Kiểm tra và cập nhật dữ liệu
        if (!itemName.isEmpty() && !itemQuantity.isEmpty() && !itemPrice.isEmpty()) {
            try {
                // Xử lý giá trị giá và định dạng lại
                double price = Double.parseDouble(itemPrice.replace(",", "")); // Loại bỏ dấu phẩy
                NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
                String formattedPrice = numberFormat.format(price);

                // Cập nhật vào bảng
                tableModel.setValueAt(itemName, selectedRow, 0);
                tableModel.setValueAt(itemQuantity, selectedRow, 1);
                tableModel.setValueAt(formattedPrice, selectedRow, 2);

                showMessage("Hàng hóa đã được cập nhật!");
                // Xóa thông tin nhập liệu sau khi sửa
                clearInputs();
            } catch (NumberFormatException ex) {
                showMessage("Giá không hợp lệ. Vui lòng nhập giá trị hợp lệ.");
            }
        } else {
            showMessage("Vui lòng điền đầy đủ thông tin!");
        }
    } else {
        showMessage("Vui lòng chọn hàng hóa để sửa!");
    }
});
}

public void addDeleteInventoryListener(ActionListener listener) {
    btnDelete.addActionListener(e -> {
    int selectedRow = inventoryTable.getSelectedRow();
    if (selectedRow != -1) {
        // Xác nhận trước khi xóa
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa hàng hóa này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
            showMessage("Hàng hóa đã được xóa!");
            clearInputs(); // Xóa thông tin nhập liệu
        }
    } else {
        showMessage("Vui lòng chọn hàng hóa để xóa!");
    }
});
}






    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    // Các phương thức để lấy thông tin từ các trường nhập liệu
    public String getItemName() {
        return txtItemName.getText();
    }

    public String getItemQuantity() {
        return txtItemQuantity.getText();
    }

    public String getItemPrice() {
        return txtItemPrice.getText();
    }

    // Hiển thị thông tin hàng hóa lên bảng
    public void showInventoryData(Object[][] data, String[] columnNames) {
        tableModel.setDataVector(data, columnNames);
    }

    // Hiển thị thông báo
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
public JTable getInventoryTable() {
        return inventoryTable;
    }
    // Xóa nhập liệu
    public void clearInputs() {
        txtItemName.setText("");
        txtItemQuantity.setText("");
        txtItemPrice.setText("");
    }
   


}
