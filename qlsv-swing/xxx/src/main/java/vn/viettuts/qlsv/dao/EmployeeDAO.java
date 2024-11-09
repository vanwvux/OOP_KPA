package vn.viettuts.qlsv.dao;

import java.util.ArrayList;
import java.util.List;

import vn.viettuts.qlsv.entity.Employee;
import vn.viettuts.qlsv.entity.EmployeeXML;
import vn.viettuts.qlsv.utils.FileUtils;

/**
 * EmployeeDao class
 * 
 * @author viettuts.vn
 */
public class EmployeeDAO {
    private static final String EMPLOYEE_FILE_NAME = "employee.xml";
    private List<Employee> listEmployees;

    public EmployeeDAO() {
        this.listEmployees = readListEmployees();
        if (listEmployees == null) {
            listEmployees = new ArrayList<Employee>();
        }
    }

    /**
     * Lưu các đối tượng Employee vào file employee.xml
     * 
     * @param employees
     */
    public void writeListEmployees(List<Employee> employees) {
        EmployeeXML employeeXML = new EmployeeXML();
        employeeXML.setEmployee(employees);
        FileUtils.writeXMLtoFile(EMPLOYEE_FILE_NAME, employeeXML);
    }

    /**
     * Đọc các đối tượng Employee từ file employee.xml
     * 
     * @return list employee
     */
    public List<Employee> readListEmployees() {
        List<Employee> list = new ArrayList<Employee>();
        EmployeeXML employeeXML = (EmployeeXML) FileUtils.readXMLFile(
                EMPLOYEE_FILE_NAME, EmployeeXML.class);
        if (employeeXML != null) {
            list = employeeXML.getEmployee();
        }
        return list;
    }

    /**
     * Thêm Employee vào listEmployees và lưu listEmployees vào file
     * 
     * @param employee
     */
    public void add(Employee employee) {
        listEmployees.add(employee);
        writeListEmployees(listEmployees);
    }

    /**
     * Cập nhật Employee vào listEmployees và lưu listEmployees vào file
     * 
     * @param employee
     */
    public void edit(Employee employee) {
        int size = listEmployees.size();
        for (int i = 0; i < size; i++) {
            if (listEmployees.get(i).getId() == employee.getId()) {
                listEmployees.set(i, employee);
                writeListEmployees(listEmployees);
                break;
            }
        }
    }

    /**
     * Xóa Employee từ listEmployees và lưu listEmployees vào file
     * 
     * @param employee
     */
    public boolean delete(Employee employee) {
        boolean isFound = listEmployees.removeIf(emp -> emp.getId() == employee.getId());
        if (isFound) {
            writeListEmployees(listEmployees);
            return true;
        }
        return false;
    }

    /**
     * Lấy danh sách nhân viên
     * 
     * @return list of employees
     */
    public List<Employee> getListEmployees() {
        return listEmployees;
    }

    public void setListEmployees(List<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }
}
