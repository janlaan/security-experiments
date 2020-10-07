package org.jan.securityexperiments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MvcConfig.class})
public class SecurityExperimentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityExperimentsApplication.class, args);
    }

}
