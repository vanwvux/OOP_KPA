package vn.viettuts.qlsv.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXML {

    private List<Order> order; // Danh sách đơn hàng

    // Getter và Setter cho order
    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    // Lưu danh sách đơn hàng vào tệp XML
    public void saveOrders(List<Order> orders) {
        try {
            JAXBContext context = JAXBContext.newInstance(OrderXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            OrderXML orderXML = new OrderXML();
            orderXML.setOrder(orders);
            marshaller.marshal(orderXML, new File("orders.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // Tải danh sách đơn hàng từ tệp XML
    public List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            File file = new File("orders.xml");
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(OrderXML.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                OrderXML orderXML = (OrderXML) unmarshaller.unmarshal(file);
                orders = orderXML.getOrder();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Thêm đơn hàng mới vào tệp XML
    public void addOrder(Order newOrder) {
        List<Order> orders = loadOrders();  // Tải danh sách đơn hàng hiện tại từ XML
        orders.add(newOrder);  // Thêm đơn hàng mới vào danh sách
        saveOrders(orders);  // Lưu lại danh sách đơn hàng đã được cập nhật vào tệp XML
    }
}
