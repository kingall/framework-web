package com.lighting.framework.core.commond;

import com.lighting.framework.core.generator.CodeGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * 执行命令行
 */
@Profile("dev")
@Component
public class CommondScanner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(CommondScanner.class);

    @Autowired
    private CodeGenerator codeGenerator;

    /**
     * 命令监视
     */
    public void scanner() {
        try{
            Scanner scanner = new Scanner(System.in);
            while (true){
                scanner.useDelimiter("\n");
                if (scanner.hasNext()) {
                    String ipt = scanner.next();
                    if (StringUtils.isNotBlank(ipt)) {
                        executeCommond(ipt);
                    }
                }
            }
        }catch (Exception e){
            logger.debug(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 执行命令
     */
    public void executeCommond(String commondLine){
        String[] commond = commondLine.split(" ");
        if(commond.length>0){
            switch (Commond.fromValue(commond[0])){
                case GEN:
                    codeGenerator.execute(Commond.fromValue(commond[0]), commond);
                    break;
                case UPDATE:
                    codeGenerator.execute(Commond.fromValue(commond[0]), commond);
                    break;
                case REMOVE:
                    codeGenerator.executeRemove(commond);
                    break;
                default:
                    System.out.println("===========未找到相应的命令============");
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        Runnable command = new Runnable(){
            @Override
            public void run() {
                scanner();
            }
        };
        new Thread(command).start();
    }
}
