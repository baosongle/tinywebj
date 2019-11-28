package com.baosongle.tinywebj.core;

class ResponseParser {
    private static final String CRLF = "\r\n";

    static String parse(Response response) {
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
                .append(CRLF);
        if (response.getBody() == null) {
            sb.append(CRLF).append(CRLF);
        } else {
            sb.append(CRLF)
                    .append(response.getBody())
                    .append(CRLF)
                    .append(CRLF);
        }
        return sb.toString();
    }
}
