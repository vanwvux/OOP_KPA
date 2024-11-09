package vn.viettuts.qlsv.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "inventoryItems")
@XmlAccessorType(XmlAccessType.FIELD)
public class InventoryItemXML {
    
    private List<InventoryItem> inventoryItem; // Danh sách các mặt hàng trong kho

    public List<InventoryItem> getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(List<InventoryItem> inventoryItem) {
        this.inventoryItem = inventoryItem;
    }
}
