import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Vector;


public class Uploader {
    int clientSocketId,id;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStreamFile;
    private DataOutputStream dataOutputStreamFile;
    boolean isOnline;
    int countFile;
    Vector<Message> messageVector;

    public Uploader(int clientSocketId,DataInputStream dataInputStream, DataOutputStream dataOutputStream, DataInputStream dataInputStreamFile, DataOutputStream dataOutputStreamFile){
        this.clientSocketId = clientSocketId;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        this.dataOutputStreamFile = dataOutputStreamFile;
        this.dataInputStreamFile = dataInputStreamFile;
        this.isOnline = true;
        this.countFile = 0;
        this.messageVector = new Vector<Message>();
    }

    public void update(Vector<Message>messageVector,int fileCounter){
        this.messageVector = messageVector;
        this.countFile = fileCounter;
    }

    public Vector<Message> getMessageVector(){
        return messageVector;
    }

    public int getClientSocketId(){
        return clientSocketId;
    }

    public void takeId() throws IOException{
        dataOutputStream.writeUTF("To enter File System enter your id : ");
        dataOutputStream.flush();
    }

    public void setId(int id) throws IOException{
        this.id = id;
        System.out.println("Client ID : "+id);
        dataOutputStream.writeUTF("Welcome to File System. Upload and Download files.");
        dataOutputStream.flush();
//        boolean publicDir = new File("files/"+Integer.toString(id)+"/public").mkdirs();
//        boolean privateDir = new File("files/"+Integer.toString(id)+"/private").mkdirs();
        new File("files"+File.separator+Integer.toString(id)+File.separator+"public").mkdirs();
        new File("files"+File.separator+Integer.toString(id)+File.separator+"private").mkdirs();
    }

    public int getId(){
        return id;
    }

    public DataOutputStream getDataOutputStream(){
        return dataOutputStream;
    }

    public DataOutputStream getDataOutputStreamFile(){
        return dataOutputStreamFile;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream){
        this.dataOutputStream = dataOutputStream;
    }

    public void writeText(String text) throws IOException{
        dataOutputStream.writeUTF(text);
        dataOutputStream.flush();
    }

    public void writeToFileStream(String text) throws IOException{
        dataOutputStreamFile.writeUTF(text);
        dataOutputStreamFile.flush();
    }

    public void goOffline(){
        isOnline = false;
    }

    public String getStatus(){
        if(isOnline) return "Online";
        else return "Offline";
    }

    public int getCountFile(){
        return countFile;
    }

    public void updateQueue(Message message){
        messageVector.add(message);
    }

    public Vector<String> showUnreadMessage(){
        System.out.println("Unread messages ...");
        Vector<String>stringVector = new Vector<String>();
        for(Message m:messageVector){
            System.out.println(m.isMessageRead());
            if(!m.isMessageRead()){
                stringVector.add("Uploader "+m.getUpMessage()+" : "+m.getMessage());
                m.setMessageRead(true);
            }
        }
        return stringVector;
    }

}
