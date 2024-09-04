package barelchat.model;

import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private String username;
    private ArrayList<String> messages;
    private ArrayList<String> onlineUsers;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Socket clientSocket;

    public void connect(String username, String serverAddress, int port) throws IOException {
        clientSocket = new Socket(serverAddress, port);
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        this.username = username;
    }

    public void sendMessage(String message) throws IOException {
        // TODO: Encrypted message        
        objectOutputStream.writeObject(username + ": " + message);
    }

}