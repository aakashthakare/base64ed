package com.at.ed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {
    @Test
    public void shouldDecodeAbcd() {
        String input = "YWJjZA==";
        String actual = Decoder.getInstance().decode(input);
        String expected = "abcd";

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDecode1234() {
        String input = "MTIzNA==";
        String actual = Decoder.getInstance().decode(input);
        String expected = "1234";

        assertEquals(expected, actual);
    }

    @Test
    public void shouldDecodeSymbols() {
        String input = "IUAjJCVe";
        String actual = Decoder.getInstance().decode(input);
        String expected = "!@#$%^";

        assertEquals(expected, actual);
    }
}