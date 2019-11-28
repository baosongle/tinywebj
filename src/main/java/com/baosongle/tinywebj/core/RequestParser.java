package com.baosongle.tinywebj.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class RequestParser {
    private static final String CRLF = "\r\n";

    static Request parse(InputStream inputStream) throws IOException, HttpParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Request request = new Request();
        String line = readHttpLine(bufferedReader);
        parseMethodLine(request, line);

        // 解析 header
        while (!(line = readHttpLine(bufferedReader)).equals(CRLF)) {
            parseHeaderLine(request, line);
        }

        // 解析 body
        if (request.getMethod().equals(HttpMethod.POST) || request.getMethod().equals(HttpMethod.PUT)) {
            StringBuilder bodyBuilder = new StringBuilder();
            int i = 0;
            while (i < 2) {
                line = readHttpLine(bufferedReader);
                if (line.equals(CRLF)) {
                    if (bodyBuilder.length() == 0)
                        break;
                    i++;
                    continue;
                }
                bodyBuilder.append(line);
            }
            request.setBody(bodyBuilder.length() == 0 ? null : bodyBuilder.toString());
        }
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

    private static void parseHeaderLine(Request request, String line) throws HttpParseException {
        String[] headerPart = line.split(":");
        if (headerPart.length != 2)
            throw new HttpParseException("HTTP 请求的头部部分应当且仅应当包含一个冒号，实际 " + line);
        String name = headerPart[0].trim();
        String value = headerPart[1].trim();
        request.setHeader(name, value);
    }

    // 读取一行，但是保存行尾的 CRLF
    private static String readHttpLine(BufferedReader bufferedReader) throws IOException {
        char[] last = new char[2];
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (last[i] != '\r' && last[i == 0 ? 1 : 0] != '\n') {
            int read = bufferedReader.read();
            if (read == -1)
                break;
            char c = (char) read;
            sb.append(c);
            last[i] = c;
            i = i == 0 ? 1 : 0;
        }
        return sb.toString();
    }
}
