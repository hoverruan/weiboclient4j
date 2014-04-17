Weiboclient4j
=============

[![Build Status](https://travis-ci.org/hoverruan/weiboclient4j.png?branch=master)](https://travis-ci.org/hoverruan/weiboclient4j)

演示 Demo
--------

一个演示怎样使用 `weiboclient4j` 的Demo应用：

- 访问[代码 @ Github](https://github.com/hoverruan/weiboclient4j-demo)
- 访问[部署在Heroku上的应用](http://wc4jdemo.herokuapp.com/)

为什么需要另外一个Java版本的微博客户端？
----------------------------------

新浪微博官方推荐的Java客户端 [weibo4j](http://code.google.com/p/weibo4j/) 一直没有发布到maven仓库，而我们是重度maven用户，因而重新发明了这个新的轮子。

通过maven引用weiboclient4j
-------------------------

在项目pom.xml里面加入依赖：

```xml
<dependency>
  <groupId>com.github.hoverruan</groupId>
  <artifactId>weiboclient4j</artifactId>
  <version>0.4.14</version>
</dependency>
```

使用
----

Weiboclient4j支持新浪微博API V1和V2（未完成），目前推荐使用V2版本的接口：

```java
// 使用你的应用的api key和secret
String apiKey = "xxxxxxx";
String apiSecret = "xxxxxxxx";

WeiboClient client = new WeiboClient(apiKey, apiSecret);
```

OAuth2例子：

```java
String authorizationCallback = "..."; // 你的Callback地址
String state = "...";
String url = client.getAuthorizationUrl(state, authorizationCallback);

// 浏览器重定向到url; 用户授权; 然后返回callback地址
String code = ... // 从新浪的回调请求里面获得code
String accessTokenCallback = "..."; // 或者Access Token的Callback地址
SinaWeibo2AccessToken accessToken = client.getAccessTokenByCode(code, accessTokenCallback);

System.out.println("Access token: " + accessToken.getToken());
System.out.println("User Uid: " + accessToken.getUid());
System.out.println("Expires in: " + accessToken.getExpiresIn());
System.out.println("Remind in: " + accessToken.getRemindIn());
```

获取用户Timeline例子：

```java
String accessToken = "xxxxxxx";
WeiboClient client = new WeiboClient(accessToken);
StatusService service = client.getStatusService();
Timeline friendsTimeline = service.getFriendsTimeline();
```

更多的使用例子可以参考 `weiboclient4j.examples.OAuth2CommandLine`

API参数对象化
------------

WeiboClient里面，大部分的方法都没有Javadoc，取而代之的是大部分的参数都是特定的对象，这样做的原因是因为：

- 写Javadoc太麻烦
- 一些API的参数较多，如果使用基本类型容易混淆各个参数的含义
- IDE对已知类型的对象、Enum能提供更友好的提醒和自动完成

所有的参数对象在 `package weiboclient4j.params` 下面；举一个例子：

使用静态引入 **CoreParameters.\***:

```java
import static weiboclient4j.params.CoreParameters.*;

FriendshipService service = client.getFriendshipService();
Friendship friendship;

friendship = service.getFriendship(sourceUid(12345), targetUid(67890));

// 或者
friendship = service.getFriendship(sourceScreenName("xxx"), targetScreenName("yyy"));
```

使用 **P**:

```java
import weiboclient4j.params.P;

FriendshipService service = client.getFriendshipService();
Friendship friendship;

friendship = service.getFriendship(P.sourceUid(12345), P.targetUid(67890));

// 或者
friendship = service.getFriendship(P.sourceScreenName("xxx"), P.targetScreenName("yyy"));
```

分页对象 Paging
--------------

分页相关的参数，全部通过 `Paging` 对象封装：

```java
Paging paging = Paging.create()
        .sinceId(12345)
        .count(25);

Timeline timeline = service.getFriendsTimeline(paging, BaseApp.No, Feature.All); // 后两个参数可省略
```

新浪微博API V2支持情况
--------------------

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
<tr><td>推荐接口</td><td>SuggestionService</td><td>完成</td></tr>
<tr><td>提醒接口</td><td>RemindService</td><td>完成</td></tr>
<tr><td>短链接口</td><td>ShortUrlService</td><td>完成</td></tr>
<tr><td>通知接口</td><td>NotificationService</td><td>完成</td></tr>
<tr><td>公共服务接口</td><td>CommonService</td><td>完成</td></tr>
<tr><td>位置服务接口</td><td>PlaceService</td><td>完成</td></tr>
<tr><td>地理信息接口</td><td>LocationService</td><td>开发中</td></tr>
</table>

反馈
----

[阮永沛Hover](http://weibo.com/hoverruan) @ 新浪微博

CHANGELOG
---------

* 0.4.14
    * 将默认的grantType设置为AuthorizationCode
    * 将UnreadCount的V1的字段删除
    * 添加简化的获取AccessToken的方法
    * 在User中添加attitudes字段
* 0.4.13
    * 修复WeiboClient使用AccessToken初始化时的一个问题
* 0.4.12
    * 将get(...)和post(...)方法添加到WeiboClient
* 0.4.11
    * 修复FloatParam没法添加参数的问题
    * 在WeiboClient添加getPlaceService()方法
* 0.4.10
    * 在User中添加新字段：verifiedType
* 0.4.9
    * 在User中添加两个新字段：avatarLarge和verifiedReason
* 0.4.8
    * 抛出OAuthException
    * 修复获取转发列表的一个BUG
* 0.4.7
    * 处理OAuth的异常
* 0.4.6
    * 添加微博错误代码
    * 去掉了一些已经不存的接口
* 0.4.5
    * 修复上传图片的错误
* 0.4.4
    * 实现上传图片以及发送多图微博的接口
    * 支持password类型的grant_type认证
* 0.4.3
    * 实现地理接口中的"坐标转换接口"
    * 引入 weiboclient4j.params.P 简化参数对象的创建
    * 实现地理接口中的"生成一张静态的地图图片"接口
    * 将model中的字段hasvisible改名成visible
* 0.4.2
    * 将WeiboClient2重命名为WeiboClient
* 0.4.1
    * Fixed #7
* 0.4.0
    * 添加CoreParameters语法糖
    * 去掉第一版的API支持
    * 添加travis-ci.org自动构建状态
    * 重构各个Service，使用可变参数
* 0.3.3
    * 完成所有的位置服务的接口

* 0.3.2
    * 实现位置接口中的"获取用户的todo列表"接口
    * 实现位置接口中的"获取用户的点评列表"接口
    * 实现位置接口中的"获取用户的照片列表"接口
    * 实现位置接口中的"获取用户签到过的地点列表"接口
    * 实现推荐接口中的"获取用户可能感兴趣的人"接口
    * 将 Access Token 放到 HTTP Header 中
    * 为上传图片功能添加几个快捷方法
* 0.3.1
    * 实现位置服务 (PlaceService) 中的用户读取接口
    * 实现上传图片并发布一条微博的接口 (http://open.weibo.com/wiki/2/statuses/upload)
    * 实现位置服务 (PlaceService) 中的动态读取接口
* 0.3.0
    * 将新浪微博接口2.0的实现从巨大的 WeiboClient2 分离到多个小的 Service 中

* 0.2.x
    * 实现新浪微博接口2.0的主要功能

* 0.1
    * 实现新浪微博接口1.0的主要功能
