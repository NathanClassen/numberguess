package com.scissortail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

public class MessageGeneratorImpl implements MessageGenerator {
    private static final Logger logger = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    @Autowired
    private Game game;

    private int guessCount = 10;

    @PostConstruct
    public void init() {
        logger.info("guess count = {}", guessCount);
        logger.info("game = {}", game);
    }

    @Override
    public String getMainMessage() {
        return "Number is between "+
                game.getSmallest() +
                " and "+
                game.getBiggest()+
                "\n can you guess it?";
    }

    public String getResultMessage() {
        if (game.isGameWon()) {
            return "you guessed it! it was " + game.getNumber();
        } else if (game.isGameLost()) {
            return "you lost. the number was " + game.getNumber();
        } else if (!game.isValidNumberRange()) {
            return "invalid number range";
        } else if (game.getRemaining() == guessCount) {
            return "what is your first guess?";
        } else {
            String direction = game.getGuess() < game.getNumber() ? "higher" : "lower";
            return direction + "! you have " + game.getRemaining() + " guesses left";
        }
    }
}
