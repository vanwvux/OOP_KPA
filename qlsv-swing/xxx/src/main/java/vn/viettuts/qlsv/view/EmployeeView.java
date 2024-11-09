package vn.viettuts.qlsv.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel employeeTableModel;
    private JButton btnAdd, btnEdit, btnDelete, backButton;
    private JTextField txtEmployeeName, txtEmployeeID, txtEmployeeRole, txtEmployeeSalary;
    private int selectedRow = -1;

    public EmployeeView() {
        setTitle("Quản lý nhân viên");
        setSize(1728, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        customizeComponents();
    }

    private void initComponents() {
        employeeTableModel = new DefaultTableModel(new String[]{"Tên nhân viên", "Mã nhân viên", "Chức vụ", "Lương"}, 0);
        employeeTable = new JTable(employeeTableModel);
        btnAdd = new JButton("Thêm nhân viên");
        btnEdit = new JButton("Chỉnh sửa");
        btnDelete = new JButton("Xóa");
        backButton = new JButton("Quay lại");

        txtEmployeeName = new JTextField(15);
        txtEmployeeID = new JTextField(10);
        txtEmployeeRole = new JTextField(15);
        txtEmployeeSalary = new JTextField(10);

        // Set listener to load data from selected row
        employeeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    loadSelectedRowData();
                }
            }
        });
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Tên nhân viên:"), gbc);
        gbc.gridx = 1;
        add(txtEmployeeName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Mã nhân viên:"), gbc);
        gbc.gridx = 1;
        add(txtEmployeeID, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Chức vụ:"), gbc);
        gbc.gridx = 1;
        add(txtEmployeeRole, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Lương:"), gbc);
        gbc.gridx = 1;
        add(txtEmployeeSalary, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        add(btnAdd, gbc);
        gbc.gridx = 1;
        add(btnEdit, gbc);
        gbc.gridx = 2;
        add(btnDelete, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backButton, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(employeeTable), gbc);
    }

    private void customizeComponents() {
        btnAdd.setBackground(new Color(0x4CAF50));
        btnAdd.setForeground(Color.WHITE);

        btnAdd.addActionListener(e -> addEmployeeToTable());
        btnEdit.addActionListener(e -> editEmployeeInTable());
        btnDelete.addActionListener(e -> deleteEmployeeFromTable());
        backButton.addActionListener(e -> {
            this.dispose();
            CafeView cafeView = new CafeView();
            cafeView.setVisible(true);
        });
    }

    private void addEmployeeToTable() {
        String name = getEmployeeName();
        String id = getEmployeeID();
        String role = getEmployeeRole();
        String salary = getEmployeeSalary();

        if (name.isEmpty() || id.isEmpty() || role.isEmpty() || salary.isEmpty()) {
            showMessage("Vui lòng nhập đầy đủ thông tin nhân viên.");
            return;
        }

        employeeTableModel.addRow(new Object[]{name, id, role, salary});
        clearInputs();
    }

    private void editEmployeeInTable() {
        if (selectedRow >= 0) {
            employeeTableModel.setValueAt(getEmployeeName(), selectedRow, 0);
            employeeTableModel.setValueAt(getEmployeeID(), selectedRow, 1);
            employeeTableModel.setValueAt(getEmployeeRole(), selectedRow, 2);
            employeeTableModel.setValueAt(getEmployeeSalary(), selectedRow, 3);

            showMessage("Thông tin nhân viên đã được cập nhật.");
            clearInputs();
            selectedRow = -1;
        } else {
            showMessage("Vui lòng chọn một nhân viên để chỉnh sửa.");
        }
    }

    private void deleteEmployeeFromTable() {
        if (selectedRow >= 0) {
            employeeTableModel.removeRow(selectedRow);
            showMessage("Nhân viên đã được xóa.");
            clearInputs();
            selectedRow = -1;
        } else {
            showMessage("Vui lòng chọn một nhân viên để xóa.");
        }
    }

    private void loadSelectedRowData() {
        selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            txtEmployeeName.setText(employeeTableModel.getValueAt(selectedRow, 0).toString());
            txtEmployeeID.setText(employeeTableModel.getValueAt(selectedRow, 1).toString());
            txtEmployeeRole.setText(employeeTableModel.getValueAt(selectedRow, 2).toString());
            txtEmployeeSalary.setText(employeeTableModel.getValueAt(selectedRow, 3).toString());
        }
    }

    public String getEmployeeName() {
        return txtEmployeeName.getText();
    }

    public String getEmployeeID() {
        return txtEmployeeID.getText();
    }

    public String getEmployeeRole() {
        return txtEmployeeRole.getText();
    }

    public String getEmployeeSalary() {
        return txtEmployeeSalary.getText();
    }

    public void clearInputs() {
        txtEmployeeName.setText("");
        txtEmployeeID.setText("");
        txtEmployeeRole.setText("");
        txtEmployeeSalary.setText("");
    }

    public void addAddEmployeeListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addEditEmployeeListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    public void addDeleteEmployeeListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void display() {
        setVisible(true);
    }

    public void showEmployeeData(Object[][] data, String[] columnNames) {
        employeeTableModel.setDataVector(data, columnNames);
    }
}
