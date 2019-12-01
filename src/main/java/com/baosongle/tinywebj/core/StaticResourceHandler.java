package com.baosongle.tinywebj.core;

import java.io.*;

public class StaticResourceHandler implements HttpHandler {
    private ContentTypeResolver contentTypeResolver = new FileExtensionContentTypeResolver();

    @Override
    public void handle(Request request, Response response) {
        String uri = request.getUri();
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static" + uri;
        File file = new File(filePath);
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            byte[] bytes = new byte[bufferedInputStream.available()];
            int left = bytes.length;
            while (left > 0) {
                int read = bufferedInputStream.read(bytes);
                left -= read;
            }
            response.setStatusCode(HttpStatusCode.OK);
            response.setBody(bytes);
            String contentType = contentTypeResolver.getContentType(file);
            if (contentType != null) {
                response.getHeaders().put(HttpHeader.ContentType, contentType);
            }
        } catch (FileNotFoundException e) {
            response.setStatusCode(HttpStatusCode.NotFound);
            response.setBody(e.getMessage().getBytes());
        } catch (Exception e) {
            response.setStatusCode(HttpStatusCode.InternalServerError);
            response.setBody(e.getMessage().getBytes());
        }
        response.setVersion(HttpVersion.HTTP_1_1);
    }
}
