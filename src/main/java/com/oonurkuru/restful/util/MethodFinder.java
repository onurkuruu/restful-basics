package com.oonurkuru.restful.util;

import com.oonurkuru.restful.exceptions.RestFulException;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Onur Kuru on 21.7.2017.
 */

/**
 * Sınıfların içerdiği metotlar ile ilgili işlemlerin yapıldığı sınıf
 */
public abstract class MethodFinder {

    /**
     * Verilen field ismine göre ilgili field'ın get metodunu bulur.
     *
     * @param field       bulunması istenen get metot
     * @param targetClass hangi sınıf içerisinde arama yapılacak
     * @return metot bulunursa geri göndürülür.
     * @throws RestFulException
     */
    public static Method findMethod(String field, Class targetClass) throws RestFulException {
        try {
            return new PropertyDescriptor(field, targetClass).getReadMethod();
        } catch (IntrospectionException e) {
            throw new RestFulException(201, "Find Method", "");
        }
    }


    /**
     * verilen field'ın get metotu bulunur ve çalıştırılır. Üretilen değer geri döndürülür.
     *
     * @param field
     * @param object
     * @return
     * @throws RestFulException
     */
    public static Object methodValue(String field, Object object) throws RestFulException {
        Method method = findMethod(field, object.getClass());
        Object value;

        try {
            value = method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RestFulException(200, "Method Invoke", "");
        }

        return value;
    }
}
