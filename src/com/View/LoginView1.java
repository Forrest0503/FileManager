
/**
 * Created by Jason on 15/12/7.
 */

//package View;

package com.View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
    import java.awt.event.WindowEvent;

import com.Client.SocketClient;
import com.Model.*;


    public class LoginView {

        private JFrame mainFrame;
        private JLabel usernameLabel;
        private JLabel passwordLabel;
        private JTextField usernameTextField;
        private JPasswordField passwordTextField;
        private JButton loginButton;
        private JButton exitButton;
        private JLabel loginStatusLabel;
        private JPanel usernamePanel;
        private JPanel passwordPanel;
        private JPanel loginButtonPanel;


    public LoginView() {
        prepareGUI();
    }

    public static void main(){

        SocketClient.init();
//        SQLProcessor.initSQL();
        LoginView gui = new LoginView();
        gui.showLoginView();

    }



    private void prepareGUI() {

        mainFrame = new JFrame("登录");
        mainFrame.setBounds(400, 250, 400, 150);
        mainFrame.setLayout(new GridLayout(3, 1));

        usernameLabel = new JLabel("用户名:");
        passwordLabel = new JLabel("密码:");
        usernameTextField = new JTextField();
        passwordTextField = new JPasswordField();
        loginStatusLabel = new JLabel("                     ");
        loginButton = new JButton("登录");
        exitButton = new JButton("退出");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
//                SQLProcessor.disconnectFromDB();
                System.exit(0);
            }
        });

        usernameTextField.setPreferredSize(new Dimension(200, 30));
        passwordTextField.setPreferredSize(new Dimension(200, 30));


        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        usernamePanel = new JPanel();
        passwordPanel = new JPanel();
        loginButtonPanel = new JPanel();


        usernamePanel.setLayout(new GridLayout(1,2));
        passwordPanel.setLayout(new GridLayout(1,2));
        loginButtonPanel.setLayout(new FlowLayout());


        mainFrame.add(usernamePanel);
        mainFrame.add(passwordPanel);
        mainFrame.add(loginButtonPanel);

        mainFrame.setVisible(true);
    }

    private void showLoginView(){


        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);
        loginButtonPanel.add(loginButton);
        loginButtonPanel.add(loginStatusLabel);
        loginButtonPanel.add(exitButton);


        mainFrame.pack();

        mainFrame.setVisible(true);

    }

    private void loginAction() {
        User user;

        try {

            //登陆
            user = SocketClient.login(usernameTextField.getText(), passwordTextField.getText());
//            user = DataProcessor.logIn(usernameTextField.getText(), passwordTextField.getText());
            if (user != null) {

                JOptionPane.showMessageDialog(null, "登录成功",
                        "", JOptionPane.PLAIN_MESSAGE);

                mainFrame.dispose();
                user.showMenu();

            } else {
                if (usernameTextField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入用户名",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else if (passwordTextField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入密码",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "用户名或密码错误",
                            "", JOptionPane.PLAIN_MESSAGE);
                }

            }

        }
        catch (IllegalStateException illegalStateException) {
            System.out.println("loginView:" + illegalStateException.getMessage());
        }

    }

}
