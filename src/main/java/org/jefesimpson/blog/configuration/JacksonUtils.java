package org.jefesimpson.blog.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JacksonUtils {
    private static ObjectMapper mapper = new ObjectMapper();
    private static SimpleModule module = new SimpleModule();

    private JacksonUtils(){}

    public static ObjectMapper getMapper(){
        return mapper;
    }
    public static SimpleModule getModule(){
        return module;
    }
}
