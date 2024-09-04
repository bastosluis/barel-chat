package barelchat.model;

// import barelchat.model.exception.NonStringReceivedException;

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

    public void receiveMessage() throws ClassNotFoundException, IOException{
        Object obj = objectInputStream.readObject();
        if (obj instanceof String){
            String message = (String) obj;
            messages.add(message+"\n");
        } else {
            /*
             * TODO: find a better way to get the online users array
             * this can't be good practice. we have 2 problems here:
             * first, this method has 2 responsabilities, it would be better to split it into 2
             * second, we probably have a vunerability in this line bellow, better handling is needed
             */
            ArrayList<String> onlineUsers = (ArrayList<String>) obj;
            this.onlineUsers = onlineUsers;
        }
    }

}