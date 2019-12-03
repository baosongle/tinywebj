package com.baosongle.tinywebj.framework;

import com.baosongle.tinywebj.core.Request;
import com.baosongle.tinywebj.core.Response;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

@Data
public class HttpFilterChain {
    private Action action;
    private String uri;
    private Request request;
    private Response response;
    private List<HttpFilter> preFilters = new LinkedList<>();
    private List<HttpFilter> postFilters = new LinkedList<>();

    private Object controller;
    private Method method;

    public void addPreFilter(HttpFilter httpFilter) {
        preFilters.add(httpFilter);
    }

    public void addPostFilter(HttpFilter httpFilter) {
        postFilters.add(httpFilter);
    }

    public void doFilters() {
        action = new Action(uri, controller, method);
        try {
            for (HttpFilter filter : preFilters) {
                filter.doFilter(request, response, action);
            }
            action.invoke();
            for (HttpFilter filter : postFilters) {
                filter.doFilter(request, response, action);
            }
        } catch (HttpFilterException e) {
            e.printStackTrace();
        }
    }
}
