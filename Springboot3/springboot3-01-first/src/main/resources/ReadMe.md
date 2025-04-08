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