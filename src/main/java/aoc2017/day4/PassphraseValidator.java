package aoc2017.day4;

import java.util.Arrays;

public class PassphraseValidator {

    public long validatePassphrases(String[] passphrases, boolean part2) {

        return Arrays.asList(passphrases).stream().filter(passphrase -> validatePassphrase(passphrase, part2)).count();
    }

    boolean validatePassphrase(String passphrase, boolean part2) {

        String[] passArray = passphrase.split(" ");
        for (int i = 0; i < passArray.length; i++) {
            for (int j = 0; j < passArray.length; j++) {
                if (i != j) {
                    if (part2) {
                        if (areAnagrams(passArray[i], passArray[j])) return false;
                    }
                    if (passArray[i].equals(passArray[j])) return false;
                }
            }
        }
        return true;
    }

    private boolean areAnagrams(String thisOne, String thatOne) {

        if (thisOne.length() != thatOne.length()) return false;

        char[] thisArray = thisOne.toLowerCase().toCharArray();
        char[] thatArray = thatOne.toLowerCase().toCharArray();
        Arrays.sort(thisArray);
        Arrays.sort(thatArray);

        return Arrays.equals(thisArray, thatArray);
    }
}
