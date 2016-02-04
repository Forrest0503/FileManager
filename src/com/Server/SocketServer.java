package com.Server;

import com.Model.DataProcessor;
import com.Model.SQLProcessor;
import jdk.net.Sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ThreadFactory;

import com.Model.*;

/**
 * Created by Jason on 16/1/4.
 */
public class SocketServer {

    Socket socket;
    ObjectInputStream fromClient;
    ObjectOutputStream toClient;
    ArrayList list_read;
    String name_read;

    private void reInput() throws Exception {
        try {
            list_read = (ArrayList) fromClient.readObject();
            name_read = (String) list_read.get(0);
        } catch (EOFException eofe) {
            name_read = "";
        }
    }

    private void init() throws Exception{
        SQLProcessor.initSQL();
        ArrayList list = new ArrayList();
        list.add("init success");
        toClient.writeObject(list);
        list_read = (ArrayList) fromClient.readObject();
        name_read = (String) list_read.get(0);
    }

    private void login() throws Exception {
        String username = (String)list_read.get(1);
        String password = (String)list_read.get(2);
        User user;
        user = DataProcessor.logIn(username, password);
        toClient.writeObject(user);
        reInput();
    }

    private void changePassword() throws Exception {
        String name = (String)list_read.get(1);
        String pass1 = (String)list_read.get(2);
        String pass2 = (String)list_read.get(3);
        User.changePassword(name, pass1, pass2);
        reInput();
    }

    private void changeUserInfo() throws Exception {
        String name1 = (String)list_read.get(1);
        String pass = (String)list_read.get(2);
        if (Administrator.changeUseInfo(name1, pass)) {
            ArrayList list_result = new ArrayList();
            list_result.add("true");
            toClient.writeObject(list_result);
        } else {
            ArrayList list_result = new ArrayList();
            list_result.add("false");
            toClient.writeObject(list_result);
        }
        reInput();
    }

    private void listAllUser() throws Exception {
        Vector<Vector<String >> data = new Vector<Vector<String >>();
        data = DataProcessor.listAllUser();
        toClient.writeObject(data);
        reInput();
    }

    private void listAllDocs() throws Exception {
        Vector<Vector<String >> data1 = new Vector<Vector<String >>();
        data1 = DataProcessor.listAllDocs();
        toClient.writeObject(data1);
        reInput();
    }

    private void addUser() throws Exception {
        String newName = (String)list_read.get(1);
        String newPass = (String)list_read.get(2);
        String newRole = (String)list_read.get(3);
        if (DataProcessor.insert(newName, newPass, newRole)) {
            ArrayList list_result = new ArrayList();
            list_result.add("true");
            toClient.writeObject(list_result);
        } else {
            ArrayList list_result = new ArrayList();
            list_result.add("false");
            toClient.writeObject(list_result);
        }
        reInput();
    }

    private void deleteUser() throws Exception {
        String oldName = (String)list_read.get(1);
        if (DataProcessor.delete(oldName)) {
            ArrayList list_result = new ArrayList();
            list_result.add("true");
            toClient.writeObject(list_result);
        } else {
            ArrayList list_result = new ArrayList();
            list_result.add("false");
            toClient.writeObject(list_result);
        }
        reInput();
    }

    private void deleteDocs() throws Exception {
        String oldId = (String)list_read.get(1);
        if (DataProcessor.deleteDoc(oldId)) {
            ArrayList list_result = new ArrayList();
            list_result.add("true");
            toClient.writeObject(list_result);
        } else {
            ArrayList list_result = new ArrayList();
            list_result.add("false");
            toClient.writeObject(list_result);
        }
        reInput();
    }

    private void downloadDocs() throws Exception {
        String id1 = (String)list_read.get(1);
        if (DataProcessor.downloadFile(id1)) {
            ArrayList list_result = new ArrayList();
            list_result.add("true");
            toClient.writeObject(list_result);
        } else {
            ArrayList list_result = new ArrayList();
            list_result.add("false");
            toClient.writeObject(list_result);
        }
        reInput();
    }

    private void uploadDocs() throws Exception {
        String id2 = (String)list_read.get(1);
        String creator = (String)list_read.get(2);
        String description = (String)list_read.get(3);
        String filename = (String)list_read.get(4);
        if (DataProcessor.uploadFile(id2, creator, description, filename)) {
            ArrayList list_result = new ArrayList();
            list_result.add("true");
            toClient.writeObject(list_result);



        } else {
            ArrayList list_result = new ArrayList();
            list_result.add("false");
            toClient.writeObject(list_result);
        }
        reInput();
    }

    public SocketServer() {

        try {
            //①资源
            ServerSocket ss = new ServerSocket(10000);

            socket = ss.accept();


            //Socket 输出输入(与客户端通信)
            fromClient = new ObjectInputStream(socket.getInputStream());
            toClient = new ObjectOutputStream(socket.getOutputStream());


            //②：读数据、写到文件、判断结束、反馈成功


            list_read = (ArrayList) fromClient.readObject();
            name_read = (String) list_read.get(0);

            while(name_read != "") {
                switch(name_read) {
                    case "init":
                        init();
                        break;
                    case "login":
                        login();
                        break;
                    case "changePassword":
                        changePassword();
                        break;
                    case "changeUserInfo":
                        changeUserInfo();
                        break;
                    case "listAllUser":
                        listAllUser();
                        break;
                    case "listAllDocs":
                        listAllDocs();
                        break;
                    case "addUser":
                        addUser();
                        break;
                    case "deleteUser":
                        deleteUser();
                        break;
                    case "deleteDoc":
                        deleteDocs();
                        break;
                    case "downloadDoc":
                        downloadDocs();
                        break;
                    case "uploadDoc":
                        uploadDocs();
                        break;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args) throws Exception {
        SocketServer socketServer = new SocketServer();

    }

}
