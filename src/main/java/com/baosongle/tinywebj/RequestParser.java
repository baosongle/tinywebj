package com.baosongle.tinywebj;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestParser {
    public static Request parse(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = readUntil(bufferedReader, "\r\n");

    }

    private static String readUntil(BufferedReader bufferedReader, String until) {
        StringBuilder sb = new StringBuilder();
        while (!sb.toString().endsWith(until)) {
            sb.append(bufferedReader.read())
        }
    }
}
