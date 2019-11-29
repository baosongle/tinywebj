package com.baosongle.tinywebj.core;

import java.util.HashMap;
import java.util.Map;

public class HttpRouteRegistry {
    private static HttpRouteRegistry registry;

    public static HttpRouteRegistry getInstance() {
        if (registry == null) {
            synchronized (HttpRouteRegistry.class) {
                if (registry == null)
                    registry = new HttpRouteRegistry();
            }
        }
        return registry;
    }

    private Map<HttpMethod, Map<String, HttpHandler>> methodHandlers = new HashMap<>();

    void register(HttpMethod method, String uri, HttpHandler httpHandler) {
        Map<String, HttpHandler> map = methodHandlers.computeIfAbsent(method, httpMethod -> {
            Map<String, HttpHandler> handlers = new HashMap<>();
            methodHandlers.put(httpMethod, handlers);
            return handlers;
        });
        map.put(uri, httpHandler);
    }

    HttpHandler getHandler(HttpMethod method, String uri) {
        Map<String, HttpHandler> map = methodHandlers.get(method);
        if (map == null)
            return null;
        return map.get(uri);
    }
}
