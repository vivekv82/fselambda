package skilltracker.fse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import skilltracker.fse.controller.SkillsController;
//https://blogs.ashrithgn.com/spring-boot-rest-api-deployed-in-aws-lambda-serverless/

// https://github.com/galovics/aws-lambda-spring-boot2/blob/master/springboot2/src/main/java/com/arnoldgalovics/blog/BookController.java
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@Import({SkillsController.class})
public class SkillsTrackerApp {
    
    public static void main(String[] args) {
        SpringApplication.run(SkillsTrackerApp.class, args);
    }
	
}