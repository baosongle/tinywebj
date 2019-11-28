package com.baosongle.tinywebj.core;

import lombok.AllArgsConstructor;

import java.io.*;

@AllArgsConstructor
public class ResponseProcessor {
    private Request request;

    public Response process() {
        String uri = request.getUri();
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static" + uri;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            Response response = new Response(HttpStatusCode.OK, HttpVersion.HTTP_1_1);
            response.setBody(sb.toString());
            return response;
        } catch (FileNotFoundException e) {
            Response response = new Response(HttpStatusCode.NotFound, HttpVersion.HTTP_1_1);
            response.setBody(e.getMessage());
            return response;
        } catch (Exception e) {
            Response response = new Response(HttpStatusCode.IntervalServerError, HttpVersion.HTTP_1_1);
            response.setBody(e.getMessage());
            return response;
        }
    }
}
