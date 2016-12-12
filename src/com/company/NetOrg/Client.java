package com.company.NetOrg;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by cernym23 on 12.12.16.
 */
public class Client {
    public static void main(String[] args) {
        InetAddress addr;
        try {
            addr = InetAddress.getByName(null);
        } catch (UnknownHostException ex) {
            System.out.println("Unknown host");
            return;
        }
        try (Socket socket = new Socket(addr, 6666)){
            BufferedReader in = new BufferedReader (new InputStreamReader( socket.getInputStream()));
            PrintWriter out = new PrintWriter( new OutputStreamWriter( socket.getOutputStream()), true);
            for(int i = 0; i < 10; i ++) {
                out.println(i);
                String str = in.readLine();
                System.out.println(str);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
          /* nothing */
                }
            }
            out.println("END");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
