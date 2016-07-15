package saki.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by liverliu on 7/13/16.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class Application implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        /*
        InputStreamReader is = new InputStreamReader(ConstantUtil.class.getResourceAsStream("/log4j.properties"), "UTF-8");
        Properties log4jProperty = new Properties();
        log4jProperty.load(is);
        PropertyConfigurator.configure(log4jProperty);
        */
    }

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
}
