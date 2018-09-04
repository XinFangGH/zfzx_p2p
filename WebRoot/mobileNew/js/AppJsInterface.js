/**
 *
 * 用于升升投App端
 * 浏览器和 H5页面js 交互
 * 只能用于mobile H5版本与 升升投Android /Ios App
 *
 * ===============注意===========================
 * 调用方法 AppInterface.method()
 * <li>参数尽量不要为空</li>
 * <li>参数后为传入的参数类型,必须保持一致</li>
 * ==========================================
 *
 *
 */
var AppInterface = {

    /**
     * 是否是ios 设备
     */
    isIos: function () {
        return (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent))
    },

    /**
     * 是否是android 设备
     */
    isAndroid: function () {
        return (/(Android)/i.test(navigator.userAgent))
    },

    /**
     * 获取浏览器UA
     * @returns {string}
     */
    getUserAgent: function () {
        return navigator.userAgent
    },
    /**
     * 是否是 androidApp浏览器
     * @returns {boolean}
     */
    isAndroidApp: function () {
        return (/(zxzbAndroid)/i.test(navigator.userAgent))
    },
    /**
     * 是否是 iosApp浏览器
     * @returns {boolean}
     */
    isIosApp: function () {
        return (/(zxzbIos)/i.test(navigator.userAgent))
    },

    /**
     * 是否是 app 内置浏览器
     * 可用于在 其他地方来判断是否是app浏览器
     * @returns {*}
     */
    isAppZxzb: function () {
        return this.isAndroidApp() || this.isIosApp();
    },
    /**
     * 获取 App 与Js 交互的window 对象
     * @returns {*}
     */
    appObject: function () {
        if (this.isAndroidApp()) {
            return window.zxzbAndroid;
        } else if (this.isIosApp()) {
            return iOSWebView;
        }
        return window
    },
    //=================以下是H5交互时需要调用app的函数===============\(^o^)/~====== 单独调用  window.app对象名.函数(参数)=============================================

    /**
     * 第三方结果回调
     * ## resultJson  传入json格式
     * ###   必须包含以下参数
     * * extMark  回调类型 String
     * * status  回调结果  boolean
     * * errorMsg  回调信息  string
     * * errorCode  错误码
     * * {boolean}
     */
    thridPartCallBck: function (resultJson) {
        if (this.isAndroidApp()) {
            this.appObject().thridCallBck(resultJson);
            return true;
        } else if (this.isIosApp()) {
            var t = setTimeout(function () {
                debugger;
                iOSWebView.thridPartCallBck(resultJson);
                clearTimeout(t)
            }, 500)

            return true;
        }
        return false
    },
    /**
     * H5页面回调
     * ## 传入参数为JSON格式,后续可以追加参数
     * ###  必须包含以下参数
     * * status   boolean 状态
     * * msg      String  回调信息
     * * type     String  回调业务类型
     */
    nativeCallBack: function (resultJson) {
        if (this.isAndroidApp()) {
            this.appObject().nativeCallBack(resultJson);
            return true;
        } else if (this.isIosApp()) {
            var t = setTimeout(function () {
                debugger;
                iOSWebView.nativeCallBack(resultJson);
                clearTimeout(t)
            }, 500)
            return true;
        }
        return false
    }


    // /**
    //  * 刷新用户信息
    //  * @returns {boolean}
    //  */
    // refreshUserInfo: function () {
    //     if (this.isAndroidApp()) {
    //         this.appObject().refreshUserInfo();
    //         return true;
    //     } else if (this.isIosApp()) {
    //         this.appObject().refreshUserInfo();
    //         return true;
    //     }
    //     return false;
    // },
    // /**
    //  * 屏幕中间提示信息
    //  * @param messageStr 提示信息
    //  * @returns {boolean}
    //  */
    // toast: function (messageStr) {
    //     if (this.isAndroidApp()) {
    //         this.appObject().toast(messageStr);
    //         return true;
    //     } else if (this.isIosApp()) {
    //         this.appObject().toast(messageStr);
    //         return true;
    //     }
    //     return false
    // },
    // /**
    //  * app 点击返回按钮
    //  * @returns {boolean} 是否由App 调用
    //  */
    // back: function () {
    //     if (this.isAndroidApp()) {
    //         this.appObject().back();
    //         return true;
    //     } else if (this.isIosApp()) {
    //         this.appObject().back();
    //         return true;
    //     }
    //     return false;
    // },
    //
    // /**
    //  * app 点击关闭页面
    //  * @returns {boolean} 是否由App 调用
    //  */
    // close: function () {
    //     if (this.isAndroidApp()) {
    //         this.appObject().finish();
    //         return true;
    //     } else if (this.isIosApp()) {
    //         this.appObject().close();
    //         return true;
    //     }
    //     return false;
    // },
    // /**
    //  * loading 弹窗
    //  * @returns {boolean} 是否由App 调用
    //  */
    // loading: function () {
    //     if (this.isAndroidApp()) {
    //         this.appObject().loading();
    //         return true;
    //     } else if (this.isIosApp()) {
    //         this.appObject().loading();
    //         return true;
    //     }
    //     return false;
    // },
    // /**
    //  * 关闭 loading 弹窗
    //  * @returns {boolean} 是否由App 调用
    //  */
    // loadingDismiss: function () {
    //     if (this.isAndroidApp()) {
    //         this.appObject().disLoading();
    //         return true;
    //     } else if (this.isIosApp()) {
    //         this.appObject().loadingDismiss();
    //         return true;
    //     }
    //     return false;
    // },
//=================以上是H5交互时需要调用app的函数=====end==========\(^o^)/~================================================

}


