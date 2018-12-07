package com.smallsnailtech.smallsnail.command;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public class Constants {

    /**
     * 服务器HOST
     */
    public static final String SERVER_HOST = "http://atjp.hipad.com:9010/";
    /**
     * apk包服务器HOST
     */
    public static final String SERVER_APK_URL = SERVER_HOST;
    /**
     * 服务器资源地址
     */
    public static final String SERVER_IMG_URL = SERVER_HOST;
    /**
     * 发表评论时传递的参数，以标识是评论
     */
    public static final int PUBLISH_COMMENT = 1;//
    /**
     * 发表回复时传递的参数，以标识是回复
     */
    public static final int PUBLISH_REPLY = 0;//
    /**
     * 页面跳转来源的标识符
     */
    public static final String FROM_WHERE_TAG = "FROM_WHERE_TAG";//
    /**
     * 分割符号
     */
    public static final String DECOLLATOR = "#";//
    /**
     * 应用通用的一些数据存储的SharedPreferences，不区分账号，包括token(整个应用最多存储一个token)等（对于整个应用仅存储登录成功的用户的token，在自动登录的时候使用）
     */
    public static final String COMMON_CONFIG_SP = "common_config_sp";//
    /**
     * 保存上一个进行登录操作的用户账号，仅作为用户上一次输入登录账号的缓存使用
     */
    public static final String LAST_LOGIN_ACCOUNT = "last_login_account";//
    /**
     * 某个用户登录成功后的存储的一个标识，在获取本地存储的区分账号的文件的时候使用，在退出应用和账号，或者token失效的情况下需要清除，总之它必须与登录成功的账号和状态一致
     */
    public static final String LOGIN_SUCCESSED_ACCOUNT = "login_successed_account";//
    /**
     * 用户消息数据的SharedPreferences存储，区分账号，以"user_message_sp#用户登录账号"区分
     */
    public static final String USER_MESSAGE_SP = "user_message_sp";//
    /**
     * 用户个人消息数据的SharedPreferences存储，区分账号，以"user_info_sp#用户登录账号"区分，在退出应用和账号的情况下需要清除
     */
    public static final String USER_INFO_SP = "user_info_sp";//
    /**
     * 用户登录成功后后台返回的token，应用中只会在本地缓存一条token，用来实现自动登录
     */
    public static final String TOKEN = "token";
    /**
     * 用户登录账号
     */
    public static final String USER_ACCOUNT = "user_account";
    /**
     * 用户名
     */
    public static final String USERNAME = "username";
    /**
     * 用户登录密码
     */
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_ID = "user_id";//
    public static final String HEAD_ICON_URL = "head_icon_url";//
    /**
     * 引导页数据类型
     */
    public static final int DATA_TYPE_SPLASH_IMAGE = 1;
    /**
     * 引导页数据类型
     */
    public static final int DATA_TYPE_SPLASH_MEDIA = 2;

    /**
     * 主页数据类型
     */
    public static final int DATA_TYPE_HOME_BANNER_LOOPER = 1;
    /**
     * 主页数据类型
     */
    public static final int DATA_TYPE_HOME_IMAGE_TOP = 2;
    /**
     * 主页数据类型
     */
    public static final int DATA_TYPE_HOME_IMAGE_RIGHT = 3;
    /**
     * 主页数据类型
     */
    public static final int DATA_TYPE_HOME_IMAGE_MORE = 4;

    /**
     * 接口
     */
    public static final String MOBILE_API_BASE = Constants.SERVER_HOST + "AppStoreWhiteList_independent/index.php/port/";
    /**
     * 用户密码登录的接口
     */
    public static final String MOBILE_API_LOGIN = Constants.SERVER_HOST + "AppStoreWhiteList_independent/index.php/port/Mobileuser/login";
    /**
     * 用户token自动登录的接口
     */
    public static final String MOBILE_API_AUTO_LOGIN = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/port/Mobileuser/recentlogin";
    /**
     * 用户退出登录的接口
     */
    public static final String MOBILE_API_LOGOUT = Constants.SERVER_HOST + "AppStoreWhiteList_independent/index.php/Port/Mobileuser/logout";
    /**
     * 用户修改登录密码的接口
     */
    public static final String MOBILE_API_UPDATE_PASSWORD = Constants.SERVER_HOST + "AppStoreWhiteList_independent/index.php/port/Mobileuser/checkpass";
    /**
     * 验证用户账号的接口
     */
    public static final String MOBILE_API_VERIFY_USER_ACCOUNT = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/port/mobileuser/checkmobile";
    /**
     * 验证用户名的接口
     */
    public static final String MOBILE_API_VERIFY_USERNAME = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/port/mobileuser/checkuser";
    /**
     * 注册新用户账号的接口
     */
    public static final String MOBILE_API_REGISTE_USER_INFO = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/port/mobileuser/register";
    /**
     * 用户修改资料的接口
     */
    public static final String MOBILE_API_UPDATE_USER_INFO = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/port/Mobileuser/modifyUserInfo";
    /**
     * 用户发表评论接口
     */
    public static final String MOBILE_API_PUBLISH_COMMENT = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/port/CommentWrite/commentwrite";
    /**
     * 获取用户发评论列表接口
     */
    public static final String MOBILE_API_GET_PUBLISH_COMMENT_LIST = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/CommentRead/commentread";
    /**
     * 用户发表回复接口
     */
    public static final String MOBILE_API_PUBLISH_REPLY = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/CommentWrite/commentwrite";
    /**
     * 获取用户回复列表接口
     */
    public static final String MOBILE_API_GET_REPLY_LIST = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/CommentRead/commentread2";
    /**
     * 用户对评论的点赞接口
     */
    public static final String MOBILE_API_COMMENT_PRAISE = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/ClickAgree/clickagree";
    /**
     * 用户收藏应用的接口
     */
    public static final String MOBILE_API_COLLECT_APP = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Collectadd/collectadd";
    /**
     * 用户取消收藏应用的接口
     */
    public static final String MOBILE_API_REMOVE_COLLECTED_APP = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Collectdel/collectdel";
    /**
     * 获取用户收藏应用的列表接口
     */
    public static final String MOBILE_API_GET_COLLECTED_APP_LIST = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Collectlist/collectlist";
    /**
     * 获取用户是否已收藏某个应用的接口
     */
    public static final String MOBILE_API_HAS_COLLECTED_APP = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Collectis/collectis";
    /**
     * 获取用户收到的消息数据的接口
     */
    public static final String MOBILE_API_RECEIVED_MSG_COUNT = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Commentnum/commentagreeNum";
    /**
     * 增加应用的搜索量的接口
     */
    public static final String MOBILE_API_ADD_APP_SEARCH_COUNT = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Searchapp/searchcount";
    /**
     * 获取推荐应用的接口
     */
    public static final String MOBILE_API_GET_RECOMMEND_APP_LIST = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Recontent/recontent";
    /**
     * 获取分类应用的接口
     */
    public static final String MOBILE_API_GET_CATEGORY_APP_LIST = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Getsofttype/getsofttype";
    /**
     * 获取分类详情的接口
     */
    public static final String MOBILE_API_GET_CATEGORY_DETAIL = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Getsofttypeinfo/getsofttypeinfo";
    /**
     * 获取排名应用的接口
     */
    public static final String MOBILE_API_GET_RANKING_APP_LIST = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Getsoftlistbyweek/getsoftlistbyweek";
    /**
     * 获取搜索应用的接口
     */
    public static final String MOBILE_API_GET_SEARCH_APP_LIST = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/port/searchapp/searchapp";
    /**
     * 获取主题分类应用的接口
     */
    public static final String MOBILE_API_GET_THEME_APP_LIST = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Gettopictypelist/gettopictypelist";
    /**
     * 获取人气榜应用列表的接口（评论数排行）
     */
    public static final String MOBILE_API_GET_RANKING_POPULARITY_APP_LIST = "AppStoreWhiteList_independent/index.php/Port/Getsoftlistbycomment2/getsoftlistbycomment2";
    /**
     * 获取热搜索榜应用列表的接口（搜索数排行）
     */
    public static final String MOBILE_API_GET_RANKING_HOT_SEARCH_APP_LIST = "AppStoreWhiteList_independent/index.php/Port/Getsoftlistbycomment3/getsoftlistbycomment3";
    /**
     * 获取下载榜应用列表的接口（总下载次数排行）
     */
    public static final String MOBILE_API_GET_RANKING_DOWNLOAD_APP_LIST = "AppStoreWhiteList_independent/index.php/Port/Getsoftalldown/getsoftalldown";
    /**
     * 获取新锐榜应用列表的接口（上架的时间排行）
     */
    public static final String MOBILE_API_GET_RANKING_UPLOAD_TIME_APP_LIST = "AppStoreWhiteList_independent/index.php/Port/Getsoftuptime/getsoftuptime";
    /**
     * 获取风云榜榜应用列表的接口
     */
    public static final String MOBILE_API_GET_RANKING_ATTENTION_APP_LIST = "AppStoreWhiteList_independent/index.php/Port/Getsoftlistbyweek/getsoftlistbyweek";
    /**
     * 更多相关应用的接口
     */
    public static final String MOBILE_API_GET_MORE_RECOMMEND_APP_LIST = "AppStoreWhiteList_independent/index.php/Port/MoreRecommend/morerecommend";
    /**
     * 获取我接收到的针对我的评论的回复列表的接口
     */
    public static final String MOBILE_API_GET_COMMENT_REPLY_LIST = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Commentnum/commentList";
    /**
     * 获取我接收到的针对我的评论的点赞列表的接口
     */
    public static final String MOBILE_API_GET_COMMENT_PRAISE_LIST = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/Port/Commentnum/agreeList";
    /**
     * 获取已安装应用
     */
    public static final String MOBILE_API_GET_INSTALLED_APPS = Constants.SERVER_HOST
            + "AppStoreWhiteList_independent/index.php/port/Package/package";
    /**
     * 自动登录成功后的本地广播action
     */
    public static final String ACTION_AUTO_LOGIN_SUCCESSED = "com.hipad.appwhitelist.action.AUTO_LOGIN_SUCCESSED";
    /**
     * 正在自动登录中的本地广播action
     */
    public static final String ACTION_AUTO_LOGING_IN = "com.hipad.appwhitelist.action.AUTO_LOGING_IN";
    /**
     * 自动登录失败后的本地广播action
     */
    public static final String ACTION_AUTO_LOGIN_FAILED = "com.hipad.appwhitelist.action.AUTO_LOGIN_FAILED";
    /**
     * 正常的账号密码登录成功后的本地广播action
     */
    public static final String ACTION_NORMAL_LOGIN_SUCCESSED = "com.hipad.appwhitelist.action.NORMAL_LOGIN_SUCCESSED";
    /**
     * 手动操作退出账号成功后的本地广播action
     */
    public static final String ACTION_NORMAL_LOGOUT_SUCCESSED = "com.hipad.appwhitelist.action.NORMAL_LOGOUT_SUCCESSED";
    /**
     * 获取热门搜索应用推荐数据成功后的本地广播action
     */
    public static final String ACTION_HOT_SEARCH_APPS = "com.hipad.appwhitelist.action.HOT_SEARCH_APPS";
    /**
     * 获取版本成功后的本地广播action
     */
    public static final String ACTION_VERSION_UPDATE_EXECUTABLE = "com.hipad.appwhitelist.action.VERSION_UPDATE_EXECUTABLE";
    /**
     * 轮询获取用户的消息数据成功后的本地广播action
     */
    public static final String ACTION_MESSAGE_POLLING_RECEIVE = "com.hipad.appwhitelist.action.MESSAGE_POLLING_RECEIVE";
    /**
     * 用户的消息数据修改后的本地广播action
     */
    public static final String ACTION_MESSAGE_DATA_UPDATED = "com.hipad.appwhitelist.action.MESSAGE_DATA_UPDATED";
    /**
     * 界面在前台的广播
     */
    public static final String ACTION_ACTIVITY_FOREGROUND = "com.hipad.appwhitelist.action.ACTIVITY_FOREGROUND";

}
