package aoc2017.day1;

import java.util.ArrayList;

public class ReverseCaptchaParser {

    public int parseCaptcha(String input, int step) {

        int sum = 0;

        char[] charArray = input.toCharArray();
        CircularArrayList<Integer> integers = new CircularArrayList<>();

        for (char c : input.toCharArray()) {
            integers.add(Integer.parseInt(String.valueOf(c)));
        }

        for (int i = 0; i < integers.size(); i++) {

            int val1 = integers.get(i);
            int val2 = integers.get(i + step);

            if (val1 == val2) {
                sum += val2;
            }
        }
        return sum;
    }

    public class CircularArrayList<E> extends ArrayList<E> {
        @Override
        public E get(int index) {
            return super.get(index % size());
        }
    }
}
