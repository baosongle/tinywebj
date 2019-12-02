package com.baosongle.tinywebj.framework;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionResult {
    private Object result;
    private Exception exception;
}
