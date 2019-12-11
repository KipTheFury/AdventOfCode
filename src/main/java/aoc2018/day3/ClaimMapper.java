package aoc2018.day3;

import common.Coords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClaimMapper {

    private static final Logger log = LoggerFactory.getLogger(ClaimMapper.class);
    private static final String regexPattern = "(#[\\d]+) @ ([\\d]+),([\\d]+): ([\\d]+)x([\\d]+)";

    public List<Claim> parseClaims(String[] claimStrings) {

        List<Claim> claims = new ArrayList<>();
        Pattern p = Pattern.compile(regexPattern);
        for (String claim : claimStrings) {
            Matcher m = p.matcher(claim);
            if (m.find()) {
                Claim c = new Claim(m.group(1), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)),
                        Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)));

                claims.add(c);
            } else {
                log.error("No matches found in [{}]", claim);
            }
        }
        return claims;
    }

    public Map<Coords, Integer> findOverlaps(List<Claim> claims) {

        Map<Coords, Integer> overlaps = new HashMap<>();

        for (Claim c : claims) {
            for (int x = c.getX(); x < (c.getX() + c.getWidth()); x++) {
                for (int y = c.getY(); y < (c.getY() + c.getHeight()); y++) {

                    Coords coords = new Coords(x, y);
                    overlaps.put(coords, overlaps.getOrDefault(coords, 0) + 1);
                }
            }
        }
        return overlaps;
    }

    public String findClaimWithNoOverlaps(List<Claim> claims, Map<Coords, Integer> overlaps) {

        String claimId = "";

        for (Claim c : claims) {

            int claimSize = c.getHeight() * c.getWidth();
            int overlapCount = 0;

            for (int x = c.getX(); x < (c.getX() + c.getWidth()); x++) {
                for (int y = c.getY(); y < (c.getY() + c.getHeight()); y++) {
                    Coords coords = new Coords(x, y);
                    overlapCount += overlaps.get(coords);
                }
            }

            if (claimSize == overlapCount) {
                return c.getId();
            }
        }
        return claimId;
    }

    public static class Claim {

        private final String id;
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public Claim(String id, int x, int y, int width, int height) {

            this.id = id;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public String getId() {
            return id;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
