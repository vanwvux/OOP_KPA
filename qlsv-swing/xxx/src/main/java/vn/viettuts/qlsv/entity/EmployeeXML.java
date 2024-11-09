package vn.viettuts.qlsv.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employees")  // Đặt tên phần tử root là "employees"
@XmlAccessorType(XmlAccessType.FIELD)  // Sử dụng ánh xạ các trường thông qua field
public class EmployeeXML {
    
    @XmlElementWrapper(name = "employeeList")  // Bao bọc danh sách nhân viên trong phần tử "employeeList"
    @XmlElement(name = "employee")  // Đặt tên phần tử của mỗi đối tượng Employee là "employee"
    private List<Employee> employee;  // Danh sách các nhân viên

    // Getter và Setter
    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
