package aoc2016.day2;

public class KeypadNavigator {

    private static final int[][] keypad = {new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9}};

    private static final String[][] diamondKeypad = {new String[]{" ", " ", "1", " ", " "}, new String[]{" ", "2", "3"
            , "4", " "}, new String[]{"5", "6", "7", "8", "9"}, new String[]{" ", "A", "B", "C", " "}, new String[]{
                    " ", " ", "D", " ", " "}};

    public String findCode_square(String[] directions) {

        StringBuilder codeBuilder = new StringBuilder();

        int currentX = 1;
        int currentY = 1;

        for (String directionString : directions) {
            for (char direction : directionString.toCharArray()) {

                switch (direction) {
                    case 'U':
                        if (currentX > 0) {
                            currentX--;
                        }
                        break;
                    case 'D':
                        if (currentX < 2) {
                            currentX++;
                        }
                        break;
                    case 'R':
                        if (currentY < 2) {
                            currentY++;
                        }
                        break;
                    case 'L':
                        if (currentY > 0) {
                            currentY--;
                        }
                        break;
                }
            }
            codeBuilder.append(keypad[currentX][currentY]);
        }

        return codeBuilder.toString();
    }

    public String findCode_diamond(String[] directions) {

        StringBuilder codeBuilder = new StringBuilder();

        int currentX = 2;
        int currentY = 0;

        for (String directionString : directions) {
            for (char direction : directionString.toCharArray()) {
                switch (direction) {
                    case 'U':
                        if (currentX > 0 && diamondKeypad[currentX - 1][currentY] != " ") {
                            currentX--;
                        }
                        break;
                    case 'D':
                        if (currentX < 4 && diamondKeypad[currentX + 1][currentY] != " ") {
                            currentX++;
                        }
                        break;
                    case 'R':
                        if (currentY < 4 && diamondKeypad[currentX][currentY + 1] != " ") {
                            currentY++;
                        }
                        break;
                    case 'L':
                        if (currentY > 0 && diamondKeypad[currentX][currentY - 1] != " ") {
                            currentY--;
                        }
                        break;
                }
            }
            codeBuilder.append(diamondKeypad[currentX][currentY]);
        }

        return codeBuilder.toString();
    }
}
