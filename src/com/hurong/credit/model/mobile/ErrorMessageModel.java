package com.hurong.credit.model.mobile;

import java.util.HashMap;

public class ErrorMessageModel {

    private String Mess;
    private Integer Code;
    private HashMap<String ,Object> data = new HashMap<>();

    public String getMess() {
        return Mess;
    }

    public void setMess(String mess) {
        Mess = mess;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ErrorMessageModel{" +
                "Mess='" + Mess + '\'' +
                ", Code=" + Code +
                ", data=" + data +
                '}';
    }
}
