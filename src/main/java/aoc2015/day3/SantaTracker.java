package aoc2015.day3;

import common.Coords;

import java.util.HashMap;
import java.util.Map;

public class SantaTracker {

    public Map<Coords, Integer> parseDirections(String directions, boolean roboSanta) {

        Map<Coords, Integer> houses = new HashMap<>();
        Map<Coords, Integer> roboSantaHouses = new HashMap<>();

        Coords house = new Coords(0, 0);
        Coords roboSantaHouse = new Coords(0, 0);

        houses.put(house, 1);
        roboSantaHouses.put(roboSantaHouse, 1);

        int directionCount = 0;
        for (char c : directions.toCharArray()) {

            if (roboSanta) {

                if (directionCount % 2 == 0) {
                    house = deliverPresent(houses, house, c);
                } else {
                    roboSantaHouse = deliverPresent(roboSantaHouses, roboSantaHouse, c);
                }


            } else {
                house = deliverPresent(houses, house, c);
            }
            directionCount++;
        }

        if (roboSanta) {
            //merge maps
            for (Map.Entry<Coords, Integer> entry : roboSantaHouses.entrySet()) {
                houses.put(entry.getKey(), houses.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }

        return houses;
    }

    private Coords deliverPresent(Map<Coords, Integer> houses, Coords house, char c) {
        switch (c) {
            case '^':
                house = house.move(Coords.UP);
                houses.put(house, houses.getOrDefault(house, 0) + 1);
                break;
            case 'v':
                house = house.move(Coords.DOWN);
                houses.put(house, houses.getOrDefault(house, 0) + 1);
                break;
            case '>':
                house = house.move(Coords.RIGHT);
                houses.put(house, houses.getOrDefault(house, 0) + 1);
                break;
            case '<':
                house = house.move(Coords.LEFT);
                houses.put(house, houses.getOrDefault(house, 0) + 1);
                break;
        }
        return house;
    }
}
