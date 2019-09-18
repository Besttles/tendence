# tendence
个人架构功能测试

# tendence微服务项目

## 基础结构

### Springboot （2.1.5.RELEASE）

### mybatis（1.3.2）

集成mybatis使用外部配置的形式，指定配置文件位置

```yaml
mybatis:
  type-aliases-package: com.google.tendengce.mapper
  mapper-locations: classpath:mapper/*.xml
  configLocation: classpath:config/mybatis-config.xml
```

### druid

配置druid数据连接，可以提供程序运行监控功能

导入依赖：

```xml
<dependency>
   <groupId>com.alibaba</groupId>
   <artifactId>druid-spring-boot-starter</artifactId>
   <version>1.1.10</version>
</dependency>
```

配置druid

```yml
#配置数据库
spring:
  datasource:
    name: test
    url: jdbc:mysql://localhost:3306/data?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false
    username: root
    password: root
    #配置druid数据源
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    filters: stat
    maxActive: 20
    initialSize: 1
    maxWait: 60000
    minIdle: 1
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: select 'x'
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    maxOpenPreparedStatements: 20
```

配置druid数据监控平台

```java
@WebServlet(urlPatterns = "/druid/*",
        initParams={
                @WebInitParam(name="allow",value=""),// IP白名单 (没有配置或者为空，则允许所有访问)
                @WebInitParam(name="deny",value="192.168.0.1"),// IP黑名单 (存在共同时，deny优先于allow)
                @WebInitParam(name="loginUsername",value="root"),// 用户名
                @WebInitParam(name="loginPassword",value="root"),// 密码
                @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
        })
@WebFilter(filterName="myFilter",urlPatterns="/*")
public class MyFilter extends StatViewServlet implements Filter {

    /**
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println(request.getRequestURI());
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        filterChain.doFilter(request, response);
    }
    @Override
    public void destroy() {
    }
}
```

配置filter来进行控制URL

```java
@WebFilter(filterName = "druidWebStatFilter" , urlPatterns = "/*",
initParams = {
        @WebInitParam(name="exclusions",value="*.html,*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
})
public class DruidStatFilter extends WebStatFilter {
}
```

这里不要忘记在启动类开启注解扫描

```
@ServletComponentScan
```

访问监控页面是项目根路径 + urlPatterns 来访问

### 日志管理

使用logback进行日志管理

SpringBoot会自动扫描Resource中的logback.xml , logback-spring.xml,logback-spring.groovy,logback.groovy文件

程序中使用

```java
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
```

### 微服务架构

配置SpringCloud  Springboot 2.x版本在使用Eureka时的特殊处理



