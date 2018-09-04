package com.hurong.credit.action.webPhone;

import com.hurong.core.web.action.BaseAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 各个静态页面
 * <li>信息披露</li>
 * <li>安全保障</li>
 */
public class WebPhoneStaticInfoAction extends BaseAction {

    //信息披露
    static final int TYPE_INFOMATION = 1;
    //安全保障
    static final int TYPE_SECURITY = 2;
    //服务协议
    static final int TYPE_SERVICE_AGREEMENT = 3;
    //帮助中心
    static final int TYPE_HELP = 4;

    protected Log logger = LogFactory.getLog(WebPhoneStaticInfoAction.class);

    @Deprecated
    public String infoPage() {
        String type = getRequest().getParameter("type");
        String view = "infoView";
        if (null != type) {
            try {
                int infoType = Integer.parseInt(type);
                switch (infoType) {
                    case TYPE_SECURITY:
                        view = "securityInfo";
                        break;
                    case TYPE_SERVICE_AGREEMENT:
                        view = "agreementInfo";
                        break;
                    case TYPE_HELP:
                        view = "helpList";
                        break;
                    case TYPE_INFOMATION:
                    default:
                        view = "infoView";
                        break;
                }
            } catch (NumberFormatException e) {
                logger.error("param type is " + type);
            }
        }
        return view;
    }


    /**
     * 信息披露
     *
     * @return
     */
    public String infoView() {

        return "infoView";
    }

    /**
     * 安全保障
     *
     * @return
     */
    public String security() {

        return "securityInfo";
    }

    /**
     * 服务协议
     *
     * @return
     */
    public String agreement() {

        return "agreementInfo";
    }

    /**
     * 帮助中心
     *
     * @return
     */
    public String helpCenter() {

        return "helpList";
    }

    /**
     * 媒体报道
     *
     * @return
     */
    public String mediaInfo() {
        String mediaId = getRequest().getParameter("mediaId");
        try {
            int id = Integer.parseInt(mediaId);

            return "mediaInfo";
        } catch (NumberFormatException e) {

        }
        return SUCCESS;
    }

    /**
     * 消息详情
     *
     * @return
     */
    public String msgInfo() {

        return "msgInfo";
    }
}
