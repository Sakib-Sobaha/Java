public class Message {
    int upMessage,downloadMessage;
    String message;
    boolean messageRead;

    public Message(int upMessage,int downloadMessage, String message){
        this.upMessage = upMessage;
        this.downloadMessage = downloadMessage;
        this.message = message;
        this.messageRead = false;
    }

    public int getUpMessage(){
        return upMessage;
    }

    public void setUpMessage(int upMessage){
        this.upMessage = upMessage;
    }

    public int getDownloadMessage(){
        return downloadMessage;
    }

    public void setDownloadMessage(int downloadMessage){
        this.downloadMessage = downloadMessage;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public boolean isMessageRead(){
        return messageRead;
    }

    public void setMessageRead(boolean messageRead){
        this.messageRead = messageRead;
    }

}
