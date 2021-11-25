package cn.alldimensions.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WSApplication {
    public static void main(String[] args) {
        SpringApplication.run(WSApplication.class, args);
    }
}
