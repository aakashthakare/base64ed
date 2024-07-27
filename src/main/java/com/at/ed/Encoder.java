package com.at.ed;

import java.nio.charset.StandardCharsets;

public class Encoder {

    private static final Encoder ENCODER = new Encoder();

    private static final int ENCODE_BASE = 6;

    private final char[] encodingTable = new char[64];

    private Encoder() {
        initEncodingTable();
    }

    public static Encoder getInstance() {
        return ENCODER;
    }

    public String encode(String input) {
        int[] binary = toBinary(input);
        StringBuilder encoded = new StringBuilder();

        for(int start = 0; start < binary.length; start += ENCODE_BASE) {
            int end = Math.min(start + ENCODE_BASE, binary.length - 1);

            int power = ENCODE_BASE - 1;
            int charIndex = 0;
            for(int j = start; j < end; j++) {
                charIndex += (binary[j] * Math.pow(2, power--));
            }

            encoded.append(encodingTable[charIndex]);
        }

        int pad = binary.length % ENCODE_BASE;
        encoded.append("=".repeat(pad));
        return encoded.toString();
    }

    private int[] toBinary(String input) {
        final int bits = 8;
        int[] binary = new int[input.length() * bits];

        try {
            byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
            int index = 0;
            int max = (int) Math.pow(2, (bits - 1));

            for(byte b : bytes) {
                int val = b;
                for(int i = 0; i < bits; i++) {
                    binary[index++] = ((val & max) == 0 ? 0 : 1);
                    val <<= 1;
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return binary;
    }

    private void initEncodingTable() {
        int index = 0;

        for(char i = 'A'; i <= 'Z'; i++)
            encodingTable[index++] = i;

        for(char i = 'a'; i <= 'z'; i++)
            encodingTable[index++] = i;

        for(char i = '0'; i <= '9'; i++)
            encodingTable[index++] = i;

        encodingTable[62] = '+';
        encodingTable[63] = '/';
    }
}
