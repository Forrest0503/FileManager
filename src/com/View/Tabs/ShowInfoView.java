package com.View.Tabs;

import com.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jason on 15/12/14.
 */
public class ShowInfoView extends JPanel {

    public JLabel usernameLabel;
    public JLabel roleLabel;
    public JButton refreshInfoButton;

    public ShowInfoView(User user) {
        this.setLayout(new GridLayout(6,1));
        usernameLabel = new JLabel("用户名: " + user.getName());
        roleLabel = new JLabel("用户类型: " + user.getRole());
        refreshInfoButton = new JButton("刷新");
        refreshInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameLabel.setText("用户名: " + user.getName());
                roleLabel.setText("用户类型: " + user.getRole());
                repaint();
                System.out.println("refresh");
            }
        });
        refreshInfoButton.setActionCommand("RefreshInfo");

        usernameLabel.setPreferredSize(new Dimension(200,30));
        roleLabel.setPreferredSize(new Dimension(200,30));

        JPanel panel = new JPanel(new GridLayout(1,3));
        panel.add(new JPanel());
        panel.add(refreshInfoButton);
        panel.add(new JPanel());

        this.add(new JPanel());
        this.add(usernameLabel);
        this.add(roleLabel);
        this.add(new JPanel());
        this.add(panel);
        this.add(new JPanel());
    }
}
