package com.example.habit_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;




public class MainActivity extends AppCompatActivity {

    private WebSocketClient mWebSocketClient;

    public Button  bSendButton;
    private TextView mOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bSendButton = findViewById(R.id.buttonCheckVer);

        mOutput = findViewById(R.id.m_output);

        mOutput.setMovementMethod(new ScrollingMovementMethod());


        connectWebSocket();


    }

    public void getVersion(View view){
        mWebSocketClient.send(" ");
        //mWebSocketClient.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebSocketClient.close();
    }

    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://10.0.2.2:8080/version");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {

            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
            }

            @Override
            public void onMessage(String msg) {
                Log.i("Websocket", "Message Received");
                // Appends the message received to the previous messages
                mOutput.append("\n" +msg);
                if(msg.equals("version: 1.0.2")){
                    mOutput.append("\n" + "The version of app is the latest");

                }
            }

            @Override
            public void onClose(int errorCode, String reason, boolean remote) {
                Log.i("Websocket", "Closed " + reason);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }

    public void moveToLogin(View view){
        Intent in = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(in);
    }


}