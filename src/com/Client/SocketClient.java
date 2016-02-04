package com.Client;

import com.Model.User;
import com.View.LoginView;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Jason on 16/1/4.
 */
public class SocketClient {

    static Socket socket;
    static ObjectOutputStream toServer;
    static ObjectInputStream fromServer;

    public static void main(String[] args) {
        LoginView.main();
    }


    public static void init() {
        try {

            //①：定义资源
            socket = new Socket("localhost", 10000);

            toServer = new ObjectOutputStream((socket.getOutputStream()));
            fromServer = new ObjectInputStream(socket.getInputStream());
            ArrayList list = new ArrayList<>();
            list.add("init");
            socket.setSoTimeout(60000);

            //②上传数据

            toServer.writeObject(list);
//            System.out.println(fromServer.readLine());

            //③告诉服务器，客户端已经传完了。并接收服务端的反馈信息
//            socket.shutdownOutput();//关闭客户端的输出流,相当于在流中写-1

            ArrayList result;
            result = (ArrayList) fromServer.readObject();
            System.out.println(result.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User login(String name, String password) {
        try {

            //①：定义资源
            ArrayList list = new ArrayList<>();
            list.add("login");
            list.add(name);
            list.add(password);

            //②上传数据

            toServer.writeObject(list);

            //③告诉服务器，客户端已经传完了。并接收服务端的反馈信息
//            socket.shutdownOutput();//关闭客户端的输出流,相当于在流中写-1

            User user_result = (User)fromServer.readObject();
            return user_result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void changePassword(String name, String pass1, String pass2) {
        try {
            ArrayList list = new ArrayList<>();
            list.add("changePassword");
            list.add(name);
            list.add(pass1);
            list.add(pass2);

            toServer.writeObject(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Boolean changeUserInfo(String name, String role) {
        try {
            ArrayList list = new ArrayList<>();
            list.add("changeUserInfo");
            list.add(name);
            list.add(role);
            toServer.writeObject(list);

            ArrayList result = (ArrayList)fromServer.readObject();
            return result.get(0).equals("true") ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Vector<Vector<String >> listAllUser() {
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        try {
            ArrayList list = new ArrayList<>();
            list.add("listAllUser");
            toServer.writeObject(list);

            data =  (Vector<Vector<String>>)fromServer.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Vector<Vector<String >> listAllDocs() {
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        try {
            ArrayList list = new ArrayList<>();
            list.add("listAllDocs");
            toServer.writeObject(list);

            data =  (Vector<Vector<String>>)fromServer.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Boolean insert(String name, String password, String role) {
        try {
            ArrayList list = new ArrayList<>();
            list.add("addUser");
            list.add(name);
            list.add(password);
            list.add(role);
            toServer.writeObject(list);

            ArrayList result = new ArrayList();
            result = (ArrayList)fromServer.readObject();
            return result.get(0).equals("true") ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean delete(String name) {
        try {
            ArrayList list = new ArrayList<>();
            list.add("deleteUser");
            list.add(name);
            toServer.writeObject(list);

            ArrayList result = new ArrayList();
            result = (ArrayList)fromServer.readObject();
            return result.get(0).equals("true") ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean deleteDoc(String id) {
        try {
            ArrayList list = new ArrayList<>();
            list.add("deleteDoc");
            list.add(id);
            toServer.writeObject(list);

            ArrayList result = new ArrayList();
            result = (ArrayList)fromServer.readObject();
            return result.get(0).equals("true") ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean downloadFile(String id) {
        try {
            ArrayList list = new ArrayList<>();
            list.add("downloadDoc");
            list.add(id);
            toServer.writeObject(list);

            ArrayList result = new ArrayList();
            result = (ArrayList)fromServer.readObject();
            return result.get(0).equals("true") ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean uploadFile(String id, String creater, String description, String filename) {
        try {
            ArrayList list = new ArrayList<>();
            list.add("uploadDoc");
            list.add(id);
            list.add(creater);
            list.add(description);
            list.add(filename);
            toServer.writeObject(list);

            ArrayList result;
            result = (ArrayList)fromServer.readObject();
            return result.get(0).equals("true") ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
