package com.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.View.AdministratorMenu;

import javax.swing.*;

public class Administrator extends User {

    public Administrator() {}

	public Administrator(String name, String passWord, String role) {
		this.setName(name);
		this.setPassWord(passWord);
		this.setRole(role);
	}
	
	public static Boolean changeUseInfo(String name, String role) {

        try {

            String sql_find = "SELECT * FROM users where name = \"" + name + "\"";
            ResultSet rs = SQLProcessor.exeQuery(sql_find);

            if (!rs.next()) {

                JOptionPane.showMessageDialog(null, "用户名不存在",
                        "", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
            else {
                String sql = "update users set role = " + "\"" + role + "\"" +
                        " where name = " + "\"" + name + "\"";
                SQLProcessor.exeUpdate(sql);

                return true;
            }

        }

        catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return false;
	}

	public void showMenu() {

        new AdministratorMenu(this).show();

	}
}