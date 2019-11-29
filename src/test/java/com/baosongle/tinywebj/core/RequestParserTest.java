package com.baosongle.tinywebj.core;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class RequestParserTest {
    @Test
    public void testGet() {
        String httpText = "GET /index?q=aaa HTTP/1.1\r\n" +
                "Host: www.google.com\r\n" +
                "Accept: application/json;charset=utf-8\r\n" +
                "Referer: http://127.0.0.1/index.html\r\n" +
                "\r\n";
        try {
            Request request = RequestParser.parse(new ByteArrayInputStream(httpText.getBytes()));
            Assert.assertNotNull(request);
            Assert.assertEquals(HttpMethod.GET, request.getMethod());
            Assert.assertEquals("/index?q=aaa", request.getUri());
            Assert.assertEquals(HttpVersion.HTTP_1_1, request.getVersion());
            Assert.assertEquals("application/json;charset=utf-8", request.getHeader("Accept"));
            Assert.assertEquals("www.google.com", request.getHeader("Host"));
            Assert.assertEquals("http://127.0.0.1/index.html", request.getHeader("Referer"));
            Assert.assertNull(request.getBody());
        } catch (IOException | HttpParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testPost() {
        String httpText = "POST /index?q=aaa HTTP/1.1\r\n" +
                "Host: www.google.com\r\n" +
                "Accept: application/json;charset=utf-8\r\n" +
                "Content-Length: 36\r\n" +
                "\r\n" +
                "username=baosongle&password=123456\r\n";
        try {
            Request request = RequestParser.parse(new ByteArrayInputStream(httpText.getBytes()));
            Assert.assertNotNull(request);
            Assert.assertEquals(HttpMethod.POST, request.getMethod());
            Assert.assertEquals("/index?q=aaa", request.getUri());
            Assert.assertEquals(HttpVersion.HTTP_1_1, request.getVersion());
            Assert.assertEquals("application/json;charset=utf-8", request.getHeader("Accept"));
            Assert.assertEquals("www.google.com", request.getHeader("Host"));
            Assert.assertEquals("username=baosongle&password=123456\r\n", request.getBody());
        } catch (IOException | HttpParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testPost1() {
        String httpText = "POST /index?q=aaa HTTP/1.1\r\n" +
                "Host: www.google.com\r\n" +
                "Accept: application/json;charset=utf-8\r\n" +
                "Content-Length: 72\r\n" +
                "\r\n" +
                "username=baosongle&password=123456\r\n" +
                "username=baosongle&password=123456\r\n";
        try {
            Request request = RequestParser.parse(new ByteArrayInputStream(httpText.getBytes()));
            Assert.assertNotNull(request);
            Assert.assertEquals(HttpMethod.POST, request.getMethod());
            Assert.assertEquals("/index?q=aaa", request.getUri());
            Assert.assertEquals(HttpVersion.HTTP_1_1, request.getVersion());
            Assert.assertEquals("application/json;charset=utf-8", request.getHeader("Accept"));
            Assert.assertEquals("www.google.com", request.getHeader("Host"));
            Assert.assertEquals("username=baosongle&password=123456\r\nusername=baosongle&password=123456\r\n", request.getBody());
        } catch (IOException | HttpParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
