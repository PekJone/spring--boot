执行SQL分析打印：
   1、加入依赖：
        <dependency>
            <groupId>p6spy</groupId>
            <artifactId>p6spy</artifactId>
            <version>3.9.1</version>
        </dependency>
   2、修改数据库配置：
        driver-class-name: com.p6spy.engine.spy.P6SpyDriver #com.mysql.cj.jdbc.Driver
        url: jdbc:p6spy:mysql://localhost:3306/springboot3
   3、创建spy.properties文件
        modulelist=com.baomidou.mybatisplus.extension.p6spy.MybatisPlusLogFactory,com.p6spy.engine.outage.P6OutageFactory
        # 自定义日志打印
        logMessageFormat=com.baomidou.mybatisplus.extension.p6spy.P6SpyLogger
        #日志输出到控制台
        appender=com.baomidou.mybatisplus.extension.p6spy.StdoutLogger
        # 使用日志系统记录 sql
        #appender=com.p6spy.engine.spy.appender.Slf4JLogger
        # 设置 p6spy driver 代理
        deregisterdrivers=true
        # 取消JDBC URL前缀
        useprefix=true
        # 配置记录 Log 例外,可去掉的结果集有error,info,batch,debug,statement,commit,rollback,result,resultset.
        excludecategories=info,debug,result,commit,resultset
        # 日期格式
        dateformat=yyyy-MM-dd HH:mm:ss
        # 实际驱动可多个
        driverlist=com.mysql.cj.jdbc.Driver
        # 是否开启慢SQL记录
        outagedetection=true
        # 慢SQL记录标准 2 秒
        outagedetectioninterval=2