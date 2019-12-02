package com.baosongle.tinywebj.framework;

import lombok.Data;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

@Data
public class AnnotationScanner {
    private String basePackage;

    private HttpFilterChain httpFilterChain;

    public void scan() {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class<?> clazz : classes) {
            Controller controller = clazz.getAnnotation(Controller.class);
            if (controller == null)
                continue;
            this.httpFilterChain = new HttpFilterChain();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
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
            }
        }
    }
}
