# 秒杀项目

1. 概况
   - 架构
     - 前端：H5, BootStrap, thymeleaf，jQuery
     - 中间件：RabbitMQ, Redis, Druid
     - 后端:SpringBoot, JSR303, MyBatis
   - 架构模型设计图（设计来源：[Yrion](https://www.cnblogs.com/wyq178/)）
     - ![img](README.assets/1066538-20191218003313069-325957542.png)

2. 进度
   1. V1.0
      - 基础架构搭建：sprint boost、redis、druid、JSR303、MyBatis
      - 用户登录和分布式Session
   2. V1.1
      - 基础秒杀功能实现



---

# 秒杀项目错误记录

1. SpringBoot: Could not resolve placeholder 'XXXX' in value "${XXXX}"   驼峰配置注入失败 导入配置项即可
2. Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is redis.clients.jedis.exceptions.JedisConnectionException: Could not get a resource from the pool] with root cause
   - redis没有连接上
     - 先尝试localhost
     - bind关掉
       protected-mode no
3. nested exception is com.alibaba.fastjson.JSONException: default constructor not found. class com.xu.miaosha.domain.User] with root cause

- User类需要空参构造器

4. cookies没有在跳转页面获取到

   - ```
     cookie.setPath("/");  //保证同一应用服务器内共享方法
     ```

5. java.lang.IllegalArgumentException: Mapped Statements collection already contains value for ***
   - 环境：springboot
   - 错误原因： MyBatis不允许有重名方法，更改方法名称即可
   - 参考：https://stackoverflow.com/questions/37085803/mybatis-mapped-statements-collection-already-contains-value-for?utm_source=hacpai.com

---

# 项目心得

1. 两次MD5
   - 用户端：PASS=MD5(明文+固定salt)  防止用户密码明文传递
   - 服务端：PASS=MD5(用户输入（已经计算的了MD5）+随机salt)   防止数据库被控破，MD5可以反推，双保险
2. 分布式session
   - 用户登陆生成（token），和用户信息一起存储到redis中
   - token放在用户cookie中传递
   - 通过获得cookie中的token读取redis中保存的信息
