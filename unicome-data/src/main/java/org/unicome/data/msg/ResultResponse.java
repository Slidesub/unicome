package org.unicome.data.msg;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ResultResponse<T> implements Serializable {
    private T data;
    private Integer code;
    private String msg;
    private boolean success = true;

    public ResultResponse data(T data) {
        this.data = data;
        return this;
    }
    public ResultResponse code(int code) {
        this.code = code;
        return this;
    }
    public ResultResponse msg(String msg) {
        this.msg = msg;
        return this;
    }
    public ResultResponse success(boolean success) {
        this.success = success;
        return this;
    }
}
