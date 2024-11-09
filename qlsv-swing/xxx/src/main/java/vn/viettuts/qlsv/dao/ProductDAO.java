package vn.viettuts.qlsv.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.viettuts.qlsv.entity.Product;
import vn.viettuts.qlsv.entity.ProductXML;
import vn.viettuts.qlsv.utils.FileUtils;

public class ProductDAO {
    private static ProductDAO instance = null;
    private List<Product> listProducts;
    private ProductXML productXML;
    
    public ProductDAO() {
        productXML = new ProductXML();
        listProducts = productXML.readProductFromXML();
    }
    
    public static ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }
    
    public void writeToFile() {
        productXML.writeProductToXML(listProducts);
    }
    
    public List<Product> getListProducts() {
        return listProducts;
    }

    /**
     * Thêm product vào listProducts và lưu vào file
     * 
     * @param product
     */
    public void add(Product product) {
        int id = 1;
        if (listProducts != null && listProducts.size() > 0) {
            id = listProducts.get(listProducts.size() - 1).getId() + 1;
        }
        product.setId(id);
        listProducts.add(product);
        writeToFile();
    }
    
    /**
     * Cập nhật product vào listProducts và lưu vào file
     * 
     * @param product
     */
    public void edit(Product product) {
        int size = listProducts.size();
        for (int i = 0; i < size; i++) {
            if (listProducts.get(i).getId() == product.getId()) {
                listProducts.get(i).setName(product.getName());
                listProducts.get(i).setPrice(product.getPrice());
                listProducts.get(i).setQuantity(product.getQuantity());
                writeToFile();
                break;
            }
        }
    }
    
    /**
     * Xóa product từ listProducts và lưu vào file
     * 
     * @param product
     */
    public boolean delete(Product product) {
        boolean isFound = false;
        int size = listProducts.size();
        for (int i = 0; i < size; i++) {
            if (listProducts.get(i).getId() == product.getId()) {
                product = listProducts.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listProducts.remove(product);
            writeToFile();
            return true;
        }
        return false;
    }
    
    /**
     * Sắp xếp danh sách product theo name
     */
    public void sortByName() {
        Collections.sort(listProducts, new Comparator<Product>() {
            public int compare(Product product1, Product product2) {
                return product1.getName().compareTo(product2.getName());
            }
        });
    }
    
    /**
     * Sắp xếp danh sách product theo price
     */
    public void sortByPrice() {
        Collections.sort(listProducts, new Comparator<Product>() {
            public int compare(Product product1, Product product2) {
                if (product1.getPrice() > product2.getPrice()) {
                    return 1;
                }
                return -1;
            }
        });
    }
}