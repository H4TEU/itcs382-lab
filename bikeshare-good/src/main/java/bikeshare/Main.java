package bikeshare;

public class Main {
    public static void main(String[] args) {
        RideRepository repository = new CsvRideRepository("rides.csv");
        BikeShareService service = new BikeShareService(repository);
        BikeShareConsole console = new BikeShareConsole(service);
        console.run();
    }
}
