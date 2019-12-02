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
            action.getParameters().set(paramIndex, value);
        } else if (Number.class.isAssignableFrom(paramClass)) {
            try {
                if (paramClass.equals(Long.class)) {
                    action.getParameters().set(paramIndex, Long.valueOf(value));
                } else if (paramClass.equals(Integer.class)) {
                    action.getParameters().set(paramIndex, Integer.valueOf(value));
                } else if (paramClass.equals(Double.class)) {
                    action.getParameters().set(paramIndex, Double.valueOf(value));
                } else if (paramClass.equals(Short.class)) {
                    action.getParameters().set(paramIndex, Short.valueOf(value));
                } else if (paramClass.equals(Float.class)) {
                    action.getParameters().set(paramIndex, Float.valueOf(value));
                } else {
                    String msg = String.format("The required type %s of %s.%s() is not supported.",
                            paramClass.getSimpleName(), controllerClass.getSimpleName(), method.getName());
                    throw new HttpFilterFormatException(msg);
                }
            } catch (NumberFormatException e) {
                String msg = String.format("String [%s] cannot be format to %s.", value, paramClass);
                throw new HttpFilterFormatException(msg);
            }
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
            if (paramAndValue.startsWith(name + "=")) {
                int paramIndex = paramAndValue.indexOf(name + "=");
                value = paramAndValue.substring(paramIndex + 1);
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
}
