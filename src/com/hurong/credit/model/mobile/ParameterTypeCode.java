package com.hurong.credit.model.mobile;

public interface ParameterTypeCode {
    //短信注册
    String SMS_REGCODE= "0";
    //短信忘记密码
    String SMS_BACKPASSWORD="1";
    //修改手机号
    String SMS_UpdateTelphone = "3";



    //条件筛选状态
    Integer Condition_TYPE_0 = 0;       //1-全部查询
    Integer Condition_TYPE_1 = 1;       //1-充值查询
    Integer Condition_TYPE_2 = 2;       //2-取现查询
    Integer Condition_TYPE_3 = 3;       //3-投资收益查询
    Integer Condition_TYPE_4 = 4;       //4-投资查询
    Integer Condition_TYPE_5 = 5;       //5-本金收回查询
    Integer Condition_TYPE_6 = 6;       //6-取现手续费查询
    Integer Condition_TYPE_7 = 7;       //7-借款人还本付息查询
    Integer Condition_TYPE_8 = 8;       //8-借款人借款入账查询
    Integer Condition_TYPE_9 = 9;       //9-剩余未还本息查询
    Integer Condition_TYPE_10 = 10;     //10-系统红包查询
    Integer Condition_TYPE_11 = 11;     //11-平台转账查询
    Integer Condition_TYPE_13 = 13;     //12-平台收取的随息管理费用查询
    Integer Condition_TYPE_14 = 14;     //13-时间筛选查询---1个月
    Integer Condition_TYPE_15 = 15;     //13-时间筛选查询---2个月
    Integer Condition_TYPE_16 = 16;     //13-时间筛选查询---3个月
}
