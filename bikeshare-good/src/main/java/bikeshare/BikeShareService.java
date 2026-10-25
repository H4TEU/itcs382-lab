package bikeshare;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BikeShareService {
    private final Map<String, Station> stations;
    private final RideRepository rideRepository;

    public BikeShareService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
        this.stations = new LinkedHashMap<>();
        for (String name : List.of("Library", "Canteen", "Dorm")) {
            stations.put(name, new Station(name, 2));
        }
    }

    public void rentBike(String memberId, MemberType memberType, String stationName) {
        Station station = requireStation(stationName);
        Bike bike = station.getAvailableBike();
        if (bike == null) {
            throw new IllegalStateException("No available bike at " + stationName);
        }
        bike.setRented(true);
        bike.setMemberId(memberId);
        bike.setMemberType(memberType);
    }

    public Ride returnBike(String bikeId, String stationName, int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes must be zero or more");
        }
        Bike bike = findBike(bikeId);
        if (bike == null) {
            throw new IllegalStateException("Bike " + bikeId + " was not found");
        }
        if (!bike.isRented()) {
            throw new IllegalStateException("Bike " + bikeId + " is not rented");
        }
        String memberId = bike.getMemberId();
        MemberType memberType = bike.getMemberType();
        int fee = memberType.feePerMinute() * minutes;

        bike.setRented(false);
        bike.setMemberId(null);
        bike.setMemberType(null);
        bike.setStation(stationName);

        Ride ride = new Ride(bikeId, memberId, memberType, stationName, minutes, fee);
        rideRepository.save(ride);
        return ride;
    }

    public List<String> listBikes() {
        List<String> lines = new ArrayList<>();
        for (Station station : stations.values()) {
            lines.addAll(station.listBikes());
        }
        return lines;
    }

    private Station requireStation(String stationName) {
        String key = stationName.trim();
        if (!stations.containsKey(key)) {
            throw new IllegalArgumentException("Unknown station: " + stationName);
        }
        return stations.get(key);
    }

    private Bike findBike(String bikeId) {
        for (Station station : stations.values()) {
            Bike bike = station.findBike(bikeId);
            if (bike != null) {
                return bike;
            }
        }
        return null;
    }
}