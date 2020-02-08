package cn.czy15.zyweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.czy15.zyweb.mapper")
public class ZyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZyWebApplication.class, args);
    }

}
