package aoc2016.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomIdDecryptor {

    private static final String regexPattern = "([a-z-]+)(\\d+)\\[([a-z]+)\\]";

    public List<RoomID> parseRooms(String[] input) {

        List<RoomID> roomIds = new ArrayList<>();

        Pattern p = Pattern.compile(regexPattern);

        for (String id : input) {

            Matcher m = p.matcher(id);

            if (m.find()) {

                String name = m.group(1).replaceAll("-", "").trim();
                int sectorId = Integer.valueOf(m.group(2));
                String checksum = m.group(3);

                RoomID r = new RoomID(name, sectorId, checksum);
                roomIds.add(r);
            }
        }
        return roomIds;
    }

    public boolean isRealRoom(RoomID roomId) {

        boolean isReal = true;

        Map<Character, Integer> letterCount = new HashMap<>();

        for (char c : roomId.name.replaceAll("-", "").toCharArray()) {
            letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < roomId.checksum.length() - 1; i++) {

            char currentChar = roomId.checksum.charAt(i);
            char nextChar = roomId.checksum.charAt(i + 1);

            int currentCount = letterCount.getOrDefault(currentChar, 0);
            int nextCount = letterCount.getOrDefault(nextChar, 0);

            if (currentCount == 0 || nextCount == 0) {
                isReal = false;
            } else if (currentCount < nextCount) {
                isReal = false;
            } else if (currentCount == nextCount && currentChar > nextChar) {
                isReal = false;
            }
        }

        return isReal;
    }

    public String decryptRoomID(RoomID id) {

        StringBuilder decryptedName = new StringBuilder();

        for (int i = 0; i < id.name.length(); i++) {
            char currentChar = id.name.charAt(i);

            if (currentChar == '-') {
                decryptedName.append(" ");
            } else {
                decryptedName.append((char) ((currentChar - 'a' + id.sectorId) % 26 + 'a'));
            }
        }

        return decryptedName.toString();
    }

    public static class RoomID {

        String name;
        int sectorId;
        String checksum;

        public RoomID(String name, int sectorId, String checksum) {
            this.name = name;
            this.sectorId = sectorId;
            this.checksum = checksum;
        }
    }
}
