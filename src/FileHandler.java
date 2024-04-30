import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<Vehicle> getVehicleData() throws IOException {

        BufferedReader reader;

        reader = new BufferedReader(new FileReader("data/measurements.txt"));
        String line = reader.readLine();

        List<Vehicle> vehicles = new ArrayList<>();

        while (line != null) {
            Vehicle vehicle = new Vehicle();

            String[] splitLine = line.split("\\s+");

            vehicle.setLicencePlate(splitLine[0]);
            vehicle.setEntryTime(timeParser(splitLine[1], splitLine[2], splitLine[3], splitLine[4]));
            vehicle.setExitTime(timeParser(splitLine[5], splitLine[6], splitLine[7], splitLine[8]));

            vehicles.add(vehicle);

            line = reader.readLine();
        }

        reader.close();

        return vehicles;
    }

    private static LocalTime timeParser(String hour, String min, String sec, String mill) {

        String timeText = hour + ":" + min + ":" + sec + "." + mill;

        return LocalTime.parse(timeText, DateTimeFormatter.ofPattern("[H][H][:m[:s[.SSS][.SS][.S]]]"));
    }
}