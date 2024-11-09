package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import vn.viettuts.qlsv.dao.ProductSearchDAO;
import vn.viettuts.qlsv.entity.Product;
import vn.viettuts.qlsv.view.ProductSearchView;

public class ProductSearchController {
    private ProductSearchView productSearchView;
    private ProductSearchDAO productSearchDAO;

    public ProductSearchController(ProductSearchView productSearchView) {
        this.productSearchView = productSearchView;
        this.productSearchDAO = new ProductSearchDAO();

        // Gán sự kiện cho nút "Tìm kiếm"
        this.productSearchView.addSearchButtonListener(new SearchProductListener());
    }

    // Lớp xử lý sự kiện tìm kiếm
    class SearchProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String searchQuery = productSearchView.getSearchQuery(); // Lấy từ khóa tìm kiếm
            if (searchQuery != null && !searchQuery.isEmpty()) {
                searchProducts(searchQuery);
            } else {
                productSearchView.showMessage("Vui lòng nhập từ khóa tìm kiếm.");
            }
        }
    }

    // Tìm kiếm sản phẩm từ DAO và cập nhật giao diện
    private void searchProducts(String searchQuery) {
        List<Product> products = productSearchDAO.searchProductByName(searchQuery); // Gọi DAO để tìm kiếm
        if (products.isEmpty()) {
            productSearchView.showMessage("Không tìm thấy sản phẩm.");
        } else {
            productSearchView.displaySearchResults(products); // Hiển thị kết quả tìm kiếm
        }
    }
}
