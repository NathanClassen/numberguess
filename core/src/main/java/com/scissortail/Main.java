package com.scissortail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final String CONFIG_LOCATION = "beans.xml";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("guess the number game");

        // create context
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);

        log.info("number = {}", numberGenerator.next());

        // get game bean from context (container)
        Game game = context.getBean(Game.class);

        context.close();
    }
}
