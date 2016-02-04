package com.View.Tabs;

import com.Client.SocketClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Jason on 15/12/14.
 */
public class ListAllUsersView extends JPanel {

    public JButton refreshButton;
    public JScrollPane contentPane;
    public JTable table;

    private Vector<String> title;
    private Vector<Vector<String >> dataVec = new Vector<>();

    public ListAllUsersView() {

        setLayout(new BorderLayout());

        title = new Vector<>();
        title.add("用户名");
        title.add("身份");

        refreshButton = new JButton("刷新");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataVec = SocketClient.listAllUser();
//                    dataVec = DataProcessor.listAllUser();

                    DefaultTableModel model = new DefaultTableModel(dataVec,title);
                    table.setModel(model);

                }
                catch (Exception sqle) {
                }


            }
        });

        try {
            dataVec = SocketClient.listAllUser();
//            dataVec = DataProcessor.listAllUser();

        }
        catch (Exception sqle) {
        }

        table = new JTable(dataVec, title) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        contentPane = new JScrollPane(table);

        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
        add(contentPane, BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);

        this.setVisible(true);

    }


}
