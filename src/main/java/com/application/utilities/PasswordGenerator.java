package com.application.utilities;

import java.util.Random;
import java.util.stream.IntStream;

public class PasswordGenerator {
    /**
     * Generates a random password string with a length between 10 and 14 characters.
     * The password contains characters with ASCII values between 65 and 89 (inclusive),
     * which correspond to uppercase letters in the ASCII table.
     *
     * @return A randomly generated password string.
     */
    public String generate(){
        int limit = new Random().nextInt(10,15);
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(1, limit)
                .forEach(i ->{
                    int c = new Random().nextInt(65,90);
                    stringBuilder.append ( (char)c );
                });
        return stringBuilder.toString();
    }
}
