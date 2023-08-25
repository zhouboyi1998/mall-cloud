package com.cafe.generator.mybatis;

import com.cafe.common.enumeration.date.DateTimePatternEnum;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.generator.mybatis
 * @Author: zhouboyi
 * @Date: 2022/4/22 14:48
 * @Description: 注释生成器
 */
public class CommentGenerator extends DefaultCommentGenerator {

    private static final String EXAMPLE_SUFFIX = "Example";

    private static final String MAPPER_SUFFIX = "Mapper";

    /**
     * 设置用户配置的参数
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
    }

    /**
     * Java 文件添加 import
     *
     * @param compilationUnit
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        super.addJavaFileComment(compilationUnit);
        String fullyQualifiedName = compilationUnit.getType().getFullyQualifiedName();
        // Model 添加 import
        if (!fullyQualifiedName.contains(MAPPER_SUFFIX) && !fullyQualifiedName.contains(EXAMPLE_SUFFIX)) {
            compilationUnit.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModel"));
            compilationUnit.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty"));
        }
        // Mapper 接口添加 import
        if (fullyQualifiedName.contains(MAPPER_SUFFIX)) {
            compilationUnit.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
        }
    }

    /**
     * Model 添加 class 注释
     *
     * @param topLevelClass
     * @param introspectedTable
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String remarks = introspectedTable.getRemarks();
        // 添加 class 注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * @Project: mall-cloud");
        topLevelClass.addJavaDocLine(" * @Package:");
        topLevelClass.addJavaDocLine(" * @Author: zhouboyi");
        topLevelClass.addJavaDocLine(" * @Date: " + new SimpleDateFormat(DateTimePatternEnum.DEFAULT_DATE.getPattern()).format(new Date()));
        topLevelClass.addJavaDocLine(" * @Description: " + remarks);
        topLevelClass.addJavaDocLine(" */");
        // 添加 class 注解
        topLevelClass.addJavaDocLine("@ApiModel(value = \"" + "\", description = \"" + remarks + "\")");
    }

    /**
     * 字段添加注释
     *
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 添加 Swagger 注解
        field.addJavaDocLine("@ApiModelProperty(value = \"" + introspectedColumn.getRemarks() + "\")");
    }
}
