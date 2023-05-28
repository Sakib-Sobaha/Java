package tcpforward;

import java.io.Serializable;

public class Message implements Serializable{
    private String from;
    private String to;
    private String text;

    public Message(){

    }
    public void setFrom(String from){
        this.from = from;
    }
    public String getFrom(){
        return this.from;
    }
    public void setTo(String to){
        this.to = to;
    }
    public String getTo(){
        return this.to;
    }
    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
}
