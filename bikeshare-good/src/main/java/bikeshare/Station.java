package bikeshare;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private final String name;
    private final List<Bike> bikes;

    public Station(String name, int bikeCount) {
        this.name = name;
        this.bikes = new ArrayList<>();
        for (int i = 1; i <= bikeCount; i++) {
            bikes.add(new Bike(name.substring(0, 1).toLowerCase() + i, name));
        }
    }

    public String getName() {
        return name;
    }

    public Bike getAvailableBike() {
        for (Bike bike : bikes) {
            if (!bike.isRented()) {
                return bike;
            }
        }
        return null;
    }

    public Bike findBike(String bikeId) {
        for (Bike bike : bikes) {
            if (bike.getId().equalsIgnoreCase(bikeId)) {
                return bike;
            }
        }
        return null;
    }

    public List<String> listBikes() {
        List<String> lines = new ArrayList<>();
        for (Bike bike : bikes) {
            lines.add(bike.statusLine());
        }
        return lines;
    }
}
