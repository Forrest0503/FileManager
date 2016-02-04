
package com.Model;

import java.io.*;
import java.util.*;
import java.sql.*;

import javax.swing.*;

public class DataProcessor {

	static Hashtable<String, User> users;
    static Hashtable<String, Doc> docs;

	static {
		users = new Hashtable<String, User>();
		users.put("jack", new Operator("jack","123","operator"));
		users.put("rose", new Browser("rose","123","browser"));
		users.put("kate", new Administrator("kate","123","administrator"));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        docs = new Hashtable<String,Doc>();
        docs.put("0001",new Doc("0001","jack",timestamp,"Age Source Java","Age.java"));
        docs.put("0002",new Doc("0002","jack",timestamp,"","Age.java"));
        docs.put("0003",new Doc("0003","jack",timestamp,"Age Source Java","Age.java"));

    }

    public static Doc searchDoc(String ID) throws SQLException,IllegalStateException {

        String sql = "select * from docs where id = \"" + ID + "\"";
        ResultSet rs = SQLProcessor.exeQuery(sql);

        if(rs.next()) {
            Doc doc = new Doc(
                    rs.getString("id"),
                    rs.getString("creator"),
                    rs.getTimestamp("timestamp"),
                    rs.getString("description"),
                    rs.getString("filename")
            );
            return doc;

        }
        return null;
    }

    public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException,IllegalStateException {

        String sql = "select * from docs where id = ' "+ID+" '";
        ResultSet rs = SQLProcessor.exeQuery(sql);

        if (rs.next())
            return false;
        else {
            sql = "insert docs values(\"" + ID + "\",\"" + creator + "\",\"" + timestamp + "\",\"" + description + "\",\"" + filename + "\")";
            SQLProcessor.exeUpdate(sql);

            return true;
        }
    }

    public static Vector<Vector<String >> listAllDocs() throws SQLException,IllegalStateException {

        Vector<Vector<String >> data = new Vector<>();

        String sql;
        sql = "select * from docs";
        ResultSet rs = SQLProcessor.exeQuery(sql);
        while(rs.next()) {

            Vector<String> row = new Vector();
            row.add(rs.getString("id"));
            row.add(rs.getString("creator"));
            row.add(rs.getString("timestamp"));
            row.add(rs.getString("description"));
            row.add(rs.getString("filename"));
            data.add(row);
        }

        return data;
    }

	public static Vector<Vector<String >> listAllUser() throws SQLException,IllegalStateException {

        Vector<Vector<String >> data = new Vector<Vector<String >>();

        String sql;
        sql = "select name,role from users";

        ResultSet rs = SQLProcessor.exeQuery(sql);
        while(rs.next()) {
            Vector<String> row = new Vector<>();
            row.add(rs.getString("name"));
            row.add(rs.getString("role"));
            data.add(row);
        }

		return data;
	}
	
	public static User logIn(String name, String password) {


        String sql = "SELECT * FROM users where name = " + "\"" + name + "\"";

        try {

            ResultSet rs = SQLProcessor.exeQuery(sql);
            if(!rs.next()) {
                return null;
            } else if(!password.equals(rs.getString("password"))) {
                return null;
            } else {
                User user;
                switch (rs.getString("role")) {
                    case "Administrator":
                    case "administrator":
                        user = new Administrator();
                        break;
                    case "Operator":
                    case "operator":
                        user = new Operator();
                        break;
                    case "Browser":
                    case "browser":
                        user = new Browser();
                        break;
                    default:
                        user = new Browser();
                }
                user.setName(rs.getString("name"));
                user.setPassWord(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("login fail");

        return null;

    }

    public static User search(String name, String password) throws SQLException,IllegalStateException {

        if (users.containsKey(name)) {
            User temp = users.get(name);
            if ((temp.getPassWord()).equals(password))
                return temp;
        }
        return null;
    }


    public static boolean update(String name, String password, String role) throws SQLException,IllegalStateException {

        User user;

        if (users.containsKey(name)) {

            if (role.equalsIgnoreCase("administrator"))
                user = new Administrator(name,password, role);
            else if (role.equalsIgnoreCase("operator"))
                user = new Operator(name,password, role);
            else
                user = new Browser(name,password, role);
            users.put(name, user);
            return true;
        }else
            return false;
    }

    public static boolean insert(String name, String password, String role) throws SQLException,IllegalStateException {

        String sql = "SELECT * FROM users where name = " + "\"" + name + "\"";
        ResultSet rs = SQLProcessor.exeQuery(sql);
        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "用户已存在",
                    "", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else {
            sql = "insert users values(null, \"" + name + "\",\"" + password + "\",\"" + role + "\")";
            SQLProcessor.exeUpdate(sql);
            return true;
        }
    }

    public static boolean delete(String name) throws SQLException,IllegalStateException {

        String sql = "SELECT * FROM users where name = " + "\"" + name + "\"";
        ResultSet rs = SQLProcessor.exeQuery(sql);
        if (rs.next()){

            sql = "delete from users where name = \"" + name + "\"";
            SQLProcessor.exeUpdate(sql);
            return true;
        }else
            JOptionPane.showMessageDialog(null, "用户不存在",
                    "", JOptionPane.PLAIN_MESSAGE);
            return false;

    }

    public static boolean deleteDoc(String id) throws SQLException,IllegalStateException {
        String sql = "SELECT * FROM docs where id = " + "\"" + id + "\"";
        ResultSet rs = SQLProcessor.exeQuery(sql);
        if (rs.next()) {
            sql = "delete from docs where id = \"" + id + "\"";
            SQLProcessor.exeUpdate(sql);
            return true;
        } else
            JOptionPane.showMessageDialog(null, "ID不存在",
                    "", JOptionPane.PLAIN_MESSAGE);

        return false;
    }



    public static Boolean uploadFile(String id, String creater, String description, String filename) {

        try {

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            if (DataProcessor.insertDoc(id, creater, timestamp, description, filename)) {

                System.out.println("插入成功");

                File f = new File("/Users/Jason/uploadfile/" + filename);
                BufferedOutputStream fout =
                        new BufferedOutputStream(
                                new FileOutputStream(f));
                OutputStreamWriter writer = new OutputStreamWriter(fout, "UTF-8");
                writer.append(id + " " + creater + " " + timestamp + " "
                        + description + " " +filename);


                writer.close();
                fout.close();
                return true;

//                FileServer.start("/Users/Jason/uploadfile/" + filename);

//                FileClient.start("/Users/Jason/filesToUpload/" + filename);

//                File f1 = new File("/Users/Jason/uploadfile/" + filename);
//                BufferedOutputStream fout =
//                        new BufferedOutputStream(
//                                new FileOutputStream(f1));
//                OutputStreamWriter writer = new OutputStreamWriter(fout, "UTF-8");
//                writer.append(id + " " + creater + " " + timestamp + " "
//                        + description + " " + filename);
//                writer.close();
//                fout.close();
//                return true;

            } else {
                System.out.println("ID已存在,插入失败");
                return false;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Boolean downloadFile(String id) {
        try {

            Doc doc = DataProcessor.searchDoc(id);

            if (doc == null) {
                JOptionPane.showMessageDialog(null, "ID不存在",
                        "", JOptionPane.PLAIN_MESSAGE);
                return false;
            } else {
                File f = new File("/Users/Jason/downloadfile/" + doc.getFilename().toString());
                BufferedOutputStream fout =
                        new BufferedOutputStream(
                                new FileOutputStream(f));
                OutputStreamWriter writer = new OutputStreamWriter(fout, "UTF-8");
                writer.append(doc.getID() + " " + doc.getCreator() + " " + doc.getTimestamp() + " "
                        + doc.getDescription() + " " + doc.getFilename());
                writer.close();
                fout.close();
                System.out.print("下载成功");
                return true;
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
