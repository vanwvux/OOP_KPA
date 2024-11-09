package vn.viettuts.qlsv.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "inventoryItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class InventoryItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;             // ID của mặt hàng
    private String name;       // Tên mặt hàng
    private int quantity;      // Số lượng trong kho
    private float price;       // Giá của mặt hàng

    public InventoryItem() {
    }

    public InventoryItem(int id, String name, int quantity, float price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
