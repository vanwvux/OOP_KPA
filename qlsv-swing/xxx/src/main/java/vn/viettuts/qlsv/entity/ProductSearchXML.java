package vn.viettuts.qlsv.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "productSearchs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSearchXML {
    
    private ProductSearch productSearch;  // Đối tượng chứa danh sách sản phẩm

    // Getter và Setter
    public ProductSearch getProductSearch() {
        return productSearch;
    }

    public void setProductSearch(ProductSearch productSearch) {
        this.productSearch = productSearch;
    }
}
