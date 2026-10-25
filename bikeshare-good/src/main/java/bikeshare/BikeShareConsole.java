package bikeshare;

import java.util.List;
import java.util.Scanner;

public class BikeShareConsole {
    private final BikeShareService service;

    public BikeShareConsole(BikeShareService service) {
        this.service = service;
    }

    public void run() {
        System.out.println("Commands: rent <memberId> <student|staff> <station>, return <bikeId> <station> <minutes>, list, quit");
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                String line = in.nextLine();
                if (line == null) {
                    continue;
                }
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 0) {
                    continue;
                }
                String command = parts[0].toLowerCase();
                try {
                    switch (command) {
                        case "rent" -> {
                            if (parts.length != 4) {
                                System.out.println("Usage: rent <memberId> <student|staff> <station>");
                                break;
                            }
                            String memberId = parts[1];
                            MemberType memberType = MemberType.fromText(parts[2]);
                            service.rentBike(memberId, memberType, parts[3]);
                            System.out.println("Bike rented to " + memberId + " at " + parts[3]);
                        }
                        case "return" -> {
                            if (parts.length != 4) {
                                System.out.println("Usage: return <bikeId> <station> <minutes>");
                                break;
                            }
                            Ride ride = service.returnBike(parts[1], parts[2], Integer.parseInt(parts[3]));
                            System.out.println("Receipt: bike=" + parts[1]
                                    + ", minutes=" + parts[3]
                                    + ", fee=" + ride.csvLine().split(",")[5]);
                        }
                        case "list" -> {
                            List<String> bikes = service.listBikes();
                            for (String bike : bikes) {
                                System.out.println(bike);
                            }
                        }
                        case "quit" -> {
                            return;
                        }
                        default -> System.out.println("Unknown command");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Minutes must be an integer");
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}