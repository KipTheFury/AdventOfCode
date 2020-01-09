package aoc2016.day4;

import common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 4: Security Through Obscurity ---
 * Finally, you come across an information kiosk with a list of rooms. Of course, the list is encrypted and full of
 * decoy data, but the instructions to decode the list are barely hidden nearby. Better remove the decoy data first.
 * <p>
 * Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a dash, a sector ID,
 * and a checksum in square brackets.
 * <p>
 * A room is real (not a decoy) if the checksum is the five most common letters in the encrypted name, in order, with
 * ties broken by alphabetization. For example:
 * <p>
 * aaaaa-bbb-z-y-x-123[abxyz] is a real room because the most common letters are a (5), b (3), and then a tie between
 * x, y, and z, which are listed alphabetically.
 * a-b-c-d-e-f-g-h-987[abcde] is a real room because although the letters are all tied (1 of each), the first five
 * are listed alphabetically.
 * not-a-real-room-404[oarel] is a real room.
 * totally-real-room-200[decoy] is not.
 * Of the real rooms from the list above, the sum of their sector IDs is 1514.
 * <p>
 * What is the sum of the sector IDs of the real rooms?
 * <p>
 * --- Part Two ---
 * With all the decoy data out of the way, it's time to decrypt this list and get moving.
 * <p>
 * The room names are encrypted by a state-of-the-art shift cipher, which is nearly unbreakable without the right
 * software. However, the information kiosk designers at Easter Bunny HQ were not expecting to deal with a master
 * cryptographer like yourself.
 * <p>
 * To decrypt a room name, rotate each letter forward through the alphabet a number of times equal to the room's
 * sector ID. A becomes B, B becomes C, Z becomes A, and so on. Dashes become spaces.
 * <p>
 * For example, the real name for qzmt-zixmtkozy-ivhz-343 is very encrypted name.
 * <p>
 * What is the sector ID of the room where North Pole objects are stored?
 */
public class Day4 {

    private static final Logger log = LoggerFactory.getLogger("2016 - Day 4");

    public static void main(String[] args) {

        String[] input = FileUtils.getLinesAsArray("src/main/resources/2016/day4-roomids");
        RoomIdDecryptor decryptor = new RoomIdDecryptor();

        List<RoomIdDecryptor.RoomID> roomIds = decryptor.parseRooms(input);
        List<RoomIdDecryptor.RoomID> realRooms = new ArrayList<>();

        int sectorIdSum = 0;

        for (RoomIdDecryptor.RoomID id : roomIds) {
            if (decryptor.isRealRoom(id)) {
                sectorIdSum += id.sectorId;
                realRooms.add(id);
            }
        }

        log.info("Part 1 - Sector ID Sum of Valid Rooms [{}]", sectorIdSum);

        for (RoomIdDecryptor.RoomID id : realRooms) {
            log.info("Decrypted Name [{}] : Sector ID [{}]", decryptor.decryptRoomID(id), id.sectorId);
        }
    }

}
