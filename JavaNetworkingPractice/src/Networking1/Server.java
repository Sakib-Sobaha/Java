package Networking1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server Started.....");

        while(true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected......");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            //read from client
            Object clientMsg = ois.readObject();
            System.out.println("From Client  + " + (String) clientMsg);

            String serverMsg = (String) clientMsg;
            serverMsg = serverMsg.toUpperCase(Locale.ROOT);

            //Send to client
            oos.writeObject(serverMsg);
        }


    }

}
