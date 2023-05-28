package TCPtwit;

import util.NetworkUtil;

import java.util.Scanner;

public class Client {

    public Client(String serverAddress, int serverPort) {
        try {

            while(true){
                System.out.println("Here are the options :");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. GetList");
                System.out.println("4. SendMessage");
                System.out.println("5. Broadcast");
                System.out.println("Enter an option: ");
                Scanner input = new Scanner(System.in).useDelimiter("\n");
                String clientName = null;
                int clientPassword = 0;
                int options = input.nextInt();
                if(options == 1){
                    System.out.println("Please Register it is free: ");
                    System.out.println("Enter your name and password");
                     clientName = input.next();
                    clientPassword = input.nextInt();
                }
                else if(options == 2){
                    System.out.println("Enter your name to login: ");
                    clientName = input.next();
                    System.out.println("Enter your password: ");
                    clientPassword = input.nextInt();
                }
                else if(options == 3){
                    System.out.println("Here is the list of existing clients: ");

                }
                else if(options == 4){
                    System.out.println("Send message to your friend: ");
                    NetworkUtil networkUtil = new NetworkUtil(serverAddress,serverPort);
                    networkUtil.write(clientName);
                    networkUtil.write(clientPassword);
                    new ReadThreadClient(networkUtil);
                    new WriteThreadClient(networkUtil,clientName,clientPassword);
                }
                else if (options == 5){
                    System.out.println("Send message to everyone connected to this network: ");
                }
                else if(options == 6){
                    System.exit(0);
                }
            }
            /*System.out.print("Enter name of the client: ");
            Scanner scanner = new Scanner(System.in).useDelimiter("\n");
            String clientName = scanner.next();
            int clientPassword = scanner.nextInt();
            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(clientName);
            networkUtil.write(clientPassword);
            new ReadThreadClient(networkUtil);
            new WriteThreadClient(networkUtil, clientName,clientPassword);*/
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        Client client = new Client(serverAddress, serverPort);

    }
}

