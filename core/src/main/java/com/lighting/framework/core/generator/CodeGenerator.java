package com.lighting.framework.core.generator;

import com.lighting.framework.core.commond.Commond;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {

    @Autowired
    private CodeGenerateConfig codeGenerateConfig;

    private Logger logger = LoggerFactory.getLogger(CodeGenerator.class);

    private AutoGenerator mpg = new AutoGenerator();

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */


    /**
     * 初始化代码生成
     */
    public void initGenerate() {
        //全局配置
        mpg.setGlobalConfig(codeGenerateConfig.getGlobalConfig());
        //数据源配置
        mpg.setDataSource(codeGenerateConfig.getDataSourceConfig());
        //默认包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(codeGenerateConfig.getDefaultModuleName());
        pc.setParent(codeGenerateConfig.getPackageName());
        mpg.setPackageInfo(pc);
    }

    /**
     * 执行生成过程
     */
    public void execute(Commond commond, String[] args) {
        mpg = new AutoGenerator();
        initGenerate();

        StrategyConfig strategy = codeGenerateConfig.getStrategyConfig();

        //获取生成代码的表名
        if (args.length >= 2) {
            strategy.setInclude(args[1]);
            mpg.setStrategy(strategy);
        } else {
            logger.info("未输入生成数据表名称!");
        }

        //生成数据库的模块名称
        if (args.length >= 3) {
            mpg.getPackageInfo().setModuleName(args[2]);
        } else {
            mpg.getPackageInfo().setModuleName(codeGenerateConfig.getDefaultModuleName());
        }
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //更新时仅生xml和entity
        if (Commond.UPDATE.equals(commond)) {
            TemplateConfig templateConfig = new TemplateConfig();
            ((ExpandAutoGenerator)mpg).remove(TemplateType.MAPPER, TemplateType.ENTITY);
            templateConfig.disable(TemplateType.SERVICE, TemplateType.CONTROLLER, TemplateType.MAPPER);
            mpg.setTemplate(templateConfig);
        }
        mpg.execute();
        logger.info("数据库表{}代码生成成功!", args[1]);
    }

    public void executeRemove(String[] args) {
        mpg = new ExpandAutoGenerator();

        initGenerate();

        StrategyConfig strategy = codeGenerateConfig.getStrategyConfig();

        //获取生成代码的表名
        if (args.length >= 2) {
            strategy.setInclude(args[1]);
            mpg.setStrategy(strategy);
        } else {
            logger.info("未输入生成数据表名称!");
        }

        //生成数据库的模块名称
        if (args.length >= 3) {
            mpg.getPackageInfo().setModuleName(args[2]);
        } else {
            mpg.getPackageInfo().setModuleName(codeGenerateConfig.getDefaultModuleName());
        }
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        ((ExpandAutoGenerator)mpg).remove();
    }
}