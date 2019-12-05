package com.baosongle.tinywebj.framework;

import com.baosongle.tinywebj.core.HttpMethod;
import com.baosongle.tinywebj.core.Request;
import com.baosongle.tinywebj.core.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestParamTest {
    private AnnotationScanner scanner;

    @Before
    public void setup() {
        scanner = new AnnotationScanner("com.baosongle.tinywebj.framework");
    }

    @Test
    public void testRequestParamStringType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/String");
        if (httpFilterChain == null)
            throw new RuntimeException("/String 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/String?string=baosongle");
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

    @Test
    public void testRequestParamLongType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/Long");
        if (httpFilterChain == null)
            throw new RuntimeException("/Long 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/Long?long=1");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(1, action.getParameters().size());
        Assert.assertEquals(Long.class, action.getParameters().get(0).getClass());
        Assert.assertEquals(1L, action.getParameters().get(0));
    }

    @Test
    public void testRequestParam_longType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/long");
        if (httpFilterChain == null)
            throw new RuntimeException("/long 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/long?long=2");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(1, action.getParameters().size());
        Assert.assertEquals(Long.class, action.getParameters().get(0).getClass());
        Assert.assertEquals(2L, action.getParameters().get(0));
    }

    @Test
    public void testRequestParamDoubleType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/Double");
        if (httpFilterChain == null)
            throw new RuntimeException("/Double 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/Double?double=3");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(1, action.getParameters().size());
        Assert.assertEquals(Double.class, action.getParameters().get(0).getClass());
        Assert.assertEquals(3D, action.getParameters().get(0));
    }

    @Test
    public void testRequestParam_doubleType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/double");
        if (httpFilterChain == null)
            throw new RuntimeException("/Long 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/double?double=4");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(1, action.getParameters().size());
        Assert.assertEquals(Double.class, action.getParameters().get(0).getClass());
        Assert.assertEquals(4D, action.getParameters().get(0));
    }

    @Test
    public void testRequestParamIntegerType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/Integer");
        if (httpFilterChain == null)
                throw new RuntimeException("/Integer 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/Integer?integer=5");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(1, action.getParameters().size());
        Assert.assertEquals(Integer.class, action.getParameters().get(0).getClass());
        Assert.assertEquals(5, action.getParameters().get(0));
    }

    @Test
    public void testRequestParam_integerType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/integer");
        if (httpFilterChain == null)
            throw new RuntimeException("/integer 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/integer?integer=6");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(1, action.getParameters().size());
        Assert.assertEquals(Integer.class, action.getParameters().get(0).getClass());
        Assert.assertEquals(6, action.getParameters().get(0));
    }

    @Test
    public void testRequestParamShortType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/Short");
        if (httpFilterChain == null)
            throw new RuntimeException("/Short 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/Short?short=7");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(1, action.getParameters().size());
        Assert.assertEquals(Short.class, action.getParameters().get(0).getClass());
        Assert.assertEquals(Short.valueOf("7"), action.getParameters().get(0));
    }

    @Test
    public void testRequestParam_shortType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/short");
        if (httpFilterChain == null)
            throw new RuntimeException("/Long 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/short?short=8");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(1, action.getParameters().size());
        Assert.assertEquals(Short.class, action.getParameters().get(0).getClass());
        Assert.assertEquals((short) 8, action.getParameters().get(0));
    }

    @Test
    public void testRequestParamFloatType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/Float");
        if (httpFilterChain == null)
            throw new RuntimeException("/Float 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/Float?float=9");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(1, action.getParameters().size());
        Assert.assertEquals(Float.class, action.getParameters().get(0).getClass());
        Assert.assertEquals(9F, action.getParameters().get(0));
    }

    @Test
    public void testRequestParam_floatType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/float");
        if (httpFilterChain == null)
            throw new RuntimeException("/Long 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/float?float=10");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(1, action.getParameters().size());
        Assert.assertEquals(Float.class, action.getParameters().get(0).getClass());
        Assert.assertEquals(10F, action.getParameters().get(0));
    }

    @Test
    public void testRequestParam_intLongType() {
        scanner.scan();

        HttpFilterChain httpFilterChain = scanner.getHttpFilterChain(HttpMethod.GET, "/intLong");
        if (httpFilterChain == null)
            throw new RuntimeException("/intLong 不存在");
        Assert.assertNotNull(httpFilterChain);
        Request request = new Request();
        request.setUri("/intLong?i=11&l=12");
        Response response = new Response();
        httpFilterChain.setRequest(request);
        httpFilterChain.setResponse(response);
        httpFilterChain.doFilters();
        Action action = httpFilterChain.getAction();
        Assert.assertNotNull(action);
        Assert.assertEquals(2, action.getParameters().size());
        Assert.assertEquals(Integer.class, action.getParameters().get(0).getClass());
        Assert.assertEquals(Long.class, action.getParameters().get(1).getClass());
        Assert.assertEquals(11, action.getParameters().get(0));
        Assert.assertEquals(12L, action.getParameters().get(1));
    }
}
