package com.baosongle.tinywebj.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@AllArgsConstructor
public enum HttpStatusCode {
    OK(200, "OK"),
    Created(201, "Created"),

    BadRequest(400, "Bad Request"),
    NotFound(404, "Not Found"),

    InternalServerError(500, "Internal Server Error");

    @Getter
    private int statusCode;

    @Getter
    private String description;
}
