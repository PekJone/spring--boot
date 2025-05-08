environment对象： 
    环境配置和系统配置全部封装在改对象中 可以调用Environment接口的方法获取

@SpringBootApplication包括下面三个注解：
     @SpringBootConfiguration
     @EnableAutoConfiguration
     @ComponentScan

@ConfigurationProperties(prefix=""")
    将配置文件绑定到bean


web启动器-> 引入web相关的自动配置类->制造web相关的组件

a、自动配置原理：
   1、运行环境准备阶段
   2、最终传递引入了自动配置的jar包
   3、自动配置的jar包有152个自动配置类，到此运行环境准备完毕
b、运行阶段
   1、@EnableAutoConfiguration注解启动自动配置，将152个自动配置类全部加载到IOC容器中，然后根据开发场景
     帅选出必须的自动配置类
   2、自动配置类加载了一堆组件。
   3、每个组件需要的数据来自属性类。
   4、属性类又和配置类绑定在一起。
C、导入启动器，修改配置文件，就可以完成对应功能的开发。

在SpringMVC自动配置基础之上 实现自己的自定义自动化配置：
    @Configuration
    编写一个类实现WebMvcConfiguration接口 ，并使用@Configuration注解

http请求协议：
     请求头 Accept :json 
     请求行（get请求）
     空白行
     请求体 json在请求体中（post请求）
     put delete请求：
http:响应协议：
     状态行
     响应头
     空白行
     响应体

springBoot普通静态资源处理：根据控制器方法优先原则，会先去找合适的控制器方法，如果没有合适的控制器方法，静态资源处理才会生效，
自动匹配类路经的四个位置： 资源访问的优先级也是按照从上到下的顺序来找
    classpath:/META-INF/resource  资源会被自动加载  不受手动配置的影响
    classpath:/resource/
    classpath:/static/
    classpath:/public/

指定服务器返回数据格式的方式：
    1、Accept的方式指定：优先使用这种方式
        curl -H "Accept:Application/json" http://localhost:8080/detail
        curl -H "Accept:Application/xml" http://localhost:8080/detail
    2、format格式指定,需要做一下配置，如果没有一下配置，默认使用Accept方式
        mvc:
            contentnegotiation:
                favor-parameter:
                    true
        curl  http://localhost:8080/detail?format=xml


  <!--可以将java对象转换成xml格式的字符串 -->
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
        </dependency>

常见的HttpMessageConvert
   【请求】：提交表单(form)数据转换成Java对象主要任务由FormMessageConvert消息转换器完成
   【请求】【响应】：提交JSON的数据转换成JAVA对象主要任务由MappingJackson2HttpMessageConvert消息转换完成（通常使用@RequestBody注解）
   【响应】：将JAVA对象转换成Xml格式，并将其写入响应体，JaxXRootElementHttpMessageConvert
   【响应】:将String直接写入到响应体  StringHttpMessageConvert


@ControllerAdvice + @ExceptionHandler 可以用来定义全局异常处理器  针对所有的控制器都有效
   

@ExceptionHandler(YiChang.class)  当发生YiChang.class异常时  调用注解的方法

配置日志级别：
1、项目级别的日志配置： logging.level.root=debug
2、为特定的包设置日志级别：logging.level.org.com.learn.config=debug
3、为特定的类设置日志级别：logging.level.org.com.learn.config=info
4、打印SQL相关的日志：logging.level.org.com.learn.mapper=debug

日志输出到文件：
方式1： :logging.file.path= ./log/
方式2：logging.file.name=my.log  日志文件生成到项目的艮路径下 无法设置文件位置  
如果两种方式同时存在 则方式一失效  

#打印SQL日志
logging:
logback:
rollingpolicy:
#启动项目时是否清理归档日志
clean-history-on-start: false
#日志文件达到多大时进行归档
max-file-size: 100MB
#归档日志文件最多保留几天
max-history: 60
#所有归档日志文件达到多大时进行删除  默认0B  表示不删除
total-size-cap: 50GB
#归档文件日志名的格式
file-name-pattern: ${LOG.FILE}.%d{yyyy-MM-dd}.%i.gz



