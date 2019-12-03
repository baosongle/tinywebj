package com.baosongle.tinywebj.framework;

import com.baosongle.tinywebj.core.Request;
import com.baosongle.tinywebj.core.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

@Data
@AllArgsConstructor
public class RequestParamHttpFilter implements HttpFilter {
    private RequestParam requestParam;
    private Class controllerClass;
    private Class paramClass;
    private Method method;
    private int paramIndex;

    @Override
    public void doFilter(Request request, Response response, Action action) throws HttpFilterException {
        String uri = request.getUri();
        String value = retrieveRequestParam(uri, requestParam.value());
        if (value == null) {
            return;
        }
        if (paramClass.equals(String.class)) {
            action.getParameters().add(value);
        } else if (isNumericType(paramClass)) {
            try {
                parseNumericType(value, action);
            } catch (NumberFormatException e) {
                String msg = String.format("String [%s] cannot be format to %s.", value, paramClass);
                throw new HttpFilterFormatException(msg);
            }
        } else {
            String msg = String.format("The required type %s of %s.%s() is not supported.",
                    paramClass.getSimpleName(), controllerClass.getSimpleName(), method.getName());
            throw new HttpFilterFormatException(msg);
        }
    }

    private String retrieveRequestParam(String uri, String name) throws HttpFilterException {
        int qIndex = uri.lastIndexOf("?");
        if (qIndex == -1)
            return null;
        String value = null;
        String queryString = uri.substring(qIndex + 1);
        String[] paramAndValues = queryString.split("&");
        for (String paramAndValue : paramAndValues) {
            String paramEqual = name + "=";
            if (paramAndValue.startsWith(paramEqual)) {
                int paramIndex = paramAndValue.indexOf(paramEqual);
                value = paramAndValue.substring(paramIndex + paramEqual.length());
                break;
            }
        }
        if (value == null) {
            if (requestParam.required()) {
                if (requestParam.defaultValue().equals(ValueConstants.DEFAULT_NONE)) {
                    String msg = String.format("The required value of %s.%s() No.%s parameter is missing.",
                            controllerClass.getSimpleName(), method.getName(), paramIndex);
                    throw new HttpFilterValueNullException(msg);
                }
                value = requestParam.defaultValue();
            } else {
                return null;
            }
        }
        return value;
    }

    private boolean isNumericType(Class<?> clazz) {
        return clazz.equals(Long.class) || clazz.equals(long.class) ||
                clazz.equals(Integer.class) || clazz.equals(int.class) ||
                clazz.equals(Double.class) || clazz.equals(double.class) ||
                clazz.equals(Float.class) || clazz.equals(float.class) ||
                clazz.equals(Short.class) || clazz.equals(short.class);
    }

    private void parseNumericType(String value, Action action) throws HttpFilterException {
        if (paramClass.equals(Long.class) || paramClass.equals(long.class)) {
            action.getParameters().add(Long.valueOf(value));
        } else if (paramClass.equals(Integer.class) || paramClass.equals(int.class)) {
            action.getParameters().add(Integer.valueOf(value));
        } else if (paramClass.equals(Double.class) || paramClass.equals(double.class)) {
            action.getParameters().add(Double.valueOf(value));
        } else if (paramClass.equals(Short.class) || paramClass.equals(short.class)) {
            action.getParameters().add(Short.valueOf(value));
        } else if (paramClass.equals(Float.class) || paramClass.equals(float.class)) {
            action.getParameters().add(Float.valueOf(value));
        } else {
            String msg = String.format("The required type %s of %s.%s() is not supported.",
                    paramClass.getSimpleName(), controllerClass.getSimpleName(), method.getName());
            throw new HttpFilterFormatException(msg);
        }
    }
}
