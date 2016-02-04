package com.View.Tabs;

import com.Client.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jason on 15/12/14.
 */
public class DeleteUserView extends JPanel {

    public JLabel usernameLabel;
    public JTextField usernameTextfield;

    public JButton button;

    public DeleteUserView() {
        setLayout(new GridLayout(6,1));

        usernameLabel = new JLabel("用户名:", JLabel.LEFT);
        usernameTextfield = new JTextField();
        button = new JButton("确定");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (usernameTextfield.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else {
                    try {
                        if (JOptionPane.showConfirmDialog(null, "确定?") == 0) {
                            if (SocketClient.delete(usernameTextfield.getText())) {
                                JOptionPane.showMessageDialog(null, "删除成功",
                                        "", JOptionPane.PLAIN_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "删除失败",
                                        "", JOptionPane.PLAIN_MESSAGE);
                            }
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

        JPanel panel1 = new JPanel(new GridLayout(1,2));
        panel1.add(usernameLabel);
        panel1.add(usernameTextfield);
        JPanel panel2 = new JPanel(new GridLayout(1,3));
        panel2.add(new JPanel());
        panel2.add(button);
        panel2.add(new JPanel());

        add(new JPanel());
        add(panel1);
        add(new JPanel());
        add(new JPanel());
        add(panel2);
        add(new JPanel());

    }
}
