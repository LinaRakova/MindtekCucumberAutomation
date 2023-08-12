package utilities;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();

        long number = random.nextLong();

// Make the number positive if it is negative

        number = Math.abs(number);

// Truncate the number to 10 digits if it is longer

        number = number % 10000000000L;

// Print the result

        System.out.println(number);

        int dotInput = random.nextInt(900000);

        System.out.println(dotInput);

    }
}
