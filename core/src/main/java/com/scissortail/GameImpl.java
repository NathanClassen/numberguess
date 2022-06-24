package com.scissortail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class GameImpl implements Game {
    // == constants ==

    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    // == private fields ==
    @Autowired
    private NumberGenerator numberGenerator;
    private int guessCount = 10;
    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    // == constructors ==
    //    no longer using this, as it is for Constructor based dependency injection
    //      is used in conjunction with the <constructor-args> tag in the Bean configuration file

    //    public GameImpl(NumberGenerator numberGenerator) {
    //        this.numberGenerator = numberGenerator;
    //    }

    // == init ==
    @PostConstruct
    @Override
    public void reset() {
        smallest = 0;
        guess = 0;
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.info("number = {}", number);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("in Game.preDestroy");
    }

    // == public methods ==

    //  for Setter based dependency injection
    //      using in conjunction with the <property> tag in the Bean configuration file

    //    public void setNumberGenerator(NumberGenerator numberGenerator) {
    //        this.numberGenerator = numberGenerator;
    //    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemaining() {
        return remainingGuesses;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public void check() {
        checkValidNumberRange();
        if (validNumberRange) {
            if (guess > number) {
                biggest = guess - 1;
            }
            if (guess < number) {
                smallest = guess + 1;
            }
        }
        remainingGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // == private methods ==

    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && guess <= biggest;
    }
}
