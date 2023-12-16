package games;

public class FizzBuzz {

    private static final int FIZZ = 3;

    private static final int BUZZ = 5;

    private FizzBuzz() {
    }

    public static String convert(Integer input) throws OutOfRangeException {
        if (input <= 0 || input > 100) {
            throw new OutOfRangeException();
        }
        if (input % FIZZ == 0 && input % BUZZ == 0) {
            return "FizzBuzz";
        }
        if (input % FIZZ == 0) {
            return "Fizz";
        }
        if (input % BUZZ == 0) {
            return "Buzz";
        }
        return input.toString();
    }
}
