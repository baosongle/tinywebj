package com.baosongle.tinywebj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class RequestParser {
    static Request parse(InputStream inputStream) throws IOException, HttpParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Request request = new Request();
        String line = readHttpLine(bufferedReader);
        parseMethodLine(request, line);
        return request;
    }

    private static void parseMethodLine(Request request, String line) throws HttpParseException {
        // GET / HTTP/1.1
        String[] parts = line.trim().split(" ");
        if (parts.length != 3)
            throw new HttpParseException("HTTP 请求第一行应为 METHOD URI HTTP/VERSION 格式，实际 " + line);

        // method
        try {
            HttpMethod method = HttpMethod.valueOf(parts[0]);
            request.setMethod(method);
        } catch (IllegalArgumentException e) {
            throw new HttpParseException("HTTP 请求方法未知，实际 " + parts[0]);
        }

        // uri
        if (!parts[1].startsWith("/"))
            throw new HttpParseException("HTTP 请求 uri 格式不以 / 开头，实际 " + parts[1]);
        request.setUri(parts[1]);

        // version
        if (!parts[2].startsWith("HTTP/"))
            throw new HttpParseException("HTTP 请求版本不以 HTTP/ 开头，实际 " + parts[2]);
        String[] versionParts = parts[2].split("/");
        try {
            HttpVersion version = HttpVersion.versionOf(versionParts[1]);
            request.setVersion(version);
        } catch (IllegalArgumentException e) {
            throw new HttpParseException("HTTP 版本不兼容，实际 " + versionParts[1]);
        }
    }

    // 读取一行，但是保存行尾的 \r\n
    private static String readHttpLine(BufferedReader bufferedReader) throws IOException {
        char[] last = new char[2];
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (last[i == 0 ? 1 : 0] != '\r' && last[i] != '\n') {
            char c = (char) bufferedReader.read();
            sb.append(c);
            last[i] = c;
            i = i == 0 ? 1 : 0;
        }
        return sb.toString();
    }
}
