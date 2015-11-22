package com.rentflicks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan(basePackageClasses={User.class ,Video.class})
//@EntityScan(basePackageClasses=User.class)
public class RentFlicksApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentFlicksApplication.class, args);
    }
}
