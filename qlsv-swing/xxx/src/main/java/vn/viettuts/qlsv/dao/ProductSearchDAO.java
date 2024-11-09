package vn.viettuts.qlsv.dao;

import vn.viettuts.qlsv.entity.Product;

import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchDAO {

    // Đọc tất cả sản phẩm từ XML
    public static List<Product> getAllProductsFromXML() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Product.class); // Đọc lớp Product
        Unmarshaller unmarshaller = context.createUnmarshaller();
        
        // Đọc file XML vào một đối tượng Product
        File file = new File("productSearch.xml");
        List<Product> productList = new ArrayList<>();
        
        // Đọc danh sách sản phẩm từ file
        JAXBElement<Product> rootElement = (JAXBElement<Product>) unmarshaller.unmarshal(file);

        // Thêm sản phẩm vào danh sách
        productList.add(rootElement.getValue());
        
        return productList;
    }

    // Tìm kiếm sản phẩm theo tên
    public static List<Product> searchProductByName(String searchQuery) {
        try {
            List<Product> allProducts = getAllProductsFromXML();
            List<Product> result = new ArrayList<>();

            // Lọc các sản phẩm có tên chứa từ khóa tìm kiếm
            for (Product product : allProducts) {
                if (product.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                    result.add(product);
                }
            }
            return result;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
