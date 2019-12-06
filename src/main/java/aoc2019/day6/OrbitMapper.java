package aoc2019.day6;

import java.util.*;

public class OrbitMapper {

    private static final String COM = "COM";
    private boolean found;

    private final Map<String, Set<String>> orbitsMap = new HashMap<>();

    public void mapOrbits(String[] orbits) {
        for (String orbit : orbits) {
            String[] split = orbit.split("\\)");

            String keyPlanet = split[0];
            String childPlanet = split[1];

            if (!orbitsMap.containsKey(keyPlanet)) {
                orbitsMap.put(keyPlanet, new HashSet<>());
            }
            orbitsMap.get(keyPlanet).add(childPlanet);
        }
    }

    public int getTotalOrbitCount() {

        int totalOrbits = 0;

        for (String planet : orbitsMap.keySet()) {
            totalOrbits += getOrbitCount(planet);
        }
        return totalOrbits;
    }

    public int getShortestPathBetweenPlanets(String planet, String other) {

        List<String> pathToSanta = getPathToPlanet(planet);
        List<String> pathToYou = getPathToPlanet(other);

        Object[] commonAncestorArray = pathToYou.stream()
                .filter(pathToSanta::contains).toArray();

        String commonAncestor = commonAncestorArray[0].toString();

        return (pathToYou.indexOf(commonAncestor) + pathToSanta.indexOf(commonAncestor)) - 2;
    }

    private List<String> getPathToPlanet(String planet) {

        Map<String, String> parents = new HashMap<>();
        search(COM, planet, parents);

        List<String> shortestPath = new ArrayList<>();

        while (planet != null) {
            shortestPath.add(planet);
            planet = parents.get(planet);
        }
        return shortestPath;
    }

    private void search(String parent, String goal, Map<String, String> parents) {

        if (parent == goal) return;

        Set<String> children = orbitsMap.get(parent);

        if (children != null && !children.isEmpty()) {
            for (String child : children) {
                parents.put(child, parent);
                search(child, goal, parents);
            }
        }
    }

    private int getOrbitCount(String key) {

        if (!orbitsMap.containsKey(key))
            return 0;

        int count = 0;
        for (String planet : orbitsMap.get(key)) count += 1 + getOrbitCount(planet);
        return count;
    }

    private class Planet {
        String name;
        boolean visited;
    }
}
