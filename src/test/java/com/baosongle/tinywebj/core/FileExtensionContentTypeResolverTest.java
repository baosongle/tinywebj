package com.baosongle.tinywebj.core;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FileExtensionContentTypeResolverTest {
    private FileExtensionContentTypeResolver resolver = new FileExtensionContentTypeResolver();

    @Test
    public void testHtml() {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/static/index.html");
        Assert.assertEquals("text/html", resolver.getContentType(file));
    }

    @Test
    public void testIco() {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/static/favicon.ico");
        Assert.assertEquals("image/ico", resolver.getContentType(file));
    }
}
