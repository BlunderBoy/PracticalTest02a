package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerThread extends Thread{

    private int port;
    private ServerSocket serverSocket;

    public ServerThread(int port) {
        this.port = port;
        try {

            this.serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            Log.e("Eroare", "Nu s-a deschis serverul " + port);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket client_socket = serverSocket.accept();

                Log.d("App", "Accepted a connection");

                if (client_socket != null) {
                    BufferedReader bufferReader = Utils.getReader(client_socket);
                    String request_data = bufferReader.readLine();

                    List<String> operations = Arrays.asList(request_data.split(";"));

                    for (String op : operations) {
                        if (op.startsWith("add")) {
                            List<String> operands = Arrays.asList(op.split(","));
                            Thread sumThread = new SumThread(operands.get(1), operands.get(2), client_socket);
                            sumThread.start();
                        }
                        else {
                            List<String> operands = Arrays.asList(op.split(","));
                            Thread mulThread = new MulThread(operands.get(1), operands.get(2), client_socket);
                            mulThread.start();
                        }
                    }
                } else {
                    Log.e("Erroare", "Null client socket");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

