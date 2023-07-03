import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

public class Server {
    long MAX_BUFFER_SIZE = 100000*1024;
    int MIN_CHUNK_SIZE = 10;
    int MAX_CHUNK_SIZE = 50;
    volatile long CUR_BUFFER_SIZE = 0;

    int requestId = 0;
    Vector<Uploader> uploaders = new Vector<>();
    Vector<Integer> requestUploaderId = new Vector<>();

    public Uploader searchUploader(int id){
        for(Uploader uploader:uploaders){
            if(uploader.getId() == id) return uploader;
        }
        return null;
    }

    public void removeUploader(int id){
        Iterator<Uploader> it = uploaders.iterator();
        while(it.hasNext()){
            Uploader uploader = it.next();
            if(uploader.getId() == id) it.remove();
        }
    }

    public String[] findPublicFiles(int upId){
        File publicDir = new File("files"+File.separator+upId+File.separator+"public");
        return publicDir.list();
    }

    public String[] findPrivateFiles(int upId){
        File privateDir = new File("files"+File.separator+upId+File.separator+"private");
        return privateDir.list();
    }

    public int getRandomNumber(int x,int y){
        Random random = new Random();
        return (x+(Math.abs(random.nextInt()) % (y-x+1)))*1024;
    }

    Vector<Integer> randomInteger = new Vector<>();

    public String getFileId(Uploader uploader){
        int t;
        Random random = new Random();
        while(true){
            t = random.nextInt();
            if(!randomInteger.contains(t)) break;
        }
        randomInteger.add(t);
        return uploader.getId()+"_"+t;
    }

    public boolean uploadFile(String fileName, String type, int fileSize, int uploaderId, DataInputStream dataInputStreamFile, DataOutputStream dataOutputStreamFile, int chunkSize) throws IOException {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream("files"+File.separator+uploaderId+File.separator+type+File.separator+fileName);

        try{
            int size = fileSize;
            byte[] buffer = new byte[chunkSize];
            int chunkCount = 0;
            CUR_BUFFER_SIZE += chunkSize;
            while(size > 0){
                boolean flag;
                try{
                    flag = (bytes = dataInputStreamFile.read(buffer,0,Math.min(buffer.length, size))) != -1;


                }catch(SocketTimeoutException socketTimeoutException){
                    CUR_BUFFER_SIZE -= chunkSize;
                    fileOutputStream.close();
                    System.out.println("FileOutputStream Closed.");
                    return false;
                }

                if(!flag) break;
                if(chunkCount % 10000 == 0) System.out.println("ChunkNo #"+chunkCount);
                chunkCount++;
                fileOutputStream.write(buffer,0,bytes);
                size -= bytes;
                dataOutputStreamFile.writeUTF("Acknowledged");
                dataOutputStreamFile.flush();
            }
            CUR_BUFFER_SIZE -= chunkSize;
            fileOutputStream.close();
            System.out.println("FileOutputStream Closed.");
        }catch(Exception e){
            System.out.println("File System Exception");
            CUR_BUFFER_SIZE -= chunkSize;
            fileOutputStream.close();
            System.out.println("FileOutputStream Closed.");
        }

        String message = dataInputStreamFile.readUTF();
        if(message.equals("Acknowledged")){
            File file = new File("files"+File.separator+uploaderId+File.separator+type+File.separator+fileName);
            if(file.length() != fileSize){
                System.out.println("File Size does not match");
                System.out.println("Deleting File...");
                file.delete();
                return false;

            }
        }
        else{
            File file = new File("files"+File.separator+uploaderId+File.separator+type+File.separator+fileName);
            file.delete();
            return false;
        }

        return true;

    }

    public void downloadFile(String fileName, String type, int upId, int chunkSize, DataOutputStream dataOutputStream, String fileId) throws IOException{
        File file = new File("files"+File.separator+upId+File.separator+type+File.separator+fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        try{
            long fileLength = file.length();
            dataOutputStream.writeUTF("file "+fileLength+" "+fileName+" "+type+" "+chunkSize+" "+fileId);
            dataOutputStream.flush();
            int bytes = 0;
            byte[] buffer = new byte[chunkSize];
            int chunkCount = 0;
            while((bytes=fileInputStream.read(buffer))!=-1){
                if(chunkCount%10000 == 0) System.out.println("ChunkNo #"+chunkCount);
                chunkCount++;
                dataOutputStream.write(buffer,0,bytes);
                dataOutputStream.flush();
            }
            fileInputStream.close();
        }catch(Exception e){
            fileInputStream.close();
        }
    }

    Server() throws IOException{
        ServerSocket serverSocket = new ServerSocket(6666);
        ServerSocket serverSocketFile = new ServerSocket(6667);

        while(true){
            Socket conSocket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(conSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(conSocket.getOutputStream());
            Socket conSocketFile = serverSocketFile.accept();
            DataInputStream dataInputStreamFile = new DataInputStream(conSocketFile.getInputStream());
            DataOutputStream dataOutputStreamFile = new DataOutputStream(conSocketFile.getOutputStream());

            Uploader currentUploader =  new Uploader(conSocket.getPort(), dataInputStream,dataOutputStream,dataInputStreamFile,dataOutputStreamFile);
            System.out.println("A client enters into file system " + conSocket.getPort()+ " file "+conSocket.getPort());
            Thread newThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Server Started...");
                    try{
                        currentUploader.takeId();
                        String textFromClient = dataInputStream.readUTF();
                        int id = Integer.parseInt((textFromClient));
                        if(isLoggedIn(id)){
                            currentUploader.writeText("You are already logged in...");
                            Thread.currentThread().interrupt();
                            return;
                        }
                        else if(hasLoggedIn(id)){
                            for(Uploader uploader:uploaders){
                                if(uploader.getId() == id){
                                    currentUploader.update(uploader.getMessageVector(),uploader.getCountFile());
                                    break;
                                }
                            }
                            removeUploader(id);
                            currentUploader.setId(id);
                            uploaders.add(currentUploader);
                        }
                        else{
                            currentUploader.setId(id);
                            uploaders.add(currentUploader);
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }

                    while(true){
                        String textFromClient;
                        try{
                            textFromClient = dataInputStream.readUTF();
                            System.out.println("Text From Client {" + currentUploader.getClientSocketId() + " , "+currentUploader.getId() + "} : " + textFromClient);
                            StringTokenizer stringTokenizer = new StringTokenizer(textFromClient," ");
                            Vector<String>tokens = new Vector<>();

                            while(stringTokenizer.hasMoreTokens()){
                                tokens.add(stringTokenizer.nextToken());
                            }
                            if(tokens.elementAt(0).equals("a")){
                                //Look up list of clients. Currently Online distinguishable from offline
                                currentUploader.writeText(getUploaders());
                            }
                            else if(tokens.elementAt(0).equals("b")){
                                //Look up uploaded files. public and private distinguishable
                                String[] publicFiles = findPublicFiles(currentUploader.getId());
                                String filesList = "Uploaded Public Files : \n";
                                for(String str:publicFiles){
                                    filesList += (str+"\n");
                                }
                                String[] privateFiles = findPrivateFiles(currentUploader.getId());
                                filesList += "Uploaded Private Files : \n";
                                for(String str:privateFiles){
                                    filesList += (str+"\n");
                                }
                                currentUploader.writeText(filesList);
                            }
                            else if(tokens.elementAt(0).equals("c")){
                                //Look up public files of others and download
                                int uploaderId = Integer.parseInt(tokens.elementAt(1));
                                if(inSystem(uploaderId)){
                                    String[] publicFiles = findPublicFiles(uploaderId);
                                    String fileList = "Public Files of "+uploaderId+" : \n ";
                                    for(String str:publicFiles){
                                        fileList += (str+"\n");
                                    }
                                    currentUploader.writeText(fileList);
                                }
                                else{
                                    currentUploader.writeText("Uploader is not in system.");
                                }
                            }
                            else if(tokens.elementAt(0).equals("d")){
                                //file request short file description
                                String fileDescription = "";
                                for(int i=1; i<tokens.size(); i++){
                                    fileDescription += tokens.elementAt(i)+" ";
                                }
                                String message = "File Request ... Request ID : "+requestId+" , Description : "+fileDescription;
                                requestId++;
                                for(Uploader to:uploaders){
                                    sendMessage(currentUploader.getId(),to.getId(),message);
                                }
                                currentUploader.writeText("File Request Broadcast");
                                requestUploaderId.add(currentUploader.getId());
                            }
                            else if(tokens.elementAt(0).equals("showMessage")){
                                System.out.println("View");
                                Vector<String>messageVector = currentUploader.showUnreadMessage();
                                String sent = "Unread Messages\n";
                                System.out.println(sent);
                                for(String str:messageVector){
                                    sent += (str+"\n");
                                }
                                System.out.println("Sent : "+sent);
                                currentUploader.writeText(sent);
                            }
                            else if(tokens.elementAt(0).equals("Send")){
                                System.out.println("Sending Message ... ");
                                int sentTo = Integer.parseInt(tokens.elementAt(1));
                                String sentMessage = "";
                                for(int i =2; i<tokens.size(); ++i){
                                    sentMessage += tokens.elementAt(i)+" ";
                                }
                                if(inSystem(sentTo)){
                                    sendMessage(currentUploader.getId(),sentTo,sentMessage);
                                    currentUploader.writeText("Message sent successfully.");
                                }
                                else currentUploader.writeText("Not in the system.");
                            }
                            else if(tokens.elementAt(0).equals("exit")){
                                currentUploader.goOffline();
                                Thread.currentThread().interrupt();
                                return;
                            }

                        }catch(Exception e){
                            currentUploader.goOffline();
                            Thread.currentThread().interrupt();
                            return;
                        }

                    }
                }
            });
            newThread.start();

            Thread newThreadFile = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try{
                            String textFromClient = dataInputStreamFile.readUTF();
                            System.out.println("Text From Client (File) {"+currentUploader.getClientSocketId()+" , "+currentUploader.getId()+"} : "+textFromClient);
                            StringTokenizer stringTokenizer = new StringTokenizer(textFromClient," ");
                            Vector<String>tokens = new Vector<>();
                            while(stringTokenizer.hasMoreTokens()){
                                tokens.add(stringTokenizer.nextToken());
                            }
                            if(tokens.elementAt(0).equals("b_d")){
                                //download own requested files from uploader : fileName type
                                String fileName = tokens.elementAt(1);
                                String type = tokens.elementAt(2);
                                currentUploader.writeText("Download Started...");
                                downloadFile(fileName,type, currentUploader.getId(),MAX_CHUNK_SIZE*1024, currentUploader.getDataOutputStreamFile(),getFileId(currentUploader));

                            }
                            else if(tokens.elementAt(0).equals("c_d")){
                                //download file request from user : student_id file_name
                                int upId = Integer.parseInt(tokens.elementAt(1));
                                String type = "public";
                                String fileName = tokens.elementAt(2);
                                currentUploader.writeText("Download Started...");
                                downloadFile(fileName,type,upId,50,currentUploader.getDataOutputStreamFile(),getFileId(currentUploader));

                            }
                            else if(tokens.elementAt(0).equals("f")){
                                //from user : f fileName type
                                //Upload file
                                String fileName = tokens.elementAt(1);
                                String type = tokens.elementAt(2);
                                int chunkSize = getRandomNumber(MIN_CHUNK_SIZE, MAX_CHUNK_SIZE*1024);
                                if(CUR_BUFFER_SIZE + chunkSize <= MAX_BUFFER_SIZE){
                                    System.out.println("...");
                                    currentUploader.writeText("Size within range.");
                                    currentUploader.writeToFileStream("f "+chunkSize+" "+getFileId(currentUploader)+" "+fileName + " " + type);

                                }
                                else{
                                    System.out.println("File Size exceeds buffer size.");
                                    currentUploader.writeText("Size exceeds");
                                    currentUploader.writeToFileStream("Server Buffer engaged ... put to queue");

                                    while(CUR_BUFFER_SIZE + chunkSize > MAX_BUFFER_SIZE) {}
                                    System.out.println("Uploading from queue...");
                                    currentUploader.writeToFileStream("queue "+fileName+" "+type);
                                }
                            }
                            else if(tokens.elementAt(0).equals("f_r")){
                                //from user : f_r fileName requestId
                                //Upload file corresponding to user id
                                String fileName = tokens.elementAt(1);
                                String type = "public";
                                int requestId = Integer.parseInt(tokens.elementAt(2));
                                if(requestId < requestUploaderId.size()){
                                    int chunkSize = getRandomNumber(MIN_CHUNK_SIZE,MAX_CHUNK_SIZE*1024);
                                    if(CUR_BUFFER_SIZE + chunkSize <= MAX_BUFFER_SIZE){
                                        System.out.println("...");
                                        currentUploader.writeText("Size within range");
                                        currentUploader.writeToFileStream("f "+chunkSize+" "+getFileId(currentUploader)+" "+fileName + " " + type);
                                    }
                                    else{
                                        System.out.println("File Size exceeds buffer size.");
                                        currentUploader.writeText("Size exceeds");
                                        currentUploader.writeToFileStream("Server Buffer engaged ... put to queue");

                                        while(CUR_BUFFER_SIZE + chunkSize > MAX_BUFFER_SIZE) {}
                                        System.out.println("Uploading from queue...");
                                        currentUploader.writeToFileStream("queue "+fileName+" "+type);
                                    }

                                    int clientId = requestUploaderId.get(requestId);
                                    String message = "Your requested file uploaded ... Request ID : "+requestId;
                                    sendMessage(currentUploader.getId(),clientId,message);
                                }
                                else{
                                    currentUploader.writeText("Request id not in the system.");
                                }
                            }
                            else if(tokens.elementAt(0).equals("file")){
                                int fileSize = Integer.parseInt(tokens.elementAt(1));
                                String fileName = tokens.elementAt(2);
                                String type = tokens.elementAt(3);
                                int chunkSize = Integer.parseInt(tokens.elementAt(4));

                                try{
                                    conSocketFile.setSoTimeout(30000);
                                    boolean fileReceived = uploadFile(fileName,type,fileSize,currentUploader.getId(),dataInputStreamFile,dataOutputStreamFile,chunkSize);
                                    conSocketFile.setSoTimeout(0);

                                    if(fileReceived){
                                        currentUploader.writeToFileStream("Acknowledged");
                                        System.out.println("file Uploaded Successfully.");
                                    }
                                    else{
                                        File upFile = new File("files"+File.separator+currentUploader.getId()+File.separator+type+File.separator+fileName);
                                        System.out.println(upFile.delete());
                                        currentUploader.writeToFileStream("NotAcknowledged");
                                        System.out.println("File Upload Failed");
                                    }
                                }catch(Exception e){
                                    File upFile = new File("files"+File.separator+currentUploader.getId()+File.separator+type+File.separator+fileName);
                                    System.out.println(upFile.delete());
                                    currentUploader.writeToFileStream("File Deleted");
                                    System.out.println("File Upload Failed");
                                }
                            }
                            else if(tokens.elementAt(0).equals("Timeout")){
                                String type = tokens.elementAt(1);
                                String fileName = tokens.elementAt(2);

                                File upFile = new File("files"+File.separator+currentUploader.getId()+File.separator+type+File.separator+fileName);
                                System.out.println(upFile.delete());
                                currentUploader.writeToFileStream("File Deleted");
                            }

                        }catch(Exception e){
                            currentUploader.goOffline();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                }
            });
            newThreadFile.start();
        }

    }

    public void sendMessage(int upMessage,int downloadMessage,String message){
        Uploader uploader = searchUploader(downloadMessage);
        System.out.println(uploader.getId());
        uploader.updateQueue(new Message(upMessage,downloadMessage,message));
    }

    public String getUploaders(){
        String uploaderList = "All Uploaders : \n";
        for(Uploader uploader:uploaders){
            uploaderList += uploader.getId()+" ( " + uploader.getStatus() + " ) \n";
        }
        return uploaderList;
    }

    public boolean isLoggedIn(int id){
        for(Uploader uploader: uploaders){
            if(uploader.getId() == id && uploader.isOnline) return true;
        }
        return false;
    }

    public boolean inSystem(int id){
        for(Uploader uploader:uploaders){
            if(uploader.getId() == id) return true;
        }
        return false;
    }

    public boolean hasLoggedIn(int id){
        for(Uploader uploader:uploaders){
            if(uploader.getId() == id && !uploader.isOnline) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }



}
