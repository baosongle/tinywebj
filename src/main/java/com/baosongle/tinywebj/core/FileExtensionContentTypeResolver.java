package com.baosongle.tinywebj.core;

import java.io.File;

public class FileExtensionContentTypeResolver implements ContentTypeResolver {
    @Override
    public String getContentType(File file) {
        String filename = file.getName();
        int dot = filename.lastIndexOf(".");
        if (dot == -1)
            return null;
        String ext = filename.substring(dot + 1);
        switch (ext) {
            case "html":
                return "text/html";
            case "css":
                return "text/css";
            case "js":
                return "application/javascript";
            case "ico":
                return "image/ico";
            case "png":
                return "image/png";
            default:
                return null;
        }
    }
}
