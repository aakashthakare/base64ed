package com.at.ed;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Decoder {

    private static final Decoder DECODER = new Decoder();

    private static final int ENCODE_BASE = 6;

    private static final int DECODE_BASE = 8;

    private Map<Character, Integer> decodingMap;

    private Decoder() {
        initDecodingMap();
    }

    public static Decoder getInstance() {
        return DECODER;
    }

    public String decode(String input) {
        int[] binary = toBinary(input);
        StringBuilder decoded = new StringBuilder();

        for(int start = 0; start < binary.length; start += DECODE_BASE) {
            int end = Math.min(start + DECODE_BASE, binary.length - 1);

            int power = DECODE_BASE - 1;
            long decodedChar = 0;
            for(int j = start; j < end; j++) {
                decodedChar += (binary[j] * Math.pow(2, power--));
            }

            if(decodedChar > 0) {
                decoded.append((char) decodedChar);
            }
        }

        return decoded.toString();
    }

    private void initDecodingMap() {
        decodingMap = new HashMap<>();

        int index = 0;

        for(char c = 'A'; c <= 'Z'; c++)
            decodingMap.put(c, index++);

        for(char c = 'a'; c <= 'z'; c++)
            decodingMap.put(c, index++);

        for(char c = '0'; c <= '9'; c++)
            decodingMap.put(c, index++);

        decodingMap.put('+', 62);
        decodingMap.put('/', 63);
        decodingMap.put('=', 0);
    }

    private int[] toBinary(String input) {
        int[] binary = new int[input.length() * ENCODE_BASE];

        try {
            char[] ch = input.toCharArray();
            int[] bytes = new int[ch.length];
            int index = 0;
            for (char c : ch) {
                bytes[index++] = decodingMap.get(c);
            }

            index = 0;
            int max = (int) Math.pow(2, ENCODE_BASE - 1);

            for(int b : bytes) {
                int val = b;
                for(int i = 0; i < ENCODE_BASE; i++) {
                    binary[index++] = ((val & max) == 0 ? 0 : 1);
                    val <<= 1;
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return binary;
    }

}