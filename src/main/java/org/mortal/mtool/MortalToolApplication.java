package org.mortal.mtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MortalToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(MortalToolApplication.class, args);
    }
}
