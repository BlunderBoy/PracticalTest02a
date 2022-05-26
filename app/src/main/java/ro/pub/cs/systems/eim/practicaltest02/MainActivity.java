package ro.pub.cs.systems.eim.practicaltest02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button startService;
    Button clientStartRequest;
    TextView responseTextView;
    TextView serverPort;
    TextView clientAddress;
    TextView clientPort;
    TextView clientData;

    ServerThread serverThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseTextView = findViewById(R.id.resultEditText);
        startService = findViewById(R.id.StarServerButton);
        serverPort = findViewById(R.id.ServerPortEditText);
        clientAddress = findViewById(R.id.ClientConnectAddressEditText);
        clientPort = findViewById(R.id.ClientConnectPortEditText);
        clientData = findViewById(R.id.ClientRequestDataEditText);

        startService.setOnClickListener(v -> {
            serverThread = new ServerThread(Integer.parseInt(serverPort.getText().toString()));
//            serverThread = new ServerThread(6789);
            serverThread.start();
        });

        clientStartRequest = findViewById(R.id.SendRequestButton);

        clientStartRequest.setOnClickListener(v -> {
                int port = Integer.parseInt(clientPort.getText().toString());
                String address = clientAddress.getText().toString();
                String data = clientData.getText().toString();
                ClientThread clientThread = new ClientThread(port, address, data, responseTextView);
//                ClientThread clientThread = new ClientThread(6789, "127.0.0.1", "mul,2,2;add,3,3", responseTextView);
                clientThread.start();
        });
    }
}