package com.oonurkuru.restful.exceptions;

import com.oonurkuru.restful.exceptions.models.RestFulExceptionModel;

/**
 * Created by Onur Kuru on 21.7.2017.
 */

/**
 * Kullanıcı tanımlı hata sınıfı. İçerisinde barındığı modelle birlikte hatayı üst katmanlara iletir ve son kullanıcıya
 * bilgilendirme amaçlı hata modelini sunar.
 */
public class RestFulException extends RuntimeException {

    private RestFulExceptionModel restFulExceptionModel;

    public RestFulException() {
        super();
    }

    public RestFulException(Integer errorCode, String errorName, String errorDescription) {
        super();
        this.restFulExceptionModel = new RestFulExceptionModel(errorCode, errorName, errorDescription);
    }

    public RestFulExceptionModel getRestFulExceptionModel() {
        return restFulExceptionModel;
    }

    public void setRestFulExceptionModel(RestFulExceptionModel restFulExceptionModel) {
        this.restFulExceptionModel = restFulExceptionModel;
    }
}
