package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MulThread extends Thread {

    int a;
    int b;
    Socket client_socket;

    public MulThread(String a, String b, Socket client_socket) {
        this.a = Integer.parseInt(a);
        this.b = Integer.parseInt(b);
        this.client_socket = client_socket;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = a * b;

        PrintWriter printWriter = null;
        try {
            printWriter = Utils.getWriter(client_socket);
            printWriter.println(String.valueOf(result));
            Log.d("TAG", "am trimis" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}