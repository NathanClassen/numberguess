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

        // get number generator bean from context (container)
        NumberGenerator numberGenerator = context.getBean("number-generator", NumberGenerator.class);

        int next = numberGenerator.next();

        log.info("number = {}", next);

        // get game bean from context (container)
        Game game = context.getBean(Game.class);

        game.reset();

        context.close();
    }
}
