package com.baosongle.tinywebj.framework;

import lombok.Data;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
public class AnnotationScanner {
    private String basePackage;

    private List<HttpFilterChain> httpFilterChains = new LinkedList<>();

    public void scan() {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class<?> clazz : classes) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                if (getMapping == null)
                    continue;
                String uri = getMapping.value();
                HttpFilterChain httpFilterChain = new HttpFilterChain();
                httpFilterChain.setUri(uri);
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
                httpFilterChains.add(httpFilterChain);
            }
        }
    }
}
