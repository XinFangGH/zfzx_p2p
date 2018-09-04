package com.hurong.credit.action.mobile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.article.P2pBannerlink;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.article.ArticleService;
import com.hurong.credit.service.article.P2pBannerlinkService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.p2p.PlatDataPublishService;
import com.hurong.credit.service.user.BpCustMemberService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class MobileDataAction extends BaseAction {

    @Resource
    private BpCustMember bpCustMember;
    @Resource
    private BpCustMemberService bpCustMemberService;
    @Resource
    private PlBidPlanService plBidPlanService;
    @Resource
    private P2pBannerlinkService p2pBannerlinkService;
    @Resource
    private ArticleService articleService;
    @Resource
    private ObAccountDealInfoService obAccountDealInfoService;
    @Resource
    private PlatDataPublishService platDataPublishService;

    private Map<String, Object> resultMap;

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * 首页信息
     */
    public String getIndex() {

        //获取首页加载的banner图列表
        List<P2pBannerlink> bannerList = p2pBannerlinkService.getBannerList("P2P");
        //获取首页网站公告、媒体报道

        //理财计划列表
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();
        sb.append("{\"success\":true,\"data\":");
        sb.append(gson.toJson(bannerList));
        sb.append(",\"result\":1");
        sb.append("}");

        return SUCCESS;
    }


}
