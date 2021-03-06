package com.hurong.credit.util;

public class ConditionType {

    private String name;
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ConditionType{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
