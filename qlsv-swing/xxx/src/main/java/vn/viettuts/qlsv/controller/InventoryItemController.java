package vn.viettuts.qlsv.controller;

import vn.viettuts.qlsv.entity.InventoryItem;
import vn.viettuts.qlsv.view.InventoryItemView;
import vn.viettuts.qlsv.dao.InventoryItemDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InventoryItemController {
    private InventoryItemView view;
    private InventoryItemDAO inventoryDao;
    private List<InventoryItem> inventoryItems;

    public InventoryItemController(InventoryItemView view) {
        this.view = view;
        this.inventoryDao = new InventoryItemDAO(); 
        this.inventoryItems = inventoryDao.getListInventoryItems();

        // Gắn các listener cho các nút
        this.view.addAddInventoryListener(new AddInventoryListener());
        this.view.addEditInventoryListener(new EditInventoryListener());
        this.view.addDeleteInventoryListener(new DeleteInventoryListener());
        this.view.addBackListener(new BackListener());

        // Hiển thị dữ liệu hàng hóa ban đầu
        showInventoryData();
    }

    // Listener cho nút "Thêm hàng hóa"
    private class AddInventoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getItemName();
            String quantityStr = view.getItemQuantity();
            String priceStr = view.getItemPrice();

            if (name.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
                view.showMessage("Vui lòng nhập đầy đủ thông tin hàng hóa!");
                return;
            }

            try {
                int quantity = Integer.parseInt(quantityStr);
                float price = Float.parseFloat(priceStr);
                int id = inventoryItems.size() + 1;

                InventoryItem item = new InventoryItem(id, name, quantity, price);
                inventoryDao.add(item);

                view.showMessage("Thêm hàng hóa thành công!");
                showInventoryData(); 
                view.clearInputs();
            } catch (NumberFormatException ex) {
                view.showMessage("Vui lòng nhập số hợp lệ cho số lượng và giá!");
            }
        }
    }

    // Listener cho nút "Chỉnh sửa"
    // Listener for the "Edit Inventory" button
private class EditInventoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = view.getItemName();
        String quantityStr = view.getItemQuantity();
        String priceStr = view.getItemPrice();

        if (name.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
            view.showMessage("Vui lòng chọn mặt hàng cần chỉnh sửa!");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr); // Parse quantity as integer
            float price = Float.parseFloat(priceStr); // Parse price as float

            InventoryItem item = findItemByName(name);
            if (item != null) {
                item.setQuantity(quantity);  // Set updated quantity
                item.setPrice(price);  // Set updated price
                inventoryDao.edit(item);  // Update in the database

                view.showMessage("Chỉnh sửa hàng hóa thành công!");
                showInventoryData(); // Refresh the view to show updated data
                view.clearInputs();  // Clear the inputs in the view
            } else {
                view.showMessage("Không tìm thấy mặt hàng: " + name);
            }
        } catch (NumberFormatException ex) {
            view.showMessage("Vui lòng nhập số hợp lệ cho số lượng và giá!");
        }
    }
}


    // Listener cho nút "Xóa"
    private class DeleteInventoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getItemName();

            if (name.isEmpty()) {
                view.showMessage("Vui lòng nhập tên mặt hàng cần xóa!");
                return;
            }

            InventoryItem item = findItemByName(name);
            if (item != null) {
                if (inventoryDao.delete(item)) {
                    view.showMessage("Xóa hàng hóa thành công!");
                    showInventoryData();
                    view.clearInputs();
                } else {
                    view.showMessage("Xóa hàng hóa không thành công!");
                }
            } else {
                view.showMessage("Không tìm thấy mặt hàng: " + name);
            }
        }
    }

    // Listener cho nút "Quay lại"
    private class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
    }

    // Tìm kiếm mặt hàng theo tên
    private InventoryItem findItemByName(String name) {
        return inventoryItems.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // Hiển thị dữ liệu hàng hóa trong bảng
    private void showInventoryData() {
        inventoryItems = inventoryDao.getListInventoryItems(); 
        String[] columnNames = {"ID", "Tên hàng hóa", "Số lượng", "Giá"};
        Object[][] data = new Object[inventoryItems.size()][4];

        for (int i = 0; i < inventoryItems.size(); i++) {
            InventoryItem item = inventoryItems.get(i);
            data[i][0] = item.getId();
            data[i][1] = item.getName();
            data[i][2] = item.getQuantity();
            data[i][3] = item.getPrice();
        }

        view.showInventoryData(data, columnNames);
    }
}
