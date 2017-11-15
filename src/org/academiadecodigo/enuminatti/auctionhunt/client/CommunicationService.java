package org.academiadecodigo.enuminatti.auctionhunt.client;

import org.academiadecodigo.enuminatti.auctionhunt.Service;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 15/11/17.
 */
public class CommunicationService implements Runnable, Service {

    private Socket clientSocket;

    public String readData() {

        String line = null;

        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            line = in.readLine();
            System.out.println(line);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    /**
     * @param data
     */
    public void sendData(String data) {

        System.out.println(data + "                SEND DATA");

        byte[] bytes = new byte[1024];
        DataOutputStream itemOutput;
        DataInputStream dataInputStream;

        try {
           /* if (data.startsWith("/item/")) {

                itemOutput = new DataOutputStream(clientSocket.getOutputStream());
                dataInputStream = new DataInputStream(new FileInputStream(data));

                int bytesReaden = dataInputStream.read(bytes);

                while (bytesReaden != -1) {

                    itemOutput.write(bytes, 0, bytesReaden);
                    itemOutput.flush();
                    bytesReaden = dataInputStream.read(bytes);
                }
            }*/

            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            System.out.println("isto é cenas fixes ------------------");
            out.println(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadImage(String path) {


        byte[] bytes = new byte[1024];
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;

        try {

            fileOutputStream = new FileOutputStream(String.valueOf(clientSocket.getOutputStream()));
            fileInputStream = new FileInputStream(path);
            int bytesRead = fileInputStream.read(bytes);

            while (bytesRead != -1) {

                fileOutputStream.write(bytes, 0, bytesRead);
                bytesRead = fileInputStream.read(bytes);


                fileOutputStream.flush();
            }

            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            out.println(path);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {


        while (true) {


            String answer = ParseClient.getInstance().decodeServerMessage(readData());


            if (answer != null) {


                if (Navigation.getInstance().getController() instanceof LogicController) {


                    LogicController logicController = (LogicController) Navigation.getInstance().getController();
                    logicController.changeView(answer);

                }


            }
        }
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
