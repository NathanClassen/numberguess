package com.scissortail;

import java.util.Random;

// not annotated with @Component, so needs to be produced in ApplicationConfiguration as a Bean
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    private final Random random = new Random();
    private final int maxNumber = 100;

    // == public methods ==
    @Override
    public int next() {
        return random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
