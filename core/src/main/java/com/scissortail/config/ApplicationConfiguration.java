package com.scissortail.config;

import com.scissortail.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/*
    Spring configuration class for project

    This repaces the beans.xml file which was previously used to configure beans, and spring resources

    @Configuration is also a @Component and this annotation comes from the spring-context.annotation package which can
        be seen as imported in both POM.xml files
        See ExternalLibraries to see that the context.annotation package exports the Configuration and
            ComponentScan @interface's

    @Import allows one to import the Bean definitions of another class; allows modular Bean definitions/configs
 */

@Configuration
//  @Import(GameConfig.class)  used to use this to add another configuration modules context to the overall application
//      context
@PropertySource("classpath:config/game.properties") // brings game.properties text file in to use its keys as @Value's
@ComponentScan(basePackages = "com.scissortail")    // scan this root package for @Component's
public class ApplicationConfiguration {
    // == property configuration file fields ==
    @Value("${game.maxNumber:20}")
    private int maxNumber;
    @Value("${game.minNumber:1}")
    private int minNumber;
    @Value("${game.guessCount:5}")
    private int guessCount;

    // == bean producers ==
    @Bean
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }

    @Bean
    @MinNumber
    public int minimumNumber() { return minNumber; }

    @Bean
    @GuessCount
    public int guessCount() {
        return guessCount;
    }
    /*
        when not using the @Component for annotating classes, one may create respective producer methods.
        they produce Beans to be managed by the Spring container
           returns the given classes as Beans; bean will have the same name as the method, though one may
           specify a name in the Bean annotation arguments

            @Bean
            public Game game() {
                return new GameImpl();
            }

        producers are useful if additional configuration is required for the Beans
        if not using @Component to create beans, the above @ComponentScan annotation is not needed on ApplicationContext
            configuring classes such as this
     */
}
