package com.rentflicks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

import com.rentflicks.model.User;
//import com.rentflicks.model.Video;

@SpringBootApplication
//@EntityScan(basePackageClasses={User.class ,Video.class})
@EntityScan(basePackageClasses=User.class)
public class RentFlicksApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentFlicksApplication.class, args);
    }
}
