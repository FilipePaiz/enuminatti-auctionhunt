package org.academiadecodigo.enuminatti.auctionhunt.model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Server  {

    private LinkedList<ServerThread> clientList;
    public static final int PORT = 9090;
    public static final String HOST = "localhost";


    public Server(){

        clientList = new LinkedList<>();

    }

    public static void main(String[] args) {

        Server server = new Server();

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            server.start(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void start(ServerSocket serverSocket) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerThread serverThread;

        while(true){
            try {

                Socket clientSocket = serverSocket.accept();

                if(clientSocket.isConnected()){
                    System.out.println("Connection established");
                }

                serverThread = new ServerThread(clientSocket);
                serverThread.run();

                clientList.add(serverThread);

                //  broadcast();

            } catch (IOException e){
                executorService.shutdown();
                e.printStackTrace();
            }
        }


    }

    private class ServerThread implements Runnable{

        private Socket clientSocket;
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;

        public ServerThread(Socket clientSocket) {

            this.clientSocket = clientSocket;

        }

        /**
         * Reads from the client and save on the resources directory
         */

        @Override
        public void run() {

            try {
                System.out.println("aqui ta");
                dataInputStream = new DataInputStream(clientSocket.getInputStream());
                dataOutputStream = new DataOutputStream(new FileOutputStream("resources/badjoraz.jpg"));
                copyFile();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void copyFile() {

            byte[] bytes = new byte[1024];

            try {
                int bytesReaden = dataInputStream.read(bytes);

                while (bytesReaden != -1){
                    dataOutputStream.write(bytes, 0, bytesReaden);
                    bytesReaden = dataInputStream.read(bytes);
                }

            } catch (IOException e){
                e.printStackTrace();
            } finally {

                closeFiles();
            }

        }


        private void closeFiles() {

            try {
                dataOutputStream.close();
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
