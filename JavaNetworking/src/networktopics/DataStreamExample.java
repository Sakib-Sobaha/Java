package networktopics;

import java.io.*;

public class DataStreamExample {
    public static void main(String[] args) {
        String filename = "data.bin";
        try{
            //Write data to the file using DataOutputStream
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
            dos.writeInt(21);
            dos.writeChar('c');
            dos.writeBoolean(true);
            dos.writeDouble(3.14159);
            dos.writeUTF("HelloDataOutputStream");
            dos.close();

            //Read data from file using DataInputStream
            DataInputStream dis = new DataInputStream(new FileInputStream(filename));

            int intVal = dis.readInt();
            char charVal = dis.readChar();
            boolean boolVal = dis.readBoolean();
            double doubleVal = dis.readDouble();
            String stringVal = dis.readUTF();

            dis.close();

            System.out.println(intVal);
            System.out.println(charVal);
            System.out.println(doubleVal);
            System.out.println(boolVal);
            System.out.println(stringVal);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
