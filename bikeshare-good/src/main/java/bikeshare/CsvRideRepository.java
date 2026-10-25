package bikeshare;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CsvRideRepository implements RideRepository {
    private final Path path;

    public CsvRideRepository(String fileName) {
        this.path = Path.of(fileName);
    }

    @Override
    public void save(Ride ride) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                path,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            writer.write(ride.csvLine());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException("Could not save ride to " + path, e);
        }
    }
}
