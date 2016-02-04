package com.View.Tabs;

import com.Client.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jason on 15/12/14.
 */
public class AddUserView extends JPanel {

    public JLabel usernameLabel;
    public JTextField usernameTextfield;
    public JLabel passwordLabel;
    public JTextField passwordTextfield;
    public JLabel roleLabel;
    public JComboBox roleComboBox;
    public JButton button;


    public AddUserView() {
        setLayout(new GridLayout(6,1));

        usernameLabel = new JLabel("用户名:", JLabel.LEFT);
        usernameTextfield = new JTextField();
        passwordLabel = new JLabel("密码:");
        passwordTextfield = new JTextField();
        roleLabel = new JLabel("权限: ", JLabel.LEFT);
        roleComboBox = new JComboBox();
        roleComboBox.addItem("administrator");
        roleComboBox.addItem("operator");
        roleComboBox.addItem("browser");

        button = new JButton("确定");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (roleComboBox.getSelectedItem().toString().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "权限不能为空",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else if (usernameTextfield.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else if (passwordTextfield.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else {
                    try {
                        if (SocketClient.insert(usernameTextfield.getText(),
                                passwordTextfield.getText(), roleComboBox.getSelectedItem().toString())) {
                            JOptionPane.showMessageDialog(null, "创建成功",
                                    "", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "创建失败",
                                    "", JOptionPane.PLAIN_MESSAGE);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }

            }
        });
        button.setHorizontalTextPosition(JButton.CENTER);

        usernameLabel.setPreferredSize(new Dimension(200,20));
        usernameTextfield.setPreferredSize(new Dimension(200,30));
        passwordTextfield.setPreferredSize(new Dimension(200,20));
        passwordTextfield.setPreferredSize(new Dimension(200,30));
        roleLabel.setPreferredSize(new Dimension(200, 20));

        JPanel panel1 = new JPanel(new GridLayout(1,2));
        panel1.add(usernameLabel);
        panel1.add(usernameTextfield);
        JPanel panel2 = new JPanel(new GridLayout(1,2));
        panel2.add(passwordLabel);
        panel2.add(passwordTextfield);
        JPanel panel3 = new JPanel(new GridLayout(1,2));
        panel3.add(roleLabel);
        panel3.add(roleComboBox);
        JPanel panel4 = new JPanel(new GridLayout(1,3));
        panel4.add(new JPanel());
        panel4.add(button);
        panel4.add(new JPanel());

        add(new JPanel());
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(new JPanel());

    }
}
