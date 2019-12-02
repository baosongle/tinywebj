package com.baosongle.tinywebj.framework;

import com.baosongle.tinywebj.core.Request;
import com.baosongle.tinywebj.core.Response;
import org.junit.Assert;
import org.junit.Test;

public class RequestParamTest {
    @Test
    public void testRequestParam() {
        AnnotationScanner scanner = new AnnotationScanner();
        scanner.setBasePackage("com.baosongle.tinywebj.framework");
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain();
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/index?name=baosongle");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(1, action.getParameters().size());
        Assert.assertEquals(String.class, action.getParameters().get(0).getClass());
        Assert.assertEquals("baosongle", action.getParameters().get(0));
    }
}
