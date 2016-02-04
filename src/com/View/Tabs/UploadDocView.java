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
public class UploadDocView extends JPanel {

    private JLabel idLabel;
    private JLabel descreptionLabel;
    private JLabel filenameLabel;
    public JTextField idTextfield;
    public JTextField descreptionTextfield;
    public JTextField filenameTextfield;
    public JButton uploadButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    public UploadDocView(User user) {

        setLayout(new GridLayout(6, 1));

        idLabel = new JLabel("请输入ID:");
        idTextfield = new JTextField();
        descreptionLabel = new JLabel("请输入描述:");
        descreptionTextfield = new JTextField();
        filenameLabel = new JLabel("请输入文件名");
        filenameTextfield = new JTextField();
        uploadButton = new JButton("上传");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idTextfield.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "ID不能为空",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else if (filenameTextfield.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "文件名不能为空",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else {
                    if (SocketClient.uploadFile(
                            idTextfield.getText(),
                            user.getName(),
                            descreptionTextfield.getText(),
                            filenameTextfield.getText()
                    )) {
                        JOptionPane.showMessageDialog(null, "成功上传到服务器",
                                "", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "上传到服务器失败",
                                "", JOptionPane.PLAIN_MESSAGE);
                    }

                }

            }
        });

        panel1 = new JPanel(new GridLayout(1, 2));
        panel2 = new JPanel(new GridLayout(1, 2));
        panel3 = new JPanel(new GridLayout(1, 2));
        panel4 = new JPanel(new GridLayout(1, 3));
        panel1.add(idLabel);
        panel1.add(idTextfield);
        panel2.add(descreptionLabel);
        panel2.add(descreptionTextfield);
        panel3.add(filenameLabel);
        panel3.add(filenameTextfield);
        panel4.add(new JPanel());
        panel4.add(uploadButton);
        panel4.add(new JPanel());

        add(panel1);
        add(panel2);
        add(panel3);
        add(new JPanel());
        add(panel4);
        add(new JPanel());

        this.setVisible(true);
    }
}
