package com.scissortail;

public interface Game {
    int getNumber();
    int getGuess();
    int getSmallest();
    int getBiggest();
    int getRemaining();
    void setGuess(int guess);
    void reset();
    void check();
    boolean isValidNumberRange();
    boolean isGameWon();
    boolean isGameLost();
}
