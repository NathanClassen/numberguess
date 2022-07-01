package com.scissortail.config;

import com.scissortail.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
@Import(GameConfig.class)
@ComponentScan(basePackages = "com.scissortail")
public class ApplicationConfiguration {
    /*
        with the removal of @Component from both classes represent below, and that creation of these respective
            producer methods (they produce Beans to be managed by the Spring container) the above @ComponentScan
            annotation is not actually needed
     */

    /*
        returns the given classes as Beans; bean will have same name as the method, though one may specify a name in the
            Bean annotation

        producers are useful if additonal configuration is required for the Beans
     */
    @Bean
    public NumberGenerator numberGenerator() {
        return new NumberGeneratorImpl();
    }

    @Bean
    public Game game() {
        return new GameImpl();
    }

    @Bean
    public MessageGenerator messageGenerator(){
        return new MessageGeneratorImpl();
    }
}
