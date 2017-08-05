package com.hust.weixin.json;

/**
 * Created by Administration on 2016/6/21.
 */
public class ModelMsgData {
    private String value;
    private String color;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ModelMsgData(String value, String color) {
        this.value = value;
        this.color = color;
    }

    @Override
    public String toString() {
        return "ModelMsgData{" +
                "value='" + value + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
