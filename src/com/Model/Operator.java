
package com.Model;
import com.View.OperatorMenu;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.io.*;

public class Operator extends User{

    public Operator() {}
	
	public Operator(String name, String passWord, String role) {

		this.setName(name);
		this.setPassWord(passWord);
		this.setRole(role);
	}
	
//	public void uploadFile() {
//
//        try {
//            Scanner scanner = new Scanner(System.in);
//            String id, description, filename;
//            System.out.print("请输入ID:");
//            id = scanner.next();
//            scanner.nextLine();
//            System.out.print("请输入描述:");
//            description = scanner.next();
//            scanner.nextLine();
//            System.out.print("请输入文件名:");
//            filename = scanner.next();
//            scanner.nextLine();
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//
//
//            if (DataProcessor.insertDoc(id, this.getName(), timestamp, description, filename)) {
//
//                System.out.println("插入成功");
//
//                File f1 = new File("/Users/Jason/uploadfile/" + filename);
//                BufferedOutputStream fout =
//                        new BufferedOutputStream(
//                            new FileOutputStream(f1));
//                OutputStreamWriter writer = new OutputStreamWriter(fout, "UTF-8");
//                writer.append(id + " " + this.getName() + " " + timestamp + " "
//                        + description + " " + filename);
//                writer.close();
//                fout.close();
//
//            } else {
//                System.out.println("ID已存在,插入失败");
//            }
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//	}
//	public void downloadFile(String id) {
//
//        DataProcessor.downloadFile(id);
//
//    }

	public  void showMenu() {

        new OperatorMenu(this).show();

	}
}