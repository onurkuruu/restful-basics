package com.oonurkuru.restful.util;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Onur Kuru on 21.7.2017.
 */
public abstract class QueryParamHandler {


    /**
     * @param objectList içerisinde arama yaparak istenen kriterlere uygun nesneler bulunur.
     * @param queryList  kriterlerin bulunduğu liste
     * @return
     */
    public static List<Object> findByQueryParams(List<Object> objectList, MultivaluedMap<String, String> queryList) {

        return objectList.stream()
                .filter(employee -> {
                    boolean isEqual = true;
                    for (Map.Entry<String, List<String>> entrySet : queryList.entrySet()) {
                        Object value = MethodFinder.methodValue(entrySet.getKey(), employee);

                        if (!entrySet.getValue().get(0).equals(value.toString())) {
                            isEqual = false;
                            break;
                        }
                    }
                    return isEqual;
                }).collect(Collectors.toList());
    }
}
