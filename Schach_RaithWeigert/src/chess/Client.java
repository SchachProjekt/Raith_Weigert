package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);
        String ip = "192.168.8.102";
        try {
            clientSocket = new Socket(ip,6969);
            System.out.println("Erfolgreich mit Schach verbunden");
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread reciever = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    try {
                        msg = in.readLine();

                        while (msg!=null){
                            System.out.println(msg);
                            msg = in.readLine();
                        }
                        System.out.println("Server Offline");
                        out.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            reciever.start();


            Thread sender = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while (true){
                        msg = sc.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            });

            sender.start();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}