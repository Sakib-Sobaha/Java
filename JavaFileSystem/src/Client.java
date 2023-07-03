import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class Client {

    private Socket clientSocket = null;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;
    private Socket clientSocketFile = null;
    private DataInputStream dataInputStreamFile = null;
    private DataOutputStream dataOutputStreamFile = null;

    public void uploadFile(String fileName, String type, int chunkSize, DataInputStream dataInputStream, DataOutputStream dataOutputStream, DataInputStream dataInputStreamFile, DataOutputStream dataOutputStreamFile) throws IOException{
        File uploadFile = new File("src"+File.separator+fileName);
        //System.out.println(uploadFile.createNewFile());
        FileInputStream fileInputStream = new FileInputStream(uploadFile);
        long fileLength = uploadFile.length();
        dataOutputStreamFile.writeUTF("uploadedFile "+fileLength+" "+fileName+" "+type+" "+chunkSize);
        int bytes = 0;
        byte[] buffer = new byte[chunkSize];
        int chunkCount = 0;
        while((bytes=fileInputStream.read(buffer)) != -1){
            chunkCount++;
            dataOutputStreamFile.write(buffer,0,bytes);
            dataOutputStreamFile.flush();

            try{
                String message = dataInputStreamFile.readUTF();
                if(!message.equals("Acknowledged")){
                    System.out.println("Not Acknowledged");
                    break;
                }
            }catch (SocketTimeoutException socketTimeoutException){
                System.out.println("Timeout");
                dataOutputStreamFile.writeUTF("Timeout "+type+" "+ fileName);
                dataOutputStreamFile.flush();
                fileInputStream.close();
                return;
            }
        }
        fileInputStream.close();

        dataOutputStreamFile.writeUTF("Acknowledged");
        dataOutputStreamFile.flush();
        String message = dataInputStreamFile.readUTF();
        if(message.equals("Acknowledged")) System.out.println("File Upload Successful");
        else System.out.println("File Upload Failed");



    }


    public void downloadFile(String fileName, String type, int fileSize, DataInputStream dataInputStream, int chunkSize, String fileId) throws IOException{
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream("src"+File.separator+type+"_"+fileId+"_"+fileName);
        int size = fileSize;
        byte[] buffer = new byte[chunkSize];
        int chunkCount = 0;
        while(size > 0 && (bytes = dataInputStream.read(buffer, 0, Math.min(buffer.length, size))) != -1){
            chunkCount++;
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;
        }
        fileOutputStream.close();
    }

    Client(){
        try{
            clientSocket = new Socket("127.0.0.1", 6666);
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            clientSocketFile = new Socket("127.0.0.1", 6667);
            dataInputStreamFile = new DataInputStream(clientSocketFile.getInputStream());
            dataOutputStreamFile = new DataOutputStream(clientSocketFile.getOutputStream());

        }catch(Exception e){
            System.out.println("Cannot connect to server, Exiting main");
            System.exit(1);
        }

        Thread dataFromServer = new Thread(new Runnable() {
            @Override
            public void run() {
                String textFromServer = null;
                try{
                    textFromServer = dataInputStream.readUTF();
                }catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println("From Server : \n " + textFromServer);

                while(true){
                    System.out.println("From Server ...");
                    Scanner scanner = new Scanner(System.in);
                    String answer = scanner.nextLine();
                    StringTokenizer stringTokenizer = new StringTokenizer(answer," ");
                    Vector<String>tokens = new Vector<>();

                    while(stringTokenizer.hasMoreTokens()){
                        tokens.add(stringTokenizer.nextToken());
                    }

                    try{
                        if(tokens.elementAt(0).equals("f") || tokens.elementAt(0).equals("f_r") || tokens.elementAt(0).equals("b_d") || tokens.elementAt(0).equals("c_d")){
                            dataOutputStreamFile.writeUTF(answer);
                            dataOutputStreamFile.flush();
                        }
                        else{
                            dataOutputStream.writeUTF(answer);
                            dataOutputStream.flush();
                        }
                        if(answer.equals("exit")){
                            System.exit(1);
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                    try{
                        textFromServer = dataInputStream.readUTF();
                        System.out.println("From Server : "+textFromServer);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread fileStream = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("File Stream : ");
                    String textFromServerFile = null;
                    try{
                        textFromServerFile = dataInputStreamFile.readUTF();
                        System.out.println("From Server (File Stream) : "+ textFromServerFile);
                        StringTokenizer stringTokenizer = new StringTokenizer(textFromServerFile," ");
                        Vector<String> tokens = new Vector<>();
                        while(stringTokenizer.hasMoreTokens()){
                            tokens.add(stringTokenizer.nextToken());
                        }
                        if(tokens.elementAt(0).equals("f")){
                            int chunkSize = Integer.parseInt(tokens.elementAt(1));
                            String fileId = tokens.elementAt(2);
                            String fileName = tokens.elementAt(3);
                            String type = tokens.elementAt(4);
                            clientSocketFile.setSoTimeout(30000);
                            uploadFile(fileName,type,chunkSize,dataInputStream,dataOutputStream,dataInputStreamFile,dataOutputStreamFile);
                            clientSocketFile.setSoTimeout(0);
                        }
                        else if(tokens.elementAt(0).equals("file")){
                            int fileSize = Integer.parseInt(tokens.elementAt(1));
                            String fileName = tokens.elementAt(2);
                            String type = tokens.elementAt(3);
                            int chunkSize = Integer.parseInt(tokens.elementAt(4));
                            String fileId = tokens.elementAt(5);
                            downloadFile(fileName,type,fileSize,dataInputStreamFile,chunkSize,fileId);
                            System.out.println("File Downloaded Successful");
                        }
                        else if(tokens.elementAt(0).equals("queue")){
                            String fileName = tokens.elementAt(1);
                            String type = tokens.elementAt(2);
                            dataOutputStreamFile.writeUTF("f "+fileName+" "+type);
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        dataFromServer.start();
        fileStream.start();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();

    }
}
