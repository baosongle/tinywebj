package com.baosongle.tinywebj.core;

import java.io.*;

public class StaticResourceHandler implements HttpHandler {
    @Override
    public void handle(Request request, Response response) {
        String uri = request.getUri();
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static" + uri;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            response.setStatusCode(HttpStatusCode.OK);
            response.setBody(sb.toString());
        } catch (FileNotFoundException e) {
            response.setStatusCode(HttpStatusCode.NotFound);
            response.setBody(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(HttpStatusCode.IntervalServerError);
            response.setBody(e.getMessage());
        }
        response.setVersion(HttpVersion.HTTP_1_1);
    }
}
