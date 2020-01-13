package aoc2016.day9;

public class FileDecompressor {

    public long decompress(String file, boolean part2) {

        long count = 0;

        for (int i = 0; i < file.length(); i++) {

            char currentChar = file.charAt(i);

            if (currentChar == '(') {

                int markerEnd = file.indexOf(")", i);
                String[] marker = file.substring(i + 1, markerEnd).split("x");

                int length = Integer.valueOf(marker[0]);
                int repetitions = Integer.valueOf(marker[1]);

                String repeat = file.substring(markerEnd + 1, markerEnd + 1 + length);

                count += repetitions * (part2 ? decompress(repeat, true) : repeat.length());

                i = length + markerEnd;

            } else {
                count++;
            }
        }

        return count;
    }
}
