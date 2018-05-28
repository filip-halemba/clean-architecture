package io.halemba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 https://github.com/bastman/spring-kotlin-jooq
 https://blog.philipphauer.de/do-it-yourself-orm-alternative-hibernate-drawbacks/
 https://medium.com/@readsethu/jooq-flyway-spring-boot-and-gradle-44a8d3f289
 https://github.com/jOOQ/jOOQ/tree/master/jOOQ-examples/jOOQ-spring-example/src
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
