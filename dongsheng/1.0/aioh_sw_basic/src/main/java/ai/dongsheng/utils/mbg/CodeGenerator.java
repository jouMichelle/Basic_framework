package ai.dongsheng.utils.mbg;

/**
 * @program: aioh_sw_basic
 * @description: 代码生成器
 * @author: MichelleJou
 * @create: 2019-11-26 10:34
 **/

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * 代码生成器,使用方法,直接运行；根据控制台提示输入模块名(demo),然后输入表名即可
 * </p>
 *
 * @author liuzongqiang
 * @since 2019-07-19
 */
public class CodeGenerator {
    // 输出的目录
    public static final String OUTPUTDIR = "/src/main/java";
    public static final String AUTHOR = "MichelleJou";
    // 数据库连接URL
    public static final String DBURL = "jdbc:mysql://192.168.10.31/payment?characterEncoding=utf-8&useSSL=false" +
            "&allowMultiQueries=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Taipei&tinyInt1isBit=false";
    // 数据库驱动
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    // 数据库用户名
    public static final String DBUSER = "root";
    // 数据库连接密码
    public static final String DBPASSWORD = "zhijia2018";


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 文件生成路径
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + OUTPUTDIR);
        // 作者
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        //是否覆盖文件
        gc.setFileOverride(false);
        // 日期类型的字段使用哪个类型，默认是 java8的 日期类型，此处改为 java.util.date
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);  //  实体属性 Swagger2 注解
        gc.setFileOverride(true); // 如果生成的不好，进行覆盖处理。
        // gc.setActiveRecord(true);    // 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// mapper.xml 是否生成 ResultMap，默认 false 不生成
        gc.setBaseColumnList(true);// mapper.xml 是否生成 ColumnList，默认 false 不生成
        //自定义文件名
        gc.setMapperName("%sMapper");
        gc.setServiceName("%sDao");
        gc.setServiceImplName("%sDaoImpl");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DBURL);
        dsc.setDriverName(DBDRIVER);
        dsc.setUsername(DBUSER);
        dsc.setPassword(DBPASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // pc.setModuleName(scanner("模块名"));
        pc.setParent("ai.dongsheng");
        pc.setEntity("model.entity");
        pc.setXml("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";



        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/ai/dongsheng/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");
        // 需要生成的表
        strategy.setInclude(scanner("表名多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        mpg.execute();
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}