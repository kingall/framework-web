package com.lighting.framework.core.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据生成文件配置
 */
@Component
@PropertySource("classpath:codeGenerator.properties")
public class CodeGenerateConfig {
    @Value("${author}")
    private String author;

    @Value("${default.module.name}")
    private String defaultModuleName;

    @Value("${package.name}")
    private String packageName;

    @Value("${is.gen.swagger2}")
    private boolean isGenSwagger2;

    @Value("${db.type}")
    private String dbType;

    @Value("${default.table.prefix}")
    private String tablePrefix;


    private String projectPath = System.getProperty("user.dir");

    private String outputDir = System.getProperty("user.dir")+"/src/main/java";

    @Autowired
    private DataSourceProperties dataSourceProperties;


    /**
     * 获取生成全局配置
     * @return
     */

    public GlobalConfig getGlobalConfig(){
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setSwagger2(isGenSwagger2);
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setIdType(IdType.ASSIGN_UUID);
        return gc;
    }

    /**
     * 获取数据源配置
     * @return
     */
    public DataSourceConfig getDataSourceConfig(){
        DataSourceConfig dataSource = new DataSourceConfig().setDbType(DbType.getDbType(dbType))// 数据库类型
                .setDriverName(dataSourceProperties.getDriverClassName())
                .setUsername(dataSourceProperties.getUsername()).setPassword(dataSourceProperties.getPassword())
                .setUrl(dataSourceProperties.getUrl());
        return dataSource;
    }

    /**
     * 策略配置
     */
    public StrategyConfig getStrategyConfig(){
        StrategyConfig strategy = new StrategyConfig();

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(tablePrefix);

        //默认实体
        strategy.setLogicDeleteFieldName("isdel");
        List<TableFill> tableFillList= new ArrayList<TableFill>();
        TableFill fill=new TableFill("update_time", FieldFill.INSERT_UPDATE);
        tableFillList.add(fill);
        fill = new TableFill("create_time", FieldFill.INSERT);
        tableFillList.add(fill);

        strategy.setTableFillList(tableFillList);

        return strategy;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDefaultModuleName() {
        return defaultModuleName;
    }

    public void setDefaultModuleName(String defaultModuleName) {
        this.defaultModuleName = defaultModuleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isGenSwagger2() {
        return isGenSwagger2;
    }

    public void setGenSwagger2(boolean genSwagger2) {
        isGenSwagger2 = genSwagger2;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public String getOutputDir() {
        return outputDir;
    }
}

