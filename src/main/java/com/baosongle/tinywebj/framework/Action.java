package com.baosongle.tinywebj.framework;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

@Data
public class Action {
    private ActionResult result;
    private Object controller;
    private List<Object> parameters = new LinkedList<>();
    private Method method;

    public void invoke() {
        try {
            result = new ActionResult(method.invoke(controller, parameters.toArray()), null);
        } catch (Exception e) {
            result = new ActionResult(null, e);
        }
    }
}
