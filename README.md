#framework-web Introduce  
##Quick start  
###Environment  
1、maven  
2、git  
3、java1.8+
###Step 1、install framework 
install framework into local maven repository  
`mvn install`
###Step 2、create new project use idea IDE.
###Step 3、 update pom.xml
add parent element.  
`<parent>`   
`   <artifactId>framework-web</artifactId>`   
`   <groupId>cn.alldimensions</groupId>`  
`   <version>1.0.0</version>`  
`</parent>`  
  
add framework dependency.  
`<dependency>`  
`<artifactId>core</artifactId>`  
`<groupId>cn.alldimensions</groupId>`  
`<version>1.0.0</version>`  
`</dependency>`  
### Step 4、 add springboot boot file 

`@SpringBootApplication`  
`@MapperScan("xxx.xxx.xxx.**.mapper")`  
`@ComponentScan("xxx.xxx")`  
`@EnableTransactionManagement`  
`public class AllcmisApplication {`  
`public static void main(String[] args) {`  
`SpringApplication.run(AllcmisApplication.class, args);`  
`}`  
`}`    
###Step 4、 add config file
add `application.properties`  
spring.profiles.active=dev
####应用端口配置
server.port=8081
####文件上传请求文件大小配置
spring.servlet.multipart.max-file-size=50MB
####文件上传请求大小配置
spring.servlet.multipart.max-request-size=100MB
####日志配置
logging.level.cn=debug  
logging.level.org=info  
logging.file.name=core.log  
#### 配置数据源
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource  
spring.datasource.driverClassName=com.mysql.jdbc.Driver  
#### Mysql
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/allcmis?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8  
spring.datasource.username=******  
spring.datasource.password=******  
####mybatis-plus配置
mybatis-plus.type-aliases-package=xxx.xxx.**.entity  
mybatis-plus.mapper-locations=classpath*:/xxx/xxx/**/xml/*.xml  
####时间格式
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss  


