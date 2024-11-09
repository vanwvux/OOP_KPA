package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vn.viettuts.qlsv.dao.ProductDAO;
import vn.viettuts.qlsv.entity.Product;
import vn.viettuts.qlsv.view.ProductView;

public class ProductController {
    private ProductDAO productDAO;
    private ProductView productView;
    private List<Product> products;

      public ProductController() {
        products = new ArrayList<>();
        
        // Thêm một số sản phẩm mẫu
        products.add(new Product(1, "Cà Phê Sữa Đá", 35000, "100"));
        products.add(new Product(2, "Cà Phê Đen", 29000, "100"));
        products.add(new Product(3, "Bạc Xỉu", 39000, "100"));
        products.add(new Product(4, "Espresso", 35000, "100"));
        products.add(new Product(5, "Cappuccino", 45000, "100"));
        products.add(new Product(6, "Latte", 45000, "100"));
        products.add(new Product(7, "Mocha", 49000, "100"));
        products.add(new Product(8, "Americano", 39000, "100"));
        products.add(new Product(9, "Caramel Macchiato", 49000, "100"));
        products.add(new Product(10, "Cà Phê Trứng", 45000, "100"));
        products.add(new Product(11, "Matcha Latte", 49000, "100"));
        products.add(new Product(12, "Chocolate Đá Xay", 49000, "100"));
        products.add(new Product(13, "Trà Đào Cam Sả", 45000, "100"));
        products.add(new Product(14, "Trà Vải", 45000, "100"));
        products.add(new Product(15, "Sinh Tố Bơ", 49000, "100"));
        products.add(new Product(16, "Sinh Tố Dâu", 45000, "100"));
        products.add(new Product(17, "Nước Ép Cam", 39000, "100"));
        products.add(new Product(18, "Nước Ép Dứa", 39000, "100"));
        
    }
       public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null; // Không tìm thấy sản phẩm
    }
        public List<Product> getAllProducts() {
        return products;
    }
    public ProductController(ProductView view) {
        this.productView = view;
        productDAO = new ProductDAO();
        
     
    }

    
   
}