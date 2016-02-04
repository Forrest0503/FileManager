package com.View.Tabs;

import com.Client.SocketClient;
import com.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jason on 15/12/14.
 */
public class ChangeUserInfoVIew extends JPanel {

    public JLabel usernameLabel;
    public JTextField usernameTextfield;
    public JLabel roleLabel;
    public JComboBox roleComboBox;
    public JButton changeRoleButton;

    public ChangeUserInfoVIew(User user) {
        setLayout(new GridLayout(6,1));

        usernameLabel = new JLabel("用户名:", JLabel.LEFT);
        usernameTextfield = new JTextField();
        roleLabel = new JLabel("新权限: ", JLabel.LEFT);
        roleComboBox = new JComboBox();
        roleComboBox.addItem("administrator");
        roleComboBox.addItem("operator");
        roleComboBox.addItem("browser");
        changeRoleButton = new JButton("确定");
        changeRoleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (roleComboBox.getSelectedItem().toString().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "权限不能为空",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else if (usernameTextfield.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else {
                    if (SocketClient.changeUserInfo(usernameTextfield.getText(),
                            roleComboBox.getSelectedItem().toString())) {
                        JOptionPane.showMessageDialog(null, "修改成功",
                                "", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "修改失败",
                                "", JOptionPane.PLAIN_MESSAGE);
                    }

                }

            }
        });
        changeRoleButton.setHorizontalTextPosition(JButton.CENTER);

        usernameLabel.setPreferredSize(new Dimension(200,20));
        usernameTextfield.setPreferredSize(new Dimension(200,30));
        roleLabel.setPreferredSize(new Dimension(200, 20));

        JPanel panel1 = new JPanel(new GridLayout(1,2));
        panel1.add(usernameLabel);
        panel1.add(usernameTextfield);
        JPanel panel2 = new JPanel(new GridLayout(1,2));
        panel2.add(roleLabel);
        panel2.add(roleComboBox);
        JPanel panel3 = new JPanel(new GridLayout(1,3));
        panel3.add(new JPanel());
        panel3.add(changeRoleButton);
        panel3.add(new JPanel());

        add(new JPanel());
        add(panel1);
        add(panel2);
        add(new JPanel());
        add(panel3);
        add(new JPanel());

    }

}
