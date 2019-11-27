package ai.dongsheng.utils.mbg;

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

import java.util.*;

/**
 * @program: aioh_sw_basic
 * @description: 代码自动生成
 * @author: MichelleJou
 * @create: 2019-11-27 16:09
 **/
public class CodeGenerator1 {
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

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 文件生成路径
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        // 作者
        gc.setAuthor("MichelleJou");
        gc.setOpen(false);   // 是否生成完成后打开资源管理器
        // 日期类型的字段使用哪个类型，默认是 java8的 日期类型，此处改为 java.util.date
        gc.setDateType(DateType.ONLY_DATE);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        gc.setFileOverride(true); // 如果生成的不好，进行覆盖处理。
        // gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// mapper.xml 是否生成 ResultMap，默认 false 不生成
        gc.setBaseColumnList(true);// mapper.xml 是否生成 ColumnList，默认 false 不生成
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setEntityName("%sEntity");
        // gc.setMapperName("%sMapper");
        // gc.setXmlName("%sMapper");
        // gc.setServiceName("I%sService");
        // gc.setServiceImplName("%sServiceImpl");
        // gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://47.106.230.10/userdata?characterEncoding=utf-8&useSSL=false&allowMultiQueries=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Taipei");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("zhijia2018");
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
        // InjectionConfig in = new InjectionConfig() {
        //     @Override
        //     public void initMap() {
        //         Map<String, Object> map = new HashMap<String, Object>();
        //         //自定义配置，在模版中cfg.superColums 获取
        //         // TODO 这里解决子类会生成父类属性的问题，在模版里会用到该配置
        //         map.put("superColums", this.getConfig().getStrategyConfig().getSuperEntityColumns());
        //         this.setMap(map);
        //     }
        // };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                // return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
                //         + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                return projectPath + "/src/main/resources/ai/dongsheng/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //此处配置为 下划线转驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //生成的字段 是否添加注解，默认false
        // strategy.setEntityTableFieldAnnotationEnable(true);
        //实体类的基础父类。没有可以不配置。
        // strategy.setSuperEntityClass("com.example.mybatisplus.common.BaseEntity");
        //是否启用 Lombok
        strategy.setEntityLombokModel(true);
        //是否启用 builder 模式 例：new DevDevice().setDealerId("").setDeviceCode("");
        strategy.setRestControllerStyle(true);
        // 公共父类
        // strategy.setSuperControllerClass("com.example.mybatisplus.common.BaseController");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // strategy.setSuperServiceClass(null);
        // strategy.setSuperServiceImplClass(null);
        // strategy.setSuperMapperClass(null);
        mpg.execute();
    }
}
