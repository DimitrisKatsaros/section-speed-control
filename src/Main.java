import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private final static String TIME_FORMAT_PATTERN = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
    private final static int SECTION_LENGTH = 10;

    public static void main(String[] args) {

//        List<Vehicle> vehicles = answerTwo();

//        answerThree(vehicles);

//        answerFourA(vehicles);

//        answerFourB(vehicles);

//        answerFive(vehicles);

//        answerSix(vehicles);
    }

    private static List<Vehicle> answerTwo() {
        List<Vehicle> vehicles = new ArrayList<>();

        try {
            vehicles = FileHandler.getVehicleData();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        System.out.println("Numbers of Vehicles recorder: " + vehicles.size());

        return vehicles;
    }

    // Question 3
    private static void answerThree(List<Vehicle> vehicles) {
        int vehiclesBefore = 0;

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getExitTime().isBefore(LocalTime.of(9, 0))) {
                vehiclesBefore++;
            }
        }

        System.out.println("Number of vehicles that passed before 9 o'clock: " + vehiclesBefore);
    }

    // Question 4a
    private static void answerFourA(List<Vehicle> vehicles) {

        Scanner scannerObj = new Scanner(System.in);
        String inputTime = "";
        int vehiclesBefore = 0;
        do {
            System.out.print("Please give a time in HH:mm format: ");
            inputTime = scannerObj.nextLine();
        } while (!Pattern.matches(TIME_FORMAT_PATTERN, inputTime));

        LocalTime passTime = LocalTime.parse(inputTime);
        for (Vehicle vehicle : vehicles) {

            if (vehicle.getEntryTime().getHour() == passTime.getHour() && vehicle.getEntryTime().getMinute() == passTime.getMinute()) {
                vehiclesBefore++;
            }
        }

        System.out.println("The number of vehicle that passed the entry point recorder at " + inputTime + " is: " + vehiclesBefore);
    }

    // Question 4b
    private static void answerFourB(List<Vehicle> vehicles) {

        Scanner scannerObj = new Scanner(System.in);
        String inputTime;
        do {
            System.out.print("Please give a time for intensity calculation in HH:mm format: ");
            inputTime = scannerObj.nextLine();
        } while (!Pattern.matches(TIME_FORMAT_PATTERN, inputTime));

        LocalTime intensityMinute = LocalTime.parse(inputTime);
        int vehiclesOnSection = 0;

        for (Vehicle vehicle : vehicles) {

            if (!vehicle.getEntryTime().isAfter(intensityMinute.plusMinutes(1)) && !vehicle.getExitTime().isBefore(intensityMinute)) {
                vehiclesOnSection++;
            }
        }

        System.out.println("The traffic intensity is: " + (double) vehiclesOnSection / SECTION_LENGTH);
    }

    // Question 5
    private static void answerFive(List<Vehicle> vehicles) {

        int maxSpeed = 0;
        Vehicle fastestVehicle = new Vehicle();

        for (Vehicle vehicle : vehicles) {
            int speed = (int) vehicle.getAvgSpeed(SECTION_LENGTH);

            if (speed > maxSpeed) {
                maxSpeed = speed;
                fastestVehicle = vehicle;
            }
        }

        int overtaken = vehiclesOvertaken(vehicles, fastestVehicle);

        System.out.println("The data of the vehicle with the highest speed are");
        System.out.println("license plate number: " + fastestVehicle.getLicencePlate());
        System.out.println("average speed: " + fastestVehicle.getAvgSpeed(SECTION_LENGTH) + " km/h");
        System.out.println("number of overtaken vehicles: " + overtaken);
    }

    private static int vehiclesOvertaken(List<Vehicle> vehicles, Vehicle fastestVehicle) {

        int overtaken = 0;
        for (Vehicle vehicle: vehicles){

            if(vehicle.equals(fastestVehicle))
                continue;

            if(vehicle.getExitTime().isAfter(fastestVehicle.getExitTime()) && vehicle.getEntryTime().isBefore(fastestVehicle.getEntryTime())){
                overtaken++;
            }
        }

        return overtaken;
    }

    // Question 6
    private static void answerSix(List<Vehicle> vehicles) {

        int exceedsSpeedLimitCounter = 0;
        for(Vehicle vehicle: vehicles){

            if (vehicle.getAvgSpeed(SECTION_LENGTH) > 90){
                exceedsSpeedLimitCounter++;
            }
        }

        System.out.println(Double.parseDouble(String.format("%.2f", ((double) exceedsSpeedLimitCounter/vehicles.size()) * 100)));
    }
}