/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.Khamonline.base.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * @author langhsu
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1491499610244557029L;

    public static final int SUCCESS = 0;
    public static final int ERROR = -1;

    /**
     */
    private int code;

    /**
     */
    private String message;

    /**
     */
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return success("Thành công", data);
    }

    public static <T> Result<T> successMessage(String message) {
        return success(message, null);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(Result.SUCCESS, message, data);
    }

    public static <T> Result<T> failure(String message) {
        return failure(Result.ERROR, message);
    }

    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message, null);
    }

    public boolean isOk() {
        return code == SUCCESS;
    }

    public boolean isError() {
        return !isOk();
    }

}
