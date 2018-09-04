package com.hurong.credit.action.webPhone;

import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.mobile.ParameterTypeCode;
import com.hurong.credit.util.ConditionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebConditionAction extends BaseAction{

    //资金明细筛选条件状态返回接口
    public String screening(){
        String isApp = this.getRequest().getParameter("isApp");
        String isHapp = this.getRequest().getHeader("isApp");
        MobileDataResultModel model = new MobileDataResultModel();
        ConditionType conditionType0 = new ConditionType();
        conditionType0.setName("全部");
        conditionType0.setType(ParameterTypeCode.Condition_TYPE_0);
        ConditionType conditionType1 = new ConditionType();
        conditionType1.setName("充值");
        conditionType1.setType(ParameterTypeCode.Condition_TYPE_1);
        ConditionType conditionType2 = new ConditionType();
        conditionType2.setName("取现");
        conditionType2.setType(ParameterTypeCode.Condition_TYPE_2);
        ConditionType conditionType3 = new ConditionType();
        conditionType3.setName("出借收益");
        conditionType3.setType(ParameterTypeCode.Condition_TYPE_3);
        ConditionType conditionType4 = new ConditionType();
        conditionType4.setName("出借");
        conditionType4.setType(ParameterTypeCode.Condition_TYPE_4);
        ConditionType conditionType5 = new ConditionType();
        conditionType5.setName("本金收回");
        conditionType5.setType(ParameterTypeCode.Condition_TYPE_5);
        ConditionType conditionType6 = new ConditionType();
        conditionType6.setName("取现手续费");
        conditionType6.setType(ParameterTypeCode.Condition_TYPE_6);
        ConditionType conditionType7 = new ConditionType();
        conditionType7.setName("借款人借款入账");
        conditionType7.setType(ParameterTypeCode.Condition_TYPE_7);
        ConditionType conditionType8 = new ConditionType();
        conditionType8.setName("借款人还本付息");
        conditionType8.setType(ParameterTypeCode.Condition_TYPE_8);
        ConditionType conditionType9 = new ConditionType();
        conditionType9.setName("剩余未还本息");
        conditionType9.setType(ParameterTypeCode.Condition_TYPE_9);
        ConditionType conditionType10 = new ConditionType();
        conditionType10.setName("系统红包");
        conditionType10.setType(ParameterTypeCode.Condition_TYPE_10);
        ConditionType conditionType11 = new ConditionType();
        conditionType11.setName("平台转账");
        conditionType11.setType(ParameterTypeCode.Condition_TYPE_11);
        ConditionType conditionType13 = new ConditionType();
        conditionType13.setName("系统转账");
        conditionType13.setType(ParameterTypeCode.Condition_TYPE_13);
        ConditionType conditionType14 = new ConditionType();
        conditionType14.setName("全部");
        conditionType14.setType(ParameterTypeCode.Condition_TYPE_14);
        ConditionType conditionType15 = new ConditionType();
        conditionType15.setName("1个月内");
        conditionType15.setType(ParameterTypeCode.Condition_TYPE_15);
        ConditionType conditionType16 = new ConditionType();
        conditionType16.setName("3个月内");
        conditionType16.setType(ParameterTypeCode.Condition_TYPE_16);
        List<ConditionType> conditionTypeList = new ArrayList<>();
        List<ConditionType> timeTypeList = new ArrayList<>();
        conditionTypeList.add(conditionType0);
        conditionTypeList.add(conditionType1);
        conditionTypeList.add(conditionType2);
        conditionTypeList.add(conditionType3);
        conditionTypeList.add(conditionType4);
        conditionTypeList.add(conditionType5);
        conditionTypeList.add(conditionType6);
        conditionTypeList.add(conditionType7);
        conditionTypeList.add(conditionType8);
        conditionTypeList.add(conditionType9);
        conditionTypeList.add(conditionType10);
        conditionTypeList.add(conditionType11);
        conditionTypeList.add(conditionType13);
        timeTypeList.add(conditionType14);
        timeTypeList.add(conditionType15);
        timeTypeList.add(conditionType16);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("name","筛选条件");
        map1.put("list",conditionTypeList);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name","时间筛选");
        map2.put("list",timeTypeList);
        ArrayList screeningList = new ArrayList();
        screeningList.add(map1);
        screeningList.add(map2);
        if("1".equals(isApp) || "1".equals(isHapp)){
            model.addDataContent("screeningList",screeningList);
//        model.addDataContent("map2",map2);
            setJsonString(model.toJSON());
            return SUCCESS;
        }
        this.getRequest().setAttribute("map1",map1);
        this.getRequest().setAttribute("map2",map2);
        this.getRequest().setAttribute("screeningList",screeningList);
        return "capital_details";
    }

}
