package com.example.steven.mddnproject3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.io.IOException;

/**
 * Created by Ashton on 24/05/17.
 */

public class Client extends Thread{

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Thread clientThreadIN;
    private Thread clientThreadOUT;
//    @Override
//    public void run(){
//
//    }

    public void connectToServer(String IP){
        final String ip = IP;
        clientThreadIN = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    socket = new Socket(ip, 3000);
                    dos = new DataOutputStream(socket.getOutputStream());
                    dis = new DataInputStream(socket.getInputStream());
                    System.out.println("Connected");
                } catch (IOException e) {
                    System.out.println("There was a problem setting up the connection");
                    e.printStackTrace();
                }

                while(true){
                    try {
                        if (dis.available() != 0) {

                        }
                    }catch (IOException e){
                        System.out.println("There was something wrong with the input stream");
                    }
                }
            }
        });
        clientThreadIN.start();
    }

    public void sendOrder(String order) {
        final String message = order;
        clientThreadOUT = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("In sendOrder");
                    dos.writeUTF(message);
                    dos.flush();
                } catch (IOException e) {
                    System.out.println("Couldn't send message");
                }

            }

            ;
        });
        clientThreadOUT.start();
    }

}
