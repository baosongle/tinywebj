package com.baosongle.tinywebj.core;

class ResponseParser {
    private static final String CRLF = "\r\n";

    static byte[] parse(Response response) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/")
                .append(response.getVersion().version())
                .append(" ")
                .append(response.getStatusCode().statusCode())
                .append(" ")
                .append(response.getStatusCode().description())
                .append(CRLF);
        sb.append(HttpHeader.ContentLength.header())
                .append(": ")
                .append(response.getContentLength())
                .append(CRLF)
                .append(CRLF);
        byte[] nonBodyBytes = sb.toString().getBytes();
        if (response.getBody() == null)
            return nonBodyBytes;
        byte[] bytes = new byte[nonBodyBytes.length + response.getBody().length];
        System.arraycopy(nonBodyBytes, 0, bytes, 0, nonBodyBytes.length);
        System.arraycopy(response.getBody(), 0, bytes, nonBodyBytes.length, response.getBody().length);
        return bytes;
    }
}
