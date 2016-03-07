package com.csus.csc258.csc258_group_project;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Ben on 3/6/2016.
 */
public class SocketServerTask extends AsyncTask<JSONObject, Void, Void> {
    private JSONObject mJSONData;
    private boolean mSuccess;

    private InetAddress mHostAddress;
    private int mHostPort;

    // For debugging
    private static final String TAG = "SocketServerTask";

    public SocketServerTask(InetAddress hostAddress, int hostPort) {
        mHostAddress = hostAddress;
        mHostPort = hostPort;
    }

    @Override
    protected Void doInBackground(JSONObject... params) {
        Socket socket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        mJSONData = params[0];

        try {
            // Create a new Socket instance and connect to host
            socket = new Socket(mHostAddress, mHostPort);

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());

            // Transfer JSON object as String to the Server
            dataOutputStream.writeUTF(mJSONData.toString());
            Log.i(TAG, "waiting for response from host");

            // Thread will wait until server replies
            String response = dataInputStream.readUTF();
            if (response != null && response.equals("Connection Accepted"))
                mSuccess = true;
            else
                mSuccess = false;
        } catch (IOException e) {
            e.printStackTrace();
            mSuccess = false;
        } finally {

        }

        return null;
    }
}
