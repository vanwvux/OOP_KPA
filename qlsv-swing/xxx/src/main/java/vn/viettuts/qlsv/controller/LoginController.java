package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vn.viettuts.qlsv.dao.UserDao;
import vn.viettuts.qlsv.entity.User;
import vn.viettuts.qlsv.view.LoginView;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private Runnable onLoginSuccess;  // Khai báo biến để lưu callback

    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    // Thêm phương thức này để nhận callback sau khi đăng nhập thành công
    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    // Lớp LoginListener
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // Nếu đăng nhập thành công, gọi hành động đã set qua setOnLoginSuccess
                if (onLoginSuccess != null) {
                    onLoginSuccess.run();  // Gọi callback đã được set
                }
                loginView.setVisible(false); // Ẩn màn hình đăng nhập
            } else {
                loginView.showMessage("Tài khoản hoặc mật khẩu không đúng.");
            }
        }
    }
}
