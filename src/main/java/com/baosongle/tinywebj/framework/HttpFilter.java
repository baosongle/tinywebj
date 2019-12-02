package com.baosongle.tinywebj.framework;

import com.baosongle.tinywebj.core.Request;
import com.baosongle.tinywebj.core.Response;

public interface HttpFilter {
    void doFilter(Request request, Response response, Action action) throws HttpFilterException;
}
