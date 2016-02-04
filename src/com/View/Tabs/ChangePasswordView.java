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
public class ChangePasswordView extends JPanel {
    public JLabel passLabel;
    public JLabel passLabel2;
    public JTextField passTextField;
    public JTextField passTextField2;
    public JButton changePasswordButton;

    public ChangePasswordView(User user) {
        setLayout(new GridLayout(6,1));

        passLabel = new JLabel("新密码: ", JLabel.LEFT);
        passLabel2 = new JLabel("确认新密码: ", JLabel.LEFT);
        passTextField = new JTextField();
        passTextField2 = new JTextField();
        changePasswordButton = new JButton("确定");
        changePasswordButton.setActionCommand("ChangePassword");
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (passTextField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else if (passTextField2.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else if (!passTextField.getText().equals(passTextField2.getText())) {
                    JOptionPane.showMessageDialog(null, "两次输入的密码不一致",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else {
                    SocketClient.changePassword(user.getName(), passTextField.getText(), passTextField2.getText());
//                    user.changePassword(passTextField.getText(), passTextField2.getText());
                    JOptionPane.showMessageDialog(null, "修改成功",
                            "", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
        changePasswordButton.setHorizontalTextPosition(JButton.CENTER);

        passLabel.setPreferredSize(new Dimension(200, 20));
        passLabel2.setPreferredSize(new Dimension(200, 20));
        passTextField.setPreferredSize(new Dimension(100,30));
        passTextField2.setPreferredSize(new Dimension(100,30));

        JPanel panel1 = new JPanel(new GridLayout(1,2));
        panel1.add(passLabel);
        panel1.add(passTextField);
        JPanel panel2 = new JPanel(new GridLayout(1,2));
        panel2.add(passLabel2);
        panel2.add(passTextField2);
        JPanel panel3 = new JPanel(new GridLayout(1,3));
        panel3.add(new JPanel());
        panel3.add(changePasswordButton);
        panel3.add(new JPanel());

        add(new JPanel());
        add(panel1);
        add(panel2);
        add(new JPanel());
        add(panel3);
        add(new JPanel());

    }
}
