Weiboclient4j
===

为什么需要另外一个Java版本的微博客户端？
---

新浪微博官方推荐的Java客户端 [weibo4j](http://code.google.com/p/weibo4j/) 一直没有发布到maven仓库，而我们是重度maven用户，因而重新发明了这个新的轮子。

通过maven引用weiboclient4j
---

在项目pom.xml里面加入依赖：

    <dependency>
      <groupId>com.github.hoverruan</groupId>
      <artifactId>weiboclient4j</artifactId>
      <version>0.3.0</version>
      <scope>runtime</scope>
    </dependency>

使用
---

Weiboclient4j支持新浪微博API V1和V2（未完成），目前推荐使用V2版本的接口：

    // 使用你的应用的api key和secret
    String apiKey = "xxxxxxx";
    String apiSecret = "xxxxxxxx";

    WeiboClient2 client = new WeiboClient2(apiKey, apiSecret);

OAuth2例子：

    String authorizationCallback = "..."; // 你的Callback地址
    String state = "...";
    String url = client.getAuthorizationUrl(ResponseType.Code, DisplayType.Default, state, authorizationCallback);

    // 浏览器重定向到url; 用户授权; 然后返回callback地址
    String code = ... // 从新浪的回调请求里面获得code
    String accessTokenCallback = "..."; // 或者Access Token的Callback地址
    SinaWeibo2AccessToken accessToken = client.getAccessToken(GrantType.AuthorizationCode, code, state, accessTokenCallback);

    System.out.println("Access token: " + accessToken.getToken());
    System.out.println("User Uid: " + accessToken.getUid());
    System.out.println("Expires in: " + accessToken.getExpiresIn());
    System.out.println("Remind in: " + accessToken.getRemindIn());

获取用户Timeline例子：

    client.setAccessToken(new SinaWeibo2AccessToken("..."));
    StatusService service = client.getStatusService();
    Timeline friendsTimeline = service.getFriendsTimeline();

更多的使用例子可以参考 `weiboclient4j.examples.OAuth2CommandLine`

API参数对象化
---

WeiboClient2里面，大部分的方法都没有Javadoc，取而代之的是大部分的参数都是特定的对象，这样做的原因是因为：

- 写Javadoc太麻烦
- 一些API的参数较多，如果使用基本类型容易混淆各个参数的含义
- IDE对已知类型的对象、Enum能提供更友好的提醒和自动完成

所有的参数对象在 `package weiboclient4j.params` 下面；举一个例子：

    FriendshipService service = client.getFriendshipService();
    Friendship friendship;

    friendship = service.getFriendship(new SourceUid(12345), new TargetUid(67890));

    // 或者
    friendship = service.getFriendship(new SourceScreenName("xxx"), new TargetScreenName("yyy"));

分页对象 Paging
---

分页相关的参数，全部通过 `Paging` 对象封装：

    Paging paging = Paging.create()
            .sinceId(12345)
            .count(25);

    Timeline timeline = service.getFriendsTimeline(paging, BaseApp.No, Feature.All); // 后两个参数可省略

新浪微博API V2支持情况
---

[API文档 V2](http://open.weibo.com/wiki/API%E6%96%87%E6%A1%A3_V2)

<table>
<tr><td>微博接口</td><td>StatusService</td><td>完成</td></tr>
<tr><td>评论接口</td><td>CommentService</td><td>完成</td></tr>
<tr><td>用户接口</td><td>UserService</td><td>完成</td></tr>
<tr><td>关系接口</td><td>FriendshipService</td><td>完成</td></tr>
<tr><td>帐号接口</td><td>AccountService</td><td>完成</td></tr>
<tr><td>收藏接口</td><td>FavoriteService</td><td>完成</td></tr>
<tr><td>话题接口</td><td>TrendService</td><td>完成</td></tr>
<tr><td>标签接口</td><td>TagService</td><td>完成</td></tr>
<tr><td>注册接口</td><td>RegisterService</td><td>完成</td></tr>
<tr><td>搜索接口</td><td>SearchService</td><td>完成</td></tr>
<tr><td>推荐接口</td><td>SuggestionService</td><td>施工中</td></tr>
<tr><td>提醒接口</td><td>RemindService</td><td>完成</td></tr>
<tr><td>短链接口</td><td>ShortUrlService</td><td>完成</td></tr>
<tr><td>通知接口</td><td>NotificationService</td><td>完成</td></tr>
<tr><td>公共服务接口</td><td>CommonService</td><td>完成</td></tr>
<tr><td>位置服务接口</td><td>PlaceService</td><td> </td></tr>
<tr><td>地理信息接口</td><td>LocationService</td><td> </td></tr>
</table>

反馈
---

[阮永沛Hover](http://weibo.com/hoverruan) @ 新浪微博
