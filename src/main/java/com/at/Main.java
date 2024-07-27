package com.at;

import com.at.ed.Decoder;
import com.at.ed.Encoder;

public class Main {


    public static void main(String[] args) {
//        Encoder encoder = Encoder.getInstance();
//        System.out.println(encoder.encode("abcd"));

        Decoder decoder = Decoder.getInstance();
        System.out.println(decoder.decode("IUAk"));
    }
}



