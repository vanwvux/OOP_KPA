package vn.viettuts.qlsv.controller;

import vn.viettuts.qlsv.entity.Employee;
import vn.viettuts.qlsv.view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeController {
    private EmployeeView employeeView;
    private ArrayList<Employee> employees;
    private int nextId;

    public EmployeeController(EmployeeView employeeView) {
        this.employeeView = employeeView;
        this.employees = new ArrayList<>();
        this.nextId = 1;

        // Thêm các listener cho các nút
        this.employeeView.addAddEmployeeListener(new AddEmployeeListener());
        this.employeeView.addEditEmployeeListener(new EditEmployeeListener());
        this.employeeView.addDeleteEmployeeListener(new DeleteEmployeeListener());
        
        this.employeeView.addBackListener(new BackListener());
    }

    private class AddEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = employeeView.getEmployeeName();
            String idStr = employeeView.getEmployeeID();
            String position = employeeView.getEmployeeRole();
            String salaryStr = employeeView.getEmployeeSalary();

            // Kiểm tra thông tin hợp lệ
            if (name.isEmpty() || idStr.isEmpty() || position.isEmpty() || salaryStr.isEmpty()) {
                employeeView.showMessage("Vui lòng nhập đủ thông tin nhân viên!");
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                float salary = Float.parseFloat(salaryStr);

                Employee newEmployee = new Employee(id, name, position, salary);
                employees.add(newEmployee);
                employeeView.showMessage("Thêm nhân viên thành công!");

                clearInputs();
                updateEmployeeTable();
            } catch (NumberFormatException ex) {
                employeeView.showMessage("Vui lòng nhập đúng định dạng cho ID và Lương!");
            }
        }
    }

    private class EditEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String idStr = employeeView.getEmployeeID();
            String name = employeeView.getEmployeeName();
            String position = employeeView.getEmployeeRole();
            String salaryStr = employeeView.getEmployeeSalary();

            if (idStr.isEmpty() || name.isEmpty() || position.isEmpty() || salaryStr.isEmpty()) {
                employeeView.showMessage("Vui lòng nhập đủ thông tin nhân viên để chỉnh sửa!");
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                float salary = Float.parseFloat(salaryStr);

                for (Employee employee : employees) {
                    if (employee.getId() == id) {
                        employee.setName(name);
                        employee.setPosition(position);
                        employee.setSalary(salary);
                        employeeView.showMessage("Chỉnh sửa thông tin nhân viên thành công!");
                        clearInputs();
                        updateEmployeeTable();
                        return;
                    }
                }

                employeeView.showMessage("Nhân viên không tồn tại!");
            } catch (NumberFormatException ex) {
                employeeView.showMessage("Vui lòng nhập đúng định dạng cho ID và Lương!");
            }
        }
    }

    private class DeleteEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String idStr = employeeView.getEmployeeID();

            if (idStr.isEmpty()) {
                employeeView.showMessage("Vui lòng nhập mã nhân viên để xóa!");
                return;
            }

            try {
                int id = Integer.parseInt(idStr);

                employees.removeIf(employee -> employee.getId() == id);
                employeeView.showMessage("Xóa nhân viên thành công!");

                clearInputs();
                updateEmployeeTable();
            } catch (NumberFormatException ex) {
                employeeView.showMessage("Vui lòng nhập đúng định dạng cho ID!");
            }
        }
    }

    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearInputs();
        }
    }

    private class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Thực hiện hành động quay lại
            employeeView.dispose(); // Đóng cửa sổ hiện tại
        }
    }

    private void clearInputs() {
        employeeView.clearInputs();
    }

    private void updateEmployeeTable() {
        String[] columnNames = {"ID", "Tên", "Chức vụ", "Lương"};
        Object[][] data = new Object[employees.size()][4];

        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            data[i][0] = emp.getId();
            data[i][1] = emp.getName();
            data[i][2] = emp.getPosition();
            data[i][3] = emp.getSalary();
        }

        employeeView.showEmployeeData(data, columnNames);
    }
    
}
