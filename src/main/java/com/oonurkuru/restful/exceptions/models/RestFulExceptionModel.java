package com.oonurkuru.restful.exceptions.models;

/**
 * Created by Onur Kuru on 21.7.2017.
 */

/**
 * Özelleştirilmiş hata modelidir. Hataya ait kod, isim ve bilgilendirme içeriklere sahiptir.
 */
public class RestFulExceptionModel {

    private Integer errorCode;
    private String errorName;
    private String message;

    public RestFulExceptionModel() {

    }

    public RestFulExceptionModel(Integer errorCode, String errorName, String message) {
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
