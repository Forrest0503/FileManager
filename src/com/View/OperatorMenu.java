

package com.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import com.Model.*;
import com.View.Tabs.*;

/**
 * Created by Jason on 15/12/9.
 */
public class OperatorMenu {

    public User user;

    private ShowInfoView showInfoView;
    private ChangePasswordView changePasswordView;
    private ListDocsView listDocsView;
    private DownloadDocView downloadDocView;
    private ListAllUsersView listAllUsersView;
    private UploadDocView uploadDocView;
    private DeleteDocsView deleteDocsView;

    private JFrame mainFrame;
    private JLabel title;

//    private JPanel downloadDocView;

    private JTabbedPane tabbedPane;


    public OperatorMenu(User user) {

        this.user = user;

        showInfoView = new ShowInfoView(user);
        changePasswordView = new ChangePasswordView(user);
        listDocsView = new ListDocsView();
        downloadDocView = new DownloadDocView();
        listAllUsersView = new ListAllUsersView();
        uploadDocView = new UploadDocView(user);
        deleteDocsView = new DeleteDocsView();

    }

    private void prepareGUI() {
        mainFrame = new JFrame("档案管理系统");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setBounds(400, 200, 700, 300);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
//                SQLProcessor.disconnectFromDB();  //为什么会空指针?
                System.exit(0);
            }
        });

        title = new JLabel("Operator菜单", JLabel.CENTER);

        tabbedPane = new JTabbedPane();



    }

    private void  showOperatorMenu() {


        tabbedPane.add(showInfoView, "显示账户信息");
        tabbedPane.add(changePasswordView, "修改密码");
        tabbedPane.add(listAllUsersView, "列出所有用户");
        tabbedPane.add(listDocsView, "列出所有档案");
        tabbedPane.add(downloadDocView, "下载档案");
        tabbedPane.add(uploadDocView, "上传档案");
        tabbedPane.add(deleteDocsView, "删除档案");



        mainFrame.add(title, BorderLayout.NORTH);
        mainFrame.add(tabbedPane, BorderLayout.CENTER);

        mainFrame.setVisible(true);

    }

    public void show() {
        prepareGUI();
        showOperatorMenu();
    }


}


