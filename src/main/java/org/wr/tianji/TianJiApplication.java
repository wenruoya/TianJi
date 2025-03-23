package org.wr.tianji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication()
public class TianJiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TianJiApplication.class, args);
    }

}
