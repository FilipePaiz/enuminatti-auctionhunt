package org.academiadecodigo.enuminatti.auctionhunt.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Server {

    private LinkedList<ServerThread> clientList;
    public static final int PORT = 9090;
    public static final String HOST = "localhost";
    public static final String PATH = "resources/";

    public Server() {

        clientList = new LinkedList<>();

    }

    public static void main(String[] args) {


        UserService userService = new MockUserService();
        BidService bidService = new MockBidService();

        ServiceRegistry.getInstance().addService("UserService", userService);
        ServiceRegistry.getInstance().addService("BidService", bidService);

        Server server = new Server();

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            server.start(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("jbh bh");


    }

    private void start(ServerSocket serverSocket) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerThread serverThread;

        while (true) {

            try {

                Socket clientSocket = serverSocket.accept();

                if (clientSocket.isConnected()) {
                    System.out.println("Connection established");
                }

                serverThread = new ServerThread(clientSocket);

                clientList.add(serverThread);

                executorService.submit(serverThread);

                //  broadcast();

            } catch (IOException e) {
                executorService.shutdown();
                e.printStackTrace();
            }
        }


    }

    private class ServerThread implements Runnable {

        private Socket clientSocket;
        private BufferedReader bufferedReader;
        private BufferedWriter bufferedWriter;
        private ParseServer parseServer;

        public ServerThread(Socket clientSocket) {

            this.clientSocket = clientSocket;

            try {

                bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                parseServer = new ParseServer(clientSocket, bufferedWriter, bufferedReader);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Reads from the client and save on the resources directory
         */

        @Override
        public void run() {

            System.out.println("aqui ta");
            while (true) {
                parseServer.decodeMessage();

            }
        }
/*
        private void decodeMessage() {

          //  byte[] bytes = new byte[1024];

            try {
               // int bytesReaden = bufferedReader.read();

                while (bytesReaden != -1){
                   // dataOutputStream.write(bytes, 0, bytesReaden);

                    bytesReaden = bufferedReader.read();
                }

            } catch (IOException e){
                e.printStackTrace();
            } finally {

                closeFiles();
            }

        }
*/


    }
}