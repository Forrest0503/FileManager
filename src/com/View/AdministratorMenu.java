

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
public class AdministratorMenu {

    public User user;

    private ShowInfoView showInfoView;
    private ChangePasswordView changePasswordView;
    private ListDocsView listDocsView;
    private DownloadDocView downloadDocView;
    private ListAllUsersView listAllUsersView;
    private UploadDocView uploadDocView;
    private ChangeUserInfoVIew changeUserInfoVIew;
    private AddUserView addUserView;
    private DeleteUserView deleteUserView;
    private DeleteDocsView deleteDocsView;

    private JFrame mainFrame;
    private JLabel title;

    private JTabbedPane tabbedPane;


    public AdministratorMenu(User user) {

        this.user = user;

        showInfoView = new ShowInfoView(user);
        changePasswordView = new ChangePasswordView(user);
        listDocsView = new ListDocsView();
        downloadDocView = new DownloadDocView();
        listAllUsersView = new ListAllUsersView();
        uploadDocView = new UploadDocView(user);
        changeUserInfoVIew = new ChangeUserInfoVIew(user);
        addUserView = new AddUserView();
        deleteUserView = new DeleteUserView();
        deleteDocsView = new DeleteDocsView();

    }

    private void prepareGUI() {
        mainFrame = new JFrame("档案管理系统");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setBounds(200, 200, 900, 300);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
//                SQLProcessor.disconnectFromDB();  //为什么会空指针?
                System.exit(0);
            }
        });

        title = new JLabel("Administrator菜单", JLabel.CENTER);

        tabbedPane = new JTabbedPane();

    }

    private void  showAdministratorMenu() {

        tabbedPane.setPreferredSize(new Dimension(600, 30));

        tabbedPane.add(showInfoView, "显示账户信息");
        tabbedPane.add(changePasswordView, "修改密码");
        tabbedPane.add(changeUserInfoVIew, "修改用户信息");
        tabbedPane.add(addUserView, "新建用户");
        tabbedPane.add(deleteUserView, "删除用户");
        tabbedPane.add(listAllUsersView, "列出所有用户");
        tabbedPane.add(downloadDocView, "下载档案");
        tabbedPane.add(uploadDocView, "上传档案");
        tabbedPane.add(deleteDocsView, "删除档案");
        tabbedPane.add(listDocsView, "列出所有档案");


        mainFrame.add(title, BorderLayout.NORTH);
        mainFrame.add(tabbedPane, BorderLayout.CENTER);

        mainFrame.setVisible(true);

    }

    public void show() {
        prepareGUI();
        showAdministratorMenu();
    }


}


