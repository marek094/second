package com.company.NetOrg;

import com.company.DataBase;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cernym23 on 12.12.16.
 */
public class Server {

    private static final Path filePath = Paths.get(System.getProperty("user.home"),".JavaDatabase");
    private static List<Item> items;

    public static void main(String[] args) throws IOException {


        try (ServerSocket s = new ServerSocket(6666)) {
            System.out.println("Server pripraven");
            try (Socket socket = s.accept()) {
                System.out.println("Akceptuji spojeni");

                BufferedReader in = new BufferedReader(new InputStreamReader( socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter( socket.getOutputStream()), true);

                while (true) {
                    String str = in.readLine();
                    if (str.equals("END"))
                        break;
                    out.println(str);
                }
                System.out.println("Konec spojeni");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void readItems() {
        if (!Files.exists(filePath)) {
            System.out.println("File do not exists!");
            items = new ArrayList<>();
        } else {
            try {
                items = Files.lines(filePath).map(Item::parseItem).collect(Collectors.toList());
            } catch (IOException e) {
                System.out.println("Cannot read");
                System.exit(0);
            }

        }
    }

    public static void writeItems() {
        try {
            List lines = items.stream().map(Item::toString).collect(Collectors.toList());
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Cannot write file");
            System.exit(0);
        }
        filePath.toFile();
    }
}
