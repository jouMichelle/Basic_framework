package com.example.infrastructure.config.mybatis;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @program: aiui
 * @description:
 * @author:
 * @create: 2020-09-21 16:25
 **/
public class MyCommentGenerator implements CommentGenerator {
    private Properties properties = new Properties();
    /**
     * 抑制日期  默认false：不抑制
     */
    private boolean suppressDate = false;
    /**
     * 抑制注释 默认false：不抑制
     */
    private boolean suppressAllComments = false;

    /**
     * 显示数据库comments 默认false：不显示
     */
    private boolean addRemarkComments = false;
    /**
     * 日期格式
     */
    private SimpleDateFormat dateFormat;

    public MyCommentGenerator() {
        super();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    }


    /**
     * 读取配置文件
     *
     * @param properties
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));
        this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
        String dateFormatString = properties.getProperty("dateFormat");
        if (StringUtility.stringHasValue(dateFormatString)) {
            this.dateFormat = new SimpleDateFormat(dateFormatString);
        }

    }

    /**
     * 日期格式化
     *
     * @return 格式化后的日期
     */
    protected String getDateString() {
        if (this.suppressDate) {
            return null;
        } else {
            return this.dateFormat != null ? this.dateFormat.format(new Date()) : (new Date()).toString();
        }
    }

    /**
     * 创建的数据表对应的类添加的注释
     *
     * @param topLevelClass
     * @param introspectedTable
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            topLevelClass.addJavaDocLine("/**");
            // topLevelClass.addJavaDocLine(" * @author shensr");
            // topLevelClass.addJavaDocLine(" * @version V1.0 ");
            topLevelClass.addJavaDocLine(" * @description MyBatis Generator 自动创建,对应数据表为：" + introspectedTable.getFullyQualifiedTable());
            topLevelClass.addJavaDocLine(" * @create " + this.getDateString());
            topLevelClass.addJavaDocLine(" */");
        }
    }

    /**
     * <p>生成xx.java文件（model）属性的注释</p>
     *
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (!this.suppressAllComments) {
            // 注释开始的地方
            field.addJavaDocLine("/**");
            String remarks = introspectedColumn.getRemarks();
            // 开启注释，并且数据库中comment有值
            if (this.addRemarkComments && StringUtility.stringHasValue(remarks)) {
                // 通过换行符分割 System.getProperty("line.separator")：换行符 ，屏蔽了 Windows和Linux的区别
                String[] remarkLines = remarks.split(System.getProperty("line.separator"));
                int length = remarkLines.length;
                // 如果有多行，就换行显示
                for (int i = 0; i < length; i++) {
                    String remarkLine = remarkLines[i];
                    field.addJavaDocLine(" * " + remarkLine);
                }
            }
            // 注释结束
            field.addJavaDocLine(" */");
        }
    }

    /**
     * xxxMapper接口和xxxExample类方法注解
     *
     * @param method
     * @param introspectedTable
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            method.addJavaDocLine("/**");
            method.addJavaDocLine(" * " + method.getName());
            List<Parameter> parameters = method.getParameters();
            parameters.forEach(parameter -> method.addJavaDocLine(" * @param " + parameter.getName()));
            // 如果有返回类型，添加@return
            String returnType = "void";
            if (!returnType.equals(method.getReturnType())) {
                method.addJavaDocLine(" * @return ");
            }
            method.addJavaDocLine(" */");
        }

    }

    /**
     * 数据库对应实体类的Getter方法注解
     *
     * @param method
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    /**
     * 数据库对应实体类的Setter方法注解
     *
     * @param method
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    /**
     * 生成xxMapper.XML文件的注释
     *
     * @param xmlElement
     */
    @Override
    public void addComment(XmlElement xmlElement) {
    }



    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
    }

    @Override
    public void addRootComment(XmlElement rootElement) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn,
                                           Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn,
                                   Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {

    }
}
