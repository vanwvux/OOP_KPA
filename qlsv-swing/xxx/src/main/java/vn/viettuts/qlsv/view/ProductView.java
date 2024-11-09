package vn.viettuts.qlsv.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductView extends JFrame {

    private JPanel panel;
    private JButton backButton;
    private JLabel imageLabel;

    public ProductView() {
        // Cấu hình cửa sổ
        setTitle("Product View");
        setSize(1920, 1080); 
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        setLocationRelativeTo(null);  

        // Khởi tạo JPanel
        panel = new JPanel();
        panel.setLayout(null); 

        // Tạo nút "Back"
        backButton = new JButton("Back");
        backButton.setBounds(850, 950, 100, 30); 
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Khi nhấn nút Back, quay lại CafeView
                dispose(); // Đóng cửa sổ hiện tại (ProductView)
                CafeView cafeView = CafeView.getInstance(); // Lấy thể hiện của CafeView
                if (!cafeView.isVisible()) {
                    cafeView.setVisible(true); // Hiển thị CafeView nếu nó chưa hiển thị
                }
            }
        });

        // Đường dẫn tuyệt đối đến ảnh sản phẩm
        String imagePath = "C:\\Users\\Admin\\Desktop\\De9-23010320-nguyenvanvu\\qlsv-swing\\xxx\\src\\main\\java\\vn\\viettuts\\qlsv\\img\\menu.png";
        
        // Tải ảnh và chèn vào JLabel
        ImageIcon imageIcon = new ImageIcon(imagePath); 
        imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(50, 50, imageIcon.getIconWidth(), imageIcon.getIconHeight());

        // Thêm ảnh và nút vào panel
        panel.add(imageLabel);
        panel.add(backButton);

        // Thêm panel vào cửa sổ
        add(panel);

        // Hiển thị cửa sổ
        setVisible(true);
    }

    
}
