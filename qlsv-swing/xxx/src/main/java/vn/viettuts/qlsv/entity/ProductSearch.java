/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.viettuts.qlsv.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "productSearch")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSearch implements Serializable {
    private static List<Product> productList;

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "products")
    private List<Product> products;  // Danh sách các sản phẩm

    // Constructor mặc định
    public ProductSearch() {
    }

    // Constructor với danh sách sản phẩm
    public ProductSearch(List<Product> products) {
        this.products = products;
    }

    // Getter và Setter cho danh sách sản phẩm
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
   

    // Phương thức tìm kiếm sản phẩm theo tên
  public String findProductByName(String name) {
    for (Product product : products) {
        if (product.getName().equalsIgnoreCase(name)) {
            return "ID: " + product.getId() + ", Giá: " + product.getPrice();  // Trả về ID và giá nếu tên trùng
        }
    }
    return "Không tìm thấy sản phẩm";  // Nếu không tìm thấy sản phẩm
}
    public static Optional<Product> searchProductById(String productId) {
        return productList.stream()
                .filter(product -> String.valueOf(product.getId()).equals(productId))
                .findFirst();
    }
}
