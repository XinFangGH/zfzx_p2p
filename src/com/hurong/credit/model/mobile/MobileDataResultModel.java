package com.hurong.credit.model.mobile;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

public class MobileDataResultModel {
    private static String dateFormat = "yyyy-MM-dd";
    //自定义时间格式
    private static SerializeConfig mapping = new SerializeConfig();


    //接口版本号
    private int version = 1;
    //状态码
    private int code = MobileErrorCode.SUCCESS;
    //返回错误消息
    private String msg = "";
    //返回数据

    //返回date日期格式
    private HashMap<String, Object> data = new HashMap<>();

    public int getVersion() {
        return version;
    }

    //格式化json
    public String toJSON() {
        return new GsonBuilder().setDateFormat(dateFormat).create().toJson(MobileDataResultModel.this);

//        return JSON.toJSONString(this, SerializerFeature.WriteNullListAsEmpty
//                , SerializerFeature.WriteNullBooleanAsFalse
//                , SerializerFeature.WriteNullNumberAsZero
//                , SerializerFeature.WriteNullStringAsEmpty
//                , SerializerFeature.WriteDateUseDateFormat
//        );
    }

    /**
     * 格式化json
     *
     * @param dateFormatParttern 格式化时间格式
     */
    public String toJSON(String dateFormatParttern) {
        return new GsonBuilder().setDateFormat(dateFormat).create().toJson(MobileDataResultModel.this);

//        return JSON.toJSONString(this, SerializerFeature.WriteNullListAsEmpty
//                , SerializerFeature.WriteNullBooleanAsFalse
//                , SerializerFeature.WriteNullNumberAsZero
//                , SerializerFeature.WriteNullStringAsEmpty
//                , SerializerFeature.WriteDateUseDateFormat
//        );
    }


    //自定义事件返回格式
//    public String Json(String dateFormat, String jsonText) {
//        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
//        return JSON.toJSONString(jsonText, mapping);
//    }

    //添加返回内容
    public void addDataContent(String key, Object dataContent) {
        this.data.put(key, dataContent);
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getCode() {
        return code;
    }

    /**
     * @param code {@link MobileErrorCode}
     */
    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return toJSON();
    }
}
