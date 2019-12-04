package aoc2019.day4;

public class PasswordChecker {

    private static final int PASSWORD_LENGTH = 6;

    public boolean checkPassword(String password, boolean part2) {

        if (password.length() != PASSWORD_LENGTH) {
            return false;
        }

        boolean containsPair = false;

        int previous = 0;
        int groupValue = 0;
        int groupCount = 1;

        for (char number : password.toCharArray()) {

            if (number < previous) return false;

            if (part2) {
                if (number == groupValue) {
                    groupCount++;
                } else {
                    if (groupCount == 2) {
                        containsPair = true;
                    }
                    groupValue = number;
                    groupCount = 1;
                }
            } else {
                if (number == previous) {
                    containsPair = true;
                }
            }
            previous = number;
        }

        //check the last pair of numbers
        if (groupCount == 2) {
            containsPair = true;
        }

        return containsPair;
    }
}
