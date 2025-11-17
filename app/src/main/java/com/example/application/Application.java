package com.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Map;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

//@Controller
//@Component
@Service
class CustomerService {

    private final JdbcClient dataSource;

    CustomerService(JdbcClient dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    Collection<Customer> getCustomers() throws Exception {
        return this.dataSource
                .sql("select * from customers where id = ?")
                .params(1)
                .query((rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("name")))
                .list();
    }

}

record Customer(int id, String name) {
}

// SPRING FRAMEWORK
// 1. portable service abstractions
// 2. dependency injection
// 3. aspect oriented programming

// SPRING BOOT
// 4. autoconfiguration

@Controller
@ResponseBody
class HiController {

    @GetMapping("/")
    Map<String, String> hello() {
        return Map.of("message", "Hi there!");
    }
}

//@EnableTransactionManagement

//@ComponentScan
@Configuration
class MyConfig {

//    @Bean
//    JdbcClient jdbcClient(DataSource dataSource) {
//        return JdbcClient.create(dataSource);
//    }
//
//    @Bean
//    EmbeddedDatabase dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .build();
//    }

//    @Bean
//    CustomerService customerService(DataSource dataSource) {
//        return new CustomerService(dataSource);
//    }

}