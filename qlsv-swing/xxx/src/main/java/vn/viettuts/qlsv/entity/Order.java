package vn.viettuts.qlsv.entity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderId;                // Mã đơn hàng
    private String customerName;        // Tên khách hàng
    private LocalDateTime orderDate;    // Ngày đặt hàng (dùng LocalDateTime)
    private float totalAmount;          // Tổng số tiền của đơn hàng
    private String status;              // Trạng thái đơn hàng (ví dụ: Đang chờ, Đã hoàn thành)
    private List<Product> products;
    // Constructor mặc định
    public Order(int orderId1, String customerName1) {
        // Khởi tạo các giá trị mặc định
        this.status = "Đang chờ"; 
        this.orderDate = LocalDateTime.now(); // Lấy thời gian hiện tại
    }

    // Constructor với tham số
    public Order(int orderId, String customerName, float totalAmount, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.status = "Đang chờ"; // Trạng thái mặc định
        this.orderDate = orderDate;
    }

    // Getter và Setter
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Phương thức định dạng tiền
    public String getFormattedTotalAmount() {
        NumberFormat formatter = NumberFormat.getInstance(Locale.getDefault());
        return formatter.format(totalAmount);
    }

    // Phương thức định dạng ngày đặt hàng
    public String getFormattedOrderDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return orderDate.format(formatter);
    }

    // Phương thức cập nhật trạng thái đơn hàng (hoàn thành hay đang chờ)
    public void setCompleted(boolean completed) {
        this.status = completed ? "Đã hoàn thành" : "Đang chờ";
    }

      public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
}
