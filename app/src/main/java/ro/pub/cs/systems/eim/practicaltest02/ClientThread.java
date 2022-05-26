package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {


    int port;
    String address;
    String request_data;
    TextView responseTextView;

    ClientThread(int port, String address, String request_data, TextView responseTextView) {
        this.port = port;
        this.address = address;
        this.request_data = request_data;
        this.responseTextView = responseTextView;
    }

    @Override
    public void run() {
        try {
            // opens the connection
            Socket socket = new Socket(address, port);
            //* Add logs and checks */

            // gets I/O
            BufferedReader bufferedReader = Utils.getReader(socket);
            PrintWriter printWriter = Utils.getWriter(socket);

            // sends the requested data
            printWriter.println(request_data);

            // reads the response data
            String response = null;
            String finalResponse = "";
            while (true) {
                response = bufferedReader.readLine();

                // prints the response data
                finalResponse += response + " ";
                String finalResponse1 = finalResponse;
                responseTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        responseTextView.setText(finalResponse1);
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}