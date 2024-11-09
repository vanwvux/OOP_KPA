package vn.viettuts.qlsv.dao;

import java.util.ArrayList;
import java.util.List;

import vn.viettuts.qlsv.entity.InventoryItem;
import vn.viettuts.qlsv.entity.InventoryItemXML;
import vn.viettuts.qlsv.utils.FileUtils;

/**
 * InventoryItemDAO class
 * 
 * @author viettuts.vn
 */
public class InventoryItemDAO {
    private static final String INVENTORY_ITEM_FILE_NAME = "inventoryitem.xml";
    private List<InventoryItem> listInventoryItems;

    public InventoryItemDAO() {
        this.listInventoryItems = readListInventoryItems();
        if (listInventoryItems == null) {
            listInventoryItems = new ArrayList<>();
        }
    }

    /**
     * Lưu các đối tượng InventoryItem vào file inventory_item.xml
     * 
     * @param inventoryItems danh sách mặt hàng
     */
    public void writeListInventoryItems(List<InventoryItem> inventoryItems) {
        InventoryItemXML inventoryItemXML = new InventoryItemXML();
        inventoryItemXML.setInventoryItem(inventoryItems);
        FileUtils.writeXMLtoFile(INVENTORY_ITEM_FILE_NAME, inventoryItemXML);
    }

    /**
     * Đọc các đối tượng InventoryItem từ file inventory_item.xml
     * 
     * @return list inventory item
     */
    public List<InventoryItem> readListInventoryItems() {
        List<InventoryItem> list = new ArrayList<>();
        InventoryItemXML inventoryItemXML = (InventoryItemXML) FileUtils.readXMLFile(
                INVENTORY_ITEM_FILE_NAME, InventoryItemXML.class);
        if (inventoryItemXML != null) {
            list = inventoryItemXML.getInventoryItem();
        }
        return list;
    }

    /**
     * Thêm InventoryItem vào listInventoryItems và lưu listInventoryItems vào file
     * 
     * @param inventoryItem
     */
    public void add(InventoryItem inventoryItem) {
        listInventoryItems.add(inventoryItem);
        writeListInventoryItems(listInventoryItems);
    }

    /**
     * Cập nhật InventoryItem trong listInventoryItems và lưu vào file
     * 
     * @param inventoryItem
     */
    public void edit(InventoryItem inventoryItem) {
        for (int i = 0; i < listInventoryItems.size(); i++) {
            if (listInventoryItems.get(i).getId() == inventoryItem.getId()) {
                listInventoryItems.set(i, inventoryItem);
                writeListInventoryItems(listInventoryItems);
                break;
            }
        }
    }

    /**
     * Xóa InventoryItem từ listInventoryItems và lưu vào file
     * 
     * @param inventoryItem
     */
    public boolean delete(InventoryItem inventoryItem) {
        boolean isFound = listInventoryItems.removeIf(item -> item.getId() == inventoryItem.getId());
        if (isFound) {
            writeListInventoryItems(listInventoryItems);
        }
        return isFound;
    }

    /**
     * Lấy danh sách mặt hàng trong kho
     * 
     * @return list of inventory items
     */
    public List<InventoryItem> getListInventoryItems() {
        return listInventoryItems;
    }

    public void setListInventoryItems(List<InventoryItem> listInventoryItems) {
        this.listInventoryItems = listInventoryItems;
    }

    /**
     * Lưu danh sách mặt hàng vào file XML
     * 
     * @param inventoryItems danh sách mặt hàng
     */
    public void saveListInventoryItems(List<InventoryItem> inventoryItems) {
        // Cập nhật danh sách mặt hàng hiện tại
        setListInventoryItems(inventoryItems);
        // Ghi danh sách mới vào file XML
        writeListInventoryItems(inventoryItems);
    }
}
