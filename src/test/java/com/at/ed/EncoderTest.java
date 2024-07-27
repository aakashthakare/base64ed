package com.at.ed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncoderTest {

    @Test
    public void shouldEncodeAbcd() {
        String input = "abcd";
        String actual = Encoder.getInstance().encode(input);
        String expected = "YWJjZA==";

        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncode1234() {
        String input = "1234";
        String actual = Encoder.getInstance().encode(input);
        String expected = "MTIzNA==";

        assertEquals(expected, actual);
    }

    @Test
    public void shouldEncodeSymbols() {
        String input = "!@#$%^";
        String actual = Encoder.getInstance().encode(input);
        String expected = "IUAjJCVe";

        assertEquals(expected, actual);
    }
}