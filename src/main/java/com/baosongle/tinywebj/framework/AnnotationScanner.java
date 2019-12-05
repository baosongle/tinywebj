package com.baosongle.tinywebj.framework;

import com.baosongle.tinywebj.core.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Data
public class AnnotationScanner {
    private String basePackage;

    public AnnotationScanner(String basePackage) {
        this.basePackage = basePackage;
    }

    private Map<HttpMethod, Map<String, HttpFilterChain>> httpFilterChainMap;

    public HttpFilterChain getHttpFilterChain(HttpMethod httpMethod, String uri) {
        if (httpFilterChainMap == null)
            throw new IllegalStateException("You have to invoke scan() method before invoke getHttpFilterChain() method.");
        Map<String, HttpFilterChain> filterChainMap = httpFilterChainMap.get(httpMethod);
        if (filterChainMap == null)
            return null;
        return filterChainMap.get(uri);
    }

    public void scan() {
        httpFilterChainMap = new HashMap<>();
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class<?> clazz : classes) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                RequestMapping requestMapping = getRequestMapping(method);
                if (requestMapping == null)
                    continue;
                HttpFilterChain httpFilterChain = new HttpFilterChain();
                httpFilterChain.setUri(requestMapping.getUri());
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                    if (requestParam == null)
                        continue;
                    RequestParamHttpFilter requestParamHttpFilter = new RequestParamHttpFilter(
                            requestParam, clazz, parameter.getType(), method, i
                    );
                    httpFilterChain.getPreFilters().add(requestParamHttpFilter);
                }
                httpFilterChainMap.computeIfAbsent(requestMapping.getHttpMethod(), m -> new HashMap<>())
                        .put(requestMapping.getUri(), httpFilterChain);
            }
        }
    }

    private RequestMapping getRequestMapping(Method method) {
        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        if (getMapping != null)
            return new RequestMapping(HttpMethod.GET, getMapping.value());
        return null;
    }

    @Getter
    @AllArgsConstructor
    private static class RequestMapping {
        private HttpMethod httpMethod;
        private String uri;
    }
}
