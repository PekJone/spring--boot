import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-13  16:39
 */
public class GeneratorApp {
    public static void main(String[] args) {

        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //获取当前项目路径
        String project = System.getProperty("user.dir")+"/mybatis-plus-generator-code";
        //设置生成路径
        globalConfig.setOutputDir(project+"/src/main/java/");
        globalConfig.setAuthor("王朋飞");
        //代码生成后是不是打开所在的文件夹
        globalConfig.setOpen(false);
        //生成swagger2注解
        globalConfig.setSwagger2(true);
        //会在mapper.xml文件中生成mapResult 映射所有字段
        globalConfig.setBaseResultMap(true);
        //同文件生成覆盖
        globalConfig.setFileOverride(true);
        globalConfig.setDateType(DateType.ONLY_DATE);
        //直接用表的名字 %s 代表表明   驼峰命名
        globalConfig.setEntityName("%s");
        //mapper接口名 表明+Mapper
        globalConfig.setMapperName("%sMapper");
        //mapper。xmk文件名 表明+Mapper
        globalConfig.setXmlName("%sMapper");
        //设置服务名
        globalConfig.setServiceName("%sService");
        //业务逻辑类实现类名
        globalConfig.setServiceImplName("%sImplService");

        //将全局配置设置到生成器中
        mpg.setGlobalConfig(globalConfig);

        //数据源的配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/springboot3");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");

        mpg.setDataSource(dsc);

        //包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.mybatis.plus");

        mpg.setPackageInfo(packageConfig);

        StrategyConfig strategyConfig = new StrategyConfig();
        //表名的生成策略 下换线转驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //列名的生成策略 下换线转驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //生层的对象是否使用lombok注解
        strategyConfig.setEntityLombokModel(true);
        //是否使用restController
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setInclude("tbl_employee,transaction_one,t_vip");
        //驼峰转连字符
        strategyConfig.setControllerMappingHyphenStyle(false);

        mpg.setStrategy(strategyConfig);
        mpg.execute();


    }
}
