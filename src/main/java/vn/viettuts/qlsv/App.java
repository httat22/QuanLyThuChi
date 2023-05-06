package vn.viettuts.qlsv;

import java.awt.EventQueue;

import javax.swing.UIManager;

import vn.viettuts.qlsv.controller.AppController;
import vn.viettuts.qlsv.controller.LoginController;
import vn.viettuts.qlsv.view.AppView;
import vn.viettuts.qlsv.view.LoginView;

public class App {
    public static void main(String[] args) {
        // EventQueue.invokeLater(new Runnable() {
        //     public void run() {
        //         LoginView view = new LoginView();
        //         LoginController controller = new LoginController(view);
        //         // hiển thị màn hình login
        //         controller.showLoginView();
        //     }
        // });
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            AppController controller = new AppController(new AppView());
            controller.showAppView();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}