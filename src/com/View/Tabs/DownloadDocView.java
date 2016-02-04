package com.View.Tabs;

import com.Client.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jason on 15/12/14.
 */
public class DownloadDocView extends JPanel {

    private JLabel label;
    public JTextField idTextfield;
    public JButton downloadButton;
    private JPanel panel1;
    private JPanel panel2;

    public DownloadDocView() {

        setLayout(new GridLayout(6,1));

        label = new JLabel("请输入ID:");
        idTextfield = new JTextField();
        downloadButton = new JButton("下载");
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idTextfield.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "ID不能为空",
                            "", JOptionPane.PLAIN_MESSAGE);
                } else {
                    if (SocketClient.downloadFile(idTextfield.getText())) {
                        JOptionPane.showMessageDialog(null, "成功下载到/Users/Jason/downloadfile/文件夹",
                                "", JOptionPane.PLAIN_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "下载失败",
                                "", JOptionPane.PLAIN_MESSAGE);
                    }

                }

            }
        });

        panel1 = new JPanel(new GridLayout(1,2));
        panel2 = new JPanel(new GridLayout(1,3));
        panel1.add(label);
        panel1.add(idTextfield);
        panel2.add(new JPanel());
        panel2.add(downloadButton);
        panel2.add(new JPanel());

        add(new JPanel());
        add(panel1);
        add(new JPanel());
        add(new JPanel());
        add(panel2);
        add(new JPanel());

        this.setVisible(true);

    }


}
