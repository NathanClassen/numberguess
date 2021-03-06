package com.scissortail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageGeneratorImpl implements MessageGenerator {
    private static final Logger logger = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    private final Game game;

    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    @Override
    public String getMainMessage() {
        return "Number is between "+
                game.getSmallest() +
                " and "+
                game.getBiggest()+
                "\n can you guess it?\n\n";
    }

    public String getResultMessage() {
        if (game.isGameWon()) {
            return "you guessed it! it was " + game.getNumber();
        } else if (game.isGameLost()) {
            return "you lost. the number was " + game.getNumber();
        } else if (!game.isValidNumberRange()) {
            return "invalid number range";
        } else if (game.getRemaining() == game.getGuessCount()) {
            return "what is your first guess?";
        } else {
            String direction = game.getGuess() < game.getNumber() ? "higher" : "lower";
            return direction + "! you have " + game.getRemaining() + " guesses left";
        }
    }
}
