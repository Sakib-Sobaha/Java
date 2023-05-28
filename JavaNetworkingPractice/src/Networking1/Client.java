package Networking1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public Client(String serverAddress, int serverPort){
        try{
            Socket socket = new Socket(serverAddress,serverPort);
            System.out.println("Client Started.......");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Write your message......");
            Scanner input = new Scanner(System.in).useDelimiter("\n");
            String message = input.next();
            oos.writeObject(message);
            System.out.println("From Server : " +ois.readObject());


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        try {
            Socket socket = new Socket("127.0.0.1",22222);
            System.out.println("Client Connected......");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Scanner input = new Scanner(System.in).useDelimiter("\n");
            String message = input.next();
            oos.writeObject(message);

            //Receive from server
            try {
                Object fromServer = ois.readObject();
                System.out.println("From server : "+(String)fromServer);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
