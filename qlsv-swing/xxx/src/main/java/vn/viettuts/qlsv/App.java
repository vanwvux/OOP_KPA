package vn.viettuts.qlsv;

import java.awt.EventQueue;
import vn.viettuts.qlsv.controller.CafeController;
import vn.viettuts.qlsv.controller.LoginController;
import vn.viettuts.qlsv.view.LoginView;
import vn.viettuts.qlsv.view.CafeView;
import vn.viettuts.qlsv.entity.Order;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // Create an empty list of orders to be used later in CafeView
            List<Order> orderList = new ArrayList<>();

            // Create and set up the LoginView and LoginController
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);

            // Define the callback action for successful login
            loginController.setOnLoginSuccess(() -> {
                // When login is successful, open the CafeView with the order list
                openCafeView(orderList);
            });

            // Show the login view
            loginController.showLoginView();
        });
    }

    // Method to open the CafeView with the order list
    private static void openCafeView(List<Order> orderList) {
        // Create and set up the CafeView and its controller
        CafeView cafeView = new CafeView();
        CafeController cafeController = new CafeController(cafeView, orderList);
        
        // Show the CafeView
        cafeController.showCafeView();
    }
}
