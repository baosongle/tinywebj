package com.baosongle.tinywebj.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class RequestParser {
    private static final String CRLF = "\r\n";

    static Request parse(InputStream inputStream) throws IOException, HttpParseException {
        Request request = new Request();
        String line = readHttpLine(inputStream);
        parseMethodLine(request, line);

        // 解析 header
        while (!(line = readHttpLine(inputStream)).equals(CRLF)) {
            parseHeaderLine(request, line);
        }

        // 关于如何截断一个 HTTP 报文非常复杂，具体可以看 https://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html#sec4.4
        String contentLength = request.getHeader(HttpHeader.ContentLength.header());
        if (contentLength != null) {
            int length = Integer.parseInt(contentLength);
            byte[] bodyBytes = new byte[length];
            int read;
            int left = length;
            while ((read = inputStream.read(bodyBytes, length - left, left)) != -1) {
                left -= read;
            }
            request.setBody(new String(bodyBytes, StandardCharsets.UTF_8));
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
        if (headerPart.length < 2)
            throw new HttpParseException("HTTP 请求的头部部分应当至少包含一个冒号，实际 " + line);
        String name = headerPart[0].trim();
        String value = line.substring(name.length() + 1).trim();
        request.setHeader(name, value);
    }

    // 读取一行，但是保存行尾的 CRLF
    private static String readHttpLine(InputStream inputStream) throws IOException {
        byte[] last = new byte[2];
        int i = 0;
        List<Byte> byteList = new ArrayList<>(50);
        while (last[i] != '\r' && last[i == 0 ? 1 : 0] != '\n') {
            int read = inputStream.read();
            if (read == -1)
                break;
            byteList.add((byte) read);
            last[i] = (byte) read;
            i = i == 0 ? 1 : 0;
        }
        byte[] bytes = new byte[byteList.size()];
        for (i = 0; i < bytes.length; i++)
            bytes[i] = byteList.get(i);
        return new String(bytes);
    }
}
