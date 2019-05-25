package oose.dea.lotusterhaar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = "oose.dea.lotusterhaar")
@EnableJpaRepositories("oose.dea.lotusterhaar.dao")
@EntityScan("oose.dea.lotusterhaar.model")
public class Application {
    public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
    }
}