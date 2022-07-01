package com.scissortail.console;

import com.scissortail.Game;
import com.scissortail.MessageGenerator;
import com.scissortail.MessageGeneratorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleNumberGuess {
    private static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);

    // == fields ==
    private final Game game;
    private final MessageGenerator messageGenerator;

    @Autowired
    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    // == events ==
    @EventListener
    public void start(ContextRefreshedEvent contextRefreshedEvent) {

        log.info("\n\n======= It's the 'Guess That Number' Game!! ========\n\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.flush();
            System.out.println(messageGenerator.getMainMessage());
            System.out.print(messageGenerator.getResultMessage());

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if (game.isGameWon() || game.isGameLost()) {
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("play again? (y/n)");

                String playAgain = scanner.nextLine().trim();

                if (!playAgain.equalsIgnoreCase("y")) {
                    break;
                }

                game.reset();
            }
        }
    }
}
