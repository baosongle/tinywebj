package com.baosongle.tinywebj.core;

import java.io.*;

public class StaticResourceHandler implements HttpHandler {
    @Override
    public void handle(Request request, Response response) {
        String uri = request.getUri();
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static" + uri;
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            byte[] bytes = new byte[bufferedInputStream.available()];
            int left = bytes.length;
            while (left > 0) {
                int read = bufferedInputStream.read(bytes);
                left -= read;
            }
            response.setStatusCode(HttpStatusCode.OK);
            response.setBody(bytes);
        } catch (FileNotFoundException e) {
            response.setStatusCode(HttpStatusCode.NotFound);
            response.setBody(e.getMessage().getBytes());
        } catch (Exception e) {
            response.setStatusCode(HttpStatusCode.IntervalServerError);
            response.setBody(e.getMessage().getBytes());
        }
        response.setVersion(HttpVersion.HTTP_1_1);
    }
}
