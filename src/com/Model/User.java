
package com.Model;

import javax.swing.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Scanner;

public class User implements Serializable{

	private String name;
	private String passWord;
	private String role;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void showMenu() {

	}

    public void showInfo() {
        System.out.println("Username:" + DataProcessor.users.get(getName()).getName() );
        System.out.println("PassWord:" + DataProcessor.users.get(getName()).getPassWord());
        System.out.println("Role:" + DataProcessor.users.get(getName()).getRole());
    }

    public static void changePassword(String name, String pass1, String pass2) {
        try {
            if (pass1.equals(pass2)) {

                String sql = "update users set password = " + "\"" + pass1 + "\"" +
                        " where name = " + "\"" + name + "\"";
                SQLProcessor.exeUpdate(sql);
//                this.setPassWord(pass1);

            } else {
                System.out.print("两次输入不一致");
                JOptionPane.showMessageDialog(null, "两次输入不一致",
                        "", JOptionPane.PLAIN_MESSAGE);
            }

        }

        catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }


}


