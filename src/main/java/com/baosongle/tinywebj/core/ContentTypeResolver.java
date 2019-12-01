package com.baosongle.tinywebj.core;

import java.io.File;

public interface ContentTypeResolver {
    String getContentType(File file);
}
