package com.Model;
import java.net.Socket;
import java.io.*;
/**
 * Created by Jason on 16/1/4.
 */
public class FileClient {

    public static void start(String file) {
        try {

            //①：定义资源
            Socket s = new Socket("localhost", 10000);
            s.setSoTimeout(60000);
            BufferedReader bufr = new BufferedReader(new FileReader(file));
            //字符流转换成字节流
            BufferedWriter toServer = new BufferedWriter(
                    new OutputStreamWriter(s.getOutputStream()));
            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));


            //②上传数据
            String line = null;
            while ((line = bufr.readLine()) != null) {

                System.out.println(line);
                toServer.write(line);
                toServer.newLine();
                toServer.flush();
            }

            //③告诉服务器，客户端已经传完了。并接收服务端的反馈信息
            s.shutdownOutput();//关闭客户端的输出流,相当于在流中写-1

            String line2 = null;
            while ((line2 = fromServer.readLine()) != null) {
                if ("success".equals(line2)) {
                    System.out.println("上传文件成功!");
                    break;
                }
            }

            //④关闭资源
//            bufr.close();
//            s.close();
        } catch (Exception e) {}


    }





}
