package day1;

public class FuelCalculator {

    public int calculateFuel(int mass) {

        return (mass / 3) - 2;
    }

    public int calculateFuelWithAdditionalFuel(int mass) {

        int totalFuel = 0;
        int newMass = mass;

        do {
            newMass = calculateFuel(newMass);
            if (newMass > 0)
                totalFuel += newMass;
        } while (newMass > 0);

        return totalFuel;
    }
}
