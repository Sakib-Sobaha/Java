package Networking1;

import java.net.*;
import java.io.*;
import java.util.*;
public class HttpURLDemo {
    public static void main(String[] args) throws Exception{
        URL hp = new URL("http://www.google.com");
        HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();

        //Display request method
        System.out.println("Request method is " + hpCon.getRequestMethod());

        //Display response code
        System.out.println("Response code is " + hpCon.getResponseCode());

        //Display response message
        System.out.println("Response message is " + hpCon.getResponseMessage());

        // Get a list of header fields and a ser of header keys
        Map<String, List<String>> hdrMap = hpCon.getHeaderFields();
        Set<String> hdrField = hdrMap.keySet();

        System.out.println("\n Here is the header:");

        //Display all header keys and values
        for(String k : hdrField){
            System.out.println("Key: " + k + "Value: " + hdrMap.get(k));
        }

    }

}
