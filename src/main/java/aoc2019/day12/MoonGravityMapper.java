package aoc2019.day12;

import java.util.List;

public class MoonGravityMapper {

    public void processTimeStep(List<Moon> system) {

        for (int i = 0; i < system.size(); i++) {
            for (int j = 0; j < system.size(); j++) {
                system.get(i).calculateGravityFrom(system.get(j));
            }
        }

        for (Moon m : system) {
            m.applyVelocity();
        }
    }

    public int calculateSystemEnergy(List<Moon> system) {

        int totalEnergy = 0;

        for (Moon m : system) {
            totalEnergy += m.calculateEnergy();
        }
        return totalEnergy;
    }
}


