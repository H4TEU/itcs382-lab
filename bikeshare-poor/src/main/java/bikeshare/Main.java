package bikeshare;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final int STUDENT = 1;
    public static final int STAFF = 2;
    public static final String[] STATIONS = {"Library", "Canteen", "Dorm"};

    public static final List<Bike> BIKES = new ArrayList<>();
    public static final List<String> RIDES = new ArrayList<>();

    static {
        for (String station : STATIONS) {
            BIKES.add(new Bike(station.substring(0, 1) + "1", station));
            BIKES.add(new Bike(station.substring(0, 1) + "2", station));
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        showMenu();
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] w = line.split("\\s+");
            String cmd = w[0].toLowerCase();

            try {
                if (cmd.equals("rent")) {
                    if (w.length < 4) {
                        showMenu();
                        continue;
                    }
                    String memberId = w[1];
                    int type = parseType(w[2]);
                    String station = w[3];
                    Bike bike = findBike(station, true);
                    if (bike == null) {
                        System.out.println("No available bike at " + station);
                    } else {
                        bike.rented = true;
                        bike.memberId = memberId;
                        bike.memberType = type;
                        bike.station = station;
                        System.out.println("Bike " + bike.id + " rented to " + memberId);
                    }
                } else if (cmd.equals("return")) {
                    if (w.length < 4) {
                        showMenu();
                        continue;
                    }
                    Bike bike = null;
                    for (Bike b : BIKES) {
                        if (b.id.equalsIgnoreCase(w[1]) && b.rented) {
                            bike = b;
                            break;
                        }
                    }
                    if (bike == null) {
                        System.out.println("Bike " + w[1] + " is not currently rented");
                    } else {
                        int minutes = Integer.parseInt(w[3]);
                        int fee = calcFee(bike.memberType, minutes);
                        String station = w[2];
                        bike.rented = false;
                        bike.memberId = "";
                        bike.memberType = 0;
                        bike.station = station;

                        String ride = bike.id + "," + station + "," + minutes + "," + fee;
                        RIDES.add(ride);
                        saveRide(ride);

                        System.out.println("Receipt: bike=" + bike.id + ", minutes=" + minutes + ", fee=" + fee);
                    }
                } else if (cmd.equals("list")) {
                    printBikes(false);
                } else if (cmd.equals("quit")) {
                    break;
                } else {
                    showMenu();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        in.close();
    }

    static void showMenu() {
        System.out.println("Commands: rent <memberId> <memberType:1=student,2=staff> <station>, " +
                "return <bikeId> <station> <minutes>, list, quit");
    }

    static Bike findBike(String station, boolean free) {
        for (Bike bike : BIKES) {
            if (bike.station.equalsIgnoreCase(station) && bike.rented == !free) {
                return bike;
            }
        }
        return null;
    }

    static void printBikes(boolean onlyFree) {
        for (Bike bike : BIKES) {
            if (bike.rented == onlyFree) {
                System.out.println(bike.id + " " + bike.station + " " + (bike.rented ? "rented" : "free"));
            }
        }
    }

    static int calcFee(int type, int minutes) {
        if (type == STUDENT) {
            return minutes * 2;
        }
        if (type == STAFF) {
            return minutes * 3;
        }
        return 0;
    }

    static int parseType(String text) {
        String t = text.toLowerCase();
        if (t.equals("student")) {
            return STUDENT;
        }
        if (t.equals("staff")) {
            return STAFF;
        }
        return Integer.parseInt(text);
    }

    static void saveRide(String line) {
        Path p = Path.of("rides.csv");
        try (BufferedWriter out = Files.newBufferedWriter(
                p,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            out.write(line);
            out.newLine();
        } catch (IOException e) {
            System.out.println("Could not save ride: " + e.getMessage());
        }
    }

    public static class Bike {
        public String id;
        public String station;
        public boolean rented;
        public String memberId;
        public int memberType;

        Bike(String id, String station) {
            this.id = id;
            this.station = station;
            this.rented = false;
            this.memberId = "";
            this.memberType = 0;
        }
    }
}
