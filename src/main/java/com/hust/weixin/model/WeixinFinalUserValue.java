package com.hust.weixin.model;

/**
 * 用户标签管理
 * Created by Administration on 2016/6/23.
 */
public class WeixinFinalUserValue {
    /**
     * 创建标签URL:POST
     * 说明：一个公众号，最多可以创建100个标签。
     */
    public final static String TAG_CREATE = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
    /**
     * 获取公众号已创建的标签URL:GET
     */
    public final static String TAG_GET = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
    /**
     * 编辑标签URL:POST
     */
    public final static String TAG_UPDATE = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
    /**
     * 删除标签URL:POST
     */
    public final static String TAG_DELETE = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN";
    /**
     * 获取标签下粉丝列表URL:POST
     */
    public final static String TAG_USER_GET = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN";
    /**
     * 批量为用户打标签URL:POST
     */
    public final static String TAG_USER_BATCHTAGGING = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";
    /**
     * 批量为用户取消标签URL:POST
     */
    public final static String TAG_USER_BATCHUNTAGGING = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";
    /**
     * 获取用户身上的标签列表URL:POST
     */
    public final static String USER_TAGIDS = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=ACCESS_TOKEN";


    /**
     * 获取用户基本信息（包括UnionID机制）URL:GET
     * UnionID机制说明：
     * 开发者可通过OpenID来获取用户基本信息。特别需要注意的是，如果开发者拥有多个移动应用、网站应用和公众帐号，
     * 可通过获取用户基本信息中的unionid来区分用户的唯一性，因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号，
     * 用户的unionid是唯一的。换句话说，同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。
     */
    public final static String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 批量获取用户基本信息URL:POST
     * 说明：最多支持一次拉取100条。
     */
    public final static String USER_INFO_BATCHGET = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";

    /**
     * 微信网页授权：用户同意授权，获取code URL:GET
     * appid	是	公众号的唯一标识
     * redirect_uri	是	授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * response_type	是	返回类型，请填写code
     * scope	是	应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * state	否	重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * #wechat_redirect	是	无论直接打开还是做页面302重定向时候，必须带此参数
     */
    public final static String OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    /**
     * 通过code换取网页授权access_token:GET
     * code说明 ： code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期.
     * appid	是	公众号的唯一标识
     * secret	是	公众号的appsecret
     * code	是	填写第一步获取的code参数
     * grant_type	是	填写为authorization_code
     */
    public final static String OAUTH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    /**
     * 刷新access_token（如果需要）
     * appid	是	公众号的唯一标识
     * grant_type	是	填写为refresh_token
     * refresh_token	是	填写通过access_token获取到的refresh_token参数
     */
    public final static String OAUTH_REACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";


}
