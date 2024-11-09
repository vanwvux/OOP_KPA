package vn.viettuts.qlsv.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;               // Mã sản phẩm
    private String name;          // Tên sản phẩm
    private float price;          // Giá sản phẩm
    private String quantity;      // Số lượng
           // Đường dẫn tới hình ảnh sản phẩm

    public Product(int par, String cafe, double par1) {
    }

    public Product(int id, String name, int price, String quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

   
}
