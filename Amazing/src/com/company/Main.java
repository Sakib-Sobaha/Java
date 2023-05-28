package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class child{

}

public class Main {



    public static void main(String[] args) {
        int i = -10;
        System.out.println(--i);
        System.out.println(i++);
    }
}

class Base{
    int x = 2;
    int method(){
        return x;
    }
}

class Subclass extends Base{
    int x = 3;
    int method(){
        return x;
    }
}
