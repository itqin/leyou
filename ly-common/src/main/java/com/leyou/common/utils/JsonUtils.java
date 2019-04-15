package com.leyou.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: HuYi.Zhang
 * @create: 2018-04-24 17:20
 **/
public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static Optional<String> toString(Object obj) {
        if (obj == null) {
            return Optional.empty();
        }
        if (obj.getClass() == String.class) {
            return Optional.of(obj.toString());
        }
        try {
            return Optional.ofNullable(mapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            logger.error("json序列化出错：" + obj, e);
            return Optional.empty();
        }
    }

    public static <T> Optional<T> toBean(String json, Class<T> tClass) {
        try {
            return Optional.ofNullable(mapper.readValue(json, tClass));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return Optional.empty();
        }
    }

    public static <E> Optional<List<E>> toList(String json, Class<E> eClass) {
        try {
            return Optional.ofNullable(mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass)));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return Optional.empty();
        }
    }

    public static <K, V> Optional<Map<K, V>> toMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return Optional.ofNullable(mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass)));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return Optional.empty();
        }
    }

    public static <T> Optional<T> nativeRead(String json, TypeReference<T> type) {
        try {
            return Optional.ofNullable(mapper.readValue(json, type));
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return Optional.empty();
        }
    }

}
