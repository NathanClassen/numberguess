package com.scissortail.console;

import com.scissortail.config.ApplicationConfiguration;
import com.scissortail.Game;
import com.scissortail.MessageGenerator;
import com.scissortail.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final String CONFIG_LOCATION = "beans.xml";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("guess the number game");

        /*
        create context

            now that the project uses the @Configuration annotated ApplicationConfiguration class for Spring configuration
                the following will be replaced:

                ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

            with:
         */
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);


        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);

        log.info("number = {}", numberGenerator.next());

        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);
        log.info(messageGenerator.getMainMessage());
        log.info(messageGenerator.getResultMessage());

        // get game bean from context (container)
        Game game = context.getBean(Game.class);

        context.close();
    }
}
