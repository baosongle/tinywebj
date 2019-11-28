package com.baosongle.tinywebj.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@AllArgsConstructor
public enum HttpVersion {
    HTTP_0_9("0.9"),
    HTTP_1_0("1.0"),
    HTTP_1_1("1.1"),
    HTTP_2("2"),
    HTTP_3("3");

    @Getter
    private String version;

    public static HttpVersion versionOf(String s) {
        switch (s) {
            case "0.9":
                return HTTP_0_9;
            case "1.0":
                return HTTP_1_0;
            case "1.1":
                return HTTP_1_1;
            case "2":
                return HTTP_2;
            case "3":
                return HTTP_3;
            default:
                throw new IllegalArgumentException("Illegal value of " + s + " for HttpVersion.versionOf(String)");
        }
    }
}
