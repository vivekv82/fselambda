package skilltracker.fse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

// https://github.com/galovics/aws-lambda-spring-boot2/blob/master/springboot2/src/main/java/com/arnoldgalovics/blog/BookController.java
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class SkillsTrackerApp {
    
    public static void main(String[] args) {
        SpringApplication.run(SkillsTrackerApp.class, args);
    }
	
}