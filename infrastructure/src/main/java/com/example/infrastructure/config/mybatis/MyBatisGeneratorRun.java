package com.example.infrastructure.config.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: aiui
 * @description:
 * @author:
 * @create: 2020-09-21 16:28
 **/
public class MyBatisGeneratorRun {
    public static void main(String[] args) {
        List<String> warnings = new ArrayList<>();
        // 覆盖
        boolean overwrite = true;
        // 给出generatorConfig.xml文件的位置
        File configFile = new File("src\\main\\resources\\mybatis-generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        try {
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
