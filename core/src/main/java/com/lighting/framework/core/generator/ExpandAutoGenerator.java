package com.lighting.framework.core.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ExpandAutoGenerator extends AutoGenerator {
    public void remove() {
        if (null == config) {
            config = new ConfigBuilder(getPackageInfo(),getDataSource(), getStrategy(), getTemplate(), getGlobalConfig());
            if (null != injectionConfig) {
                injectionConfig.setConfig(config);
            }
        }
        if(super.config.getTableInfoList().size()>0){
            TableInfo tableInfo = super.config.getTableInfoList().get(0);
            Map<String, String> pathInfo = super.config.getPathInfo();
            if(pathInfo.get("entity_path")!=null && !pathInfo .get("entity_path").isEmpty() && tableInfo.getEntityName()!=null && !tableInfo.getEntityName().isEmpty()){
                File entityFile = new File(super.config.getPathInfo().get("entity_path") +"/"+ tableInfo.getEntityName()+".java");
                entityFile.delete();
            }
            if(pathInfo.get("xml_path")!=null && !pathInfo .get("xml_path").isEmpty() && tableInfo.getXmlName()!=null && !tableInfo.getXmlName().isEmpty()){
                File xmlFile = new File(super.config.getPathInfo().get("xml_path") +"/"+ tableInfo.getXmlName()+".xml");
                xmlFile.delete();
            }
            if(pathInfo.get("mapper_path")!=null && !pathInfo .get("mapper_path").isEmpty() && tableInfo.getMapperName()!=null && !tableInfo.getMapperName().isEmpty()){
                File mapperFile = new File(super.config.getPathInfo().get("mapper_path") +"/"+ tableInfo.getMapperName()+".java");
                mapperFile.delete();
            }
            if(pathInfo.get("controller_path")!=null && !pathInfo .get("controller_path").isEmpty() && tableInfo.getControllerName()!=null && !tableInfo.getControllerName().isEmpty()){
                File controllerFile = new File(super.config.getPathInfo().get("controller_path") +"/"+ tableInfo.getControllerName()+".java");
                controllerFile.delete();
            }
            if(pathInfo.get("service_impl_path")!=null && !pathInfo .get("service_impl_path").isEmpty() && tableInfo.getServiceImplName()!=null && !tableInfo.getServiceImplName().isEmpty()){
                File serviceImplFile = new File(super.config.getPathInfo().get("service_impl_path") +"/"+ tableInfo.getServiceImplName()+".java");
                serviceImplFile.delete();
            }
            if(pathInfo.get("service_path")!=null && !pathInfo .get("service_path").isEmpty() && tableInfo.getServiceName()!=null && !tableInfo.getServiceName().isEmpty()){
                File serviceFile = new File(super.config.getPathInfo().get("service_path") +"/"+ tableInfo.getServiceName()+".java");
                serviceFile.delete();
            }

        }
        List<TableInfo> tableInfoList = getAllTableInfoList(super.config);
    }
}
